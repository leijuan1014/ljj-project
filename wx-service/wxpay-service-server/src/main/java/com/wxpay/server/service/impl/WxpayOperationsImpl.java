package com.wxpay.server.service.impl;

import org.springframework.stereotype.Service;

import com.wxpay.bean.WxpayAccount;
import com.wxpay.bean.WxpayPlaceOrderRequest;
import com.wxpay.bean.WxpayPlaceOrderResult;
import com.wxpay.bean.WxpayQueryOrderRequest;
import com.wxpay.bean.WxpayQueryOrderResult;
import com.wxpay.bean.WxpayQueryRefundRequest;
import com.wxpay.bean.WxpayQueryRefundResult;
import com.wxpay.bean.WxpayRefundRequest;
import com.wxpay.server.WxpayAskForRefund;
import com.wxpay.server.WxpayPlaceOrder;
import com.wxpay.server.WxpayQueryOrder;
import com.wxpay.server.WxpayQueryRefund;
import com.wxpay.server.service.WxpayOperations;
@Service
public class WxpayOperationsImpl implements WxpayOperations{

	@Override
	public WxpayPlaceOrderResult placeOrder(WxpayAccount account, String notifyUrl, WxpayPlaceOrderRequest request) {
		WxpayPlaceOrder wxpayPlaceOrder = new WxpayPlaceOrder();
		wxpayPlaceOrder.setAccount(account);
		wxpayPlaceOrder.setRequest(request);
		wxpayPlaceOrder.setNotifyUrl(notifyUrl);
		return wxpayPlaceOrder.run();
	}

	@Override
	public WxpayQueryOrderResult getPlaceOrder(WxpayAccount account, WxpayQueryOrderRequest request) {
		WxpayQueryOrder action = new WxpayQueryOrder();
		action.setRequest(request);
		action.setAccount(account);
		return action.run();
	}

	@Override
	public String askForRefund(WxpayAccount account, WxpayRefundRequest request) {
		WxpayAskForRefund action = new WxpayAskForRefund();
		action.setRequest(request);
		action.setAccount(account);
		return action.run();
	}

	@Override
	public WxpayQueryRefundResult queryRefund(WxpayAccount account, WxpayQueryRefundRequest request) {
		WxpayQueryRefund action = new WxpayQueryRefund();
		action.setRequest(request);
		action.setAccount(account);
		return action.run();
	}


}
