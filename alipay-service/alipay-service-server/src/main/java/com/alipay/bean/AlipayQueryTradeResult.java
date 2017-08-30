package com.alipay.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AlipayQueryTradeResult implements Serializable {

	private static final long serialVersionUID = -5469504905365648666L;

	/** （必填）支付宝交易号 */
	private String alipayId;
	/** （必填）商户内部订单号 */
	private String orderId;
	/** （必填）买家支付宝账号 */
	private String buyerLogonId;
	/** （必填）买家在支付宝的用户ID */
	private String buyerUserId;
	/**
	 * （必填）交易状态。
	 * <ul>
	 * <li>WAIT_BUYER_PAY（交易创建，等待买家付款）</li>
	 * <li>TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）</li>
	 * <li>TRADE_SUCCESS（交易支付成功）</li>
	 * <li>TRADE_FINISHED（交易结束，不可退款）</li>
	 * </ul>
	 */
	private String status;
	/** （必填）交易的订单金额，单位元，小数点后两位 */
	private BigDecimal totalAmount;
	/** （必填）实收金额，单位元，小数点后两位 */
	private BigDecimal receiptAmount;
	/** 买家实付金额，单位元，小数点后两位 */
	private BigDecimal buyerPayAmount;
	/** 积分支付的金额，单位元，小数点后两位 */
	private BigDecimal pointAmount;
	/** 交易中用户支付的可开具发票的金额，单位元，小数点后两位 */
	private BigDecimal invoiceAmount;
	/** （必填）本次交易打款给买家的时间 */
	private Date payTime;
	/** 支付宝店铺编号 */
	private String alipayStoreId;
	/** 商户门店编号 */
	private String storeId;
	/** 商户机具终端编号 */
	private String terminalId;
	/** （必填）交易支付使用的资金渠道列表 */
	private List<AlipayFundBill> bills;
	/** 请求支付交易的商户店铺的名称 */
	private String storeName;
	/** 本次交易支付所使用的单品券优惠的商品优惠信息，JSON格式，不解析 */
	private String discountGoodsDetail;
	/** 行业特殊信息（例如在医保卡支付业务中，向用户返回医疗信息），JSON格式，不解析 */
	private String industrySpecDetail;
	
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayQueryTradeResult [alipayId=").append(alipayId).append(", orderId=").append(orderId)
				.append(", buyerLogonId=").append(buyerLogonId).append(", buyerUserId=").append(buyerUserId)
				.append(", status=").append(status).append(", totalAmount=").append(totalAmount)
				.append(", receiptAmount=").append(receiptAmount).append(", buyerPayAmount=").append(buyerPayAmount)
				.append(", pointAmount=").append(pointAmount).append(", invoiceAmount=").append(invoiceAmount)
				.append(", payTime=").append(payTime).append(", alipayStoreId=").append(alipayStoreId)
				.append(", storeId=").append(storeId).append(", terminalId=").append(terminalId).append(", bills=")
				.append(bills).append(", storeName=").append(storeName).append(", discountGoodsDetail=")
				.append(discountGoodsDetail).append(", industrySpecDetail=").append(industrySpecDetail).append("]");
		return builder.toString();
	}
	
	/**
	 * 转换成AlipayOrder对象。
	 * 
	 * @return
	 */
	public AlipayOrder toAlipayOrder() {
		AlipayOrder order = new AlipayOrder();
		order.setAlipayId(alipayId);
		order.setOrderId(orderId);
		order.setBuyerUserId(buyerUserId);
		order.setBuyerLogonId(buyerLogonId);
		order.setStatus(status);
		order.setTotalAmount(totalAmount);
		order.setReceiptAmount(receiptAmount);
		order.setBuyerPayAmount(buyerPayAmount);
		order.setPointAmount(pointAmount);
		order.setInvoiceAmount(invoiceAmount);
		order.setPayTime(payTime);
		order.setAlipayStoreId(alipayStoreId);
		order.setStoreId(storeId);
		order.setTerminalId(terminalId);
		order.setStoreName(storeName);
		order.setBills(bills);
		order.setDiscountGoodsDetail(discountGoodsDetail);
		order.setIndustrySpecDetail(industrySpecDetail);
		return order;
	}
}
