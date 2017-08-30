package com.xiss.model.shops;
/** 
 * 卡号激活记录表
* @author leijj
* @since  2017年4月11日 下午3:34:53 
*/

import java.io.Serializable;
import java.util.Date;

public class Cards implements Serializable{

	private static final long serialVersionUID = 8953899473719179411L;

	private int id;
	/** 卡号*/
	private String cid;
	/** 激活码*/
	private String pin;
	/** 状态：1-已激活*/
	private int status;
	private Date createdAt;
	private Date updatedAt;
	/** 激活时间*/
	private Date activedAt;
	/** 汽车id*/
	private int carId;
	/** */
	private int range;
	/** 渠道*/
	private int channel;
	/** 卡面值*/
	private double price;
	/** 卡id*/
	private int growingUserId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public Date getActivedAt() {
		return activedAt;
	}
	public void setActivedAt(Date activedAt) {
		this.activedAt = activedAt;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getGrowingUserId() {
		return growingUserId;
	}
	public void setGrowingUserId(int growingUserId) {
		this.growingUserId = growingUserId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cards [id=");
		builder.append(id);
		builder.append(", cid=");
		builder.append(cid);
		builder.append(", pin=");
		builder.append(pin);
		builder.append(", status=");
		builder.append(status);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append(", activedAt=");
		builder.append(activedAt);
		builder.append(", carId=");
		builder.append(carId);
		builder.append(", range=");
		builder.append(range);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", price=");
		builder.append(price);
		builder.append(", growingUserId=");
		builder.append(growingUserId);
		builder.append("]");
		return builder.toString();
	}
}
