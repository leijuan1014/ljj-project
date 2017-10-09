package com.xiss.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.system.CarModelDao;
import com.xiss.model.system.CarModel;

/** 
* @author leijj
* @since  2017年7月24日 上午11:30:12 
*/
@Service
public class CarModelService {
	@Autowired
	private CarModelDao carModelDao;
	public CarModel getByBrandIdAndName(Integer carBrandId, String cnName) {
		return carModelDao.getByBrandIdAndName(carBrandId, cnName);
	}
}
