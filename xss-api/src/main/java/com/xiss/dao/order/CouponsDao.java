package com.xiss.dao.order;
/** 
* @author leijj
* @since  2017年6月5日 上午11:12:06 
*/

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.order.Coupon;

public interface CouponsDao {
	public Coupon getById(int id);
	/**
	 * 获取用户的优惠券列表
	 * userId:登录用户id
	 * status:优惠券状态
	 * isvalid:是否有效
	 * */
	public List<Coupon> getCoupons(@Param("userId") Integer userId, @Param("status") Integer status, @Param("isvalid") Boolean isvalid);
}
