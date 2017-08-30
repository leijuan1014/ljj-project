package com.xiss.model.system;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年6月14日 上午10:04:41 
*/
public class CarModel implements Serializable{
	private static final long serialVersionUID = -7512146148718140122L;
	
	private int id;
	private int carBrandId;
	private String cnName;
	private String enName;
	private String initialLetter;
	private Date createdAt;
	private Date updatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCarBrandId() {
		return carBrandId;
	}
	public void setCarBrandId(int carBrandId) {
		this.carBrandId = carBrandId;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getInitialLetter() {
		return initialLetter;
	}
	public void setInitialLetter(String initialLetter) {
		this.initialLetter = initialLetter;
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
		builder.append("CarModel [id=");
		builder.append(id);
		builder.append(", carBrandId=");
		builder.append(carBrandId);
		builder.append(", cnName=");
		builder.append(cnName);
		builder.append(", enName=");
		builder.append(enName);
		builder.append(", initialLetter=");
		builder.append(initialLetter);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}