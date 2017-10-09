package com.xiss.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.order.CouponUsersDao;
import com.xiss.model.order.CouponUser;

/** 
* @author leijj
* @since  2017年6月5日 上午11:12:40 
*/
@Service
public class CouponUsersService {
	@Autowired
	private CouponUsersDao couponUsersDao;
	public CouponUser getById(int id){
		return couponUsersDao.getById(id);
	}
	public void updateStatusById(int id, int status) {
		couponUsersDao.updateStatusById(id, status);
	}
}
