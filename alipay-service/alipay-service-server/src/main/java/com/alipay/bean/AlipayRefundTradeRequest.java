package com.alipay.bean;

import java.io.Serializable;

public class AlipayRefundTradeRequest implements Serializable {

	private static final long serialVersionUID = -7712225156308112556L;

	/** 商户内部订单号。与支付宝交易号二者必填其一 */
	private String orderId;
	/** 支付宝交易号。与商户内部订单号二者必填其一。优先使用支付宝交易号 */
	private String alipayId;
	/***/
	private String refundAmount;
	private String refundReason;
	private String outRequestNo ;
	
	public AlipayRefundTradeRequest orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public AlipayRefundTradeRequest alipayId(String alipayId) {
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

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getOutRequestNo() {
		return outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayRefuntTradeRequest [orderId=");
		builder.append(orderId);
		builder.append(", alipayId=");
		builder.append(alipayId);
		builder.append(", refundAmount=");
		builder.append(refundAmount);
		builder.append(", refundReason=");
		builder.append(refundReason);
		builder.append(", outRequestNo=");
		builder.append(outRequestNo);
		builder.append("]");
		return builder.toString();
	}
}
