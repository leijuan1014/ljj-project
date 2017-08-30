package com.xiss.dao.order;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.order.GrowingUsers;

/** 
* @author leijj
* @since  2017年4月18日 下午4:32:03 
*/
public interface GrowingUsersDao {
	
	public int insert(GrowingUsers growingUsers);
	
	public void updateGrowingCardId(@Param("mobile") String mobile, @Param("enrolled520") boolean enrolled520);
	
	public GrowingUsers getById(Integer id);
	
	public GrowingUsers getByMobile(String mobile);
}