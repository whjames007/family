package com.whcdit.family.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.whcdit.family.mapper.SystemMenuInfoMapper;
import com.whcdit.family.mapper.SystemRoleInfoMapper;
import com.whcdit.family.mapper.SystemUserInfoMapper;
import com.whcdit.family.model.SystemMenuInfo;
import com.whcdit.family.model.SystemRoleInfo;
import com.whcdit.family.model.SystemUserInfo;
import com.whcdit.family.service.IUserService;
import com.whcdit.family.utils.WuhanConstants;

@Service
public class UserService extends BaseService implements IUserService {

	@Autowired
	private SystemUserInfoMapper systemUserInfoMapper;
	@Autowired
	private SystemRoleInfoMapper systemRoleInfoMapper;
	@Autowired
	private SystemMenuInfoMapper systemMenuInfoMapper;

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public void login(SystemUserInfo param) {
		// 获取登录者的角色信息
		SystemRoleInfo role = systemRoleInfoMapper.selectByPrimaryKey(param.getSystemRoleId());
		param.setSystemRoleName(role.getSystemRoleName());
		// 获取登录者的菜单信息
		SystemMenuInfo root = systemMenuInfoMapper.selectByPrimaryKey(1);
		SystemMenuInfo pbranch = new SystemMenuInfo();
		pbranch.setSystemParentId(root.getSystemMenuId());
		List<SystemMenuInfo> branchs = systemMenuInfoMapper.selectWithCondition(pbranch);
		for (SystemMenuInfo bra : branchs) {
			SystemMenuInfo pleaf = new SystemMenuInfo();
			pleaf.setSystemParentId(bra.getSystemMenuId());
			List<SystemMenuInfo> leafs = systemMenuInfoMapper.selectWithCondition(pleaf);
			// 存储三级导航URL和判断可关闭布尔标识
			leafs.forEach(e-> {
				e.setSystemUrlFirst(root.getSystemMenuUrl());
				e.setSystemUrlSecond(bra.getSystemMenuUrl());
				e.setSystemUrlThird(e.getSystemMenuUrl());
				if (WuhanConstants.YES.equals(e.getSystemMenuClose())) {
					e.setSystemBoolClose(true);
				} else {
					e.setSystemBoolClose(false);
				}
			});
			bra.setChilds(leafs);
		}
		root.setChilds(branchs);
		param.setMenu(root);
		// 为前端初始化基础属性
		param.setUid(param.getSystemUserId());
		param.setPageNum(1);
		param.setPageSize(10);
	}

	@Override
	public int add(SystemUserInfo param) {
		super.settingWithInsert(param, "新增用户账户数据");
		int res = systemUserInfoMapper.insertSelective(param);
		return res;
	}

	@Override
	public int del(SystemUserInfo param) {
		// 本系统约定采用假删除策略
		super.settingWithDelete(param, "本系统约定采用假删除策略");
		int res = systemUserInfoMapper.updateByPrimaryKeySelective(param);
		return res;
	}

	@Override
	public int edit(SystemUserInfo param) {
		super.settingWithUpdate(param, null);
		int res = systemUserInfoMapper.updateByPrimaryKeySelective(param);
		return res;
	}

	@Override
	public List<SystemUserInfo> list(SystemUserInfo param) {
		super.settingValid(param);
		return systemUserInfoMapper.selectWithCondition(param);
	}

	@Override
	public PageInfo<SystemUserInfo> page(SystemUserInfo param) {
		super.pageInit(param);
		List<SystemUserInfo> list = systemUserInfoMapper.selectWithMorethan(param);
		PageInfo<SystemUserInfo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public boolean repeat(SystemUserInfo param) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SystemMenuInfo> menus(SystemUserInfo param) {
		SystemMenuInfo pm = new SystemMenuInfo();
		super.settingValid(pm);
		return systemMenuInfoMapper.selectWithCondition(pm); 
	}

	@Override
	public PageInfo<SystemRoleInfo> rolePage(SystemRoleInfo param) {
		super.pageInit(param);
		List<SystemRoleInfo> list = systemRoleInfoMapper.selectWithCondition(param);
		PageInfo<SystemRoleInfo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<SystemMenuInfo> menuList(SystemMenuInfo param) {
		return systemMenuInfoMapper.selectWithCondition(param);
	}

//	@Override
//	public boolean repeat(UserInfo param) {
//		boolean bool = true;
//		UserInfo check = new UserInfo();
//		super.settingNoid(check, param.getUserId());
//		check.setUserAccount(param.getUserAccount());
//		List<UserInfo> list = userInfoMapper.selectWithCondition(check);
//		if (GeweiMethods.listNullEmpty(list)) {
//			// 不重复就返回假
//			bool = false;
//		} else {
//			// 出现重复就返回真
//			bool = true;
//			logger.warn("【用户账号[{}]有重复】", param.getUserAccount());
//		}
//		return bool;
//	}

}
