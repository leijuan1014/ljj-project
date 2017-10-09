package com.xiss.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.order.GrowingUsersDao;
import com.xiss.model.order.GrowingUsers;

/** 
* @author leijj
* @since  2017年4月18日 下午4:32:03 
*/
@Service
public class GrowingUsersService {
	
	@Autowired
	private GrowingUsersDao growingUsersDao;
	public int insert(GrowingUsers growingUsers) {
		return growingUsersDao.insert(growingUsers);
	}
	public void updateGrowingCardId(String mobile, boolean enrolled520) {
		growingUsersDao.updateGrowingCardId(mobile, enrolled520);
	}
	public GrowingUsers getById(Integer id) {
		return growingUsersDao.getById(id);
	}
	
	public GrowingUsers getByMobile(String mobile){
		return growingUsersDao.getByMobile(mobile);
	}
}