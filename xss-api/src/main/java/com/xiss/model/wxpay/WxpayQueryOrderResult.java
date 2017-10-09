package com.xiss.model.wxpay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class WxpayQueryOrderResult implements Serializable {

	private static final long serialVersionUID = -1199016602604174178L;

	/** 微信支付分配的终端设备号 */
	private String deviceInfo;
	/** 用户在商户appid下的唯一标识。必填 */
	private String openId;
	/** 用户是否关注公众账号。仅在公众账号类型支付有效 */
	private boolean subscribed;
	/** 调用接口提交的交易类型。必填 */
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
	/** 银行类型，采用字符串类型的银行标识，如CMC。必填 */
	private String bankType;
	/** 订单总金额，单位为元，小数点后两位。必填 */
	private BigDecimal totalFee;
	/** 货币类型，默认人民币：CNY。 */
	private String feeType;
	/** 现金支付金额，单位元，小数点后两位。必填 */
	private BigDecimal cashFee;
	/** 现金支付货币类型，默认人民币：CNY。 */
	private String cashFeeType;
	/** 代金券或立减优惠金额，单位元，小数点后两位 */
	private BigDecimal couponFee;
	/** 代金券或立减优惠列表 */
	private List<WxpayCoupon> coupons;
	/** 微信支付订单号。必填 */
	private String wxpayId;
	/** 商户订单号。必填 */
	private String orderId;
	/** 附加数据，原样返回 */
	private String attach;
	/** 支付完成时间。必填 */
	private Date endTime;
	/** 交易状态描述。必填 */
	private String description;
	
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
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
	public List<WxpayCoupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<WxpayCoupon> coupons) {
		this.coupons = coupons;
	}
	public String getWxpayId() {
		return wxpayId;
	}
	public void setWxpayId(String wxpayId) {
		this.wxpayId = wxpayId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayQueryOrderResult [deviceInfo=").append(deviceInfo).append(", openId=").append(openId)
				.append(", subscribed=").append(subscribed).append(", tradeType=").append(tradeType)
				.append(", tradeState=").append(tradeState).append(", bankType=").append(bankType).append(", totalFee=")
				.append(totalFee).append(", feeType=").append(feeType).append(", cashFee=").append(cashFee)
				.append(", cashFeeType=").append(cashFeeType).append(", couponFee=").append(couponFee)
				.append(", coupons=").append(coupons).append(", wxpayId=").append(wxpayId).append(", orderId=")
				.append(orderId).append(", attach=").append(attach).append(", endTime=").append(endTime)
				.append(", description=").append(description).append("]");
		return builder.toString();
	}
	
	/**
	 * 转成WxpayOrder对象。
	 * 
	 * @return
	 */
	public WxpayOrder toWxpayOrder() {
		WxpayOrder order = new WxpayOrder();
		order.setWxpayId(wxpayId);
		order.setOpenId(openId);
		order.setSubscribed(subscribed);
		order.setOrderId(orderId);
		order.setDeviceInfo(deviceInfo);
		order.setTradeType(tradeType);
		order.setTradeState(tradeState);
		order.setBankType(bankType);
		order.setTotalFee(totalFee);
		order.setFeeType(feeType);
		order.setCashFee(cashFee);
		order.setCashFeeType(cashFeeType);
		order.setCouponFee(couponFee);
		//order.setCoupons(coupons);
		order.setAttach(attach);
		order.setEndTime(endTime);
		order.setDescription(description);
		return order;
	}
	
}
