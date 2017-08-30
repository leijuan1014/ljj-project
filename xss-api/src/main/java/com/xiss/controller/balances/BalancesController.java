package com.xiss.controller.balances;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xiss.model.balances.Balances;
import com.xiss.model.balances.enums.BalanceStatus;
import com.xiss.model.shops.Shops;
import com.xiss.service.balances.BalancesService;
import com.xiss.service.shops.ShopsService;
import com.xiss.util.Const;
import com.xiss.util.DateUtil;
import com.xiss.util.UuidUtil;
import com.xiss.util.file.PhotoUtil;
import com.xiss.util.upload.FileUpload;
import com.xiss.util.upload.PathUtil;
/** 
* @author leijj
* @since  2017年4月20日 上午11:06:22 
*/
@Controller
@RequestMapping("/balances")
public class BalancesController {
	Logger logger = LoggerFactory.getLogger(BalancesController.class);
	@Autowired
	private BalancesService balancesService;
	
	@Autowired
	private ShopsService shopsService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("/index");
	}
	@RequestMapping("/auth")
	public ModelAndView auth() {
		return new ModelAndView("/auth");
	}
	@RequestMapping("/insert")
	@ResponseBody
	public String Insert(){
		Balances balances = new Balances();
		balances.setMoney(30.0);
		balances.setStatus(BalanceStatus.WITHOUTDRAW.ordinal());
		balances.setUserId(8);
		balancesService.insert(balances);
		return "ok";
	}
	/**
	 * 商户结算申请列表 
	 * @param province 省
	 * @param county 区
	 * @param name 商户名称
	 * */
	@RequestMapping("/applyList")
	@ResponseBody
	public ModelMap applyList(String province, String city, String name, Integer pageNo, Integer pageSize){
		ModelMap model = new ModelMap();
		int total = balancesService.balancesQueryTotal(null, null, null, province, city, name, BalanceStatus.WITHDRAWING.ordinal());
		List<Map<String, Object>> applyList = balancesService.balancesQuery(null, null, null, province, city, name, BalanceStatus.WITHDRAWING.ordinal(), pageSize, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null);
		
		model.put("applyList", applyList);
		model.put("total", total);
		return model;
	}
	
	/**
	 * 商户结算打款列表 
	 * @param start 结算时间开始
	 * @param end 结算时间截止
	 * 
	 * @param province 省
	 * @param county 区
	 * @param name 商户名称
	 * */
	@RequestMapping("/finishedList")
	@ResponseBody
	public ModelMap finishedList(String start, String end, String province, String city, String name, Integer pageNo, Integer pageSize){
		ModelMap model = new ModelMap();
		int total = balancesService.balancesQueryTotal(null, start, end, province, city, name, BalanceStatus.WITHDRAW_SUCCESS.ordinal());
		List<Map<String, Object>> finishedList = balancesService.balancesQuery(null, start, end, province, city, name, BalanceStatus.WITHDRAW_SUCCESS.ordinal(), pageSize, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null);
		
		model.put("finishedList", finishedList);
		model.put("total", total);
		return model;
	}
	
	/**
	 * 商户结算打款列表 
	 * @param start 结算时间开始
	 * @param end 结算时间截止
	 * 
	 * @param province 省
	 * @param county 区
	 * @param name 商户名称
	 * */
	@RequestMapping("/cardDealsList")
	@ResponseBody
	public ModelMap cardDealsList(int balanceId, Integer pageNo, Integer pageSize){
		ModelMap model = new ModelMap();
		Balances balances = balancesService.getById(balanceId);
		
		if (null == balances) {
			return model;
		}
		Shops shops = shopsService.getByUserId(balances.getUserId());
		String start = DateUtil.getDay(balances.getRangeStart()).concat(" 00:00:00");
		String end = DateUtil.getAfterDayDateYMD(1, balances.getRangeEnd()).concat(" 00:00:00");
		int total = balancesService.getCardDealsCountByUserId(balances.getUserId(), start, end);
		List<Map<String, Object>> cardDealsList = balancesService.getCardDealsByUserId(balances.getUserId(), start, end, pageSize, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null);
		model.put("cardDealsList", cardDealsList);
		Map<String, Object> balanceMap = new LinkedHashMap<String, Object>();
		balanceMap.put("id", balances.getId());
		balanceMap.put("userId", balances.getUserId());
		balanceMap.put("money", balances.getMoney());
		balanceMap.put("status", balances.getStatus());
		balanceMap.put("applyAt", DateUtil.getDay(balances.getApplyAt()));
		balanceMap.put("finishedAt", DateUtil.getDay(balances.getFinishedAt()));
		balanceMap.put("createdAt", DateUtil.getDay(balances.getCreatedAt()));
		balanceMap.put("updatedAt", DateUtil.getDay(balances.getUpdatedAt()));
		balanceMap.put("rangeEnd", DateUtil.getDay(balances.getRangeEnd()));
		model.put("balances", balanceMap);
		if (null != shops) {
			model.put("shopName", shops.getName());
		}
		model.put("total", total);
		return model;
	}
	
	/**确认结算*/
	@ResponseBody
	@RequestMapping("/finished")
	public Map<String, String> finished(int id, HttpServletRequest request) {
		Map<String, String> model = new LinkedHashMap<String, String>();
		Balances balances = balancesService.getById(id);
		if (null != balances) {
			balancesService.finish(id, BalanceStatus.WITHDRAW_SUCCESS.ordinal(), "");
			model.put("code", "SUCCESS");
		} else {
			model.put("message", "当前结算单不存在");
		}
		return model;
	}
	
	/**确认结算*/
	@ResponseBody
	@RequestMapping("/finishedImg")
	public Map<String, String> finishedImg(@RequestParam("receiptImage") MultipartFile receiptImage, int id, HttpServletRequest request) {
		String  ffile = DateUtil.getDays(), fileName = "";
		Map<String, String> model = new LinkedHashMap<String, String>();
		//不是图片的处理
		if(!PhotoUtil.isImage(receiptImage.getOriginalFilename())){
			model.put("message", "只能上传jgp/jpeg/png/gif类型的图片");
			return model;
		}
		
		//图片大小不超过5MB
		if(receiptImage.getSize()/1024/1024 >= 5){
			model.put("message", "请上传小于5M的图片");
			return model;
		}
		String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
		if (null != receiptImage && !receiptImage.isEmpty()) {
			System.out.println("========filePath====" + filePath);
			fileName = FileUpload.fileUp(receiptImage, filePath, this.get32UUID());				//执行上传
		}else{
			model.put("message", "上传失败");
		}
		try {
			Balances balances = balancesService.getById(id);
			if (null != balances) {
				balancesService.finish(id, BalanceStatus.WITHDRAW_SUCCESS.ordinal(), fileName);
				// 设置头像
				model.put("filename", fileName);
				model.put("fileUrl", filePath);
				model.put("code", String.valueOf("SUCCESS"));
			} else {
				model.put("message", "当前结算单不存在");
			}
		} catch (Exception e) {
			model.put("message", "上传图片异常");
			// 上传文件失败
			logger.info("setPhoto.uploadPhoto.faluire", e);
		}
		return model;
	}
	/**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
}
