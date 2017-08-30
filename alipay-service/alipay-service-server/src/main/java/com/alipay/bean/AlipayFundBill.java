package com.alipay.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付宝交易中支付使用的资金渠道。
 * 
 * @author zhy
 *
 */
public class AlipayFundBill implements Serializable {

	private static final long serialVersionUID = 125961802380442904L;
	
	/** （必填）资金渠道码 */
	private String channel;
	/** 该支付工具类型所使用的金额 */
	private BigDecimal amount;
	/** 渠道实际付款金额，单位元，小数点后两位 */
	private BigDecimal realAmount;
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayFundBill [channel=").append(channel).append(", amount=").append(amount)
				.append(", realAmount=").append(realAmount).append("]");
		return builder.toString();
	}

}
