package com.xiss.model.order;
/** 
* @author leijj
* @since  2017年4月26日 下午5:04:40 
*/

import java.io.Serializable;
import java.util.Date;

public class Suites implements Serializable{
	private static final long serialVersionUID = -201906569818901417L;

	private int id;
	
	private String name;
	
	private double originPrice;
	
	private double salePrice;
	
	private int store;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private int shopId;
	
	private int tags;
	
	private String avatar;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(double originPrice) {
		this.originPrice = originPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
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

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getTags() {
		return tags;
	}

	public void setTags(int tags) {
		this.tags = tags;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Suites [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", originPrice=");
		builder.append(originPrice);
		builder.append(", salePrice=");
		builder.append(salePrice);
		builder.append(", store=");
		builder.append(store);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append(", shopId=");
		builder.append(shopId);
		builder.append(", tags=");
		builder.append(tags);
		builder.append(", avatar=");
		builder.append(avatar);
		builder.append("]");
		return builder.toString();
	}
	
}
