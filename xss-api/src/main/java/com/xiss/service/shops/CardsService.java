package com.xiss.service.shops;
/** 
* @author leijj
* @since  2017年4月11日 下午4:36:11 
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.shops.CardsDao;
import com.xiss.model.shops.Cards;

@Service
public class CardsService {
	@Autowired
	private CardsDao cardsDao;
	
	public int insert(Cards cards) {
		return cardsDao.insert(cards);
	}
	
	public Cards getById(int id){
		return cardsDao.getById(id);
	}
	public Cards getByPin(String pin){
		return cardsDao.getByPin(pin);
	}
	public Cards getByCid(String cid){
		return cardsDao.getByCid(cid);
	}
}
