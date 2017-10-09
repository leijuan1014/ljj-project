package com.xiss.model.system;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年6月14日 上午10:04:41 
*/
public class Car implements Serializable{
	private static final long serialVersionUID = -7512146148718140122L;
	
	private int id;
	private int carModelId;
	private String licensedId;
	private int status;
	private Date joinedAt;
	private Date visitedAt;
	private int userId;
	private String city;
	private Date createdAt;
	private Date updatedAt;
	private Date validAt;
	private String vin;
	private String engineNo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCarModelId() {
		return carModelId;
	}
	public void setCarModelId(int carModelId) {
		this.carModelId = carModelId;
	}
	public String getLicensedId() {
		return licensedId;
	}
	public void setLicensedId(String licensedId) {
		this.licensedId = licensedId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getJoinedAt() {
		return joinedAt;
	}
	public void setJoinedAt(Date joinedAt) {
		this.joinedAt = joinedAt;
	}
	public Date getVisitedAt() {
		return visitedAt;
	}
	public void setVisitedAt(Date visitedAt) {
		this.visitedAt = visitedAt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public Date getValidAt() {
		return validAt;
	}
	public void setValidAt(Date validAt) {
		this.validAt = validAt;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [id=");
		builder.append(id);
		builder.append(", carModelId=");
		builder.append(carModelId);
		builder.append(", licensedId=");
		builder.append(licensedId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", joinedAt=");
		builder.append(joinedAt);
		builder.append(", visitedAt=");
		builder.append(visitedAt);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", city=");
		builder.append(city);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append(", validAt=");
		builder.append(validAt);
		builder.append(", vin=");
		builder.append(vin);
		builder.append(", engineNo=");
		builder.append(engineNo);
		builder.append("]");
		return builder.toString();
	}
}
