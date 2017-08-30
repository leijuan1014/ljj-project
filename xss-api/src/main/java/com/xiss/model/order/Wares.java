package com.xiss.model.order;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年4月26日 下午5:05:07 
*/
public class Wares implements Serializable{
	
	private static final long serialVersionUID = -3715088246440388264L;

	private long id;
	
	private int suiteId;
	
	private String name;
	
	private double originPrice;
	
	private double salePrice;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private long shopId;
	
	private int tags;
	
	private String avatar;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(int suiteId) {
		this.suiteId = suiteId;
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

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
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
		builder.append("Wares [id=");
		builder.append(id);
		builder.append(", suiteId=");
		builder.append(suiteId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", originPrice=");
		builder.append(originPrice);
		builder.append(", salePrice=");
		builder.append(salePrice);
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
