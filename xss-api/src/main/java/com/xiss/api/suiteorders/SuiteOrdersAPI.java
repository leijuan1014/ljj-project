package com.xiss.api.suiteorders;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiss.model.balances.Balances;
import com.xiss.model.balances.enums.BalanceStatus;
import com.xiss.model.order.SuiteOrders;
import com.xiss.model.order.Suites;
import com.xiss.model.order.Wares;
import com.xiss.model.order.enums.OrderState;
import com.xiss.model.shops.Shops;
import com.xiss.model.system.Users;
import com.xiss.service.balances.BalancesService;
import com.xiss.service.order.SuiteOrdersService;
import com.xiss.service.order.SuitesService;
import com.xiss.service.order.WaresService;
import com.xiss.service.shops.ShopsService;
import com.xiss.service.system.UsersService;
import com.xiss.util.DateUtil;

/** 
* @author leijj
* @since  2017年5月16日 下午4:26:17 
*/
@Controller
@RequestMapping("/api/suiteOrder")
public class SuiteOrdersAPI {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private SuitesService suitesService;
	@Autowired
	private WaresService waresService;
	@Autowired
	private ShopsService shopsService;
	@Autowired
	private SuiteOrdersService suiteOrdersService;
	@Autowired
	private BalancesService balancesService;
	
