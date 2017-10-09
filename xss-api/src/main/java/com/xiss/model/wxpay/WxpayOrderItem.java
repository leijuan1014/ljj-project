package com.xiss.model.wxpay;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信支付订单商品详情。
 * 
 * @author zhy
 *
 */
public class WxpayOrderItem implements Serializable {

	private static final long serialVersionUID = -1570262841909533031L;

	/** 商品编号。必填，最多32字 */
	private String id;
	/** 微信支付定义的统一商品编号。选填，最多32字 */
	private String wxpayId;
	/** 商品名称。必填，最多256字 */
	private String name;
	/** 商品数量。必填 */
	private long quantity;
	/** 商品单价，单位为元，小数点后两位。必填 */
	private BigDecimal price;
	/** 商品类目。选填，最多32字 */
	private String category;
	/** 商品详情。选填，最多1000字 */
	private String body;
	
	public WxpayOrderItem id(String id) {
		this.id = id;
		return this;
	}
	
	public WxpayOrderItem wxpayId(String wxpayId) {
		this.wxpayId = wxpayId;
		return this;
	}
	
	public WxpayOrderItem name(String name) {
		this.name = name;
		return this;
	}
	
	public WxpayOrderItem quantity(int quantity) {
		this.quantity = quantity;
		return this;
	}
	
	public WxpayOrderItem price(BigDecimal price) {
		this.price = price;
		return this;
	}
	
	public WxpayOrderItem category(String category) {
		this.category = category;
		return this;
	}
	
	public WxpayOrderItem body(String body) {
		this.body = body;
		return this;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWxpayId() {
		return wxpayId;
	}
	public void setWxpayId(String wxpayId) {
		this.wxpayId = wxpayId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayOrderItem [id=").append(id).append(", wxpayId=").append(wxpayId).append(", name=")
				.append(name).append(", quantity=").append(quantity).append(", price=").append(price)
				.append(", category=").append(category).append(", body=").append(body).append("]");
		return builder.toString();
	}
	
}
