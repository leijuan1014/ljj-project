package com.xiss.controller.basic;
/** 
* @author leijj
* @since  2017年6月6日 下午3:00:25 
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.xiss.model.basic.ShopCategory;
import com.xiss.service.basic.ShopCategoryService;
@Controller
public class ShopCategoryAPI {
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@RequestMapping(value="/getAllShopCategory")
    @ResponseBody
    public JSONArray allProvince(){
    	List<ShopCategory> list = shopCategoryService.getAll();
    	JSONArray shopCategoryArray = new JSONArray();
    	shopCategoryArray.add("全部");
        if(list != null && !list.isEmpty())
			for(ShopCategory shopCategory : list){
				shopCategoryArray.add(shopCategory.getName());
			}
        return shopCategoryArray;
    }
}
