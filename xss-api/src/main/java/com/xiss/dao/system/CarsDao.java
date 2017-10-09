package com.xiss.dao.system;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.system.Car;

/** 
* @author leijj
* @since  2017年6月14日 上午10:10:56 
*/
public interface CarsDao {
	public void save(Car car);
	public Car getByLicensedId(String licensedId);
	//-- update cars set valid_at = valid_at + interval '${years} years', updated_at=now()
	public void updateValid(@Param("licensedId") String licensedId, @Param("validAt") String validAt);
}
