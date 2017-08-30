package com.alipay.server.service;

import com.alipay.bean.AlipayAppPayRequest;
import com.alipay.bean.AlipayQueryTradeRequest;
import com.alipay.bean.AlipayQueryTradeResult;
import com.alipay.bean.AlipayRefundTradeRequest;
import com.alipay.bean.AlipayRefundTradeResult;
import com.alipay.bean.AlipayWapPayRequest;
import com.alipay.server.AlipayAccount;

public interface AlipayOperations {
	public String preparePayForWap(AlipayWapPayRequest request, AlipayAccount account, String notifyUrl);
	
	public String preparePayForPage(AlipayWapPayRequest request, AlipayAccount account, String notifyUrl);
	
	public String preparePayForApp(AlipayAppPayRequest request, AlipayAccount account, String notifyUrl);
	
	public AlipayQueryTradeResult queryTrade(AlipayQueryTradeRequest request, AlipayAccount account);
	
	public AlipayRefundTradeResult refundTrade(AlipayRefundTradeRequest request, AlipayAccount account);
}
