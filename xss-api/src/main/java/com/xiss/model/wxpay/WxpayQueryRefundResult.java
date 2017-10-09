package com.xiss.model.wxpay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 微信支付退款查询结果。
 * 
 * @author zhy
 *
 */
public class WxpayQueryRefundResult implements Serializable {

	private static final long serialVersionUID = 6431523062318644568L;

	/** 终端设备号。必填 */
	private String deviceInfo;
	/** 微信支付订单号。必填 */
	private String wxpayId;
	/** 商户订单号。必填 */
	private String orderId;
	/** 订单总金额，单位为元，小数点后两位。必填 */
	private BigDecimal totalFee;
	/** 订单货币种类，默认人民币：CNY */
	private String feeType;
	/** 现金支付金额，单位为元，小数点后两位。必填 */
	private BigDecimal cashFee;
	/** 退款记录。必填 */
	private List<WxpayRefundRecord> refunds;
	/** 退款资金来源 */
	private String refundAccount;
	
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
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public BigDecimal getCashFee() {
		return cashFee;
	}
	public void setCashFee(BigDecimal cashFee) {
		this.cashFee = cashFee;
	}
	public List<WxpayRefundRecord> getRefunds() {
		return refunds;
	}
	public void setRefunds(List<WxpayRefundRecord> refunds) {
		this.refunds = refunds;
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
		builder.append("WxpayQueryRefundResult [deviceInfo=").append(deviceInfo).append(", wxpayId=").append(wxpayId)
				.append(", orderId=").append(orderId).append(", totalFee=").append(totalFee).append(", feeType=")
				.append(feeType).append(", cashFee=").append(cashFee).append(", refunds=").append(refunds)
				.append(", refundAccount=").append(refundAccount).append("]");
		return builder.toString();
	}
	
}
