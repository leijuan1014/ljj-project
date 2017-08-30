package com.alipay.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class AlipayDirectPayRequest implements Serializable {

	private static final long serialVersionUID = 2816436239788219322L;

	/** 支付完成后前台回跳地址 */
	private String returnUrl;
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
	private BigDecimal totalFee;
	/** 客户端IP地址 */
	private String clientIp;
	/** 
	 * 其它参数。上面未指明的其它参数请放在这里，它们会参与签名的计算。
	 * 如果一参数在上面指明，在这里也填写了，那么这里填写的优先。
	 */
	private Map<String, String> extension;
	
	public AlipayDirectPayRequest returnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
		return this;
	}
	
	public AlipayDirectPayRequest body(String body) {
		this.body = body;
		return this;
	}
	
	public AlipayDirectPayRequest subject(String subject) {
		this.subject = subject;
		return this;
	}
	
	public AlipayDirectPayRequest orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public AlipayDirectPayRequest expiry(String expiry) {
		this.expiry = expiry;
		return this;
	}
	
	public AlipayDirectPayRequest totalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
		return this;
	}
	
	public AlipayDirectPayRequest clientIp(String clientIp) {
		this.clientIp = clientIp;
		return this;
	}
	
	public AlipayDirectPayRequest extension(String name, String value) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("param name cannot be null or empty");
		if (value == null || value.isEmpty())
			throw new IllegalArgumentException("param value cannot be null or empty");
		if (extension == null) 
			extension = new TreeMap<String, String>();
		extension.put(name, value);
		return this;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
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

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Map<String, String> getExtension() {
		return extension;
	}

	public void setExtension(Map<String, String> extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayDirectPayRequest [returnUrl=").append(returnUrl).append(", body=").append(body)
				.append(", subject=").append(subject).append(", orderId=").append(orderId).append(", expiry=")
				.append(expiry).append(", totalFee=").append(totalFee).append(", clientIp=").append(clientIp)
				.append(", extension=").append(extension).append("]");
		return builder.toString();
	}
	
}
