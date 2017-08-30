package com.xiss.dao.order;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.order.CouponUser;

/** 
* @author leijj
* @since  2017年6月5日 上午11:12:06 
*/
public interface CouponUsersDao {
	public CouponUser getById(int id);
	public void updateStatusById(@Param("id") int id, @Param("status") int status);
}
