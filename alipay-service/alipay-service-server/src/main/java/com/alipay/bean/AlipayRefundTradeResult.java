package com.alipay.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alipay.api.domain.TradeFundBill;

public class AlipayRefundTradeResult implements Serializable {

	private static final long serialVersionUID = -5469504905365648666L;

	/** （必填）支付宝交易号 */
	private String alipayId;
	/** （必填）商户内部订单号 */
	private String orderId;
	/** （必填）买家支付宝账号 */
	private String buyerLogonId;
	/** （必填）买家在支付宝的用户ID */
	private String buyerUserId;
	/** 
	 * 本次退款是否发生了资金变化
	 */
	private String fundChange;
	/** 
	 * 退款支付时间
	 */
	private Date gmtRefundPay;
	/** 
	 * 退款使用的资金渠道
	 */
	private List<TradeFundBill> refundDetailItemList;
	/** 
	 * 退款总金额
	 */
	private String refundFee;
	/** 
	 * 本次商户实际退回金额
注：在签约收单产品时需勾选“返回资金明细”才会返回
	 */
	private String sendBackFee;
	public String getAlipayId() {
		return alipayId;
	}
	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBuyerLogonId() {
		return buyerLogonId;
	}
	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}
	public String getBuyerUserId() {
		return buyerUserId;
	}
	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}
	public String getFundChange() {
		return fundChange;
	}
	public void setFundChange(String fundChange) {
		this.fundChange = fundChange;
	}
	public Date getGmtRefundPay() {
		return gmtRefundPay;
	}
	public void setGmtRefundPay(Date gmtRefundPay) {
		this.gmtRefundPay = gmtRefundPay;
	}
	public List<TradeFundBill> getRefundDetailItemList() {
		return refundDetailItemList;
	}
	public void setRefundDetailItemList(List<TradeFundBill> refundDetailItemList) {
		this.refundDetailItemList = refundDetailItemList;
	}
	public String getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}
	public String getSendBackFee() {
		return sendBackFee;
	}
	public void setSendBackFee(String sendBackFee) {
		this.sendBackFee = sendBackFee;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayRefundTradeResult [alipayId=");
		builder.append(alipayId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", buyerLogonId=");
		builder.append(buyerLogonId);
		builder.append(", buyerUserId=");
		builder.append(buyerUserId);
		builder.append(", fundChange=");
		builder.append(fundChange);
		builder.append(", gmtRefundPay=");
		builder.append(gmtRefundPay);
		builder.append(", refundDetailItemList=");
		builder.append(refundDetailItemList);
		builder.append(", refundFee=");
		builder.append(refundFee);
		builder.append(", sendBackFee=");
		builder.append(sendBackFee);
		builder.append("]");
		return builder.toString();
	}
	
}
