package com.alipay.server;

import java.io.Serializable;

public class AlipayAccount implements Serializable {

	private static final long serialVersionUID = -8207971041496900439L;

	/** 支付宝应用唯一标识 */
	private String appId;
	/** RSA私钥 */
	private String privateKey;
	/** RSA公钥 */
	private String publicKey;
	/** 即时到账公钥 */
	private String directPayPublicKey;
	/** 支付宝合作伙伴ID */
	private String partnerId;
	/** 支付方式：RSA、RSA2*/
	public String signType;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getDirectPayPublicKey() {
		return directPayPublicKey;
	}

	public void setDirectPayPublicKey(String directPayPublicKey) {
		this.directPayPublicKey = directPayPublicKey;
	}

	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayAccount [appId=");
		builder.append(appId);
		builder.append(", privateKey=");
		builder.append(privateKey);
		builder.append(", publicKey=");
		builder.append(publicKey);
		builder.append(", directPayPublicKey=");
		builder.append(directPayPublicKey);
		builder.append(", partnerId=");
		builder.append(partnerId);
		builder.append(", signType=");
		builder.append(signType);
		builder.append("]");
		return builder.toString();
	}
}
