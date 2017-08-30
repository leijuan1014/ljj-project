package com.xiss.model.shops;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年4月10日 下午4:56:43 
*/
public class UnionShop implements Serializable{

	private static final long serialVersionUID = 4889043401588380867L;

	private int id;
	/** 手机号(用户id)*/
	private int userId;
	/** 名称*/
	private String name;
	/** 联系电话*/
	private String phone;
	/** 地区-省*/
	private String province;
	/** 地区-市*/
	private String city;
	/** 地区-区*/
	private String county;
	/** 星级*/
	private int star;
	private String category;
	private int status;
	private String profile;
	private String openning;
	/** 地址*/
	private String address;
	/** 坐标*/
	private String position;
	/** 经度*/
	private String longitude;
	/** 纬度*/
	private String latitude;
	/** 门头照片*/
	private String image;
	private String originImage;
	private Date createdAt;
	private Date updatedAt;
	private String distance;
	public UnionShop() {
	}
	public UnionShop(int userId, String name, String phone, String province, String city, String county, int star, String category, int status, 
			String profile, String openning, String address, String longitude, String latitude, String image, String originImage) {
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.county = county;
		this.star = star;
		this.category = category;
		this.status = status;
		this.profile = profile;
		this.openning = openning;
		this.address  = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.image = image;
		this.originImage = originImage;
		this.position = "{".concat(latitude).concat(",").concat(longitude).concat("}");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getOpenning() {
		return openning;
	}
	public void setOpenning(String openning) {
		this.openning = openning;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOriginImage() {
		return originImage;
	}
	public void setOriginImage(String originImage) {
		this.originImage = originImage;
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
	public String getDistance() {
		return distance == null ? "" : distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnionShop [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append(", county=");
		builder.append(county);
		builder.append(", star=");
		builder.append(star);
		builder.append(", category=");
		builder.append(category);
		builder.append(", status=");
		builder.append(status);
		builder.append(", profile=");
		builder.append(profile);
		builder.append(", openning=");
		builder.append(openning);
		builder.append(", address=");
		builder.append(address);
		builder.append(", position=");
		builder.append(position);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", image=");
		builder.append(image);
		builder.append(", originImage=");
		builder.append(originImage);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append(", distance=");
		builder.append(distance);
		builder.append("]");
		return builder.toString();
	}
}