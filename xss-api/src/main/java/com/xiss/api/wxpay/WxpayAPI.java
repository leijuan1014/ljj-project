package com.xiss.api.wxpay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xiss.api.BasicAPI;
import com.xiss.model.order.GrowingUsers;
import com.xiss.model.order.SuiteOrders;
import com.xiss.model.order.Suites;
import com.xiss.model.order.enums.OrderState;
import com.xiss.model.order.enums.TradeNoTypeEnum;
import com.xiss.model.shops.CardDeals;
import com.xiss.model.shops.Shops;
import com.xiss.model.system.Users;
import com.xiss.model.wxpay.WxpayAccount;
import com.xiss.model.wxpay.WxpayPlaceOrderRequest;
import com.xiss.model.wxpay.WxpayPlaceOrderResult;
import com.xiss.model.wxpay.WxpayQueryOrderResult;
import com.xiss.model.wxpay.WxpaySignature;
import com.xiss.service.order.CouponUsersService;
import com.xiss.service.order.GrowingUsersService;
import com.xiss.service.order.SuiteOrdersService;
import com.xiss.service.shops.CardDealsService;
import com.xiss.service.shops.ShopsService;
import com.xiss.service.system.UsersService;
import com.xiss.service.wxpay.api.WxpayPlaceOrder;
import com.xiss.service.wxpay.api.WxpayQueryOrder;
import com.xiss.util.IdMangler;
import com.xiss.util.properties.WxPayProperties;

/** 
* @author leijj
* @since  2017年4月26日 下午5:46:57 
*/
@Controller
@RequestMapping("/api/wxpay")
public class WxpayAPI extends BasicAPI{
	@Autowired
	private UsersService usersService;
	@Autowired
	private GrowingUsersService growingUsersService;
	@Autowired
	private ShopsService shopsService;
	@Autowired
	private CardDealsService cardDealsService;
	@Autowired
	private WxpayPlaceOrder wxpayPlaceOrder;
	@Autowired
	private WxpayQueryOrder wxpayQueryOrder;
	@Autowired
	private SuiteOrdersService suiteOrdersService;
	@Autowired
	private CouponUsersService couponUsersService;
	/**
	 * 获取微信支付预付订单相关信息
	 * @param suiteId
	 * @param mobile
	 * @param couponId
	 * @param openid
	 * */
	@ResponseBody
	@RequestMapping("/burningMan")
	public ModelMap burningMan(Integer suiteId, String mobile, String travelMode, String openid) throws Exception {
		ModelMap model = new ModelMap();
		if (null == suiteId || null == mobile || mobile.isEmpty() || null == openid || openid.isEmpty()) {
			return getCodeMessage(false, "参数有误", model);
		}
		WxpayAccount account = WxPayProperties.getJsapiWxpayAccount();
		
		Suites suites = suiteOrdersService.getSuiteById(suiteId);
		if (null == suiteId || null == suites) {
			return getCodeMessage(false, "套餐不存在", model);
		}
		Users users = usersService.getByMobile(mobile);
		if (users == null) {
			users = new Users();
			users.setEmail(mobile);
			users.setMobile(mobile);
			usersService.save(users);
		}
		
		SuiteOrders suiteOrders = suiteOrdersService.saveOrder(users, suites, null, "JSAPI", 1, TradeNoTypeEnum.BM.name());
		
		WxpayPlaceOrderRequest request = setWxpayPlaceOrderRequest(suiteOrders, suites, "JSAPI", "JSAPI", openid);
		request.setAttach(travelMode);
		String notifyUrl = WxPayProperties.burningManJsapiNotify();
		WxpayPlaceOrderResult result = wxpayPlaceOrder.run(request, account, notifyUrl);
		if (null == result) {
			return getCodeMessage(false, "生成微信订单失败", model);
		}
    	
    	assembleResult(model, "JSAPI", account, result, suiteOrders);
		return model; 
	}
	
