package com.wxpay.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信支付退款请求。
 * 
 * @author zhy
 *
 */
public class WxpayRefundRequest implements Serializable {

	private static final long serialVersionUID = -522216169551817181L;

	/** 终端设备号 */
	private String deviceInfo;
	/** 微信支付订单号。与商户内部订单号二者必填其一 */
	private String wxpayId;
	/** 商户内部订单号。与微信支付订单号二者必填其一 */
	private String orderId;
	/** 商户内部退款单号。必填 */
	private String refundId;
	/** 订单总金额，单位为元，小数点后两位。必填 */
	private BigDecimal totalFee;
	/** 本笔退款金额，单位为元，小数点后两位。必填 */
	private BigDecimal refundFee;
	/** 退款货币种类，默认人民币：CNY */
	private String refundFeeType;
	/** 操作员账号，默认为商户号。必填。如果是人工操作的退款，应当填写操作员用户号 */
	private String operatorId;
	/**
	 * 退款资金来源。仅针对老资金流商户使用：
	 * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
	 * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
	 */
	private String refundAccount;
	
	public WxpayRefundRequest deviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
		return this;
	}
	
	public WxpayRefundRequest wxpayId(String wxpayId) {
		this.wxpayId = wxpayId;
		return this;
	}
	
	public WxpayRefundRequest orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public WxpayRefundRequest refundId(String refundId) {
		this.refundId = refundId;
		return this;
	}
	
	public WxpayRefundRequest totalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
		return this;
	}
	
	public WxpayRefundRequest refundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
		return this;
	}
	
	public WxpayRefundRequest refundFeeType(String refundFeeType) {
		this.refundFeeType = refundFeeType;
		return this;
	}
	
	public WxpayRefundRequest operatorId(String operatorId) {
		this.operatorId = operatorId;
		return this;
	}
	
	public WxpayRefundRequest refundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
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

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundFeeType() {
		return refundFeeType;
	}

	public void setRefundFeeType(String refundFeeType) {
		this.refundFeeType = refundFeeType;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getRefundAccount() {
		return refundAccount;
	}

	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayRefundRequest [deviceInfo=").append(deviceInfo).append(", wxpayId=").append(wxpayId)
				.append(", orderId=").append(orderId).append(", refundId=").append(refundId).append(", totalFee=")
				.append(totalFee).append(", refundFee=").append(refundFee).append(", refundFeeType=")
				.append(refundFeeType).append(", operatorId=").append(operatorId).append(", refundAccount=")
				.append(refundAccount).append("]");
		return builder.toString();
	}
	
}
