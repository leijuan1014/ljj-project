package com.xiss.controller.system.enums;
/** 
* @author leijj
* @since  2017年6月28日 下午5:22:18 
*/

import com.xiss.util.properties.UploadProperties;

public enum UploadType {
	SHOP_IMAGE(UploadProperties.getShopImgPath(), UploadProperties.getShopImgBrowse()),//车行列表图片
	SHOP_DETAIL_IMAGE(UploadProperties.getShopImgDetailPath(), UploadProperties.getShopImgDetailBrowse()),//车行详情图片
	UNION_SHOP_IMAGE(UploadProperties.getUnionshopImgPath(), UploadProperties.getUnionshopImgBrowse()),//联盟商家列表图片
	UNION_SHOP_DETAIL_IMAGE(UploadProperties.getUnionshopImgDetailPath(), UploadProperties.getUnionshopImgDetailBrowse());//联盟商家详情图片
	
	String path;
	String browse;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBrowse() {
		return browse;
	}

	public void setBrowse(String browse) {
		this.browse = browse;
	}

	private UploadType(String path, String browse){
		
		this.path = path;
		this.browse = browse;
	}
	
    /**
     * @param ordinal 序列号
     * @return 结果枚举对象
     */
    public static UploadType get(int ordinal) {
    	UploadType[] list = values();
        for (UploadType type : list) {
            if (type.ordinal() == ordinal) {
                return type;
            }
        }
        return null;
    }
    /**
     * @param name 
     * @return 结果枚举对象
     */
    public static UploadType get(String name) {
    	UploadType[] list = values();
        for (UploadType type : list) {
            if (name.equals(type.name())) {
                return type;
            }
        }
        return null;
    }
    public static void main(String[] args) {
    	UploadType uploadType = UploadType.get("SHOP_IMAGE");
		System.out.println(uploadType.getPath() + "===" + uploadType.getBrowse());
	}
}
