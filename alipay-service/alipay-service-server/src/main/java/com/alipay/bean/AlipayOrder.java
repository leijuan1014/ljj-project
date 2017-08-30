package com.alipay.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 本地保存的支付宝支付订单。
 * 
 * @author zhy
 *
 */
public class AlipayOrder implements Serializable {

	private static final long serialVersionUID = 4205853277556943868L;

	/** 支付宝交易号 */
	private String alipayId;
	/** 商户内部订单号 */
	private String orderId;
	/** 支付宝分配给开发者的应用ID */
	private String appId;
	/** 买家支付宝账号 */
	private String buyerLogonId;
	/** 买家在支付宝的用户ID */
	private String buyerUserId;
	/**
	 * 交易状态。
	 * <ul>
	 * <li>WAIT_BUYER_PAY（交易创建，等待买家付款）</li>
	 * <li>TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）</li>
	 * <li>TRADE_SUCCESS（交易支付成功）</li>
	 * <li>TRADE_FINISHED（交易结束，不可退款）</li>
	 * </ul>
	 */
	private String status;
	/** 交易的订单金额，单位元，小数点后两位 */
	private BigDecimal totalAmount;
	/** 实收金额，单位元，小数点后两位 */
	private BigDecimal receiptAmount;
	/** 买家实付金额，单位元，小数点后两位 */
	private BigDecimal buyerPayAmount;
	/** 积分支付的金额，单位元，小数点后两位 */
	private BigDecimal pointAmount;
	/** 交易中用户支付的可开具发票的金额，单位元，小数点后两位 */
	private BigDecimal invoiceAmount;
	/** 本次交易打款给买家的时间 */
	private Date payTime;
	/** 支付宝店铺编号 */
	private String alipayStoreId;
	/** 商户门店编号 */
	private String storeId;
	/** 商户机具终端编号 */
	private String terminalId;
	/** 交易支付使用的资金渠道列表 */
	private List<AlipayFundBill> bills;
	/** 请求支付交易的商户店铺的名称 */
	private String storeName;
	/** 本次交易支付所使用的单品券优惠的商品优惠信息，JSON格式，不解析 */
	private String discountGoodsDetail;
	/** 行业特殊信息（例如在医保卡支付业务中，向用户返回医疗信息），JSON格式，不解析 */
	private String industrySpecDetail;
	
	/** 创建时间。只读 */
	private Date creationTime;
	/** 最后修改时间。只读 */
	private Date modifiedTime;
	
	public String getAlipayId() {
		return alipayId;
	}
	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getBuyerLogonId() {
		return buyerLogonId;
	}
	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}
	public String getBuyerUserId() {
		return buyerUserId;
	}
	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public BigDecimal getBuyerPayAmount() {
		return buyerPayAmount;
	}
	public void setBuyerPayAmount(BigDecimal buyerPayAmount) {
		this.buyerPayAmount = buyerPayAmount;
	}
	public BigDecimal getPointAmount() {
		return pointAmount;
	}
	public void setPointAmount(BigDecimal pointAmount) {
		this.pointAmount = pointAmount;
	}
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getAlipayStoreId() {
		return alipayStoreId;
	}
	public void setAlipayStoreId(String alipayStoreId) {
		this.alipayStoreId = alipayStoreId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public List<AlipayFundBill> getBills() {
		return bills;
	}
	public void setBills(List<AlipayFundBill> bills) {
		this.bills = bills;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getDiscountGoodsDetail() {
		return discountGoodsDetail;
	}
	public void setDiscountGoodsDetail(String discountGoodsDetail) {
		this.discountGoodsDetail = discountGoodsDetail;
	}
	public String getIndustrySpecDetail() {
		return industrySpecDetail;
	}
	public void setIndustrySpecDetail(String industrySpecDetail) {
		this.industrySpecDetail = industrySpecDetail;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayOrder [alipayId=").append(alipayId).append(", orderId=").append(orderId)
				.append(", appId=").append(appId).append(", buyerLogonId=").append(buyerLogonId)
				.append(", buyerUserId=").append(buyerUserId).append(", status=").append(status)
				.append(", totalAmount=").append(totalAmount).append(", receiptAmount=").append(receiptAmount)
				.append(", buyerPayAmount=").append(buyerPayAmount).append(", pointAmount=").append(pointAmount)
				.append(", invoiceAmount=").append(invoiceAmount).append(", payTime=").append(payTime)
				.append(", alipayStoreId=").append(alipayStoreId).append(", storeId=").append(storeId)
				.append(", terminalId=").append(terminalId).append(", bills=").append(bills).append(", storeName=")
				.append(storeName).append(", discountGoodsDetail=").append(discountGoodsDetail)
				.append(", industrySpecDetail=").append(industrySpecDetail).append(", creationTime=")
				.append(creationTime).append(", modifiedTime=").append(modifiedTime).append("]");
		return builder.toString();
	}
	
}
