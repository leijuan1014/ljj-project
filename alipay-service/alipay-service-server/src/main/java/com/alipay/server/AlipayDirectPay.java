package com.alipay.server;

import static com.alipay.api.AlipayConstants.CHARSET_UTF8;
import static com.alipay.api.AlipayConstants.NOTIFY_URL;
import static com.alipay.api.AlipayConstants.RETURN_URL;
import static com.alipay.api.AlipayConstants.SIGN;
import static com.alipay.api.AlipayConstants.SIGN_TYPE;
import static com.alipay.api.AlipayConstants.SIGN_TYPE_RSA;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.bean.AlipayDirectPayRequest;
import com.fasterxml.jackson.core.JsonGenerator;


public class AlipayDirectPay extends AlipayStep {
	
	private AlipayDirectPayRequest request;
	private AlipayAccount account;
	private String notifyUrl;
	
	public AlipayDirectPayRequest getRequest() {
		return request;
	}

	public void setRequest(AlipayDirectPayRequest request) {
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
	
	public void run() {
		Map<String, String> params = new TreeMap<String, String>();
		try {
			params.put("service", "create_direct_pay_by_user");
			params.put("partner", account.getPartnerId());
			String charset = null;
			if (request.getExtension() != null)
				charset = request.getExtension().get("_input_charset");
			if (charset == null || charset.isEmpty())
				charset = CHARSET_UTF8.toLowerCase();
			params.put("_input_charset", charset);
			if (notifyUrl != null && !notifyUrl.isEmpty())
				params.put(NOTIFY_URL, notifyUrl);
			if (request.getReturnUrl() != null && !request.getReturnUrl().isEmpty())
				params.put(RETURN_URL, request.getReturnUrl());
			
			if (request.getOrderId() != null && !request.getOrderId().isEmpty())
				params.put("out_trade_no", request.getOrderId());
			if (request.getSubject() != null && !request.getSubject().isEmpty())
				params.put("subject", request.getSubject());
			params.put("payment_type", "1");
			if (request.getTotalFee() != null)
				params.put("total_fee", request.getTotalFee().toPlainString());
			params.put("seller_id", account.getPartnerId());
			if (request.getBody() != null && !request.getBody().isEmpty())
				params.put("body", request.getBody());
			if (request.getClientIp() != null && !request.getClientIp().isEmpty())
				params.put("exter_invoke_ip", request.getClientIp());
			if (request.getExpiry() != null && !request.getExpiry().isEmpty())
				params.put("it_b_pay", request.getExpiry());
			
			if (request.getExtension() != null)
				for (Map.Entry<String, String> entry : request.getExtension().entrySet()) {
					String value = entry.getValue();
					if (value != null && !value.isEmpty())
						params.put(entry.getKey(), value);
				}
			
			String sign = AlipaySignature.rsaSign(params, account.getPrivateKey(), charset);
			System.out.println("========sign=" + sign);
			params.put(SIGN_TYPE, SIGN_TYPE_RSA);
			params.put(SIGN, sign);
		} catch (Exception e) {
			return;
		}
	}

	@Override
	protected void encodeBizContent(JsonGenerator generator) throws IOException {
	}

}
