package com.xiss.model.wxpay;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信支付代金券或立减优惠信息。
 * 
 * @author zhy
 *
 */
public class WxpayCoupon implements Serializable {

	private static final long serialVersionUID = 6404417219817363837L;

	/** 代金券或立减优惠批次ID */
	private String batchId;
	/** 代金券或立减优惠ID */
	private String id;
	/** 单个代金券或立减优惠支付金额，单位元，小数点后两位 */
	private BigDecimal fee;
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayCoupon [batchId=").append(batchId).append(", id=").append(id).append(", fee=").append(fee)
				.append("]");
		return builder.toString();
	}
	
}
