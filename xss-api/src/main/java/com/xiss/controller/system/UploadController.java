package com.xiss.controller.system;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiss.api.BasicAPI;
import com.xiss.controller.system.enums.UploadType;
import com.xiss.util.file.PhotoUtil;

/** 
* @author leijj
* @since  2017年6月28日 下午5:20:36 
*/
@Controller
@RequestMapping("/api")
public class UploadController extends  BasicAPI{
	@RequestMapping("/upload/{type}")
	@ResponseBody
	public ModelMap upload(@RequestParam("file") MultipartFile file, @PathVariable("type") String type) throws IllegalStateException, IOException{
		ModelMap model = new ModelMap();
		if(type == null || type.isEmpty())
			return getMessage(false, "请传入上传类型", model);
		String imgPath = "", imgBrowse = "";
		UploadType uploadType = UploadType.get(type);
		if(uploadType != null) {
			imgPath = uploadType.getPath();
			imgBrowse = uploadType.getBrowse();
		}
		
		//不是图片的处理
		if(!PhotoUtil.isImage(file.getOriginalFilename()))
			return getMessage(false, "只能上传jgp/jpeg/png/gif类型的图片", model);
		
		//图片大小不超过5MB
		if(file.getSize() >= 200000)
			return getMessage(false, "请上传小于200k的图片", model);
		
		File desFiledir = new File(imgPath);
		if(!desFiledir.exists())
			desFiledir.mkdir();
		
		File desFile = new File(imgPath + file.getOriginalFilename());
		file.transferTo(desFile);
		if (desFile.exists()) {
			model = getMessage(true, "上传成功", model);
			model.put("imageUrl", imgBrowse + file.getOriginalFilename());
			return model;
		} else
			return getMessage(false, "上传失败，请重新上传", model);
	}
}
