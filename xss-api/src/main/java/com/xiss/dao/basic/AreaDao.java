package com.xiss.dao.basic;


import java.util.List;

import com.xiss.model.basic.Area;
/** 
* @author leijj
* @since  2017年4月9日 上午11:53:20 
*/
public interface AreaDao extends BaseDao<Area>{

    public List<Area> getAreaByCityId(String id);
    
    public Area getAreaById(Integer id);
}