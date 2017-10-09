package com.xiss.service.basic;

import java.util.List;

import com.xiss.model.basic.Area;
import com.xiss.model.basic.City;
import com.xiss.model.basic.Province;


/** 
* @author leijj
* @since  2017年4月9日 下午12:04:59 
*/
public interface RegionService {

    public List<Province> getAllProvince();

    public List<City> getCityByProvinceId(String id, Boolean activity);

    public List<Area> getAreaByCityId(String id);
    
    public Province getProvince(Integer id);
    
    public Province getProvinceByName(String name);
    
    public City getCity(Integer id);
    
    public City getCityByName(String name);
    
    public Area getArea(Integer id);
}