	/**
	 * 获取微信支付预付订单相关信息
	 * @param suiteId
	 * @param mobile
	 * @param invitationToken
	 * @param deviceInfo
	 * @param couponId
	 * @param amount
	 * @param quantity
	 * @param openid
	 * @param payType 支付类型 WEEK=7天体验卡,YEAR=年卡
	 * */
	@ResponseBody
	@RequestMapping("/jsapi")
	public ModelMap jsapi(Integer suiteId, String mobile, String invitationId, String invitationToken, String deviceInfo, Integer couponId, Double amount, Integer quantity, String openid, String payType) throws Exception {
		System.out.println("=======参数列表:suiteId=" + suiteId + ",mobile=" + mobile + ",invitationId=" + invitationId + ",deviceInfo="+deviceInfo + ",couponId=" + couponId + ",amount=" + amount + ",quantity=" + quantity + ",openid=" + openid + ",payType=" + payType);
		ModelMap model = new ModelMap();
		if (null == suiteId || null == mobile || mobile.isEmpty() || null == openid || openid.isEmpty()) {
			return getCodeMessage(false, "参数有误", model);
		}
		WxpayAccount account = WxPayProperties.getJsapiWxpayAccount();
		
		/*if (null == shopUser) {
			model.put("return_code", false);
			model.put("message", "推荐商家不存在");
			return model;
		}*/
		
		Suites suites = suiteOrdersService.getSuiteById(suiteId);
		if (null == suiteId || null == suites) {
			return getCodeMessage(false, "套餐不存在", model);
		}
		
		Users shopUser = null;
		if (invitationId != null && ! invitationId.isEmpty()) 
			shopUser = usersService.getById((int)IdMangler.demangle(invitationId));
		else if(invitationToken != null && ! invitationToken.isEmpty())
			shopUser = usersService.getByToken(invitationToken);
		
		int invitedBy = 0;
		if(shopUser != null)
			invitedBy = shopUser.getId();
		
		if(TradeNoTypeEnum.WEEK.name().equals(payType)){//活动7天体验卡
			GrowingUsers growingUsers = growingUsersService.getByMobile(mobile);
			if (growingUsers != null && growingUsers.isEnrolled520()) {
				return getMessage(false, "您已经参加过7天体验活动", model);
			} else if (growingUsers == null) {
				growingUsers = new GrowingUsers();
				growingUsers.setMobile(mobile);
				growingUsers.setStatus(0);
				growingUsersService.insert(growingUsers);
			}
		}
		Users users = usersService.getByMobile(mobile);
		if (null == mobile || null == users) {
			users = new Users();
			users.setEmail(mobile);
			users.setMobile(mobile);
			users.setInvitationToken(shopUser.getAuthenticationToken());
			users.setInvitedBy(shopUser.getId());
			usersService.save(users);
		} else if(users.getInvitedBy() == 0 && invitedBy > 0)
			usersService.updateInvited(mobile, shopUser.getAuthenticationToken(), shopUser.getId());
		
		SuiteOrders suiteOrders = suiteOrdersService.saveOrder(users, suites, couponId, deviceInfo, quantity, payType);
		if (invitedBy > 0) 
			cardDealsSave(invitedBy, suiteOrders, users.getId(), suites.getSalePrice());
		
		WxpayPlaceOrderRequest request = setWxpayPlaceOrderRequest(suiteOrders, suites, deviceInfo, "JSAPI", openid);
		String notifyUrl = WxPayProperties.cardDealJsapiNotify();
		WxpayPlaceOrderResult result = wxpayPlaceOrder.run(request, account, notifyUrl);
		if (null == result) {
			return getCodeMessage(false, "生成微信订单失败", model);
		}
    	
    	assembleResult(model, "JSAPI", account, result, suiteOrders);
		return model; 
	}
	
	private void cardDealsSave(int shopUserId, SuiteOrders suiteOrders, int customerId, double price) {
		CardDeals cardDeals = new CardDeals();
		cardDeals.setSellerId(shopUserId);
		cardDeals.setCustomerId(customerId);
		cardDeals.setSuiteOrderId(suiteOrders.getId());
		cardDeals.setTradeNo(suiteOrders.getTradeNo());
		cardDealsService.insert(cardDeals);
	}
	
