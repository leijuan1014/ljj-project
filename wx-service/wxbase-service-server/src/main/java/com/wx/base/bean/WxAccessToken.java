package com.wx.base.bean;

import java.io.Serializable;
import java.util.Date;

public class WxAccessToken implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 访问令牌 */
	private String accessToken;
	/** 访问令牌有效时长，单位秒 */
	private int expiresIn;
	/** 访问令牌到期时间 */
	private transient Date expiresAt;
	
	/* 下面的字段在网页授权成功后可获得 */
	
	/** 访问令牌的刷新令牌 */
	private String refreshToken;
	/** 授权用户的OpenId */
	private String openId;
	/** 授权范围 */
	private String scope;
	/** 授权用户的UnionId。只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 */
	private String unionId;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Date getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxAccessToken [accessToken=").append(accessToken)
				.append(", expiresIn=").append(expiresIn)
				.append(", refreshToken=").append(refreshToken)
				.append(", openId=").append(openId).append(", scope=")
				.append(scope).append(", unionId=").append(unionId).append("]");
		return builder.toString();
	}
	
}
