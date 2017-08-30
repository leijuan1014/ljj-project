package com.alipay.bean;

/**
 * 支付宝支付资金渠道码。
 * 
 * @author zhy
 *
 */
public enum FundChannel {
	COUPON("支付宝红包"),
	ALIPAYACCOUNT("支付宝余额"),
	POINT("集分宝"),
	DISCOUNT("折扣券"),
	PCARD("预付卡"),
	MCARD("商家储值卡"),
	MDISCOUNT("商户优惠券"),
	MCOUPON("商户红包"),
	PCREDIT("蚂蚁花呗");
	
	private final String description;
	
	FundChannel(String description) {
		this.description = description;
	}

	public String description() {
		return description;
	}
	
	/**
	 * 资金渠道码转成文字描述。如果遇到不认识的资金渠道码，直接返回资金渠道码。
	 * 
	 * @param name 资金渠道码，如COUPON
	 * @return 资金渠道描述
	 */
	public static String describe(String name) {
		if (name == null || name.isEmpty())
			return "";
		try {
			return FundChannel.valueOf(name).description();
		} catch (Exception e) {
			return name;
		}
	}
}
