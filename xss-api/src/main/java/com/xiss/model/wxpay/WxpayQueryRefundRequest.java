package com.xiss.model.wxpay;

import java.io.Serializable;

/**
 * 微信支付退款查询请求。
 * 
 * @author zhy
 *
 */
public class WxpayQueryRefundRequest implements Serializable {

	private static final long serialVersionUID = 2892317576671278636L;
	
	/** 终端设备号 */
	private String deviceInfo;
	/** 微信支付订单号。微信支付订单号、商户订单号、微信支付退款单号、商户退款单号四者必填其一 */
	private String wxpayId;
	/** 商户订单号。微信支付订单号、商户订单号、微信支付退款单号、商户退款单号四者必填其一 */
	private String orderId;
	/** 微信支付退款单号。微信支付订单号、商户订单号、微信支付退款单号、商户退款单号四者必填其一 */
	private String wxpayRefundId;
	/** 商户订单号。微信支付订单号、商户订单号、微信支付退款单号、商户退款单号四者必填其一 */
	private String refundId;
	
	public WxpayQueryRefundRequest deviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
		return this;
	}
	
	public WxpayQueryRefundRequest wxpayId(String wxpayId) {
		this.wxpayId = wxpayId;
		return this;
	}
	
	public WxpayQueryRefundRequest orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public WxpayQueryRefundRequest wxpayRefundId(String wxpayRefundId) {
		this.wxpayRefundId = wxpayRefundId;
		return this;
	}
	
	public WxpayQueryRefundRequest refundId(String refundId) {
		this.refundId = refundId;
		return this;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getWxpayId() {
		return wxpayId;
	}

	public void setWxpayId(String wxpayId) {
		this.wxpayId = wxpayId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getWxpayRefundId() {
		return wxpayRefundId;
	}

	public void setWxpayRefundId(String wxpayRefundId) {
		this.wxpayRefundId = wxpayRefundId;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayQueryRefundRequest [deviceInfo=").append(deviceInfo).append(", wxpayId=").append(wxpayId)
				.append(", orderId=").append(orderId).append(", wxpayRefundId=").append(wxpayRefundId)
				.append(", refundId=").append(refundId).append("]");
		return builder.toString();
	}

}
