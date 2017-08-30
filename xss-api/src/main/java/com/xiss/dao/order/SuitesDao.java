package com.xiss.dao.order;

import com.xiss.model.order.Suites;

/** 
* @author leijj
* @since  2017年4月26日 下午5:13:03 
*/
public interface SuitesDao {
	public void insert(Suites suites);
	
	public Suites getById(int id);
}
