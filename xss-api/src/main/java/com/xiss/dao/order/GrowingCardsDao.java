package com.xiss.dao.order;

import com.xiss.model.order.GrowingCards;

/** 
* @author leijj
* @since  2017年4月18日 下午4:32:03 
*/
public interface GrowingCardsDao {
	
	public int insert(GrowingCards growingCards);
	
	public GrowingCards getById(Integer id);
}