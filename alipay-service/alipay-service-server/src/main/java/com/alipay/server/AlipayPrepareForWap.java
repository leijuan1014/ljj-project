package com.alipay.server;

import static com.alipay.api.AlipayConstants.APP_ID;
import static com.alipay.api.AlipayConstants.BIZ_CONTENT_KEY;
import static com.alipay.api.AlipayConstants.CHARSET;
import static com.alipay.api.AlipayConstants.CHARSET_UTF8;
import static com.alipay.api.AlipayConstants.FORMAT;
import static com.alipay.api.AlipayConstants.FORMAT_JSON;
import static com.alipay.api.AlipayConstants.METHOD;
import static com.alipay.api.AlipayConstants.NOTIFY_URL;
import static com.alipay.api.AlipayConstants.RETURN_URL;
import static com.alipay.api.AlipayConstants.SIGN;
import static com.alipay.api.AlipayConstants.SIGN_TYPE;
import static com.alipay.api.AlipayConstants.SIGN_TYPE_RSA;
import static com.alipay.api.AlipayConstants.TIMESTAMP;
import static com.alipay.api.AlipayConstants.VERSION;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.bean.AlipayWapPayRequest;
import com.alipay.conf.AlipayConfig;
import com.alipay.config.AlipayServerConfig;
import com.fasterxml.jackson.core.JsonGenerator;
/**
 * 移动网站支付
 * */
public class AlipayPrepareForWap extends AlipayStep {
	
	private AlipayWapPayRequest request;
	private AlipayAccount account;
	private String notifyUrl;
	
	public AlipayWapPayRequest getRequest() {
		return request;
	}

	public void setRequest(AlipayWapPayRequest request) {
		this.request = request;
	}

	public AlipayAccount getAccount() {
		return account;
	}

	public void setAccount(AlipayAccount account) {
		this.account = account;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	@Override
	protected void encodeBizContent(JsonGenerator generator) throws IOException {
		generator.writeStartObject();
		if (request.getBody() != null)
			generator.writeStringField("body", request.getBody());
		if (request.getSubject() != null)
			generator.writeStringField("subject", request.getSubject());
		if (request.getOrderId() != null)
			generator.writeStringField("out_trade_no", request.getOrderId());
		if (request.getExpiry() != null)
			generator.writeStringField("timeout_express", request.getExpiry());
		if (request.getTotalAmount() != null)
			generator.writeStringField("total_amount", request.getTotalAmount().toPlainString());
		if (request.getSellerId() != null)
			generator.writeStringField("seller_id", request.getSellerId());
		if (request.getAuthToken() != null)
			generator.writeStringField("auth_token", request.getAuthToken());
		generator.writeStringField("product_code", "QUICK_WAP_PAY");
		generator.writeEndObject();
	}

	public void run1() {
		Map<String, String> params = new TreeMap<String, String>();
		try {
			params.put(APP_ID, account.getAppId());
			params.put(METHOD, "alipay.trade.wap.pay");
			params.put(FORMAT, FORMAT_JSON);
			if (request.getReturnUrl() != null && !request.getReturnUrl().isEmpty())
				params.put(RETURN_URL, request.getReturnUrl());
			params.put(CHARSET, CHARSET_UTF8);
			params.put(SIGN_TYPE, SIGN_TYPE_RSA);
			params.put(TIMESTAMP, formatDateTime(new Date()));
			params.put(VERSION, VERSION_1_0);
			if (notifyUrl != null && !notifyUrl.isEmpty())
				params.put(NOTIFY_URL, notifyUrl);
			params.put(BIZ_CONTENT_KEY, getBizContent());
			
			String sign = AlipaySignature.rsaSign(params, account.getPrivateKey(), CHARSET_UTF8);
			params.put(SIGN, sign);
		} catch (Exception e) {
			return;
		}
	}
	public String run() {
		/**********************/
	    // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
	    //调用RSA签名方式
	    AlipayClient client = new DefaultAlipayClient(AlipayConfig.PAY_URL, account.getAppId(), account.getPrivateKey(), AlipayConfig.FORMAT, AlipayConfig.CHARSET, account.getPublicKey(),AlipayConfig.SIGNTYPE);
	    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
	    
	    // 封装请求支付信息
	    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	    model.setOutTradeNo(request.getOrderId());
	    model.setSubject(request.getSubject());
	    model.setTotalAmount(request.getTotalAmount().toString());
	    model.setBody(request.getBody());
	    model.setTimeoutExpress(AlipayConfig.TIMEOUT_EXPRESS);
	    model.setProductCode(AlipayConfig.WAP_PRODUCT_CODE);
	    alipay_request.setBizModel(model);
	    // 设置异步通知地址
	    if (notifyUrl != null && !notifyUrl.isEmpty())
	    		alipay_request.setNotifyUrl(notifyUrl);
	    // 设置同步地址
	    if (request.getReturnUrl() != null && !request.getReturnUrl().isEmpty())
	    		alipay_request.setReturnUrl(request.getReturnUrl());  
	    
	    String form = "";
		try {
			form = client.pageExecute(alipay_request).getBody();
			System.out.println("====form=" + form);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	    return form;
	}
}
