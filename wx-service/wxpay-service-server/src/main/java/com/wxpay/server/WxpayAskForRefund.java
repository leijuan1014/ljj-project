package com.wxpay.server;

import java.io.InputStream;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import com.wxpay.bean.WxpayAccount;
import com.wxpay.bean.WxpayRefundRequest;
import com.wxpay.bean.WxpaySignature;

public class WxpayAskForRefund extends WxpayStep {
	
	private WxpayRefundRequest request;
	private WxpayAccount account;
	
	public WxpayRefundRequest getRequest() {
		return request;
	}

	public void setRequest(WxpayRefundRequest request) {
		this.request = request;
	}

	public WxpayAccount getAccount() {
		return account;
	}

	public void setAccount(WxpayAccount account) {
		this.account = account;
	}

	public String run() {
		InputStream resultIS = postRun("https://api.mch.weixin.qq.com/secapi/pay/refund");
		String wxpayRefundId = (String) decodeResponse(resultIS);
		System.out.println("======wxpayRefundId=" + wxpayRefundId);
		return wxpayRefundId;
	}

	@Override
	protected void encode(XMLStreamWriter writer) throws Exception {
		WxpaySignature signature = new WxpaySignature();
		
		if (account.getAppId() != null) {
			writeElement(writer, "appid", account.getAppId());
			signature.param("appid", account.getAppId());
		}
		
		if (account.getMerchantId() != null) {
			writeElement(writer, "mch_id", account.getMerchantId());
			signature.param("mch_id", account.getMerchantId());
		}
		
		String deviceInfo = request.getDeviceInfo();
		if (deviceInfo != null && !deviceInfo.isEmpty()) {
			writeElement(writer, "device_info", deviceInfo);
			signature.param("device_info", deviceInfo);
		}
		
		String nonce = WxpaySignature.genNonce();
		writeElement(writer, "nonce_str", nonce);
		signature.param("nonce_str", nonce);

		String wxpayId = request.getWxpayId();
		if (wxpayId != null && !wxpayId.isEmpty()) {
			writeElement(writer, "transaction_id", wxpayId);
			signature.param("transaction_id", wxpayId);
		}
		
		String orderId = request.getOrderId();
		if (orderId != null && !orderId.isEmpty()) {
			writeElement(writer, "out_trade_no", orderId);
			signature.param("out_trade_no", orderId);
		}
		
		String refundId = request.getRefundId();
		if (refundId != null && !refundId.isEmpty()) {
			writeElement(writer, "out_refund_no", refundId);
			signature.param("out_refund_no", refundId);
		}
		
		String totalFee = String.valueOf(request.getTotalFee().multiply(HUNDRED).longValue());
		writeElement(writer, "total_fee", totalFee);
		signature.param("total_fee", totalFee);
		
		String refundFee = String.valueOf(request.getRefundFee().multiply(HUNDRED).longValue());
		writeElement(writer, "refund_fee", refundFee);
		signature.param("refund_fee", refundFee);
		
		String refundFeeType = request.getRefundFeeType();
		if (refundFeeType != null && !refundFeeType.isEmpty()) {
			writeElement(writer, "refund_fee_type", refundFeeType);
			signature.param("refund_fee_type", refundFeeType);
		}
		
		String operatorId = request.getOperatorId();
		if (operatorId != null && !operatorId.isEmpty()) {
			writeElement(writer, "op_user_id", operatorId);
			signature.param("op_user_id", operatorId);
		}
		
		String refundAccount = request.getRefundAccount();
		if (refundAccount != null && !refundAccount.isEmpty()) {
			writeElement(writer, "refund_account", refundAccount);
			signature.param("refund_account", refundAccount);
		}
		
		// 计算签名
		String sig = signature.sign(account.getKey());
		writeElement(writer, "sign", sig);
		
		// 不要忘了写元素的结束tag
		writer.writeEndElement();
	}

	@Override
	protected Object decode(XMLStreamReader reader) throws Exception {
		if (reader.getEventType() != XMLStreamConstants.START_ELEMENT)
			throw new XMLStreamException("Unexpected event type " + reader.getEventType(), reader.getLocation());
		
		WxpaySignature signature = new WxpaySignature();
		String appId = null, merchantId = null, sign = null;
		String wxpayRefundId = null;
		while (reader.nextTag() == XMLStreamConstants.START_ELEMENT) {
			String name = reader.getLocalName();
			String value = reader.getElementText().trim();
			if (!"sign".equals(name))
				// 除sign外的所有字段都要参与签名的计算
				signature.param(name, value);
			
			if ("appid".equals(name)) 
				appId = value;
			else if ("mch_id".equals(name)) 
				merchantId = value;
			else if ("sign".equals(name)) 
				sign = value;
			else if ("refund_id".equals(name)) 
				wxpayRefundId = value;
			// 忽略其它元素
		}
		
		// 检查签名
		String sig = signature.sign(account.getKey());
		if (!account.getAppId().equals(appId) ||
				!account.getMerchantId().equals(merchantId) ||
				!sig.equals(sign))
				wxpayRefundId = null;

		return wxpayRefundId;
	}

}
