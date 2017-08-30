package com.xiss.model.order;

import java.io.Serializable;
import java.util.Date;

import com.xiss.model.order.enums.PaymentGateway;

/**
 * 支付信息。
 * 
 * @author zhy
 *
 */
public class PaymentInfo implements Serializable {

	private static final long serialVersionUID = -729931449989635237L;

	/** 订单号。必填 */
	private String orderTradeNo;
	/** 支付方式，如支付宝、微信支付等。必填 */
	private PaymentGateway mode;
	/** 支付金额，单位元，精确到小数点后3位。必填 */
	private double amount = 0;
	/** 支付完成时间。必填 */
	private Date time;
	/** 备注。选填 */
	private String remark;
	
	public PaymentInfo orderTradeNo(String orderTradeNo) {
		this.orderTradeNo = orderTradeNo;
		return this;
	}
	
	public PaymentInfo mode(PaymentGateway mode) {
		this.mode = mode;
		return this;
	}
	
	public PaymentInfo amount(double amount) {
		this.amount = amount;
		return this;
	}
	
	public PaymentInfo time(Date time) {
		this.time = time;
		return this;
	}
	
	public PaymentInfo remark(String remark) {
		this.remark = remark;
		return this;
	}

	public String getOrderTradeNo() {
		return orderTradeNo;
	}

	public void setOrderTradeNo(String orderTradeNo) {
		this.orderTradeNo = orderTradeNo;
	}

	public PaymentGateway getMode() {
		return mode;
	}

	public void setMode(PaymentGateway mode) {
		this.mode = mode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentInfo [orderTradeNo=").append(orderTradeNo).append(", mode=").append(mode).append(", amount=")
				.append(amount).append(", time=").append(time).append(", remark=").append(remark).append("]");
		return builder.toString();
	}

}