	@ResponseBody
	@RequestMapping("/member")
	public ModelMap memberPay(Integer suiteId, Integer couponId, String userToken, String licensedId, String deviceInfo, Double totalAmount, String openid) throws Exception {
		ModelMap model = new ModelMap();
		if (null == suiteId || null == userToken || userToken.isEmpty() || 
				null == deviceInfo ||deviceInfo.isEmpty()) {
			return getMessage(false, "参数有误", model);
		}
		
		Users user = usersService.getByToken(userToken);
		if (user == null)
			return getMessage(false, "用户不存在", model);
		
		Suites suites = suiteOrdersService.getSuiteById(suiteId);
		if (null == suiteId || null == suites) {
			return getMessage(false, "套餐不存在", model);
		}
		String tradeType = "APP";
		WxpayAccount account = WxPayProperties.getAppWxpayAccount();
		String notifyUrl = "";
		if (deviceInfo.toUpperCase().equals("WXAPP")) {
			tradeType = "JSAPI";
			account = WxPayProperties.getWxappWxpayAccount();
			notifyUrl = WxPayProperties.memberWxAppNotify();
		} else if (deviceInfo.toUpperCase().equals("JSAPI")) {
			tradeType = "JSAPI";
			account = WxPayProperties.getJsapiWxpayAccount();
			notifyUrl = WxPayProperties.memberJsapiNotify();
		} else if(deviceInfo.toUpperCase().equals("NATIVE")) {
			tradeType = "NATIVE";//TODO
			account = WxPayProperties.getJsapiWxpayAccount();
			notifyUrl = WxPayProperties.memberJsapiNotify();
		} else if(deviceInfo.toUpperCase().equals("IOS") || deviceInfo.toUpperCase().equals("ANDROID")){
			tradeType = "APP";
			account = WxPayProperties.getAppWxpayAccount();
			notifyUrl = WxPayProperties.memberAppNotify();
		}
		System.out.println("==============notifyUrl=" + notifyUrl);
		SuiteOrders suiteOrders = suiteOrdersService.saveOrder(user, suites, couponId, deviceInfo, 1, null);
		
		WxpayPlaceOrderRequest request = setWxpayPlaceOrderRequest(suiteOrders, suites, deviceInfo, tradeType, openid);
		JSONObject attachObj = new JSONObject();
		attachObj.put("licensedId", licensedId);
		request.setAttach(attachObj.toJSONString());
		WxpayPlaceOrderResult result = wxpayPlaceOrder.run(request, account, notifyUrl);
		if (null == result) {
			return getCodeMessage(false, "生成微信订单失败", model);
		}
		if (suiteOrders.getCouponId() > 0) {
			couponUsersService.updateStatusById(suiteOrders.getCouponId(), 1);
		}
		return assembleResult(model, tradeType, account, result, suiteOrders); 
	}
	
	/**
	 * 获取微信支付预付订单相关信息
	 * */
	@RequestMapping(value = "/unifiedorder",produces = "application/json; charset=utf-8")  
	@ResponseBody
	public ModelMap unifiedorder(Integer suiteId, String userToken, String deviceInfo, Integer couponId, Double amount, Integer quantity) throws Exception {
		ModelMap model = new ModelMap();
		System.out.println("=======参数列表:suiteId=" + suiteId + ",userToken=" + userToken + ",deviceInfo="+deviceInfo + ",couponId=" + couponId + ",amount=" + amount + ",quantity=" + quantity);
		if (null == suiteId || null == userToken || userToken.isEmpty()) 
			return getMessage(false, "传入的参数错误", model);
		
		WxpayAccount appWxpayAccount = WxPayProperties.getAppWxpayAccount();
		
		Users users = usersService.getByToken(userToken);
		if (null == userToken || null == users) 
			return getMessage(false, "用户不存在", model);
		
		Suites suites = suiteOrdersService.getSuiteById(suiteId);
		if (null == suiteId || null == suites) 
			return getMessage(false, "商品不存在", model);
		
		SuiteOrders suiteOrders = suiteOrdersService.saveOrder(users, suites, couponId, deviceInfo, quantity, null);
		String tradeType = "APP";
		if("NATIVE".equals(deviceInfo))
			tradeType = "NATIVE";
		
		WxpayPlaceOrderRequest request = setWxpayPlaceOrderRequest(suiteOrders, suites, deviceInfo, tradeType, null);
		String notifyUrl = WxPayProperties.suiteOrderAppNotify();
		WxpayPlaceOrderResult result = wxpayPlaceOrder.run(request, appWxpayAccount, notifyUrl);
		if (null == result) 
			return getCodeMessage(false, "生成微信订单失败", model);
		
		assembleResult(model, tradeType, appWxpayAccount, result, suiteOrders);
    	return model; 
	}
	
