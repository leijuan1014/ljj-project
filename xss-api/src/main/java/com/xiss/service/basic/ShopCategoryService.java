package com.xiss.service.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.basic.ShopCategoryDao;
import com.xiss.model.basic.ShopCategory;

/** 
* @author leijj
* @since  2017年6月6日 下午3:03:39 
*/
@Service
public class ShopCategoryService {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	public List<ShopCategory> getAll() {
		return shopCategoryDao.getAll();
	}
}
