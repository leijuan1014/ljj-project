package com.wx.base.bean;

import java.io.Serializable;

public class WxFailure implements Serializable {

	private static final long serialVersionUID = -6888729106434539400L;
	
	/** 返回码 */
	private int code;
	/** 返回消息 */
	private String msg;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxFailure [code=").append(code).append(", msg=")
				.append(msg).append("]");
		return builder.toString();
	}

}
