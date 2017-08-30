package com.xiss.model.wxpay;

import java.io.Serializable;

public class WxpayQueryOrderRequest implements Serializable {

	private static final long serialVersionUID = -5421503356261708358L;

	/** 微信订单号。有的话建议优先使用 */
	private String wxpayId;
	/** 商户系统内部的订单号。与微信订单号二者必填其一 */
	private String orderId;
	
	public WxpayQueryOrderRequest wxpayId(String wxpayId) {
		this.wxpayId = wxpayId;
		return this;
	}
	
	public WxpayQueryOrderRequest orderId(String orderId) {
		this.orderId = orderId;
		return this;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayQueryOrderRequest [wxpayId=").append(wxpayId).append(", orderId=").append(orderId)
				.append("]");
		return builder.toString();
	}
	
}
