package com.xiss.model.shops;

import java.io.Serializable;
import java.util.Date;

/** 
 * 洗车记录表
* @author leijj
* @since  2017年4月11日 下午3:34:43 
*/
public class Deals implements Serializable{

	private static final long serialVersionUID = -9081004993769262743L;

	private int id;
	/** 车id*/
	private int carId;
	/** 车行id*/
	private int shopId;
	/** */
	private Date visitedAt;
	/** 洗车时间*/
	private Date cleanedAt;
	/** 状态*/
	private int status;
	/** 评价时间*/
	private Date commentedAt;
	private Date createdAt;
	private Date updatedAt;
	private int userId;
	/** 评论id*/
	private int commentId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public Date getVisitedAt() {
		return visitedAt;
	}
	public void setVisitedAt(Date visitedAt) {
		this.visitedAt = visitedAt;
	}
	public Date getCleanedAt() {
		return cleanedAt;
	}
	public void setCleanedAt(Date cleanedAt) {
		this.cleanedAt = cleanedAt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCommentedAt() {
		return commentedAt;
	}
	public void setCommentedAt(Date commentedAt) {
		this.commentedAt = commentedAt;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Deals [id=");
		builder.append(id);
		builder.append(", carId=");
		builder.append(carId);
		builder.append(", shopId=");
		builder.append(shopId);
		builder.append(", visitedAt=");
		builder.append(visitedAt);
		builder.append(", cleanedAt=");
		builder.append(cleanedAt);
		builder.append(", status=");
		builder.append(status);
		builder.append(", commentedAt=");
		builder.append(commentedAt);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", commentId=");
		builder.append(commentId);
		builder.append("]");
		return builder.toString();
	}
}
