package com.whcdit.family.controller;

import com.whcdit.family.utils.WuhanConstants;
import com.whcdit.family.utils.WuhanResp;

public class BaseController<T> {

	public void baseExcep(Exception e, WuhanResp<T> res) {
		e.printStackTrace();
		res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
	}
	
	 

}
