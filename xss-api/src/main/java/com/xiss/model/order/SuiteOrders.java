package com.xiss.model.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体业务对象
 * 
 * by zhaochen on 2016-09-13
 */
public class SuiteOrders implements Serializable {

	private static final long serialVersionUID = -7347508246762223276L;

	/** 订单Id */
	private int id;
	/** 用户Id */
	private int userId;
	private int suiteId;
	private int couponId;
	/** 结算单id*/
	private int balanceId;
	/** 订单状态*/
	private int state;
	/** 支付方式：0=微信支付，1=支付宝支付*/
	private int paymentGateway;
	/** 订单编号*/
	private String tradeNo;
	/** 单价*/
	private double price;
	/** 数量*/
	private int quantity;
	/** 平台：0=Android，1=IOS,3=JSAPI*/
	private int platform;
	private Date createdAt;
	private Date updatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSuiteId() {
		return suiteId;
	}
	public void setSuiteId(int suiteId) {
		this.suiteId = suiteId;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public int getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(int balanceId) {
		this.balanceId = balanceId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPaymentGateway() {
		return paymentGateway;
	}
	public void setPaymentGateway(int paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SuiteOrders [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", suiteId=");
		builder.append(suiteId);
		builder.append(", couponId=");
		builder.append(couponId);
		builder.append(", balanceId=");
		builder.append(balanceId);
		builder.append(", state=");
		builder.append(state);
		builder.append(", paymentGateway=");
		builder.append(paymentGateway);
		builder.append(", tradeNo=");
		builder.append(tradeNo);
		builder.append(", price=");
		builder.append(price);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", platform=");
		builder.append(platform);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}