package com.wxpay.server.service;

import com.wxpay.bean.WxpayAccount;
import com.wxpay.bean.WxpayPlaceOrderRequest;
import com.wxpay.bean.WxpayPlaceOrderResult;
import com.wxpay.bean.WxpayQueryOrderRequest;
import com.wxpay.bean.WxpayQueryOrderResult;
import com.wxpay.bean.WxpayQueryRefundRequest;
import com.wxpay.bean.WxpayQueryRefundResult;
import com.wxpay.bean.WxpayRefundRequest;

public interface WxpayOperations {
	/**
	 * 使用默认微信支付商户账号下单，成功后返回预支付交易会话标识。
	 * 
	 * @param request 微信支付下单请求
	 * @return 预支付交易会话标识
	 */
	public WxpayPlaceOrderResult placeOrder(WxpayAccount account, String notifyUrl, WxpayPlaceOrderRequest request);
	
	public WxpayQueryOrderResult getPlaceOrder(WxpayAccount account, WxpayQueryOrderRequest request);
	
	public String askForRefund(WxpayAccount account, WxpayRefundRequest request);
	
	public WxpayQueryRefundResult queryRefund(WxpayAccount account, WxpayQueryRefundRequest request);
}
