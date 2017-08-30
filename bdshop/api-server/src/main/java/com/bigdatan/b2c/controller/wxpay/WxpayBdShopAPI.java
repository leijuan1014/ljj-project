package com.bigdatan.b2c.controller.wxpay;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigdatan.b2c.WxpayProperties;
import com.bigdatan.b2c.controller.BasicAPI;
import com.bigdatan.b2c.entity.JoinOrder;
import com.bigdatan.b2c.entity.Order;
import com.bigdatan.b2c.service.IOrderItemService;
import com.wx.base.bean.WxAccessToken;
import com.wxpay.bean.WxpayPlaceOrderRequest;
import com.wxpay.bean.WxpayPlaceOrderResult;
import com.wxpay.bean.WxpaySignature;
//import com.xiss.service.wxpay.WxpayPlaceOrderResultService;
import com.wxpay.server.service.WxpayOperations;

/** 
* @author leijj
* @since  2017年4月26日 下午5:46:57 
*/
@Controller
@RequestMapping("/wxpay")
public class WxpayBdShopAPI extends BasicAPI{
	@Autowired
	private WxpayOperations wxpayOperations;
	//@Autowired
	//private WxpayPlaceOrderResultService wxpayPlaceOrderResultService;
	@Autowired
	private IOrderItemService orderItemService;
	
	@Autowired
	private WxpayProperties wxpayProperties;
	@Autowired
	private RedisTemplate redisTemplate;
	
	@ResponseBody
	@RequestMapping("/pay")
	public ModelMap jsapi(String orderNumber, String openid) throws Exception {
		System.out.println("=======参数列表:orderNumber=" + orderNumber);
		ModelMap model = new ModelMap();
		if (null == orderNumber || orderNumber.isEmpty()) {
			return getCodeMessage(false, "参数有误", model);
		}
		//WxpayAccount account = WxpayProperties.getJsapiWxpayAccount();
		WxpayPlaceOrderResult result = null;//TODO wxpayPlaceOrderResultService.getByOrderNumber(orderNumber);
		String key = "bdshop.order.".concat(orderNumber);
		boolean exists = redisTemplate.hasKey(key);
		if(exists) {
			result = (WxpayPlaceOrderResult) redisTemplate.opsForValue().get(key);
			if(result != null)
				assembleResult(model, "JSAPI", result, orderNumber);
		} else {
			WxpayPlaceOrderRequest request = setWxpayPlaceOrderRequest(orderNumber, openid);
			result = wxpayOperations.placeOrder(wxpayProperties.getWxpayAccount(wxpayProperties), wxpayProperties.getNotifyUrl(), request);
			//result = wxpayPlaceOrder.run(request, account, WxpayProperties.bdshopJsapiNotify());
			if (result == null) {
				return getCodeMessage(false, "生成微信订单失败", model);
			}
			result.setOrderNumber(orderNumber);
			//wxpayPlaceOrderResultService.insert(result);
			ValueOperations<String, WxpayPlaceOrderResult> orderOperations = redisTemplate.opsForValue();
			orderOperations.set(key, result, 7200, TimeUnit.SECONDS);
	    		assembleResult(model, "JSAPI", result, orderNumber);
		}
		return model; 
	}
	
	public WxpayPlaceOrderRequest setWxpayPlaceOrderRequest(String orderNumber, String openid) {
		Order order = orderItemService.getOrderByNumber(orderNumber);
        int totalPrice = 0;
        if (null == order) {
            JoinOrder joinOrder = orderItemService.getJoinOrderByNumber(orderNumber);
            if (null == joinOrder) {
                return null;
            } else {
                totalPrice = joinOrder.getTotalPrice();
            }
        } else {
            totalPrice = order.getPaidAmount();
        }
		if (order == null) 
			return null;
		
		WxpayPlaceOrderRequest placeOrderRequest = new WxpayPlaceOrderRequest();
		placeOrderRequest.setDeviceInfo("JSAPI");
		placeOrderRequest.setBody(orderNumber);
		placeOrderRequest.setAttach(orderNumber);
		placeOrderRequest.setOrderTradeNo(orderNumber);
		placeOrderRequest.setFeeType("CNY");
		placeOrderRequest.setTotalFee(totalPrice);
		//placeOrderRequest.setClientIp("");
		placeOrderRequest.setStartTime(new Date());
		placeOrderRequest.setTradeType("JSAPI");
		if (null != openid && !openid.isEmpty()) 
			placeOrderRequest.setOpenid(openid);
		
		placeOrderRequest.setLimitPay("no_credit");
		return placeOrderRequest;
	}
	
	/**
	 * 微信支付单结果组装
	 * */
	private ModelMap assembleResult(ModelMap model, String tradeType,  WxpayPlaceOrderResult result, String orderNumber) throws Exception {
		String nonce = WxpaySignature.genNonce();
    		String timestamp = String.valueOf(new Date().getTime()).substring(0, 10);
		WxpaySignature signature = new WxpaySignature();
		if(tradeType.equals("JSAPI")){
		    	signature.param("appId", wxpayProperties.getAppId());
		    	signature.param("timeStamp", timestamp);
		    	signature.param("nonceStr", nonce);
		    	signature.param("package", "prepay_id="+result.getPrepayId());
		    	signature.param("signType", "MD5");
		    	String sig = signature.sign(wxpayProperties.getKey());
		    	model.put("return_code", true);
			model.put("orderId", orderNumber);
		    //model.put("codeUrl", result.getCodeUrl());
		    	model.put("appId", wxpayProperties.getAppId());
		    	model.put("timeStamp", timestamp);
		    	model.put("nonceStr", nonce);
		    	model.put("packageStr", "prepay_id="+result.getPrepayId());
		    	model.put("signType", "MD5");
		    	model.put("sign", sig);
		}  else if(tradeType.equals("APP")) {
		    	signature.param("appid", wxpayProperties.getAppId());
		    	signature.param("partnerid", wxpayProperties.getMerchantId());
		    	signature.param("prepayid", result.getPrepayId());
		    	signature.param("package", "Sign=WXPay");
		    	signature.param("noncestr", nonce);
		    	signature.param("timestamp", timestamp);
		    	String sig = signature.sign(wxpayProperties.getKey());
		    	
		    	model.put("return_code", true);
		    	model.put("orderId", orderNumber);
		    //model.put("codeUrl", result.getCodeUrl());
		    	model.put("appid", wxpayProperties.getAppId());
		    	model.put("partnerid", wxpayProperties.getMerchantId());
		    	model.put("prepayid", result.getPrepayId());
		    	model.put("package", "Sign=WXPay");
		    	model.put("noncestr", nonce);
		    	model.put("timestamp", timestamp);
		    	model.put("sign", sig);
		} else if(tradeType.equals("NATIVE")){
			model.put("return_code", true);
		    	model.put("orderId", orderNumber);
		    	model.put("codeUrl", result.getCodeUrl());
		}
		return model;
	}
}