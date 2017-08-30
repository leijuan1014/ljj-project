package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 吱口令解码接口
 *
 * @author auto create
 * @since 1.0, 2016-12-22 21:53:37
 */
public class AlipayMarketingSharetokenDecodeModel extends AlipayObject {

	private static final long serialVersionUID = 4464847339365496564L;

	/**
	 * 码类型，可空，默认为吱口令类型『share_code』
	 */
	@ApiField("code_type")
	private String codeType;

	/**
	 * 扩展属性，key-value json串
	 */
	@ApiField("ext_data")
	private String extData;

	/**
	 * 8位吱口令token
	 */
	@ApiField("token")
	private String token;

	public String getCodeType() {
		return this.codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getExtData() {
		return this.extData;
	}
	public void setExtData(String extData) {
		this.extData = extData;
	}

	public String getToken() {
		return this.token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