	@RequestMapping("/getWaresBySuiteId")
	@ResponseBody
	public ModelMap getWaresBySuiteId(int suiteId) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("wares", waresService.getWaresBySuiteId(suiteId));
		return modelMap;
	}
	@RequestMapping("/getSuiteOrderById")
	@ResponseBody
	public ModelMap getSuiteOrderById(Integer suiteOrderId) {
		ModelMap modelMap = new ModelMap();
		if (suiteOrderId == null) {
			modelMap.put("success", false);
			modelMap.put("message", "传入参数有误");
			return modelMap;
		}
		SuiteOrders suiteOrders = suiteOrdersService.getSuiteOrderById(suiteOrderId);
		if (suiteOrders != null) {
			Map<String, Object> suiteOrderMap = new LinkedHashMap<String, Object>();
			modelMap.put("success", true);
			suiteOrderMap.put("tradeNo", suiteOrders.getTradeNo());
			suiteOrderMap.put("updateAt", suiteOrders.getUpdatedAt());
			Suites suites = suiteOrdersService.getSuiteById(suiteOrders.getSuiteId());
			if (suites != null) {
				suiteOrderMap.put("suiteName", suites.getName());
				suiteOrderMap.put("salePrice", suites.getSalePrice());
			}
			modelMap.put("suiteOrder", suiteOrderMap);
		}
		
		return modelMap;
	}
	@RequestMapping("/getByBalanceId")
	@ResponseBody
	public ModelMap getByBalanceId(Integer balanceId, Integer pageNo, Integer pageSize) {
		ModelMap modelMap = new ModelMap();
		if (balanceId == null) {
			modelMap.put("success", false);
			modelMap.put("message", "传入参数有误");
			return modelMap;
		}
		Balances balances = balancesService.getById(balanceId);
		List<Map<String, Object>> suiteOrders = suiteOrdersService.getByShopUserToken(null, OrderState.VERIFIED.ordinal(), balanceId, pageNo, pageSize);
		modelMap.put("success", true);
		modelMap.put("balanceRangeStart", DateUtil.getDay(balances.getRangeStart()));
		modelMap.put("balanceRangeEnd", DateUtil.getDay(balances.getRangeEnd()));
		modelMap.put("suiteOrders", suiteOrders);
		return modelMap;
	}
	
	@RequestMapping("/getByShopUserToken")
	@ResponseBody
	public ModelMap getShopSuiteOrders(String userToken, Integer pageNo, Integer pageSize) {
		ModelMap modelMap = new ModelMap();
		if (null == userToken || userToken.isEmpty()) {
			modelMap.put("success", false);
			modelMap.put("message", "传入参数有误");
			return modelMap;
		}
		List<Map<String, Object>> suiteOrders = suiteOrdersService.getByShopUserToken(userToken, OrderState.VERIFIED.ordinal(), null, pageNo, pageSize);
		modelMap.put("success", true);
		modelMap.put("suiteOrders", suiteOrders);
		return modelMap;
	}
	@RequestMapping("/getByUserToken")
	@ResponseBody
	public ModelMap getSuiteOrders(String userToken, Integer pageNo, Integer pageSize) {
		ModelMap modelMap = new ModelMap();
		if (null == userToken || userToken.isEmpty()) {
			modelMap.put("success", false);
			modelMap.put("message", "传入参数有误");
			return modelMap;
		}
		
		Users users = usersService.getByToken(userToken);
		if(null == users) {
			modelMap.put("success", false);
			modelMap.put("message", "用户不存在");
			return modelMap;
		}
		
		modelMap.put("noVerified", genOrders(users.getId(), OrderState.SUCCESS.ordinal(), pageNo, pageSize));
		modelMap.put("verified", genOrders(users.getId(), OrderState.VERIFIED.ordinal(), pageNo, pageSize));
		return modelMap;
	}
	
	private List<Map<String, Object>> genOrders(int userId, int state, Integer pageNo, Integer pageSize) {
		List<SuiteOrders> orders = suiteOrdersService.getByUserId(userId, state, pageNo, pageSize);
		List<Map<String, Object>> orderMap = new ArrayList<Map<String,Object>>();
		if(orders != null && orders.size() > 0) {
			for (SuiteOrders suiteOrder : orders) {
				orderMap.add(genOrder(suiteOrder));
			}
		}
		return orderMap;
	}
	/**
	 * 根据suiteId查询suite、shop、wares
	 * */
	private Map<String, Object> genOrder(SuiteOrders suiteOrder) {
		Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();
		jsonMap.put("tradeNo", suiteOrder.getTradeNo());
		Map<String, Object> suitesMap = new LinkedHashMap<String, Object>();
		Suites suites = suitesService.getSuiteById(suiteOrder.getSuiteId());
		if (suites != null) {
			Shops shops = shopsService.getById(suites.getShopId());
			if(shops != null){
				jsonMap.put("shopId", shops.getId());
				jsonMap.put("shopName", shops.getName());
			}
			
			suitesMap.put("id", suites.getId());
			suitesMap.put("name", suites.getName());
			suitesMap.put("originPrice", suites.getOriginPrice());
			suitesMap.put("salePrice", suites.getSalePrice());
			suitesMap.put("tags", suites.getTags());
			suitesMap.put("avatar", suites.getAvatar());
			
			List<Wares> wares = waresService.getWaresBySuiteId(suites.getId());
			if (wares != null) suitesMap.put("wares",wares);
			
			jsonMap.put("suites", suitesMap);
		}
		return jsonMap;
	}
	@RequestMapping("/verify")
	@ResponseBody
	public ModelMap verify(String tradeNo, String token){
		ModelMap modelMap = new ModelMap();
		if (tradeNo == null || tradeNo.isEmpty() || token == null || token.isEmpty()) {
			modelMap.put("success", "false");
			modelMap.put("message", "传入参数有误");
			return modelMap;
		}
		SuiteOrders orders = suiteOrdersService.getSuiteOrderByTradeNo(tradeNo);
		if (orders == null) {
			return failVerify(modelMap, "验证失败");
		}else if (orders != null && orders.getState() == OrderState.VERIFIED.ordinal()) {
			modelMap.put("success", "used");
			modelMap.put("message", "该码券已被使用过！");
			return modelMap;
		}
		Suites suite = suitesService.getSuiteById(orders.getSuiteId());
		if (suite == null) {
			return failVerify(modelMap, "验证失败");
		}
		Shops shops = shopsService.getById(suite.getShopId());
		if (shops == null) 
			return failVerify(modelMap, "验证失败");
		Users shopUser = usersService.getById(shops.getUserId());
		if (shopUser == null) 
			return failVerify(modelMap, "验证失败");
		if (!shopUser.getAuthenticationToken().equals(token)) 
			return failVerify(modelMap, "该套餐不在您登陆的车行下");
		
		suiteOrdersService.updateVerify(tradeNo, OrderState.VERIFIED.ordinal());
		orders = suiteOrdersService.getSuiteOrderByTradeNo(tradeNo);
		if (orders != null && orders.getState() == OrderState.VERIFIED.ordinal()) {
			modelMap.put("suiteName", suite.getName());
			modelMap.put("suiteSalePrice", suite.getSalePrice());
			modelMap.put("success", "true");
			modelMap.put("message", "验证成功");
			saveBalance(orders);
			return modelMap;
		} else {
			return failVerify(modelMap, "验证失败");
		}
	}
	
	private ModelMap failVerify(ModelMap modelMap, String message) {
		modelMap.put("success", "false");
		modelMap.put("message", message);
		return modelMap;
	}
	
	private void saveBalance(SuiteOrders suiteOrders) {
		if(suiteOrders.getBalanceId() > 0) {
			return;
		}
		int userId = 0;
		Suites suites = suitesService.getSuiteById(suiteOrders.getSuiteId());
		if(suites != null){
			Shops shops = shopsService.getById(suites.getShopId());
			userId = shops.getUserId();
		}
		Date now = new Date();
		String start = DateUtil.getCurWeekStart(now);
		String end = DateUtil.getWeekEndDate(now);
		String next = DateUtil.getNextWeekStart(now);
		Balances balances = balancesService.getByUserId(userId, start, next);
		double suiteSaleMoney = suiteOrders.getPrice();
		if (suiteOrders.getQuantity() > 0) {
			suiteSaleMoney = suiteSaleMoney*suiteOrders.getQuantity();
		}
		if (balances == null) {
			balances = new Balances();
			balances.setSuiteSaleMoney(suiteSaleMoney);
			balances.setMoney(suiteSaleMoney);
			balances.setStatus(BalanceStatus.WITHDRAW_INIT.ordinal());
			balances.setUserId(userId);
			balances.setRangeStart(DateUtil.fomatDate(start));
			balances.setRangeEnd(DateUtil.fomatDate(end));
			balancesService.insert(balances);
			suiteOrdersService.updateBalanceId(suiteOrders.getId(), balances.getId());
		} else {
			if (balances.getStatus() == BalanceStatus.WITHDRAW_INIT.ordinal())  {
				double money = balances.getMoney() + suiteSaleMoney;
				suiteSaleMoney = balances.getSuiteSaleMoney() + suiteSaleMoney;
				balancesService.updateMoney(balances.getId(), null, suiteSaleMoney, money);
				suiteOrdersService.updateBalanceId(suiteOrders.getId(), balances.getId());
			}
		}
	}
}