package com.whcdit.family.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WuhanMethods {
	
	/**
	 * 【判定字符串是空值】【宋鹏-18091】【武汉】【2019-10-23】
	 */
	public static boolean strNullEmpty(String param) {
		boolean result = false;
		if (param == null) {
			result = true;
		} else {
			if (param.equals("")) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}
	
	/**
	 * 【判定集合是空值】【宋鹏-18091】【武汉】【2019-10-23】
	 */
	public static boolean listNullEmpty(List<?> param) {
		boolean result = false;
		if (param == null) {
			result = true;
		} else {
			if (param.isEmpty()) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}
	
	/**
	 * 【获取毫秒级字符串】【宋鹏-18091】【武汉】【2019-10-23】
	 */
	public synchronized static String getUnicode(String prefix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String res = sdf.format(new Date());
		return prefix+res;
	}
	
	/**
	 * 【获取拆分后的年月日数字】【宋鹏-18091】【武汉】【2019-10-23】
	 */
	public  static Map<Integer, Integer> getYMD(Date date) {
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dstr = sdf.format(date);
		String[] darr = dstr.split("-");
		Map<Integer, Integer> map = new HashMap<>();;
		map.put(1, Integer.valueOf(darr[0]));
		map.put(2, Integer.valueOf(darr[1]));
		map.put(3, Integer.valueOf(darr[2]));
		return map;
	}
			
}
