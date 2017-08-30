package com.wx.base.bean;

import java.io.Serializable;
import java.util.Date;

public class WxTicket implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ticket;
	private int expiresIn;
	private Date expiresAt;
	
	@Override
	public String toString() {
		return "WxTicket [ticket=" + ticket + ", expiresIn=" + expiresIn
				+ ", expiresAt=" + expiresAt + "]";
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
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
}
