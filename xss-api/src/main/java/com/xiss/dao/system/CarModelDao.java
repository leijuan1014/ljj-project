package com.xiss.dao.system;
/** 
* @author leijj
* @since  2017年7月24日 上午11:30:27 
*/

import org.apache.ibatis.annotations.Param;

import com.xiss.model.system.CarModel;

public interface CarModelDao {
	public CarModel getByBrandIdAndName(@Param("carBrandId") Integer carBrandId, @Param("cnName") String cnName);
}