	public WxpayPlaceOrderRequest setWxpayPlaceOrderRequest(SuiteOrders suiteOrders, Suites suites, String deviceInfo, String tradeType, String openid) {
		WxpayPlaceOrderRequest placeOrderRequest = new WxpayPlaceOrderRequest();
		placeOrderRequest.setDeviceInfo(deviceInfo);
		placeOrderRequest.setBody(suites.getName());
		placeOrderRequest.setAttach(suites.getName());
		placeOrderRequest.setOrderTradeNo(suiteOrders.getTradeNo());
		placeOrderRequest.setFeeType("CNY");
		BigDecimal totalFee = new BigDecimal(suiteOrders.getPrice()).multiply(new BigDecimal(100)).multiply(new BigDecimal(suiteOrders.getQuantity()));
		placeOrderRequest.setTotalFee(totalFee.intValue());
		//placeOrderRequest.setClientIp("");
		placeOrderRequest.setStartTime(new Date());
		placeOrderRequest.setTradeType(tradeType);
		if (tradeType != null && tradeType.equals("JSAPI") &&null != openid && !openid.isEmpty()) 
			placeOrderRequest.setOpenid(openid);
		
		placeOrderRequest.setLimitPay("no_credit");
		return placeOrderRequest;
	}
	
	/**
	 * 获取微信支付是否成功信息
	 * */
	@ResponseBody
	@RequestMapping(value = "/orderquery",produces = "application/json; charset=utf-8")  
	public ModelMap orderquery(int orderId, String wxpayId) throws Exception {
		ModelMap modelMap = new ModelMap();
		WxpayAccount appWxpayAccount = WxPayProperties.getAppWxpayAccount();
		SuiteOrders suiteOrders = suiteOrdersService.getSuiteOrderById(orderId);//查询本地订单支付状态
		if (null == suiteOrders) {
			return getMessage(false, "订单不存在", modelMap);
		}
		System.out.println("=======suiteOrders=" + suiteOrders);
		WxpayQueryOrderResult result = wxpayQueryOrder.run(suiteOrders.getTradeNo(), wxpayId, appWxpayAccount);//查询微信支付查询接口
		System.out.println("=======WxpayQueryOrderResult=" + result);
		if (null != result && null != suiteOrders && OrderState.SUCCESS.ordinal() == suiteOrders.getState()) {
			modelMap.put("success", true);
			ModelMap suiteOrder = new ModelMap();
			Suites suites = suiteOrdersService.getSuiteById(suiteOrders.getSuiteId());
			if (suites != null) {
				suiteOrder.put("suiteName", suites.getName());
				Shops shops = shopsService.getById(suites.getShopId());
				if (shops != null)
					suiteOrder.put("shopName", shops.getName());
			}
			
			suiteOrder.put("price", suiteOrders.getPrice());
			suiteOrder.put("transactedAt", suiteOrders.getUpdatedAt());
			suiteOrder.put("tradeNo", suiteOrders.getTradeNo());
			modelMap.put("suiteOrder", suiteOrder);
			return modelMap;
		} else {
			return getMessage(false, "订单支付失败", modelMap);
		}
	}
	
