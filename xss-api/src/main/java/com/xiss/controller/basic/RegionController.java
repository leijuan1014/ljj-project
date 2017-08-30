package com.xiss.controller.basic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.xiss.model.basic.Area;
import com.xiss.model.basic.City;
import com.xiss.model.basic.Province;
import com.xiss.service.basic.RegionService;

/** 
* @author leijj
* @since  2017年4月9日 上午11:53:20 
*/
@Controller
public class RegionController {

	@Autowired
	private RegionService regionService;

    /**
     * 首先获取全部的省份
     * @param map
     * @return
     */
    @RequestMapping(value="/getAllProvince")
    @ResponseBody
    public JSONArray allProvince(){
    	List<Province> provinceList = regionService.getAllProvince();
    	JSONArray provinces = new JSONArray();
        if(provinceList != null && !provinceList.isEmpty())
			for(Province province : provinceList){
				provinces.add(province.getProvince());
			}
        return provinces;
    }
    @RequestMapping(value = "/getProvinceByName")
    @ResponseBody
    public JSONArray getProvinceByName(String name){
    	JSONArray cities = new JSONArray();
    	Province province = regionService.getProvinceByName(name);
    	if (province == null) {
			return cities;
		}
    	cities.add(province);
        return cities;
    }
    /**
     * 根据省份获取城市
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAllCitiesByProvinceName")
    @ResponseBody
    public JSONArray getCitiesByProvinceName(@RequestParam("name") String name){
    	JSONArray cities = new JSONArray();
    	Province province = regionService.getProvinceByName(name);
    	if (province == null) {
			return cities;
		}
        List<City> cityList = regionService.getCityByProvinceId(province.getProvinceid().toString(), null);
        if(cityList != null && !cityList.isEmpty())
			for(City city : cityList){
				Map<String, Object> cityMap = new LinkedHashMap<String, Object>();
				cityMap.put("name", city.getCity());
				cityMap.put("status", city.getStatus());
				cities.add(cityMap);
			}
        return cities;
    }
    /**
     * 根据省份获取城市
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCitiesByProvinceName")
    @ResponseBody
    public JSONArray getCityByProvinceId(@RequestParam("name") String name){
    	JSONArray cities = new JSONArray();
    	Province province = regionService.getProvinceByName(name);
    	if (province == null) {
			return cities;
		}
        List<City> cityList = regionService.getCityByProvinceId(province.getProvinceid().toString(), true);
        if(cityList != null && !cityList.isEmpty())
			for(City city : cityList){
				cities.add(city.getCity());
			}
        return cities;
    }

    /**
     * 根据城市获取区域
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAreasByCityName")
    @ResponseBody
    public JSONArray getAreaByCityId(String name){
    	JSONArray areas = new JSONArray();
    	City city = regionService.getCityByName(name);
    	if (city == null) {
			return areas;
		}
        List<Area> areaList = regionService.getAreaByCityId(city.getCityid());
        if(areaList != null && !areaList.isEmpty())
			for(Area area : areaList){
				areas.add(area.getArea());
			}
        return areas;
    }
 }
