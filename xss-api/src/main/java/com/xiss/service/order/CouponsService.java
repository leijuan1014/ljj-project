package com.xiss.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.order.CouponsDao;
import com.xiss.model.order.Coupon;

/** 
* @author leijj
* @since  2017年6月5日 上午11:12:40 
*/
@Service
public class CouponsService {
	@Autowired
	private CouponsDao couponsDao;
	
	public Coupon getById(int id) {
		return couponsDao.getById(id);
	}
	public List<Coupon> getCoupons(Integer userId, Integer status, Boolean isvalid) {
		return couponsDao.getCoupons(userId, status, isvalid);
	}
}
