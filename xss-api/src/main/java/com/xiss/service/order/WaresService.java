package com.xiss.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.order.WaresDao;
import com.xiss.model.order.Wares;

/** 
* @author leijj
* @since  2017年5月18日 下午6:04:20 
*/
@Service
public class WaresService {
	@Autowired
	private WaresDao waresDao;
	public Wares getWareById(int id) {
		return waresDao.getById(id);
	}
	public List<Wares> getWaresBySuiteId(int suiteId) {
		return waresDao.getWaresBySuiteId(suiteId);
	}
}
