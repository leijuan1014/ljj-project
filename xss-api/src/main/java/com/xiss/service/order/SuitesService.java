package com.xiss.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.order.SuitesDao;
import com.xiss.model.order.Suites;

/** 
* @author leijj
* @since  2017年5月18日 下午6:04:05 
*/
@Service
public class SuitesService {
	@Autowired
	private SuitesDao suiteDao;
	
	public void insert(Suites suites) {
		suiteDao.insert(suites);
	}
	
	public Suites getSuiteById(int id) {
		return suiteDao.getById(id);
	}
}
