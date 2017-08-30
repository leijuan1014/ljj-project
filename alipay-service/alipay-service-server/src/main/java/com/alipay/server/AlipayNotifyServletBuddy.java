package com.alipay.server;

public class AlipayNotifyServletBuddy {
	
	public static AlipayNotifyServletBuddy INSTANCE = new AlipayNotifyServletBuddy();
	
	private AlipayAccount account;
	public AlipayAccount getAccount() {
		return account;
	}
	public void setAccount(AlipayAccount account) {
		this.account = account;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayNotifyServletBuddy [account=").append(account).append("]");
		return builder.toString();
	}
	
}
