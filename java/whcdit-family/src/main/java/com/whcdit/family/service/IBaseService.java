package com.whcdit.family.service;

import com.whcdit.family.model.BaseModel;

public interface IBaseService {
	
	/**
	 * 【2019-05-15】【武汉】【宋鹏-18091】【赋值数据状态为有效状态】
	 */
	public void settingValid(BaseModel param);

	/**
	 * 【2019-05-15】【武汉】【宋鹏-18091】【赋值数据状态为无效状态】
	 */
	public void settingInvalid(BaseModel param);

	/**
	 * 【2019-05-15】【武汉】【宋鹏-18091】【赋值非本ID】
	 */
	public void settingNoid(BaseModel param, Integer noid);

	/**
	 * 【2019-05-15】【武汉】【宋鹏-18091】【插入数据时为基本属性赋值】
	 */
	public void settingWithInsert(BaseModel param, String desc);

	/**
	 * 【2019-05-15】【武汉】【宋鹏-18091】【假删除数据时为基本属性赋值】
	 */
	public void settingWithDelete(BaseModel param, String desc);

	/**
	 * 【2019-05-15】【武汉】【宋鹏-18091】【更新数据时为基本属性赋值】
	 */
	public void settingWithUpdate(BaseModel param, String desc);

	/**
	 * 【2019-12-15】【武汉】【宋鹏-18091】【定时任务插入数据时为基本属性赋值】
	 */
	public void schedulerWithInsert(BaseModel param, String desc);

	/**
	 * 【2019-12-15】【武汉】【宋鹏-18091】【定时任务更新数据时为基本属性赋值】
	 */
	public void schedulerWithUpdate(BaseModel param, String desc);
	
	/**
	 * 【分页查询前的初始化工作】【宋鹏-18091】【武汉】【2020-04-26】
	 */
	public void pageInit(BaseModel param);
	

}
