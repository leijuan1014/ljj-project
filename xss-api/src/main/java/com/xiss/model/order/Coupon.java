package com.xiss.model.order;

import java.io.Serializable;
import java.util.Date;

/** 
* @author leijj
* @since  2017年6月5日 上午10:45:22 
*/
public class Coupon implements Serializable{

	private static final long serialVersionUID = 8030755938390087430L;

	private int id;
	/*优惠券名称 */
	private String name;
	/*详情说明 */
	private String memo;
	/*优惠券图片地址 */
	private String avatar;
	/*优惠折扣 */
	private double deductible;
	/*有效期开始 */
	private Date validStart;
	/*有效期截止 */
	private Date validEnd;
	private Date createdAt;
	private Date updatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public double getDeductible() {
		return deductible;
	}
	public void setDeductible(double deductible) {
		this.deductible = deductible;
	}
	public Date getValidStart() {
		return validStart;
	}
	public void setValidStart(Date validStart) {
		this.validStart = validStart;
	}
	public Date getValidEnd() {
		return validEnd;
	}
	public void setValidEnd(Date validEnd) {
		this.validEnd = validEnd;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coupon [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", avatar=");
		builder.append(avatar);
		builder.append(", deductible=");
		builder.append(deductible);
		builder.append(", validStart=");
		builder.append(validStart);
		builder.append(", validEnd=");
		builder.append(validEnd);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}
