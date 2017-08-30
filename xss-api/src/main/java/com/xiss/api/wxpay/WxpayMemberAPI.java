package com.xiss.api.wxpay;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xiss.api.BasicAPI;
import com.xiss.model.order.SuiteOrders;
import com.xiss.model.order.Suites;
import com.xiss.model.order.enums.TradeNoTypeEnum;
import com.xiss.model.system.Car;
import com.xiss.model.system.CarModel;
import com.xiss.model.system.Users;
import com.xiss.model.wxpay.WxpayAccount;
import com.xiss.model.wxpay.WxpayPlaceOrderRequest;
import com.xiss.model.wxpay.WxpayPlaceOrderResult;
import com.xiss.model.wxpay.WxpaySignature;
import com.xiss.service.order.SuiteOrdersService;
import com.xiss.service.system.CarModelService;
import com.xiss.service.system.CarsService;
import com.xiss.service.system.UsersService;
import com.xiss.service.wxpay.api.WxpayPlaceOrder;
import com.xiss.util.properties.WxPayProperties;

/** 
* @author leijj
* @since  2017年4月26日 下午5:46:57 
*/
@Controller
@RequestMapping("/api/wxpayMember")
public class WxpayMemberAPI extends BasicAPI{
	@Autowired
	private UsersService usersService;
	@Autowired
	private CarsService carsService;
	@Autowired
	private WxpayPlaceOrder wxpayPlaceOrder;
	@Autowired
	private SuiteOrdersService suiteOrdersService;
	@Autowired
	private CarModelService carModelService;
	/**
	 * 获取微信支付预付订单相关信息
	 * @param suiteId
	 * @param mobile
	 * @param deviceInfo
	 * @param couponId
	 * @param amount
	 * @param quantity
	 * @param openid
	 * */
	@ResponseBody
	@RequestMapping("/jsapi")
	public ModelMap jsapi(String mobile, Integer suiteId, String licensedId, String carBrandId, String carModelName, String openid) throws Exception {
		ModelMap model = new ModelMap();
		if (null == suiteId || null == mobile || mobile.isEmpty() || null == openid || openid.isEmpty()) {
			return getCodeMessage(false, "参数有误", model);
		}
		Suites suites = suiteOrdersService.getSuiteById(suiteId);
		if (null == suiteId || null == suites) {
			return getCodeMessage(false, "套餐不存在", model);
		}
		WxpayAccount account = WxPayProperties.getJsapiWxpayAccount();
		account.setNotifyUrl(WxPayProperties.memberJsapiTmpNotify());
		System.out.println("============account=" + account);
		Users users = usersService.getByMobile(mobile);
		if (null == mobile || null == users) {
			users = new Users();
			users.setEmail(mobile);
			users.setMobile(mobile);
			usersService.save(users);
		}
		System.out.println("==========users=" + users);
		Car car = carsService.getByLicensedId(licensedId);
		System.out.println("==========car1=" + car);
		if(car == null){
			Integer carModelId = null;
			CarModel carModel =carModelService.getByBrandIdAndName(Integer.valueOf(carBrandId), carModelName);
			if (carModel != null) {
				carModelId = carModel.getId();
			}
			car = new Car();
			car.setCarModelId(Integer.valueOf(carModelId));
			car.setLicensedId(licensedId);
			car.setUserId(users.getId());
			carsService.save(car);
		}
		System.out.println("==========car2=" + car);
		SuiteOrders suiteOrders = suiteOrdersService.saveOrder(users, suites, null, "JSAPI", 1, TradeNoTypeEnum.YEAR.name());
		
		WxpayPlaceOrderRequest request = setWxpayPlaceOrderRequest(suiteOrders, suites, "JSAPI", "JSAPI", openid);
		String notifyUrl = WxPayProperties.memberJsapiTmpNotify();
		JSONObject attachObj = new JSONObject();
		attachObj.put("licensedId", licensedId);
		request.setAttach(attachObj.toJSONString());
		WxpayPlaceOrderResult result = wxpayPlaceOrder.run(request, account, notifyUrl);
		if (null == result) {
			return getCodeMessage(false, "生成微信订单失败", model);
		}
    	
    	assembleResult(model, "JSAPI", account, result, suiteOrders);
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