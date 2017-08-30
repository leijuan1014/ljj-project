package com.xiss.api.shops;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.xiss.api.BasicAPI;
import com.xiss.model.balances.Balances;
import com.xiss.model.order.enums.OrderState;
import com.xiss.model.shops.Shops;
import com.xiss.model.shops.enums.ShopStatusEnum;
import com.xiss.model.system.RolesEnum;
import com.xiss.model.system.Users;
import com.xiss.service.balances.BalancesService;
import com.xiss.service.shops.CardDealsService;
import com.xiss.service.shops.DealsService;
import com.xiss.service.shops.ShopsService;
import com.xiss.service.system.UsersService;
import com.xiss.util.MatrixToImageWriter;

import jdk.nashorn.internal.runtime.regexp.joni.constants.NodeStatus;

/** 
* @author leijj
* @since  2017-4-11
*/
@Controller
@RequestMapping("/api/shops")
public class ShopsAPI extends BasicAPI{
	
	@Autowired
	private ShopsService shopsService;
	
	@Autowired
	private DealsService dealsService;
	
	@Autowired
	private CardDealsService cardDealsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BalancesService balancesService;
	
	@RequestMapping("/getById")
	@ResponseBody
	public ModelMap getById(@RequestParam Integer id) {
		ModelMap model = new ModelMap();
		if(id == null)
			return getMessage(false, "参数错误", model); 
		
		Shops shops = shopsService.getById(id);
		model.put("success", true);
		model.put("shops", shops);
		return model;
	}
	
	/**
	 * 移动端：根据登录手机号获取商户信息
	 * 返回数据类型说明
	 * 服务商ID:id,登录账号:mobile,服务商名称:name,服务商地址:address,服务商姓名:linkman,联系电话:phone
	 * */
	@RequestMapping("/getByToken")
	@ResponseBody
	public ModelMap getByMobile(@RequestParam String token) {
		ModelMap model = new ModelMap();
		Users users = usersService.getByToken(token);
		if (users != null) {
			model.put("user_id", users.getId());
			model.put("mobile", users.getMobile());
			Shops shops = shopsService.getByUserId(users.getId());
			if (shops != null) {
				model.put("shop_id", shops.getId());
				model.put("name", shops.getName() == null ? "" : shops.getName());
				model.put("address", shops.getAddress() == null ? "" : shops.getAddress());
				model.put("linkman", shops.getLinkman() == null ? "" : shops.getLinkman());
				model.put("phone", shops.getPhone() == null ? "" : shops.getPhone());
				String avatar = users.getAvatar();
				if (avatar == null || !(avatar.endsWith(".png")  || avatar.endsWith(".jpg") || avatar.endsWith(".jpeg"))) {
					avatar = "https://api.autoxss.com/shops/default_avatar.png";
				}
				model.put("image", avatar);
			}
		}
		return model;
	}
	/**
	 * 洗车明细
	 * 返回：洗车总数、补贴金额
	 * 车牌、洗车时间
	 * */
	@RequestMapping("/getCarWashDealsByToken")
	@ResponseBody
	public ModelMap getCarWashDetail(String token, String yearMonth, int pageNo, int pageSize){
		ModelMap model = new ModelMap();
		Users users = usersService.getByToken(token);
		Shops shops = shopsService.getByUserId(users.getId());
		int carWashCount = dealsService.getCarWashDealsCountByUserId(shops.getId(), yearMonth);
		int carWashMonthNum = shops.getCarWashMonthNum();
		double carWashMonthAmount = shops.getCarWashMonthAmount();
		double carWashPerAmount = shops.getCarWashPerAmount();
		double commission = carWashMonthAmount;
		if(carWashCount > carWashMonthNum && carWashPerAmount > 0) {
			commission += (carWashCount - carWashMonthNum) * carWashPerAmount;
		}
		model.put("carWashCount", carWashCount);
		model.put("commission", commission);
		Map<String, Object> carWashDeals = new HashMap<String, Object>();
		carWashDeals.put("pageNo", pageNo);
		carWashDeals.put("pageSize", pageSize);
		carWashDeals.put("list", dealsService.getDealsByShopId(shops.getId(), yearMonth, pageNo, pageSize));
		model.put("carWashDeals", carWashDeals);
		return model;
	}
	/**
	 * 售卡提成明细
	 * 返回：售卡总数，总提成
	 * 售卡时间、提成、手机号、车牌
	 * */
	@RequestMapping("/getCardDealsByToken")
	@ResponseBody
	public ModelMap getCardDealDetail(int balanceId, String token, String start, String end, Integer pageNo, Integer pageSize){
		ModelMap model = new ModelMap();
		Balances balances = balancesService.getById(balanceId);
		
		if (null == balances) {
			return model;
		}
		Users users = usersService.getByToken(token);
		
		//int count = cardDealsService.getCardDealsCountByUserId(users.getId(), start, end);
		//double commission = cardDealsService.getCardDealsSumCommissionByUserId(users.getId(), start, end);
		int count = cardDealsService.getSellRecordCount(users.getId(), balanceId, OrderState.SUCCESS.ordinal(), start, end);
		model.put("cardDealsCount", count);
		model.put("commission", balances.getMoney());
		
		Map<String, Object> cardDeals = new HashMap<String, Object>();
		cardDeals.put("pageNo", pageNo);
		cardDeals.put("pageSize", pageSize);
		List<Map<String, Object>> records = cardDealsService.getSellRecords(users.getId(), balanceId, OrderState.SUCCESS.ordinal(), start, end, pageNo, pageSize);
		cardDeals.put("list", records);
		//cardDeals.put("list", cardDealsService.getCardDealsByUserId(users.getId(), start, end, pageNo, pageSize));
		model.put("cardDeals", cardDeals);
		return model;
	}
	
