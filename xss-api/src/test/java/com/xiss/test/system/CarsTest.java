package com.xiss.test.system;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.xiss.model.system.Car;
import com.xiss.service.system.CarsService;
import com.xiss.util.DateUtil;

/** 
* @author leijj
* @since  2017年5月18日 下午3:13:52 
*/
@ContextConfiguration(locations = {"classpath:application.xml"})
public class CarsTest{// extends SpringTestCase{
	@Autowired
	private CarsService carsService;
	//@Test
	public void insertTest() {
		String licensedId = "豫A12222";
		if (licensedId != null && !licensedId.isEmpty()) {
			Car car = carsService.getByLicensedId(licensedId);
			if (car != null) {
				Date validAt = car.getValidAt();
				if (validAt == null) {
					validAt = new Date();
				}
				String valid = DateUtil.getAfterDayDateYMD(365, validAt);
				System.out.println("======valid=" + valid);
				carsService.updateValid(licensedId, valid);
			}
		}
	}
}
