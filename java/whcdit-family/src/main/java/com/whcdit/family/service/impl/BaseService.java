package com.whcdit.family.service.impl;

import java.util.Date;

 
import com.github.pagehelper.PageHelper;
import com.whcdit.family.model.BaseModel;
import com.whcdit.family.service.IBaseService;
import com.whcdit.family.utils.WuhanConstants;
import com.whcdit.family.utils.WuhanMethods;

public class BaseService implements IBaseService {
	
	@Override
	public void settingValid(BaseModel param) {
		param.setDataStatus(WuhanConstants.VALID);

	}

	@Override
	public void settingInvalid(BaseModel param) {
		param.setDataStatus(WuhanConstants.INVALID);
	}

	@Override
	public void settingNoid(BaseModel param, Integer noid) {
		settingValid(param);
		param.setNoid(noid);
	}

	@Override
	public void settingWithInsert(BaseModel param, String desc) {
		settingValid(param);
		Integer uid=param.getUid();		
		Date d = new Date();
		param.setFirstCreateBy(uid);
		param.setFirstCreateDate(d);
		String description = null;		
		if (WuhanMethods.strNullEmpty(desc)) {
			String pd = param.getDescription();
			if (WuhanMethods.strNullEmpty(pd)) {
				description = "【Java程序新增的数据】";
			} else {
				description = param.getDescription();
			}
		} else {
			description = desc;
		}
		param.setDescription(description);
		param.setLastUpdateBy(uid);
		param.setLastUpdateDate(d);
	}

	@Override
	public void settingWithDelete(BaseModel param, String desc) {
		this.settingInvalid(param);
		this.settingWithUpdate(param, desc);		
	}

	@Override
	public void settingWithUpdate(BaseModel param, String desc) {
		Integer uid=param.getUid();	
		String description = null;		
		if (WuhanMethods.strNullEmpty(desc)) {
			String pd = param.getDescription();
			if (WuhanMethods.strNullEmpty(pd)) {
				description = "【Java程序更新了此数据】";
			} else {
				description = param.getDescription();
			}
		} else {
			description = desc;
		}
		param.setDescription(description);
		param.setLastUpdateBy(uid);
		param.setLastUpdateDate(new Date());
	}

	@Override
	public void schedulerWithInsert(BaseModel param, String desc) {
		Integer uid = 20; // 定时任务的用户ID默认为20
		param.setUid(uid);
		this.settingWithInsert(param, desc);
	}

	@Override
	public void schedulerWithUpdate(BaseModel param, String desc) {
		Integer uid = 20; // 定时任务的用户ID默认为20
		param.setUid(uid);
		this.settingWithUpdate(param, desc);
	}

	@Override
	public void pageInit(BaseModel param) {
		// 暂时不考虑超级管理员
		settingValid(param);
		Integer pageNum = param.getPageNum();
		Integer pageSize = param.getPageSize();
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageHelper.startPage(pageNum, pageSize);
	}
}
