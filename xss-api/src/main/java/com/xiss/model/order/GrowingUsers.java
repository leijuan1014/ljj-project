package com.xiss.model.order;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年5月17日 下午3:24:37 
*/
public class GrowingUsers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8261572613388732133L;
	private int id;
	/** 手机号*/
	private String mobile;
	/** 卡id*/
	private int growingCardId;
	private int status;
	private boolean enrolled520;
	private Date createdAt;
	private Date updatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getGrowingCardId() {
		return growingCardId;
	}
	public void setGrowingCardId(int growingCardId) {
		this.growingCardId = growingCardId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isEnrolled520() {
		return enrolled520;
	}
	public void setEnrolled520(boolean enrolled520) {
		this.enrolled520 = enrolled520;
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
		builder.append("GrowingUsers [id=");
		builder.append(id);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", growingCardId=");
		builder.append(growingCardId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", enrolled520=");
		builder.append(enrolled520);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}
