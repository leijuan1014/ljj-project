package com.alipay.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class AlipayAppPayRequest implements Serializable {

	private static final long serialVersionUID = 1199829478252639096L;

	/** 交易描述信息。如果是多种商品，请将商品描述字符串累加。 */
	private String body;
	/** （必填）订单标题／交易标题。 */
	private String subject;
	/** （必填）商户内部订单号。 */
	private String orderId;
	/** 
	 * 该笔订单允许的最晚付款时间，逾期将关闭交易。
	 * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 
	 * 该参数数值不接受小数点，如1.5h，可转换为90m。
	 */
	private String expiry;
	/** （必填）订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] */
	private BigDecimal totalAmount;
	/** 收款支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID */
	private String sellerId;
	
	public AlipayAppPayRequest body(String body) {
		this.body = body;
		return this;
	}
	
	public AlipayAppPayRequest subject(String subject) {
		this.subject = subject;
		return this;
	}
	
	public AlipayAppPayRequest orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public AlipayAppPayRequest expiry(String expiry) {
		this.expiry = expiry;
		return this;
	}
	
	public AlipayAppPayRequest totalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
		return this;
	}
	
	public AlipayAppPayRequest sellerId(String sellerId) {
		this.sellerId = sellerId;
		return this;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayAppPayRequest [body=").append(body).append(", subject=").append(subject)
				.append(", orderId=").append(orderId).append(", expiry=").append(expiry).append(", totalAmount=")
				.append(totalAmount).append(", sellerId=").append(sellerId).append("]");
		return builder.toString();
	}
	
}
