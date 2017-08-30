package com.xiss.api.system;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.xiss.api.BasicAPI;
import com.xiss.model.order.GrowingUsers;
import com.xiss.model.order.enums.TradeNoTypeEnum;
import com.xiss.model.system.Users;
import com.xiss.service.order.GrowingUsersService;
import com.xiss.service.system.UsersService;
import com.xiss.util.IdMangler;
import com.xiss.util.MatrixToImageWriter;
import com.xiss.util.QRCodeUtil;

/** 
* @author leijj
* @since  2017年5月3日 下午4:39:21 
*/
@Controller
@RequestMapping("/api/users")
public class UsersAPI extends BasicAPI{

	@Autowired
	private UsersService usersService;
	@Autowired
	private GrowingUsersService growingUsersService;
	
	@ResponseBody
	@RequestMapping("/register")
	public ModelMap register(String mobile, String invitationId, String invitationToken, String payType) {
		System.out.println("======mobile=" + mobile + ",invitationToken=" + invitationToken);
		ModelMap modelMap = new ModelMap();
		if (null == mobile || mobile.isEmpty() || 
				((invitationId == null || invitationId.isEmpty()) && 
						(invitationToken == null || invitationToken.isEmpty()))) {
			modelMap.put("success", false);
			modelMap.put("message", "手机号或者推荐人为空");
			return modelMap;
		}
		Users shopUser = null;
		if (invitationId != null && ! invitationId.isEmpty()) 
			shopUser = usersService.getById((int)IdMangler.demangle(invitationId));
		else if(invitationToken != null && ! invitationToken.isEmpty())
			shopUser = usersService.getByToken(invitationToken);
		
		if (null == shopUser) {
			modelMap.put("success", false);
			modelMap.put("message", "推荐商家不存在");
			return modelMap;
		}
		if(TradeNoTypeEnum.WEEK.name().equals(payType)){//活动7天体验卡
			GrowingUsers growingUsers = growingUsersService.getByMobile(mobile);
			if (growingUsers != null && growingUsers.isEnrolled520()) {
				modelMap.put("success", false);
				modelMap.put("message", "您已经参加过7天体验活动");
				return modelMap;
			} else if (growingUsers == null) {
				growingUsers = new GrowingUsers();
				growingUsers.setMobile(mobile);
				growingUsers.setStatus(0);
				growingUsersService.insert(growingUsers);
			}
		}
		
		Users users = usersService.getByMobile(mobile);
		if (null == users) {
			users = new Users();
			users.setEmail(mobile);
			users.setMobile(mobile);
			users.setInvitationToken(shopUser.getAuthenticationToken());
			users.setInvitedBy(shopUser.getId());
			usersService.save(users);
		} else if((users.getInvitationToken() == null || users.getInvitationToken().isEmpty()) && users.getInvitedBy() == 0){
			usersService.updateInvited(mobile, shopUser.getAuthenticationToken(), shopUser.getId());
		}
		modelMap.put("success", true);
		modelMap.put("message", "成功");
		/*else {
			modelMap.put("success", false);
			modelMap.put("message", "您已经被商家推荐过");
			return modelMap;
		}
		System.out.println("=========id=" + id);
		if (id > 0){
			modelMap.put("success", true);
			modelMap.put("message", "success");
		} else {
			modelMap.put("success", false);
			modelMap.put("message", "fail");
		}*/
		return modelMap;
	}
	
	@ResponseBody
	@RequestMapping("/getIdByToken")
	public ModelMap getIdByToken(String token) {
		ModelMap modelMap = new ModelMap();
		Users users = usersService.getByToken(token);
		if(users == null) {
			modelMap.put("success", false);
			modelMap.put("message", "用户不存在");
			return modelMap;
		} else {
			modelMap.put("success", true);
			modelMap.put("id", IdMangler.mangle(users.getId()));
			return modelMap;
		}
	}
	
	@ResponseBody
	@RequestMapping("/getIdByMobile")
	public ModelMap getIdByMobile(String mobile) {
		ModelMap modelMap = new ModelMap();
		Users users = usersService.getByMobile(mobile);
		if(users == null) {
			modelMap.put("success", false);
			modelMap.put("message", "用户不存在");
			return modelMap;
		} else {
			modelMap.put("success", true);
			modelMap.put("id", IdMangler.mangle(users.getId()));
			return modelMap;
		}
	}
	
	@RequestMapping("/qrcode")
	@ResponseBody
    public ModelMap qrcode(String mobile, String memo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelMap modelMap = new ModelMap();
		if (mobile == null || mobile.isEmpty()) 
			return getMessage(false, "请输入手机号", modelMap);
		
		Users users = usersService.getByMobile(mobile);
		if(users == null) {
			users = new Users();
			users.setEmail(mobile);
			users.setMobile(mobile);
			usersService.save(users);
		}
		System.out.println("=======users.id=" + users.getId());
		String url = "https://api.autoxss.com/sales/distribution.html?business_id=" + IdMangler.mangle(users.getId());
    	BufferedImage image = QRCodeUtil.getLogoQRCode(url, memo, request);
    	response.setContentType("image/jpeg;charset=UTF-8");  
    	ImageIO.write(image, "JPEG", response.getOutputStream());
    	return modelMap;
	}
}