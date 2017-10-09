package com.xiss.model.wxpay.enums;
/** 
 * 交易状态。必填
* @author leijj
* @since  2017年7月27日 上午11:22:15 
*/
public enum TradeStateEnum {
	 SUCCESS,//支付成功
	 REFUND,//转入退款
	 NOTPAY,//未支付
	 CLOSED,//已关闭
	 REVOKED,//已撤销（刷卡支付）
	 USERPAYING,//用户支付中
	 PAYERROR;//支付失败(其他原因，如银行返回失败)
}
