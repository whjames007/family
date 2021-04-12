package com.whcdit.family.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whcdit.family.model.SystemUserInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ABCD服务")
@RestController
public class AbcdController {
	
	@ApiOperation("abcd接口001")
	@GetMapping("/abcd/a001")
	public Object abcd001() {
		SystemUserInfo user = new SystemUserInfo();
		user.setDataDivideId(1112);
		return user;
	}

}
