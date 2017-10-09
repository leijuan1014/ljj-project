package com.xiss.model.shops.enums;

/** 
* @author leijj
* @since  2017年6月5日 下午5:13:24 
*/
public enum ShopStatusEnum {
	NO_UPLINE,//未上线	
	UPLINE,//已上线
	DELETE;//已删除
	/**
     * @param name 
     * @return 结果枚举对象
     */
    public static ShopStatusEnum get(String name) {
    	ShopStatusEnum[] list = values();
        for (ShopStatusEnum type : list) {
            if (name.equals(type.name())) {
                return type;
            }
        }
        return null;
    }
}
