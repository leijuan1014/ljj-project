package com.xiss.model.wxpay;

import java.io.Serializable;

/**
 * 微信支付商户账号信息。
 * 
 * @author zhy
 *
 */
public class WxpayAccount implements Serializable {

	private static final long serialVersionUID = 8474779372178212080L;

	/** 微信开放平台审核通过的应用APPID。必填 */
	private String appId;
	/** 用于获取access token的接口密码。在微信支付相关接口中不需要 */
	private String appSecret;
	/** 微信支付分配的商户号。必填 */
	private String merchantId;
	/** 交易过程生成签名的密钥。必填 */
	private String key;
	/** 商户证书地址 */
	private String certPath;
	/** 支付通知地址 */
	private String notifyUrl;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCertPath() {
		return certPath;
	}
	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayAccount [appId=").append(appId).append(", appSecret=").append(appSecret)
				.append(", merchantId=").append(merchantId).append(", key=").append(key).append(", certPath=")
				.append(certPath).append(", notifyUrl=").append(notifyUrl).append("]");
		return builder.toString();
	}

}
