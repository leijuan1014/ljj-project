package com.xiss.util;

import java.util.Comparator;

/** 
* @author leijj
* @since  2017年4月12日 下午12:05:18 
*/
public class MyComparator implements Comparator{
	public int compare(Object element1, Object element2) {
		int x = element2.toString().compareTo(element1.toString());
		return x;
	}
}
