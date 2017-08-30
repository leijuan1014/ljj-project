package com.xiss.test.suiteorder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.xiss.model.order.Suites;
import com.xiss.model.shops.Shops;
import com.xiss.service.order.SuitesService;
import com.xiss.service.shops.ShopsService;
import com.xiss.test.SpringTestCase;

/** 
* @author leijj
* @since  2017年5月10日 上午9:19:58 
*/

//@ContextConfiguration(locations = {"classpath:application.xml"})
public class SuiteTest{// extends SpringTestCase{
	@Autowired
	private SuitesService suitesService;
	@Autowired
	private ShopsService shopsService;
	
	//@Test
	public void saveTest() {
		try {
            String encoding="UTF-8";
            File file=new File("/Users/xiss/Documents/project/xissdoc/正式环境上线数据/车行套餐上线/20170811/车行套餐.csv");
            
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println(lineTxt);
                    if (!lineTxt.contains("车行名称")) {
                    	this.saveSuites(lineTxt);
					}
                }
                read.close();
		    }else{
		        System.out.println("找不到指定的文件");
		    }
	    } catch (Exception e) {
	        System.out.println("读取文件内容出错");
	        e.printStackTrace();
	    }
	}
	public void saveSuites(String lineTxt) {
		String[] params = lineTxt.split(",");
		if(params != null && params.length > 0){
			String shopName = params[0];
			String suiteName = params[1];
			String originPrice = params[2];
			String salePrice = params[3];
			Shops shop = shopsService.getByName(shopName);
			if (shop != null) {
				Suites suites = new Suites();
				suites.setName(suiteName);
				suites.setOriginPrice(Double.valueOf(originPrice));
				suites.setSalePrice(Double.valueOf(salePrice));
				suites.setShopId(shop.getId());
				suitesService.insert(suites);
				System.out.println("=========suites="+suites);
			}
		}
		
	}
}
