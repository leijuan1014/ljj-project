package com.xiss.test.orders;
/** 
* @author leijj
* @since  2017年4月26日 下午3:37:10 
*/

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.xiss.model.order.GrowingCards;
import com.xiss.model.order.GrowingUsers;
import com.xiss.model.order.PaymentInfo;
import com.xiss.model.order.enums.PaymentGateway;
import com.xiss.model.order.enums.TradeNoTypeEnum;
import com.xiss.model.shops.Cards;
import com.xiss.service.order.GrowingCardsService;
import com.xiss.service.order.GrowingUsersService;
import com.xiss.service.order.OrderService;
import com.xiss.service.shops.CardsService;
import com.xiss.test.SpringTestCase;
import com.xiss.util.RandomNumberGenerator;
@ContextConfiguration(locations = {"classpath:application.xml"})
public class OrdersTest extends SpringTestCase{
	@Autowired
	private OrderService orderService;
	@Autowired
	private CardsService cardsService;
	@Autowired
	private GrowingCardsService growingCardsService;
	@Autowired
	private GrowingUsersService growingUsersService;
	//@Test
	public void orderPaidTest(){
		orderService.orderPaidAndDeliver(new PaymentInfo()
			.orderTradeNo("1")
			.mode(PaymentGateway.WECHATPAY)
			.time(new Date())
			.amount(880));
	}
	//@Test
	public void aTest() {
		GrowingUsers growingUsers = growingUsersService.getByMobile("17710052758");
		if (null != growingUsers && growingUsers.getGrowingCardId() > 0) {
			System.out.println("您已参加7天体验活动，请不要重复注册");
		} else if (null == growingUsers) {
			growingUsers = new GrowingUsers();
			growingUsers.setMobile("17710052758");
			growingUsers.setStatus(0);
			growingUsersService.insert(growingUsers);
		}
	}
	
	
	public void bTest() {
		//保存卡信息
		String pin = RandomNumberGenerator.getUUId12();
		int range = 0;
		String tradeNo = "WEEK123456789";
		
		if (tradeNo.startsWith(TradeNoTypeEnum.WEEK.name())) {//7天体验运营活动
			range = -7;
			pin = "H".concat(pin);
		} else if (tradeNo.startsWith(TradeNoTypeEnum.YEAR.name())){
			range = 12;
		}
		Cards cards = new Cards();
		cards.setStatus(0);
		cards.setRange(range);
		cards.setPin(pin);
		cardsService.insert(cards);
		
		GrowingCards growingCards = new GrowingCards();
		growingCards.setPin(pin);
		growingCards.setRange(range);
		growingCardsService.insert(growingCards);
		System.out.println("======growingCards=" + growingCards);
		growingUsersService.updateGrowingCardId("17710052758", true);
	}
}
