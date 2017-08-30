package com.alipay.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class AlipayOrderQuery implements Serializable {

	private static final long serialVersionUID = -6862074974357364354L;

	/** 查询支付交易号在此集合中的记录。null或empty表示忽略此条件 */
	private Set<String> alipayIds;
	/** 查询商户订单号在此集合中的记录。null或empty表示忽略此条件 */
	private Set<String> orderIds;
	/** 查询在此时间后（含）创建的记录。null表示忽略此条件 */
	private Date after;
	/** 查询在此时间前（不含）创建的记录。null表示忽略此条件 */
	private Date before;
	/** 查询最后修改时间在此时间后（含）的记录。null表示忽略此条件 */
	private Date modifiedAfter;
	/** 查询最后修改时间在此时间前（不含）的记录。null表示忽略此条件 */
	private Date modifiedBefore;
	/** 查询支付时间在此时间后（含）的记录。null表示忽略此条件 */
	private Date paidAfter;
	/** 查询支付时间在此时间前（不含）的记录。null表示忽略此条件 */
	private Date paidBefore;
	/** 返回结果是否按创建时间排序。正值表示从旧到新排序，复制表示从新到旧排序，0表示无需排序 */
	private int orderByCreationTime;
	/** 返回结果是否按最后修改时间排序。正值表示从旧到新排序，复制表示从新到旧排序，0表示无需排序 */
	private int orderByModifiedTime;
	/** 返回结果是否按支付时间排序。正值表示从旧到新排序，复制表示从新到旧排序，0表示无需排序 */
	private int orderByPaidTime;
	/** 返回结果的起始偏移 */
	private int offset;
	/** 返回结果条数 */
	private int limit;
	
	/**
	 * 本查询条件是否为空，即没有任何子条件。
	 * 
	 * @return 如果没有任何子条件，true，否则false
	 */
	public boolean isBlank() {
		return (alipayIds == null || alipayIds.isEmpty()) &&
				(orderIds == null || orderIds.isEmpty()) &&
				after == null && before == null &&
				modifiedAfter == null && modifiedBefore == null &&
				paidAfter == null && paidBefore == null;
	}
	
	public AlipayOrderQuery alipayId(String alipayId) {
		if (alipayId == null || alipayId.isEmpty())
			throw new IllegalArgumentException("alipayId cannot be null or empty");
		if (alipayIds == null)
			alipayIds = new TreeSet<String>();
		alipayIds.add(alipayId);
		return this;
	}
	
	public AlipayOrderQuery orderId(String orderId) {
		if (orderId == null || orderId.isEmpty())
			throw new IllegalArgumentException("orderId cannot be null or empty");
		if (orderIds == null)
			orderIds = new TreeSet<String>();
		orderIds.add(orderId);
		return this;
	}
	
	public AlipayOrderQuery after(Date after) {
		this.after = after;
		return this;
	}
	
	public AlipayOrderQuery before(Date before) {
		this.before = before;
		return this;
	}
	
	public AlipayOrderQuery modifiedAfter(Date after) {
		this.modifiedAfter = after;
		return this;
	}
	
	public AlipayOrderQuery modifiedBefore(Date before) {
		this.modifiedBefore = before;
		return this;
	}
	
	public AlipayOrderQuery paidAfter(Date after) {
		this.paidAfter = after;
		return this;
	}
	
	public AlipayOrderQuery paidBefore(Date before) {
		this.paidBefore = before;
		return this;
	}
	
	public AlipayOrderQuery orderByCreationTime(int order) {
		this.orderByCreationTime = order;
		return this;
	}
	
	public AlipayOrderQuery orderByModifiedTime(int order) {
		this.orderByModifiedTime = order;
		return this;
	}
	
	public AlipayOrderQuery orderByPaidTime(int order) {
		this.orderByPaidTime = order;
		return this;
	}
	
	public AlipayOrderQuery offset(int offset) {
		this.offset = offset;
		return this;
	}
	
	public AlipayOrderQuery limit(int limit) {
		this.limit = limit;
		return this;
	}

	public Set<String> getAlipayIds() {
		return alipayIds;
	}

	public void setAlipayIds(Set<String> alipayIds) {
		this.alipayIds = alipayIds;
	}

	public Set<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(Set<String> orderIds) {
		this.orderIds = orderIds;
	}

	public Date getAfter() {
		return after;
	}

	public void setAfter(Date after) {
		this.after = after;
	}

	public Date getBefore() {
		return before;
	}

	public void setBefore(Date before) {
		this.before = before;
	}

	public Date getModifiedAfter() {
		return modifiedAfter;
	}

	public void setModifiedAfter(Date modifiedAfter) {
		this.modifiedAfter = modifiedAfter;
	}

	public Date getModifiedBefore() {
		return modifiedBefore;
	}

	public void setModifiedBefore(Date modifiedBefore) {
		this.modifiedBefore = modifiedBefore;
	}

	public Date getPaidAfter() {
		return paidAfter;
	}

	public void setPaidAfter(Date paidAfter) {
		this.paidAfter = paidAfter;
	}

	public Date getPaidBefore() {
		return paidBefore;
	}

	public void setPaidBefore(Date paidBefore) {
		this.paidBefore = paidBefore;
	}

	public int getOrderByCreationTime() {
		return orderByCreationTime;
	}

	public void setOrderByCreationTime(int orderByCreationTime) {
		this.orderByCreationTime = orderByCreationTime;
	}

	public int getOrderByModifiedTime() {
		return orderByModifiedTime;
	}

	public void setOrderByModifiedTime(int orderByModifiedTime) {
		this.orderByModifiedTime = orderByModifiedTime;
	}

	public int getOrderByPaidTime() {
		return orderByPaidTime;
	}

	public void setOrderByPaidTime(int orderByPaidTime) {
		this.orderByPaidTime = orderByPaidTime;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlipayOrderQuery [alipayIds=").append(alipayIds).append(", orderIds=").append(orderIds)
				.append(", after=").append(after).append(", before=").append(before).append(", modifiedAfter=")
				.append(modifiedAfter).append(", modifiedBefore=").append(modifiedBefore).append(", paidAfter=")
				.append(paidAfter).append(", paidBefore=").append(paidBefore).append(", orderByCreationTime=")
				.append(orderByCreationTime).append(", orderByModifiedTime=").append(orderByModifiedTime)
				.append(", orderByPaidTime=").append(orderByPaidTime).append(", offset=").append(offset)
				.append(", limit=").append(limit).append("]");
		return builder.toString();
	}

}
