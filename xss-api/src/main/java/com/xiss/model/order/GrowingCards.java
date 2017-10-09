package com.xiss.model.order;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年5月17日 下午3:24:16 
*/
public class GrowingCards implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8142829570567018407L;
	private int id;
	/** 卡号*/
	private String cid;
	/** 激活码*/
	private String pin;
	/** */
	private int range;
	private Date createdAt;
	private Date updatedAt;
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
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
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
		builder.append("GrowingCards [id=");
		builder.append(id);
		builder.append(", cid=");
		builder.append(cid);
		builder.append(", pin=");
		builder.append(pin);
		builder.append(", range=");
		builder.append(range);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}
