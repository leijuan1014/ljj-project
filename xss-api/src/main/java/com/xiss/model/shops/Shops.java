package com.xiss.model.shops;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年4月10日 下午4:56:43 
*/
public class Shops implements Serializable{

	private static final long serialVersionUID = 4889043401588380867L;

	private int id;
	/** 手机号(用户id)*/
	private int userId;
	/** 名称*/
	private String name;
	/** 联系人*/
	private String linkman;
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
	private String duration;
	private int status;
	private String profile;
	private String services;
	private String saleContent;
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
	/** 详情图片*/
	private String detailImages;
	/** 服务项目：0=洗车、1=售卡*/
	private int serviceItem;
	/** 洗车-月补金额*/
	private double carWashMonthAmount;
	/** 洗车-基础洗车次数*/
	private int carWashMonthNum;
	/** 洗车-次补*/
	private double carWashPerAmount;
	/** 售卡-提成方式:0=按比例提成,1=按固定金额提成*/
	private int cardSaleType;
	/** 售卡-提成比例*/
	private double cardSaleScale;
	/** 售卡-提成金额*/
	private double cardSaleAmount;
	/** 提款周期:0=月提,1=周提*/
	private int drawMoneyPeriod;
	private Date createdAt;
	private Date updatedAt;
	public Shops() {
	}
	public Shops(int userId, String name, String phone, String province, String city, String county, int star, String category, int status, 
			String profile, String openning, String address, String longitude, String latitude, String image, String detailImages) {
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
		this.detailImages = detailImages;
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
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getSaleContent() {
		return saleContent;
	}
	public void setSaleContent(String saleContent) {
		this.saleContent = saleContent;
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
	public String getDetailImages() {
		return detailImages;
	}
	public void setDetailImages(String detailImages) {
		this.detailImages = detailImages;
	}
	public int getServiceItem() {
		return serviceItem;
	}
	public void setServiceItem(int serviceItem) {
		this.serviceItem = serviceItem;
	}
	public double getCarWashMonthAmount() {
		return carWashMonthAmount;
	}
	public void setCarWashMonthAmount(double carWashMonthAmount) {
		this.carWashMonthAmount = carWashMonthAmount;
	}
	public int getCarWashMonthNum() {
		return carWashMonthNum;
	}
	public void setCarWashMonthNum(int carWashMonthNum) {
		this.carWashMonthNum = carWashMonthNum;
	}
	public double getCarWashPerAmount() {
		return carWashPerAmount;
	}
	public void setCarWashPerAmount(double carWashPerAmount) {
		this.carWashPerAmount = carWashPerAmount;
	}
	public int getCardSaleType() {
		return cardSaleType;
	}
	public void setCardSaleType(int cardSaleType) {
		this.cardSaleType = cardSaleType;
	}
	public double getCardSaleScale() {
		return cardSaleScale;
	}
	public void setCardSaleScale(double cardSaleScale) {
		this.cardSaleScale = cardSaleScale;
	}
	public double getCardSaleAmount() {
		return cardSaleAmount;
	}
	public void setCardSaleAmount(double cardSaleAmount) {
		this.cardSaleAmount = cardSaleAmount;
	}
	public int getDrawMoneyPeriod() {
		return drawMoneyPeriod;
	}
	public void setDrawMoneyPeriod(int drawMoneyPeriod) {
		this.drawMoneyPeriod = drawMoneyPeriod;
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
		builder.append("Shops [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", linkman=");
		builder.append(linkman);
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
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", status=");
		builder.append(status);
		builder.append(", profile=");
		builder.append(profile);
		builder.append(", services=");
		builder.append(services);
		builder.append(", saleContent=");
		builder.append(saleContent);
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
		builder.append(", detailImages=");
		builder.append(detailImages);
		builder.append(", serviceItem=");
		builder.append(serviceItem);
		builder.append(", carWashMonthAmount=");
		builder.append(carWashMonthAmount);
		builder.append(", carWashMonthNum=");
		builder.append(carWashMonthNum);
		builder.append(", carWashPerAmount=");
		builder.append(carWashPerAmount);
		builder.append(", cardSaleType=");
		builder.append(cardSaleType);
		builder.append(", cardSaleScale=");
		builder.append(cardSaleScale);
		builder.append(", cardSaleAmount=");
		builder.append(cardSaleAmount);
		builder.append(", drawMoneyPeriod=");
		builder.append(drawMoneyPeriod);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}
