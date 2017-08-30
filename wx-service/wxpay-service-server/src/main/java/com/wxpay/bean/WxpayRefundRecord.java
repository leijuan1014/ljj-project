package com.wxpay.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 微信支付退款记录。
 * 
 * @author zhy
 *
 */
public class WxpayRefundRecord implements Serializable {

	private static final long serialVersionUID = 3269972872713674198L;

	/** 微信支付订单号。必填 */
	private String wxpayId;
	/** 商户内部订单号。必填 */
	private String orderId;
	/** 微信支付退款单号。必填 */
	private String wxpayRefundId;
	/** 商户内部退款单号。必填 */
	private String refundId;
	/** 
	 * 退款渠道。
	 * ORIGINAL—原路退款
	 * BALANCE—退回到余额
	 */
	private String refundChannel;
	/** 退款金额，单位为元，小数点后两位。必填 */
	private BigDecimal refundFee;
	/** 代金券或立减优惠退款金额，单位为元，小数点后两位 */
	private BigDecimal couponRefundFee;
	/** 使用的代金券或立减优惠记录 */
	private List<WxpayCoupon> coupons;
	/** 
	 * 退款状态：
	 * SUCCESS—退款成功
	 * FAIL—退款失败
	 * PROCESSING—退款处理中
	 * NOTSURE—未确定，需要商户原退款单号重新发起
	 * CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
	 */
	private String refundStatus;
	/** 
	 * 当前退款单的退款入账方。
	 * 1）退回银行卡：
	 * {银行名称}{卡类型}{卡尾号}
	 * 2）退回支付用户零钱:
	 * 支付用户零钱
	 */
	private String refundReceiveAccount;
	
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
	public String getWxpayRefundId() {
		return wxpayRefundId;
	}
	public void setWxpayRefundId(String wxpayRefundId) {
		this.wxpayRefundId = wxpayRefundId;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	public String getRefundChannel() {
		return refundChannel;
	}
	public void setRefundChannel(String refundChannel) {
		this.refundChannel = refundChannel;
	}
	public BigDecimal getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}
	public BigDecimal getCouponRefundFee() {
		return couponRefundFee;
	}
	public void setCouponRefundFee(BigDecimal couponRefundFee) {
		this.couponRefundFee = couponRefundFee;
	}
	public List<WxpayCoupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<WxpayCoupon> coupons) {
		this.coupons = coupons;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getRefundReceiveAccount() {
		return refundReceiveAccount;
	}
	public void setRefundReceiveAccount(String refundReceiveAccount) {
		this.refundReceiveAccount = refundReceiveAccount;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayRefundRecord [wxpayId=").append(wxpayId).append(", orderId=").append(orderId)
				.append(", wxpayRefundId=").append(wxpayRefundId).append(", refundId=").append(refundId)
				.append(", refundChannel=").append(refundChannel).append(", refundFee=").append(refundFee)
				.append(", couponRefundFee=").append(couponRefundFee).append(", coupons=").append(coupons)
				.append(", refundStatus=").append(refundStatus).append(", refundReceiveAccount=")
				.append(refundReceiveAccount).append("]");
		return builder.toString();
	}
	
}
