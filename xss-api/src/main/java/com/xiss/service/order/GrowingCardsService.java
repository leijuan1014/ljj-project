package com.xiss.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.order.GrowingCardsDao;
import com.xiss.model.order.GrowingCards;

/** 
* @author leijj
* @since  2017年4月18日 下午4:32:03 
*/
@Service
public class GrowingCardsService {
	@Autowired
	private GrowingCardsDao growingCardsDao;
	public int insert(GrowingCards growingCards) {
		return growingCardsDao.insert(growingCards);
	}
	
	public GrowingCards getById(Integer id) {
		return growingCardsDao.getById(id);
	}
}