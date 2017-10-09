package com.xiss.model.balances;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年4月19日 上午10:52:14 
*/
public class Balances implements Serializable{

	private static final long serialVersionUID = 5830844284745858962L;

	private int id;
	
	private int userId;
	
	private double money;
	
	private double cardSaleMoney;
	
	private double suiteSaleMoney;
	
	private int status;
	/** 电子回单*/
	private String receiptImage;
	/** 申请时间*/
	private Date applyAt;
	/** 结算时间*/
	private Date finishedAt;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private Date rangeStart;
	
	private Date rangeEnd;
	
	/** 项目：0=售卡提成，1=套餐销售，2=洗车明细*/
	//private int itemType;

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

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReceiptImage() {
		return receiptImage;
	}

	public void setReceiptImage(String receiptImage) {
		this.receiptImage = receiptImage;
	}

	public Date getApplyAt() {
		return applyAt;
	}

	public void setApplyAt(Date applyAt) {
		this.applyAt = applyAt;
	}

	public Date getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
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

	public Date getRangeStart() {
		return rangeStart;
	}

	public void setRangeStart(Date rangeStart) {
		this.rangeStart = rangeStart;
	}

	public Date getRangeEnd() {
		return rangeEnd;
	}

	public void setRangeEnd(Date rangeEnd) {
		this.rangeEnd = rangeEnd;
	}

	public double getCardSaleMoney() {
		return cardSaleMoney;
	}

	public void setCardSaleMoney(double cardSaleMoney) {
		this.cardSaleMoney = cardSaleMoney;
	}

	public double getSuiteSaleMoney() {
		return suiteSaleMoney;
	}

	public void setSuiteSaleMoney(double suiteSaleMoney) {
		this.suiteSaleMoney = suiteSaleMoney;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Balances [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", money=");
		builder.append(money);
		builder.append(", status=");
		builder.append(status);
		builder.append(", receiptImage=");
		builder.append(receiptImage);
		builder.append(", applyAt=");
		builder.append(applyAt);
		builder.append(", finishedAt=");
		builder.append(finishedAt);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append(", rangeStart=");
		builder.append(rangeStart);
		builder.append(", rangeEnd=");
		builder.append(rangeEnd);
		builder.append(", cardSaleMoney=");
		builder.append(cardSaleMoney);
		builder.append(", suiteSaleMoney=");
		builder.append(suiteSaleMoney);
		builder.append("]");
		return builder.toString();
	}
}