	@ResponseBody
	@RequestMapping("/openId")
	public String openId(String code, String url) throws Exception {
		CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost("https://api.weixin.qq.com/sns/oauth2/access_token");
			StringBuilder reqXML = new StringBuilder();
			reqXML.append("appid=wx46b543acf6dbc7fa");
			reqXML.append("&secret=e7d006686de01c677fd218a7b53e5795");
			reqXML.append("&code="+code);
			reqXML.append("&grant_type=authorization_code");
	        post.setEntity(new ByteArrayEntity(reqXML.toString().getBytes()));
	        CloseableHttpResponse response = httpclient.execute(post);
	        try {
	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	            	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
	    			StringBuilder result = new StringBuilder();
	    			String text;
	    			while ((text = bufferedReader.readLine()) != null) {
	    				result.append(text);
	                }
	    			bufferedReader.close();
	    			System.out.println("=====result=" + result);
	            }
	            EntityUtils.consume(entity);
	        } finally {
	            response.close();
	        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
            	System.out.println(e.getMessage());
            }
        }
        return null;
	}
	@ResponseBody
	@RequestMapping("/getWxAppOpenId")
	public JSONObject getWxAppOpenId(String code) throws Exception {
		JSONObject object = new JSONObject();
		CloseableHttpClient httpclient = null;
        try {
        	WxpayAccount account = WxPayProperties.getWxappWxpayAccount();
            httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost("https://api.weixin.qq.com/sns/jscode2session");
			StringBuilder reqXML = new StringBuilder();
			reqXML.append("appid=" + account.getAppId());
			reqXML.append("&secret=" + account.getAppSecret());
			reqXML.append("&js_code=" + code);
			reqXML.append("&grant_type=authorization_code");
	        post.setEntity(new ByteArrayEntity(reqXML.toString().getBytes()));
	        CloseableHttpResponse response = httpclient.execute(post);
	        try {
	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	            	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
	    			StringBuilder result = new StringBuilder();
	    			String text;
	    			while ((text = bufferedReader.readLine()) != null) {
	    				result.append(text);
	                }
	    			System.out.println("=====result=" + result);
	    			if (result != null) {
	    				object = JSONObject.parseObject(result.toString());
	    				return object;
					}
	    			
	            }
	            EntityUtils.consume(entity);
	        } finally {
	            response.close();
	        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
            	System.out.println(e.getMessage());
            }
        }
        return object;
	}
	/**
	 * 微信支付单结果组装
	 * */
	private ModelMap assembleResult(ModelMap model, String tradeType, WxpayAccount account, WxpayPlaceOrderResult result, SuiteOrders suiteOrders) throws Exception {
		String nonce = WxpaySignature.genNonce();
    	String timestamp = String.valueOf(new Date().getTime()).substring(0, 10);
		WxpaySignature signature = new WxpaySignature();
		if(tradeType.equals("JSAPI")){
	    	signature.param("appId", account.getAppId());
	    	signature.param("timeStamp", timestamp);
	    	signature.param("nonceStr", nonce);
	    	signature.param("package", "prepay_id="+result.getPrepayId());
	    	signature.param("signType", "MD5");
	    	
	    	String sig = signature.sign(account.getKey());
	    	
	    	model.put("return_code", true);
			model.put("orderId", suiteOrders.getId());
	    	model.put("codeUrl", result.getCodeUrl());
	    	model.put("appId", account.getAppId());
	    	model.put("timeStamp", timestamp);
	    	model.put("nonceStr", nonce);
	    	model.put("packageStr", "prepay_id="+result.getPrepayId());
	    	model.put("signType", "MD5");
	    	model.put("sign", sig);
		} else if(tradeType.equals("APP")) {
	    	signature.param("appid", account.getAppId());
	    	signature.param("partnerid", account.getMerchantId());
	    	signature.param("prepayid", result.getPrepayId());
	    	signature.param("package", "Sign=WXPay");
	    	signature.param("noncestr", nonce);
	    	signature.param("timestamp", timestamp);
	    	String sig = signature.sign(account.getKey());
	    	
	    	model.put("return_code", true);
	    	model.put("orderId", suiteOrders.getId());
	    	model.put("codeUrl", result.getCodeUrl());
	    	model.put("appid", account.getAppId());
	    	model.put("partnerid", account.getMerchantId());
	    	model.put("prepayid", result.getPrepayId());
	    	model.put("package", "Sign=WXPay");
	    	model.put("noncestr", nonce);
	    	model.put("timestamp", timestamp);
	    	model.put("sign", sig);
		} else if(tradeType.equals("NATIVE")){
			model.put("return_code", true);
	    	model.put("orderId", suiteOrders.getId());
	    	model.put("codeUrl", result.getCodeUrl());
		}
		return model;
	}
}