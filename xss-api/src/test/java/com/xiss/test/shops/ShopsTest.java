
package com.xiss.test.shops;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.xiss.model.shops.Shops;
import com.xiss.model.system.CarModel;
import com.xiss.model.system.RolesEnum;
import com.xiss.model.system.Users;
import com.xiss.service.shops.CardDealsService;
import com.xiss.service.shops.ShopsService;
import com.xiss.service.system.CarModelService;
import com.xiss.service.system.UsersService;
import com.xiss.test.SpringTestCase;

/** 
* @author leijj
* @since  2017年4月9日 下午4:38:31 
*/

@ContextConfiguration(locations = {"classpath:application.xml"})

public class ShopsTest {//extends SpringTestCase{
	private static Logger logger = Logger.getLogger(ShopsTest.class);
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("######0.00");   
		double val = 9.959999999999999;
		System.out.println(df.format(val));
	}
	@Autowired
	private UsersService usersService;
	@Autowired
	private ShopsService shopsService;
	@Autowired
	private CardDealsService cardDealsService;
	@Autowired
	private CarModelService carModelService;
	
	//@Test
	public void shopTest() {
		this.saveShop("鹤壁·车道汽车服务会所,18939277755,18939277755,车行,鹤壁,淇滨区,九州路与泰山路交叉口向西20米路南,114.313294,35.746383,鹤壁·车道汽车服务会所.jpg,鹤壁·车道汽车服务会所.detail.jpg,8：00-19:30");
		/*try {
            String encoding="UTF-8";
            File file=new File("/Users/xiss/Documents/project/xissdoc/正式环境上线数据/车行上线/20170720/鹤壁上线表10.csv");
            
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println(lineTxt);
                    if (!lineTxt.contains("车行名称")) {
                    	this.saveShop(lineTxt);
					}
                }
                read.close();
		    }else{
		        System.out.println("找不到指定的文件");
		    }
	    } catch (Exception e) {
	        System.out.println("读取文件内容出错");
	        e.printStackTrace();
	    }*/
	}
	public void saveShop(String lineTxt) {
		String[] params = lineTxt.split(",");
		Users users = usersService.getByMobile(params[2]);
		if(users == null){
			String mobile = params[2];
			users = new Users(mobile, mobile, RolesEnum.SHOP_OWNER.ordinal());
			usersService.save(users);
		}
		System.out.println("=============users="+users);
		Shops shops = new Shops();
		if(params != null && params.length > 0){
			shops.setName(params[0]);
			shops.setPhone(params[1]);
			shops.setCategory(params[3]);
			shops.setProvince("河南省");
			shops.setCity(params[4]);
			shops.setCounty(params[5]);
			shops.setStar(5);
			shops.setStatus(1);
			shops.setAddress(params[6]);
			shops.setLongitude(params[7]);
			shops.setLatitude(params[8]);
			StringBuilder position = new StringBuilder();
			position.append("{").append(params[8]).append(",").append(params[7]).append("}");
			shops.setPosition(position.toString());
			shops.setImage(params[9]);
			shops.setDetailImages("{https://api.autoxss.com/images/shopdetails/".concat(params[10]).concat("}"));
			shops.setOpenning(params[11]);
		}
		
		shops.setUserId(users.getId());
		shopsService.save(shops);
		
		System.out.println("=========shops="+shops);
	}
	//@Test
	public void detalsTest(){
		//Users users = usersService.getByToken("KULTRJRb_as2Dx47mEn5");
		List<Map<String, Object>> results = cardDealsService.getTotalCommissionSellerId(8, null, null);
		if (results != null) {
			System.out.println(results);
		}
		
		//cardDealsService.getCardDealsByUserId(8, "2017-03", 1, 10);
	}
}
