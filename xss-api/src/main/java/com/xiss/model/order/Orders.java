package com.xiss.model.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体业务对象
 * 
 * by zhaochen on 2016-09-13
 */
public class Orders implements Serializable {

	private static final long serialVersionUID = -7347508246762223276L;

	/** 订单Id */
	private int id;
	/** 用户Id */
	private int userId;
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
	/** 折扣*/
	private int distcount;
	/** 主题*/
	private String subject;
	/** 平台：0=Android，1=IOS*/
	private int platform;
	/** 总价*/
	private double totalAmount;
	/** 商品描述*/
	private String body;
	private Date createdAt;
	private Date updatedAt;
	/** 订单完成时间*/
	private Date finishedAt;
	/** 订单取消时间*/
	private Date canceledAt;
	/** 订单状态*/
	private int status;
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
	public int getDistcount() {
		return distcount;
	}
	public void setDistcount(int distcount) {
		this.distcount = distcount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
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
	public Date getFinishedAt() {
		return finishedAt;
	}
	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}
	public Date getCanceledAt() {
		return canceledAt;
	}
	public void setCanceledAt(Date canceledAt) {
		this.canceledAt = canceledAt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
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
		builder.append(", distcount=");
		builder.append(distcount);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", platform=");
		builder.append(platform);
		builder.append(", totalAmount=");
		builder.append(totalAmount);
		builder.append(", body=");
		builder.append(body);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append(", finishedAt=");
		builder.append(finishedAt);
		builder.append(", canceledAt=");
		builder.append(canceledAt);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}	
}