package com.xiss.model.shops;

import java.io.Serializable;
import java.util.Date;

/** 
 * 售卡记录表
* @author leijj
* @since  2017年4月11日 下午3:58:17 
*/
public class CardDeals implements Serializable{

	private static final long serialVersionUID = 1722714208428596675L;
	private int id;
	/** 结算单id*/
	private int balanceId;
	/** 分销商id*/
	private int sellerId;
	/** 二级分销商id */
	private int secondSellerId;
	/** 客户id*/
	private int customerId;
	/** 卡号id*/
	private int cardId;
	/** 订单id*/
	private int suiteOrderId;
	private String tradeNo;
	/** 分销商提成比例*/
	private double commissionPortion;
	/** 二级分销商提成比例*/
	private double secondCommissionPortion;
	/** 分销商提成比例*/
	private double commission;
	/** 二级分销商提成比例*/
	private double secondCommission;
	
	private double totalCommission;
	
	private Date createdAt;
	private Date updatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(int balanceId) {
		this.balanceId = balanceId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getSecondSellerId() {
		return secondSellerId;
	}
	public void setSecondSellerId(int secondSellerId) {
		this.secondSellerId = secondSellerId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	public int getSuiteOrderId() {
		return suiteOrderId;
	}
	public void setSuiteOrderId(int suiteOrderId) {
		this.suiteOrderId = suiteOrderId;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public double getCommissionPortion() {
		return commissionPortion;
	}
	public void setCommissionPortion(double commissionPortion) {
		this.commissionPortion = commissionPortion;
	}
	public double getSecondCommissionPortion() {
		return secondCommissionPortion;
	}
	public void setSecondCommissionPortion(double secondCommissionPortion) {
		this.secondCommissionPortion = secondCommissionPortion;
	}
	
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public double getSecondCommission() {
		return secondCommission;
	}
	public void setSecondCommission(double secondCommission) {
		this.secondCommission = secondCommission;
	}
	public double getTotalCommission() {
		return totalCommission;
	}
	public void setTotalCommission(double totalCommission) {
		this.totalCommission = totalCommission;
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
		builder.append("CardDeals [id=");
		builder.append(id);
		builder.append(", balanceId=");
		builder.append(balanceId);
		builder.append(", sellerId=");
		builder.append(sellerId);
		builder.append(", secondSellerId=");
		builder.append(secondSellerId);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", cardId=");
		builder.append(cardId);
		builder.append(", suiteOrderId=");
		builder.append(suiteOrderId);
		builder.append(", tradeNo=");
		builder.append(tradeNo);
		builder.append(", commissionPortion=");
		builder.append(commissionPortion);
		builder.append(", secondCommissionPortion=");
		builder.append(secondCommissionPortion);
		builder.append(", commission=");
		builder.append(commission);
		builder.append(", secondCommission=");
		builder.append(secondCommission);
		builder.append(", totalCommission=");
		builder.append(totalCommission);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}