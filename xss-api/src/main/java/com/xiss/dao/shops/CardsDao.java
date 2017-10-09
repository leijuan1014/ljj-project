package com.xiss.dao.shops;

import com.xiss.model.shops.Cards;

/** 
* @author leijj
* @since  2017年4月11日 下午4:35:22 
*/
public interface CardsDao {
	public int insert(Cards cards);
	
	public Cards getById(int id);
	
	public Cards getByPin(String pin);
	
	public Cards getByCid(String cid);
}
