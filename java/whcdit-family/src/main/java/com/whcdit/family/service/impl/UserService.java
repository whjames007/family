package com.whcdit.family.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.whcdit.family.mapper.SystemMenuInfoMapper;
import com.whcdit.family.mapper.SystemRoleInfoMapper;
import com.whcdit.family.mapper.SystemRoleRelationMapper;
import com.whcdit.family.mapper.SystemUserInfoMapper;
import com.whcdit.family.model.SystemMenuInfo;
import com.whcdit.family.model.SystemRoleInfo;
import com.whcdit.family.model.SystemRoleRelation;
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
	private SystemRoleRelationMapper systemRoleRelationMapper;
	@Autowired
	private SystemMenuInfoMapper systemMenuInfoMapper;

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public SystemUserInfo login(Integer uid) {
		SystemUserInfo param = new SystemUserInfo();
		param.setUid(uid);
		param.setSystemUserId(uid);
		super.settingValid(param);
		// 获取登录者的角色信息和家族信息
		SystemUserInfo user = systemUserInfoMapper.selectWithMorethan(param).get(0);
		// 获取登录者的菜单信息
		SystemRoleRelation root = findRoleMenu(user.getSystemRoleId());
		user.setMenu(root);
		// 为前端初始化基础属性
		user.setUid(param.getSystemUserId());
		user.setPageNum(1);
		user.setPageSize(10);
		// 更新登录时间
		super.settingWithUpdate(param, "登录成功，更新登录时间");
		param.setLastLoginTime(new Date());
		this.edit(param);
		return user;
	}
	
	private SystemRoleRelation findRoleMenu(Integer rid) {
		// 1、获取全部菜单数据
		List<SystemMenuInfo> menus = this.menuList(new SystemMenuInfo());
		// 构造根节点
		SystemMenuInfo rootm = findSingleMenu(1, menus);
		// 获取某角色的所有菜单关联关系
		List<SystemRoleRelation> list = systemRoleRelationMapper.selectWithRoleId(rid);
		// 基于此不完整的关联关系，重构完整架构（因为数据库中保存的是前端tree组件提供的不完整菜单ID，可能包含根节点，也可能包含分支节点）
		Map<Integer,SystemRoleRelation > map = new HashMap<>();
		for (SystemRoleRelation rel : list) {
			Integer mid = rel.getSystemMenuId();
			String mtype = rel.getSystemMenuType();
			if (mid.equals(1)) {
				continue;
			} else if (WuhanConstants.BRANCH.equals(mtype)) {
				// 如果是分支节点，则放入map集合
				rel.setChilds(new ArrayList<>());
				map.put(mid, rel);
			} 
			else {
				// 如果是叶节点，先检查map集合中是否有自己的上级？
				Integer pid = rel.getSystemParentId();
				 SystemRoleRelation par = map.get(pid);
				if (null == par ) {
					// 如果map集合中没有自己的上级，要倒追溯其上级分支节点
					SystemMenuInfo parm = findSingleMenu(pid, menus);
					par = new SystemRoleRelation();
					par.buildFromMenu(parm);
					map.put(pid, par);
				}
				// 存储三级导航URL和判断可关闭布尔标识
				rel.setSystemUrlFirst(rootm.getSystemMenuUrl());
				rel.setSystemUrlSecond(par.getSystemMenuUrl());
				rel.setSystemUrlThird(rel.getSystemMenuUrl());
				// 判断可关闭布尔标识
				boolean clo = WuhanConstants.YES.equals(rel.getSystemMenuClose()) ? true : false;
				rel.setSystemBoolClose(clo);
				// 将叶节点放入其上级分支节点的集合
				par.getChilds().add(rel);
			}
		}
		SystemRoleRelation	root = new SystemRoleRelation();
		root.buildFromMenu(rootm);
		map.forEach( (k, v) -> {
			root.getChilds().add(v);
		});
		return root;
	}

	private SystemMenuInfo findSingleMenu(Integer id, List<SystemMenuInfo> menus) {
		SystemMenuInfo res = null;
		for (SystemMenuInfo m : menus) {
			if (id == m.getSystemMenuId()) {
				res = m;
				break;
			}
		}
		return res;
	}