	@ResponseBody
	@RequestMapping("/qrcode")
	public void getQrcode(String url, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("=========url=" + url);
        int width = 300; // 二维码图片宽度  
        int height = 300; // 二维码图片高度  
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");   // 内容所使用字符集编码  
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
	        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
			image.getGraphics().drawImage(bufferedImage.getScaledInstance(300, 300, Image.SCALE_FAST),0,0,null);
			response.setContentType("image/jpeg;charset=UTF-8");  
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping("/qrcode1")
	public void getQrcode1(String url, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("=========url=" + url);
        int width = 300; // 二维码图片宽度  
        int height = 300; // 二维码图片高度  
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");   // 内容所使用字符集编码  
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
	        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
			image.getGraphics().drawImage(bufferedImage.getScaledInstance(300, 300, Image.SCALE_FAST),0,0,null);
			response.setContentType("image/jpeg;charset=UTF-8");  
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Shops> getShops(String type, String name, String province, String city, String county, Integer pageNo, Integer pageSize) {
		Integer status = null;
		if (type != null && !type.isEmpty()) {
			ShopStatusEnum statusEnum = ShopStatusEnum.get(type);
			status = statusEnum.ordinal();
		}
		
		List<Shops> shops = shopsService.getShops(status, name, province, city, county, pageNo, pageSize);
		return shops;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ModelMap saveShop(String mobile, String name, String phone, String province, String city, String county, Integer star, Integer status, String profile, String openning, String address, String longitude, String latitude, String image , String detailImages) {
		ModelMap model = new ModelMap();
		if(mobile == null || mobile.isEmpty()
				|| name == null || name.isEmpty())
			return getMessage(false, "参数错误", model); 
		
		Users user = usersService.getByMobile(mobile);
		if (user == null) {
			user = new Users(mobile, mobile, RolesEnum.SHOP_OWNER.ordinal());
			usersService.save(user);
			System.out.println("=====user=" + user);
		}
		if(user == null || user.getId() <= 0)
			return getMessage(false, "创建账号失败", model); 
		Shops shops = shopsService.getByUserId(user.getId());
		if(shops != null && shops.getId() > 0)
			return getMessage(false, "该手机号码下存在车行", model); 
		detailImages = "{".concat(detailImages).concat("}");
		if(status == null) status = 0;
		if(star == null) star = 0;
		shops = new Shops(user.getId(), name, phone, province, city, county, star, "车行", status, profile, openning, address, longitude, latitude, image, detailImages);
		shopsService.save(shops);
		System.out.println("=======shops=" + shops);
		if(shops != null && shops.getId() > 0)
			return getMessage(true, "创建车行成功", model); 
		else
			return getMessage(false, "创建车行失败", model); 
	}
	
	@RequestMapping("/updateStatus")
	@ResponseBody
	public ModelMap updateStatus(Integer id, String type) {
		ModelMap model = new ModelMap();
		if(id == null)
			return getMessage(false, "参数错误", model);
		
		ShopStatusEnum statusEnum = ShopStatusEnum.get(type);
		if(statusEnum == null)
			return getMessage(false, "传入的状态类型错误", model);
		
		int status = statusEnum.ordinal();
		shopsService.updateStatus(id, status);
		
		return getMessage(true, "状态修改成功", model);
	}
}