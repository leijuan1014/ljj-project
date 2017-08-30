package com.xiss.api.system;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiss.util.QRCodeUtil;

/**  
 * @Description: (二维码)     
 * @author：luoguohui  
 * @date：2015-10-29 下午05:27:13     
 */
@Controller
@RequestMapping("/api/util")
public class QRCodeAPI
{
    @RequestMapping("/qrcode")
	@ResponseBody
    public void qrcode(String url, String mobile, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	BufferedImage image = QRCodeUtil.getLogoQRCode("https://www.baidu.com/", "跳转到百度的二维码", request);
    	ImageIO.write(image, "JPEG", response.getOutputStream());
	}
}