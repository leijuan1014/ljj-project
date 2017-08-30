package com.xiss.test.suiteorder;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.xiss.model.balances.Balances;
import com.xiss.model.balances.enums.BalanceStatus;
import com.xiss.model.order.SuiteOrders;
import com.xiss.model.shops.Cards;
import com.xiss.service.balances.BalancesService;
import com.xiss.service.order.SuiteOrdersService;
import com.xiss.service.shops.CardDealsService;
import com.xiss.service.shops.CardsService;
import com.xiss.service.system.UsersService;
import com.xiss.test.SpringTestCase;
import com.xiss.util.DateUtil;

/** 
* @author leijj
* @since  2017年5月10日 上午9:19:58 
*/

//@ContextConfiguration(locations = {"classpath:application.xml"})
public class SuiteOrderTest {//extends SpringTestCase{
	@Autowired
	private SuiteOrdersService suiteOrdersService;
	@Autowired
	private CardDealsService cardDealsService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private BalancesService balancesService;
	
	//@Test
	public void saveTest() {
		Date now = new Date();
		String start = DateUtil.getCurWeekStart(now);
		String next = DateUtil.getNextWeekStart(now);
		Balances balances = balancesService.getByUserId(11, start, next);
		if (balances.getStatus() == BalanceStatus.WITHDRAW_INIT.ordinal()){
			BigDecimal money = new BigDecimal(balances.getMoney()).add(new BigDecimal(0.01));
			System.out.println("=======money");
			int balanceId = 0;//balancesService.updateMoney(balances.getId(), money.doubleValue());
			System.out.println("balanceId=" + balanceId);
		}
		//保存售卡信息
	}
}
