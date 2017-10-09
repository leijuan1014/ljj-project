package com.xiss.model.order.enums;

/**
 * 订单状态
 * 
 * by zhaochen on 2016-09-13
 */
public enum OrderState {
	/** 支付中。在发起支付时进入此状态 */
	CREATED,
	/** 已过期。逾期未支付进入此状态 */
	CANCELED,
	/** 支付失败 */
	FAILED,
	/** 已支付待验证。在支付完成后进入此状态 */
	SUCCESS,
	/** 已支付已验证*/
	VERIFIED;
}
