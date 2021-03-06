package com.whcdit.family.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.whcdit.family.model.SystemMenuInfo;
import com.whcdit.family.model.SystemRoleInfo;
import com.whcdit.family.model.SystemUserInfo;
import com.whcdit.family.service.IUserService;
import com.whcdit.family.utils.WuhanConstants;
import com.whcdit.family.utils.WuhanMethods;
import com.whcdit.family.utils.WuhanResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户服务")
@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	private final String path = "/user/";

	@ApiOperation("用户登录接口")
	@PostMapping(path + "login")
	public Object login(@RequestBody SystemUserInfo param) {
		WuhanResp<SystemUserInfo> res = new WuhanResp<>();
		String account = param.getSystemUserAccount();
		String pass = param.getSystemUserPassword();
		SystemUserInfo pu = new SystemUserInfo();
		pu.setSystemUserAccount(account);
		List<SystemUserInfo> list = userService.list(pu);
		if (WuhanMethods.listNullEmpty(list)) {
			res.buildError(WuhanConstants.WUHAN_RESPONSE_30001, "账号或密码错误");
		} else {
			SystemUserInfo target = list.get(0);
			String tacco = target.getSystemUserAccount();
			String tpass = target.getSystemUserPassword();
			if (tacco.equals(account) && tpass.equals(pass)) {
				SystemUserInfo data = userService.login(target.getSystemUserId());
				res.buildResult(data, null, null);
			} else {
				res.buildError(WuhanConstants.WUHAN_RESPONSE_30001, "账号或密码错误");
			}
		}
		return res;
	}
	
	@ApiOperation("用户注册接口")
	@PostMapping(path + "reg")
	public Object userRegister  (@RequestBody SystemUserInfo param) {
		WuhanResp<SystemUserInfo> res = new WuhanResp<>();
		param.setSystemUserType(WuhanConstants.NORMAL);
		if (WuhanMethods.strNullEmpty(param.getSystemUserTelephone())) {
			param.setSystemUserTelephone("13900000009");
		}
		if (WuhanMethods.strNullEmpty(param.getSystemUserEmail())) {
			param.setSystemUserEmail("register@wuhan.com");
		}
		param.setSystemRoleId(50);
		param.setLastLoginTime(new Date());
		try {
			SystemUserInfo pacc = new SystemUserInfo();
			pacc.setSystemUserAccount(param.getSystemUserAccount());
			SystemUserInfo pnic = new SystemUserInfo();
			pnic.setSystemUserNickname(param.getSystemUserNickname());
			if (userService.repeat(pacc)) {
				res.buildError(WuhanConstants.WUHAN_RESPONSE_30002, "检查用户账号是否重复");
			} else if (userService.repeat(pnic)) {
				res.buildError(WuhanConstants.WUHAN_RESPONSE_30003, "检查用户昵称是否重复");
			} 
			else {
				userService.add(param);
				res.buildResult(param ,null, null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}
	
	@ApiOperation("用户删除接口")
	@PostMapping(path + "del")
	public Object userDel  (@RequestBody SystemUserInfo param) {
		WuhanResp<SystemUserInfo> res = new WuhanResp<>();
		try {
			userService.del(param);
			res.buildResult(param ,null, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}
	
	@ApiOperation("用户修改接口")
	@PostMapping(path + "edit")
	public Object userEdit (@RequestBody SystemUserInfo param) {
		WuhanResp<SystemUserInfo> res = new WuhanResp<>();
		try {
			userService.edit(param);
			res.buildResult(param ,null, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@ApiOperation("用户分页接口")
	@PostMapping(path + "page")
	public Object page(@RequestBody SystemUserInfo param) {
		WuhanResp<SystemUserInfo> res = new WuhanResp<>();
		try {
			PageInfo<SystemUserInfo> page = userService.page(param);
			res.buildResult(null, null, page);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@ApiOperation("用户菜单列表接口")
	@PostMapping(path + "menus")
	public Object menus(@RequestBody SystemUserInfo param) {
		WuhanResp<SystemMenuInfo> res = new WuhanResp<>();
		try {
			List<SystemMenuInfo> list = userService.menus(param);
			res.buildResult(null, list, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@ApiOperation("角色分页接口")
	@PostMapping("/role/page")
	public Object rolePage(@RequestBody SystemRoleInfo param) {
		WuhanResp<SystemRoleInfo> res = new WuhanResp<>();
		try {
			PageInfo<SystemRoleInfo> page = userService.rolePage(param);
			res.buildResult(null, null, page);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@ApiOperation("角色edit接口")
	@PostMapping("/role/edit")
	public Object roleEdit(@RequestBody SystemRoleInfo param) {
		WuhanResp<SystemRoleInfo> res = new WuhanResp<>();
		try {
			userService.roleEdit(param);
			res.buildResult(param, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@ApiOperation("菜单列表接口")
	@PostMapping("/menu/list")
	public Object menuList(@RequestBody SystemMenuInfo param) {
		WuhanResp<SystemMenuInfo> res = new WuhanResp<>();
		try {
			List<SystemMenuInfo> list = userService.menuList(param);
			res.buildResult(null, list, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@ApiOperation("菜单树状接口")
	@PostMapping("/menu/tree")
	public Object menuTree(@RequestBody SystemMenuInfo param) {
		WuhanResp<SystemMenuInfo> res = new WuhanResp<>();
		try {
			SystemMenuInfo tree = userService.menuTree(param);
			res.buildResult(tree, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}
	

}
