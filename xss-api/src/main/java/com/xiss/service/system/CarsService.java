package com.xiss.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.system.CarsDao;
import com.xiss.model.system.Car;

/** 
* @author leijj
* @since  2017年6月14日 上午10:10:28 
*/
@Service
public class CarsService {
	@Autowired
	private CarsDao carsDao;
	
	public void save(Car car) {
		carsDao.save(car);
	}
	public Car getByLicensedId(String licensedId){
		return carsDao.getByLicensedId(licensedId);
	}
	
	public void updateValid(String licensedId, String validAt) {
		carsDao.updateValid(licensedId, validAt);
	}
}
