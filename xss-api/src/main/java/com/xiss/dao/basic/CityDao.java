package com.xiss.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.basic.City;

/** 
* @author leijj
* @since  2017年4月9日 上午11:53:20 
*/

public interface CityDao extends BaseDao<City>{
    public List<City> getCityByProvinceId(@Param("id") String id, @Param("active") Boolean active);
    
    public City getCityById(Integer id);
    
    public City getCityByName(String city);
}