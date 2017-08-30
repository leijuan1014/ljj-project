package com.xiss.model.system;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年4月10日 下午12:41:59 
*/
public class Users implements Serializable{
	
	private static final long serialVersionUID = 6110379763449785830L;
	
	private int id;
	private String email;
	private String encryptedPassword;
	private String resetPasswordToken;
	private Date resetPasswordSentAt;
	private Date rememberCreatedAt;
	private int signInCount;
	private Date currentSignInAt;
	private Date lastSignInAt;
	private String currentSignInIp;
	private String lastSignInIp;
	private Date createdAt;
	private Date updatedAt;
	private String name;
	private String mobile;
	private String accessToken;
	private String pin;
	private boolean verified;
	private String authenticationToken;
	private int roles;
	private String invitationToken;
	private int invitedBy;
	private String avatar;
	public Users() {
	}
	public Users(String mobile, String email, int roles) {
		this.mobile = mobile;
		this.email = email;
		this.roles = roles;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
	public Date getResetPasswordSentAt() {
		return resetPasswordSentAt;
	}
	public void setResetPasswordSentAt(Date resetPasswordSentAt) {
		this.resetPasswordSentAt = resetPasswordSentAt;
	}
	public Date getRememberCreatedAt() {
		return rememberCreatedAt;
	}
	public void setRememberCreatedAt(Date rememberCreatedAt) {
		this.rememberCreatedAt = rememberCreatedAt;
	}
	public int getSignInCount() {
		return signInCount;
	}
	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}
	public Date getCurrentSignInAt() {
		return currentSignInAt;
	}
	public void setCurrentSignInAt(Date currentSignInAt) {
		this.currentSignInAt = currentSignInAt;
	}
	public Date getLastSignInAt() {
		return lastSignInAt;
	}
	public void setLastSignInAt(Date lastSignInAt) {
		this.lastSignInAt = lastSignInAt;
	}
	public String getCurrentSignInIp() {
		return currentSignInIp;
	}
	public void setCurrentSignInIp(String currentSignInIp) {
		this.currentSignInIp = currentSignInIp;
	}
	public String getLastSignInIp() {
		return lastSignInIp;
	}
	public void setLastSignInIp(String lastSignInIp) {
		this.lastSignInIp = lastSignInIp;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public String getAuthenticationToken() {
		return authenticationToken;
	}
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	public int getRoles() {
		return roles;
	}
	public void setRoles(int roles) {
		this.roles = roles;
	}
	public String getInvitationToken() {
		return invitationToken;
	}
	public void setInvitationToken(String invitationToken) {
		this.invitationToken = invitationToken;
	}
	public int getInvitedBy() {
		return invitedBy;
	}
	public void setInvitedBy(int invitedBy) {
		this.invitedBy = invitedBy;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Users [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", encryptedPassword=");
		builder.append(encryptedPassword);
		builder.append(", resetPasswordToken=");
		builder.append(resetPasswordToken);
		builder.append(", resetPasswordSentAt=");
		builder.append(resetPasswordSentAt);
		builder.append(", rememberCreatedAt=");
		builder.append(rememberCreatedAt);
		builder.append(", signInCount=");
		builder.append(signInCount);
		builder.append(", currentSignInAt=");
		builder.append(currentSignInAt);
		builder.append(", lastSignInAt=");
		builder.append(lastSignInAt);
		builder.append(", currentSignInIp=");
		builder.append(currentSignInIp);
		builder.append(", lastSignInIp=");
		builder.append(lastSignInIp);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append(", name=");
		builder.append(name);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", accessToken=");
		builder.append(accessToken);
		builder.append(", pin=");
		builder.append(pin);
		builder.append(", verified=");
		builder.append(verified);
		builder.append(", authenticationToken=");
		builder.append(authenticationToken);
		builder.append(", roles=");
		builder.append(roles);
		builder.append(", invitationToken=");
		builder.append(invitationToken);
		builder.append(", invitedBy=");
		builder.append(invitedBy);
		builder.append(", avatar=");
		builder.append(avatar);
		builder.append("]");
		return builder.toString();
	}
}