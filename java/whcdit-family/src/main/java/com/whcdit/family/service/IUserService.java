package com.whcdit.family.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.whcdit.family.model.SystemMenuInfo;
import com.whcdit.family.model.SystemRoleInfo;
import com.whcdit.family.model.SystemUserInfo;

public interface IUserService {

	public SystemUserInfo login(Integer uid);
	
	public int add(SystemUserInfo param);
	
	public int del(SystemUserInfo param);
	
	public int edit(SystemUserInfo param);
	
	public List<SystemUserInfo> list(SystemUserInfo param);
	
	public PageInfo<SystemUserInfo> page(SystemUserInfo param);

	public boolean repeat(SystemUserInfo param);

	public List<SystemMenuInfo> menus(SystemUserInfo param);

	public PageInfo<SystemRoleInfo> rolePage(SystemRoleInfo param);

	public List<SystemMenuInfo> menuList(SystemMenuInfo param);


}
