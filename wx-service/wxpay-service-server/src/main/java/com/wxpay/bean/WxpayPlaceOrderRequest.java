package com.wxpay.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * */
public class WxpayPlaceOrderRequest implements Serializable {
//wxpay_place_order_request
	private static final long serialVersionUID = -2682945626171747905L;

	/** 终端设备号（门店号或收银设备ID） */
	private String deviceInfo;
	/** 
	 * 商品描述。必填。
	 * 商品描述交易字段格式根据不同的应用场景按照以下格式：
	 * APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
	 */
	private String body;
	/** 商品详情，订单商品列表 */
	//private List<WxpayOrderItem> items;
	/** 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 */
	private String attach;
	/** 商户订单号。必填，最多32字 */
	private String orderTradeNo;
	/** 货币类型。符合ISO 4217标准的三位字母代码，默认人民币：CNY */
	private String feeType;
	/** 订单总金额，单位为元，小数点后两位。必填 */
	private int totalFee;
	/** 用户终端实际IP。必填，最多32字 */
	private String clientIp;
	/** 交易起始时间 */
	private Date startTime;
	/** 订单失效时间。最短失效时间间隔必须大于5分钟 */
	private Date dueTime;
	/** 商品标记，代金券或立减优惠功能的参数 */
	private String tag;
	/** 支付类型，如"APP"。必填 */
	private String tradeType;
	
	private String openid;
	
	/** 指定支付方式。如"no_credit"--指定不能使用信用卡支付 */
	private String limitPay;
	
	public WxpayPlaceOrderRequest deviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
		return this;
	}
	
	public WxpayPlaceOrderRequest body(String body) {
		this.body = body;
		return this;
	}
	
	public WxpayPlaceOrderRequest item(WxpayOrderItem item) {
		if (item == null)
			throw new IllegalArgumentException("order item cannot be null");
		/*if (items == null)
			items = new LinkedList<WxpayOrderItem>();
		items.add(item);*/
		return this;
	}
	
	public WxpayPlaceOrderRequest attach(String attach) {
		this.attach = attach;
		return this;
	}
	
	public WxpayPlaceOrderRequest orderTradeNo(String orderTradeNo) {
		this.orderTradeNo = orderTradeNo;
		return this;
	}
	
	public WxpayPlaceOrderRequest feeType(String feeType) {
		this.feeType = feeType;
		return this;
	}
	
	public WxpayPlaceOrderRequest totalFee(int totalFee) {
		this.totalFee = totalFee;
		return this;
	}
	
	public WxpayPlaceOrderRequest clientIp(String clientIp) {
		this.clientIp = clientIp;
		return this;
	}
	
	public WxpayPlaceOrderRequest startTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}
	
	public WxpayPlaceOrderRequest dueTime(Date dueTime) {
		this.dueTime = dueTime;
		return this;
	}
	
	public WxpayPlaceOrderRequest tag(String tag) {
		this.tag = tag;
		return this;
	}
	
	public WxpayPlaceOrderRequest tradeType(String tradeType) {
		this.tradeType = tradeType;
		return this;
	}
	public WxpayPlaceOrderRequest openid(String openid) {
		this.openid = openid;
		return this;
	}
	public WxpayPlaceOrderRequest limitPay(String limitPay) {
		this.limitPay = limitPay;
		return this;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
/*
	public List<WxpayOrderItem> getItems() {
		return items;
	}

	public void setItems(List<WxpayOrderItem> items) {
		this.items = items;
	}
*/
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOrderTradeNo() {
		return orderTradeNo;
	}

	public void setOrderTradeNo(String orderTradeNo) {
		this.orderTradeNo = orderTradeNo;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayPlaceOrderRequest [deviceInfo=").append(deviceInfo).append(", body=").append(body)
				//.append(", items=").append(items)
				.append(", attach=").append(attach).append(", orderTradeNo=")
				.append(orderTradeNo).append(", feeType=").append(feeType).append(", totalFee=").append(totalFee)
				.append(", clientIp=").append(clientIp).append(", startTime=").append(startTime).append(", dueTime=")
				.append(dueTime).append(", tag=").append(tag).append(", tradeType=").append(tradeType)
				.append(", limitPay=").append(limitPay).append("]");
		return builder.toString();
	}

}
