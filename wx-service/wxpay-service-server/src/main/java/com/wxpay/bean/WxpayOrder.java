package com.wxpay.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 本地保存的微信支付订单。
 * 
 * @author leijj
 *
 */
public class WxpayOrder implements Serializable {

	private static final long serialVersionUID = 5216545420863103228L;
	private int id;
	/** 微信支付订单号 */
	private String wxpayId;
	/** 微信开放平台审核通过的商户应用ID */
	private String appId;
	/** 微信用户在商户应用ID下的唯一标识 */
	private String openId;
	/** 用户是否关注公众账号。仅在公众账号类型支付有效 */
	private boolean subscribed;
	/** 商户订单号 */
	private String orderId;
	/** 微信支付分配的终端设备号 */
	private String deviceInfo;
	/** 调用接口提交的交易类型 */
	private String tradeType;
	/**
	 * 交易状态。必填
	 * SUCCESS—支付成功
	 * REFUND—转入退款
	 * NOTPAY—未支付
	 * CLOSED—已关闭
	 * REVOKED—已撤销（刷卡支付）
	 * USERPAYING--用户支付中
	 * PAYERROR--支付失败(其他原因，如银行返回失败)
	 */
	private String tradeState;
	/** 银行类型，采用字符串类型的银行标识，如CMC */
	private String bankType;
	/** 订单总金额，单位为元，小数点后两位 */
	private BigDecimal totalFee;
	/** 货币类型，默认人民币：CNY。 */
	private String feeType;
	/** 现金支付金额，单位元，小数点后两位 */
	private BigDecimal cashFee;
	/** 现金支付货币类型，默认人民币：CNY。 */
	private String cashFeeType;
	/** 代金券或立减优惠金额，单位元，小数点后两位 */
	private BigDecimal couponFee;
	/** 代金券或立减优惠列表 */
	//private List<WxpayCoupon> coupons;
	/** 附加数据 */
	private String attach;
	/** 支付完成时间 */
	private Date endTime;
	/** 交易状态描述 */
	private String description;
	/** 记录创建时间。只读 */
	private Date createdAt;
	/** 记录最后修改时间。只读 */
	private Date updateAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWxpayId() {
		return wxpayId;
	}
	public void setWxpayId(String wxpayId) {
		this.wxpayId = wxpayId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public boolean isSubscribed() {
		return subscribed;
	}
	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeState() {
		return tradeState;
	}
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public BigDecimal getCashFee() {
		return cashFee;
	}
	public void setCashFee(BigDecimal cashFee) {
		this.cashFee = cashFee;
	}
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	public BigDecimal getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(BigDecimal couponFee) {
		this.couponFee = couponFee;
	}
	/*
	public List<WxpayCoupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<WxpayCoupon> coupons) {
		this.coupons = coupons;
	}
	*/
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setModifiedTime(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayOrder [id=").append(id).append(", wxpayId=").append(wxpayId).append(", appId=").append(appId).append(", openId=")
				.append(openId).append(", subscribed=").append(subscribed).append(", orderId=").append(orderId)
				.append(", deviceInfo=").append(deviceInfo).append(", tradeType=").append(tradeType)
				.append(", tradeState=").append(tradeState).append(", bankType=").append(bankType).append(", totalFee=")
				.append(totalFee).append(", feeType=").append(feeType).append(", cashFee=").append(cashFee)
				.append(", cashFeeType=").append(cashFeeType).append(", couponFee=").append(couponFee)
				//.append(", coupons=").append(coupons)
				.append(", attach=").append(attach).append(", endTime=")
				.append(endTime).append(", description=").append(description).append(", createdAt=")
				.append(createdAt).append(", updateAt=").append(updateAt).append("]");
		return builder.toString();
	}

}
