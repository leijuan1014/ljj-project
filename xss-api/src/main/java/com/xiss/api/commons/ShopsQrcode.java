package com.xiss.api.commons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.xiss.util.MatrixToImageWriter;

/** 
* @author leijj
* @since  2017年5月3日 下午4:04:46 
*/
@Controller
public class ShopsQrcode {

	@ResponseBody
	@RequestMapping("/qrcode")
	public void getQrcode(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("======" + request.getContextPath());
		ModelMap modelMap = new ModelMap();
		String qrcodePath = "";
		
		String text = "http://baidu.com"; // 二维码内容  
        int width = 300; // 二维码图片宽度  
        int height = 300; // 二维码图片高度  
          
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   // 内容所使用字符集编码  
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
	        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			BufferedImage image = new BufferedImage(750, 750, BufferedImage.TYPE_INT_RGB);
			image.getGraphics().drawImage(bufferedImage.getScaledInstance(750, 750,java.awt.Image.SCALE_FAST),0,0,null);
			ImageIO.write(image, "jpeg", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        /*
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			// 生成二维码  
	        File outputFile = new File("./" + File.separator + "new.gif");
	        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile); 
	        System.out.println(outputFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}  
		*/
		modelMap.put("qrcodePath", qrcodePath);
	}
}
