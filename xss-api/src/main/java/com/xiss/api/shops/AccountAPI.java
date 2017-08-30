package com.xiss.api.shops;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.xiss.model.balances.enums.BalanceStatus;
import com.xiss.model.shops.Shops;
import com.xiss.model.system.Users;
import com.xiss.service.balances.BalancesService;
import com.xiss.service.shops.CardDealsService;
import com.xiss.service.shops.DealsService;
import com.xiss.service.shops.ShopsService;
import com.xiss.service.system.UsersService;
import com.xiss.util.MyComparator;

/** 
 * 
* @author leijj
* @since  2017-4-11
*/
@Controller
@RequestMapping("/api/account")
public class AccountAPI {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ShopsService shopsService;

	@Autowired
	private DealsService dealsService;
	
	@Autowired
	private CardDealsService cardDealsService;
	
	@Autowired
	private BalancesService balancesService;
	
	@RequestMapping("/woAmI")
	@ResponseBody
	public String woAmI() {
		return "我是woAmI";
	}
	/**
	 * 账户中心佣金接口
	 * */
	@RequestMapping("/commission")
	@ResponseBody
	public JSONArray commission(@RequestParam String token) {
		JSONArray result = new JSONArray();
		Users users = usersService.getByToken(token);
		if (null == users) {
			return result;
		}
		List<Map<String, Object>> cardSaleList = balancesService.balancesQueryByUserId(users.getId());
		for (Map<String, Object> cardSaleMap : cardSaleList) {
			cardSaleMap.remove("province");
			cardSaleMap.remove("city");
			cardSaleMap.remove("name");
			cardSaleMap.remove("applyAt");
			int state = (Integer) cardSaleMap.get("status");
			boolean btnHide = false;
			if (state > 0) {
				btnHide = true;
			}
			cardSaleMap.put("btnHide", btnHide);
			BalanceStatus balanceStatus = BalanceStatus.get(state);
			if (balanceStatus != null) {
				cardSaleMap.remove("status");
				cardSaleMap.put("status", balanceStatus.toString());
			}
			Double money = (Double)cardSaleMap.get("money");
			Double suiteSaleMoney = (Double)cardSaleMap.get("suiteSaleMoney");
			DecimalFormat df = new DecimalFormat("######0.00");
			cardSaleMap.put("money", df.format(money));
			cardSaleMap.put("suiteSaleMoney", df.format(suiteSaleMoney));
			result.add(cardSaleMap);
		}
		return result;
	}
	
	/**
	 * 按月份计算洗车佣金
	 * 1.查询洗车提成规则
	 * 2.按月查询洗车次数
	 * 3.按规则计算洗车佣金
	 * */
	public Map<String, Double> getCarWashCommission(int userId){
		Shops shops = shopsService.getByUserId(userId);
		Map<String, Double> commissionMap = new HashMap<String, Double>();
		int carWashMonthNum = shops.getCarWashMonthNum();
		double carWashMonthAmount = shops.getCarWashMonthAmount();
		double carWashPerAmount = shops.getCarWashPerAmount();
		List<Map<String, Object>> dealList = dealsService.getCountByShopId(shops.getId());
		if (dealList != null && dealList.size() > 0) {
			for (Map<String, Object> dealMap : dealList) {
				String yearMonth = (String) dealMap.get("yearMonth");
				Long dealNum = (Long) dealMap.get("count");
				double commission = carWashMonthAmount;
				if(dealNum > carWashMonthNum && carWashPerAmount > 0) {
					commission += (dealNum - carWashMonthNum) * carWashPerAmount;
				}
				commissionMap.put(yearMonth, commission);
			}
		}
		return commissionMap;
	}
	
	/**
	 * 按月份计算售卡佣金
	 * */
	private Map<String, Double> getCardSaleCommission(int userId){
		Map<String, Double> commissionMap = new HashMap<String, Double>();
		List<Map<String, Object>> cardMap = cardDealsService.getTotalCommissionSellerId(userId, null, null);
		for (Map<String, Object> card : cardMap) {
			String yearMonth = (String) card.get("yearMonth");
			Double commission = (Double) card.get("totalCommission");
			commissionMap.put(yearMonth, commission);
		}
		return commissionMap;
	}
	/**
	 * 根据当前登录用id查询分销记录
	 * http://localhost:8080/xiss/api/account/getSellRecords?userId=8&start='2017-01-01'&end='2017-03-01'&pageNo=1&pageSize=2
	 * */
	@RequestMapping("/getSellRecordsByToken")
	@ResponseBody
	public ModelMap getCards(String token, String start, String end, Integer pageNo, Integer pageSize, String status) {
		System.out.println("=====token=" + token + "status=" + status + ",start=" + start  + ",end=" + end +
				",pageNo=" + pageNo  + ",pageSize=" + pageSize );
		ModelMap model = new ModelMap();
		Users users = usersService.getByToken(token);
		if (users == null) {
			return model;
		}
		List<Map<String, Object>> cardDealsList = new ArrayList<Map<String,Object>>();
		Integer state = null;
		if ("all".equals(status)) {
			//cardDealsList = cardDealsService.getAllCards(users.getId(), start, end, pageNo, pageSize);
		} else if ("paid".equals(status)) {
			state = 3;
			//cardDealsList = cardDealsService.getPaidCards(users.getId(), start, end, pageNo, pageSize);
		} else if ("nopaid".equals(status)) {
			state = 0;
			//cardDealsList = cardDealsService.getNoPaidCards(users.getId(), start, end, pageNo, pageSize);
		}
		cardDealsList = cardDealsService.getSellRecords(users.getId(), null, state, start, end, pageNo, pageSize);
		if (cardDealsList != null) {
			Map<String, Object> cardDeals = new HashMap<String, Object>();
			cardDeals.put("pageNo", pageNo);
			cardDeals.put("pageSize", pageSize);
			cardDeals.put("list", cardDealsList);
			model.put("cardDeals", cardDeals);
		}
		
		return model;
	}
	
	@RequestMapping("/getSellRecordCount")
	@ResponseBody
	public ModelMap getCardCount(String token, String start, String end) {
		ModelMap model = new ModelMap();
		Users users = usersService.getByToken(token);
		if (users == null) {
			return model;
		}
		int allCount = cardDealsService.getSellRecordCount(users.getId(), null, null, start, end);
		int paidCount = cardDealsService.getSellRecordCount(users.getId(), null, 3, start, end);
		model.put("allCount", allCount);
		model.put("paidCount", paidCount);
		return model;
	}
}