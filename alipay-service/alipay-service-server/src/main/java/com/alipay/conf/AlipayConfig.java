package com.alipay.conf;

public class AlipayConfig {
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	public static String SIGNTYPE = "RSA2";
	public static String PAY_URL = "https://openapi.alipay.com/gateway.do";
	public static String TIMEOUT_EXPRESS = "90m";
	public static String WAP_PRODUCT_CODE="QUICK_WAP_PAY";
	public static String PAGE_PRODUCT_CODE="FAST_INSTANT_TRADE_PAY";
}
