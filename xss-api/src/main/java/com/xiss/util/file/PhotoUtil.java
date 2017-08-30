package com.xiss.util.file;

public class PhotoUtil {
	
	public static boolean isImage(String originalFilename) {
		
		//jpg;*.jpeg;*.gif;*.png
		if(originalFilename!=null && !originalFilename.isEmpty() && originalFilename.contains(".")){
			String fileExt = originalFilename.substring(originalFilename.lastIndexOf("."));
			if(fileExt.equals(".jpg")
					|| fileExt.equals(".jpeg")
					|| fileExt.equals(".gif")
					|| fileExt.equals(".png")){
				return true;
			}
		}
		return false;
	}
}
