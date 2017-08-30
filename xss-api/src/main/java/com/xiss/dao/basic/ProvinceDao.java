package com.xiss.dao.basic;

import com.xiss.model.basic.Province;

/** 
* @author leijj
* @since  2017年4月9日 上午11:53:20 
*/

public interface ProvinceDao extends BaseDao<Province>{
	public Province getProvinceById(Integer id);
	
	public Province getProvinceByName(String province);
}
