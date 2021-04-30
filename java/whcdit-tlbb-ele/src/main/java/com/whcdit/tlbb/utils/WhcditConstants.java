package com.whcdit.tlbb.utils;

import com.whcdit.tlbb.model.WhcditParameter;

public class WhcditConstants {
	
	public static WhcditParameter wcp = null;
	
	public static final String PARAM_PATH_NAME = "./tlbb.ini"; 
	
	/**
	 * 【成功】【宋鹏-18091】【武汉】【2019-08-23】
	 */
	public static final String SUCCESS = "SUCCESS";
	
	/**
	 * 【失败】【宋鹏-18091】【武汉】【2019-08-23】
	 */
	public static final String FAILURE = "FAILURE";


	/**
	 * WUHAN_RESPONSE_10000=一切正常
	 */
	public static final String WUHAN_RESPONSE_10000 = "WUHAN_RESPONSE_10000";
	/**
	 * WUHAN_RESPONSE_20001=Java报错
	 */
	public static final String WUHAN_RESPONSE_20001 = "WUHAN_RESPONSE_20001";
	/**
	 * WUHAN_RESPONSE_30001=账号或密码错误
	 */
	public static final String WUHAN_RESPONSE_30001 = "WUHAN_RESPONSE_30001";
	/**
	 * WUHAN_RESPONSE_30002=用户帐号重复
	 */
	public static final String WUHAN_RESPONSE_30002 = "WUHAN_RESPONSE_30002";
	/**
	 * WUHAN_RESPONSE_30003=网关序列号重复
	 */
	public static final String WUHAN_RESPONSE_30003 = "WUHAN_RESPONSE_30003";
}
