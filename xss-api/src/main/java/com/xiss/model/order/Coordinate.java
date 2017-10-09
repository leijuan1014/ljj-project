package com.xiss.model.order;

import java.io.Serializable;

/**
 * 经纬度坐标及距离（千米）。
 * 
 * @author zhy
 *
 */
public class Coordinate implements Serializable {

	private static final long serialVersionUID = -2255539407227273387L;
	
	/** 经度 */
	private double longitude;
	/** 纬度 */
	private double latitude;
	
	public Coordinate longitude(double longitude) {
		this.longitude = longitude;
		return this;
	}
	
	public Coordinate latitude(double latitude) {
		this.latitude = latitude;
		return this;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coordinate [longitude=").append(longitude)
				.append(", latitude=").append(latitude).append("]");
		return builder.toString();
	}
	

}
