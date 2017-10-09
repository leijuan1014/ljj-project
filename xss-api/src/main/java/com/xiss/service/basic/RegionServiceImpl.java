package com.xiss.service.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.basic.AreaDao;
import com.xiss.dao.basic.CityDao;
import com.xiss.dao.basic.ProvinceDao;
import com.xiss.model.basic.Area;
import com.xiss.model.basic.City;
import com.xiss.model.basic.Province;


/** 
* @author leijj
* @since  2017年4月9日 下午12:04:59 
*/
@Service
public class RegionServiceImpl implements RegionService{

	@Autowired
    private CityDao cityDao;
	@Autowired
    private ProvinceDao provinceDao;
	@Autowired
    private AreaDao areaDao;

    public List<Province> getAllProvince() {
        return provinceDao.list();
    }

    public List<City> getCityByProvinceId(String id, Boolean active) {
        return cityDao.getCityByProvinceId(id, active);
    }
    public Province getProvinceByName(String name) {
    	return provinceDao.getProvinceByName(name);
    }
    public List<Area> getAreaByCityId(String id) {
        return areaDao.getAreaByCityId(id);
    }

	public Province getProvince(Integer id) {
		return provinceDao.getProvinceById(id);
	}

	public City getCity(Integer id) {
		return cityDao.getCityById(id);
	}

	public City getCityByName(String name) {
		return cityDao.getCityByName(name);
	}
	public Area getArea(Integer id) {
		return areaDao.getAreaById(id);
	}
}
