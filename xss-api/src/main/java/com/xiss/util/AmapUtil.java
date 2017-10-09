package com.xiss.util;

import ch.hsr.geohash.GeoHash;

public class AmapUtil {

	/**
	 * 构造distance函数表达式。
	 * 
	 * @param longitudeField 经度字段名
	 * @param latitudeField 纬度字段名
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @return distance函数表达式
	 */
	public static String dist(String longitudeField, String latitudeField, double longitude, double latitude) {
		StringBuilder builder = new StringBuilder();
		builder.append("distance(")
		.append(longitudeField).append(",")
		.append(latitudeField).append(",\"")
		.append(String.format("%.6f", longitude)).append("\",\"")
		.append(String.format("%.6f", latitude)).append("\")");
		return builder.toString();
	}
	
    /** 
     * 使用Google地图公式计算地球上任意两点(经纬度)距离。
     *  
     * @param long1 第一点经度 
     * @param lat1 第一点纬度 
     * @param long2 第二点经度 
     * @param lat2 第二点纬度 
     * @return 返回距离 单位：米 
     */  
    public static double distance(double long1, double lat1, double long2, double lat2) {
        double a, b, R;  
        R = 6378137; // 地球半径  
        lat1 = lat1 * Math.PI / 180.0;  
        lat2 = lat2 * Math.PI / 180.0;  
        a = lat1 - lat2;  
        b = (long1 - long2) * Math.PI / 180.0;  
        double d;  
        double sa2, sb2;  
        sa2 = Math.sin(a / 2.0);  
        sb2 = Math.sin(b / 2.0);  
        d = 2  
                * R  
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
                        * Math.cos(lat2) * sb2 * sb2));  
        return d;  
    }
    
    /**
     * 将米换算为公里
     * */
    public static double distanceKM(double distance){
    	return distance/1000;
    }
    
    public static void main(String args[]) {
    	double long1 = 113.696978, lat1=34.80561, long2=113.6703365, lat2=34.7951719;
    	System.out.println(distanceKM(distance(long1, lat1, long2, lat2)));
	}
}