//	public int roleEdit222(SystemRoleInfo param) {
//		super.settingWithUpdate(param, null);
//		int res = systemRoleInfoMapper.updateByPrimaryKeySelective(param);
//		// 先清理目标角色的关联关系
//		SystemRoleRelation psrr = new SystemRoleRelation();
//		psrr.setSystemRoleId(param.getSystemRoleId());
//		systemRoleRelationMapper.deleteWithCondition(psrr);
//		// 再重构目标角色的关联关系（由于前端tree组件提供的菜单ID不完整，需要倒追溯）
//		psrr.setUid(param.getUid());
//		// 1、获取全部菜单数据
//		List<SystemMenuInfo> menus = this.menuList(new SystemMenuInfo());
//		
//		// 基于set集合重构目标角色的关联关系
//		for (Integer sid : sets) {
//			super.settingWithInsert(psrr, "重构目标角色的关联关系");
//			psrr.setSystemMenuId(sid);
//			psrr.setRelationId(null);
//			systemRoleRelationMapper.insertSelective(psrr);
//		}
//		return res;
//	}

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
		logger.info("用户信息重复性检查：重复是真");
		List<SystemUserInfo> list = this.list(param);
		boolean res = list.size() > 0 ? true : false;
		return res;
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
		for (SystemRoleInfo role : list) {
			List<SystemRoleRelation> relations = systemRoleRelationMapper.selectWithRoleId(role.getSystemRoleId());
			role.setRelations(relations);
		}
		PageInfo<SystemRoleInfo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public int roleEdit(SystemRoleInfo param) {
		super.settingWithUpdate(param, null);
		int res = systemRoleInfoMapper.updateByPrimaryKeySelective(param);
		// 先清理目标角色的关联关系
		SystemRoleRelation psrr = new SystemRoleRelation();
		psrr.setSystemRoleId(param.getSystemRoleId());
		systemRoleRelationMapper.deleteWithCondition(psrr);
		// 再重构目标角色的关联关系（维持前端tree组件提供的不完整菜单ID，可能包含根节点，也可能包含分支节点，留待登录方法中进行倒追溯）
		psrr.setUid(param.getUid());
		for (Integer lid : param.getLeafs()) {
			super.settingWithInsert(psrr, "重构目标角色的关联关系（维持前端tree组件提供的不完整菜单ID，可能包含根节点，也可能包含分支节点，留待登录方法中进行倒追溯）");
			psrr.setSystemMenuId(lid);
			psrr.setRelationId(null);
			systemRoleRelationMapper.insertSelective(psrr);
		}
		return res;
	}

	 

	@Override
	public List<SystemMenuInfo> menuList(SystemMenuInfo param) {
		return systemMenuInfoMapper.selectWithCondition(param);
	}

	@Override
	public SystemMenuInfo menuTree(SystemMenuInfo param) {
		SystemMenuInfo root = systemMenuInfoMapper.selectByPrimaryKey(1);
		SystemMenuInfo pbranch = new SystemMenuInfo();
		pbranch.setSystemParentId(root.getSystemMenuId());
		List<SystemMenuInfo> branchs = systemMenuInfoMapper.selectWithCondition(pbranch);
		for (SystemMenuInfo bra : branchs) {
			SystemMenuInfo pleaf = new SystemMenuInfo();
			pleaf.setSystemParentId(bra.getSystemMenuId());
			List<SystemMenuInfo> leafs = systemMenuInfoMapper.selectWithCondition(pleaf);
			bra.setChilds(leafs);
		}
		root.setChilds(branchs);
		return root;
	}

}
