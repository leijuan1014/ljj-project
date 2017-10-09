package com.xiss.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.order.Wares;

/** 
* @author leijj
* @since  2017年4月26日 下午5:12:21 
*/
public interface WaresDao {
	public Wares getById(int id);
	
	public List<Wares> getWaresBySuiteId(@Param("suiteId") int suiteId);
}
