package com.alipay.bean;

import java.io.Serializable;

public class AlipayQueryTradeRequest implements Serializable {

	private static final long serialVersionUID = -7712225156308112556L;

	/** 商户内部订单号。与支付宝交易号二者必填其一 */
	private String orderId;
	/** 支付宝交易号。与商户内部订单号二者必填其一。优先使用支付宝交易号 */
	private String alipayId;
	
	public AlipayQueryTradeRequest orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public AlipayQueryTradeRequest alipayId(String alipayId) {
		this.alipayId = alipayId;
		return this;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayQueryTradeRequest [orderId=").append(orderId).append(", alipayId=").append(alipayId)
				.append("]");
		return builder.toString();
	}
	
}
