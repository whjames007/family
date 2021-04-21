package com.whcdit.family.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.whcdit.family.model.FamilyRichesRecord;
import com.whcdit.family.model.FamilyInfo;
import com.whcdit.family.model.FamilyMember;
import com.whcdit.family.service.IFamilyService;
import com.whcdit.family.utils.WuhanConstants;
import com.whcdit.family.utils.WuhanMethods;
import com.whcdit.family.utils.WuhanResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "家族服务")
@RestController
public class FamilyController extends BaseController<FamilyInfo> {

	@Autowired
	private IFamilyService familyService;

	private final String path = "/family/";

	@ApiOperation("家族分页接口")
	@PostMapping(path + "page")
	public Object page(@RequestBody FamilyInfo param) {
		WuhanResp<FamilyInfo> res = new WuhanResp<>();
		try {
			PageInfo<FamilyInfo> page = familyService.page(param);
			res.buildResult(null, null, page);
		} catch (Exception e) {
			super.baseExcep(e, res);
		}
		return res;
	}

	@PostMapping(path + "add")
	public Object add(@RequestBody FamilyInfo param) {
		WuhanResp<FamilyInfo> res = new WuhanResp<>();
		try {
			familyService.add(param);
			res.buildResult(param, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@PostMapping(path + "del")
	public Object del(@RequestBody FamilyInfo param) {
		WuhanResp<FamilyInfo> res = new WuhanResp<>();
		try {
			PageInfo<FamilyInfo> page = null;
			res.buildResult(null, null, page);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@PostMapping(path + "edit")
	public Object edit(@RequestBody FamilyInfo param) {
		WuhanResp<FamilyInfo> res = new WuhanResp<>();
		try {
			PageInfo<FamilyInfo> page = null;
			res.buildResult(null, null, page);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@PostMapping(path + "search")
	public Object search(@RequestBody FamilyInfo param) {
		WuhanResp<FamilyInfo> res = new WuhanResp<>();
		try {
			PageInfo<FamilyInfo> page = familyService.search(param);
			res.buildResult(null, null, page);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@PostMapping(path + "join")
	public Object join(@RequestBody FamilyMember param) {
		WuhanResp<FamilyMember> res = new WuhanResp<>();
		try {
			FamilyMember data = familyService.join(param);
			res.buildResult(data, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@ApiOperation("家族资产分页接口")
	@PostMapping(path + "riches/page")
	public Object richesPage(@RequestBody FamilyRichesRecord param) {
		WuhanResp<FamilyRichesRecord> res = new WuhanResp<>();
		try {
			PageInfo<FamilyRichesRecord> page = familyService.richesPage(param);
			res.buildResult(null, null, page);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@ApiOperation("家族资产新增接口")
	@PostMapping(path + "riches/add")
	public Object richesAdd(@RequestBody FamilyRichesRecord param) {
		WuhanResp<FamilyRichesRecord> res = new WuhanResp<>();
		try {
			Map<Integer, Integer> map = WuhanMethods.getYMD(param.getRichesRecordTime());
			param.setRichesRecordYear(map.get(1));
			param.setRichesRecordMonth(map.get(2));
			param.setRichesRecordDay(map.get(3));
			if (WuhanConstants.EXPENSES.equals(param.getRichesRecordType()) && param.getRichesRecordAmount() > 0) {
				double amount = 0 - param.getRichesRecordAmount();
				param.setRichesRecordAmount(amount);
			}
			familyService.richesAdd(param);
			res.buildResult(param, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

	@PostMapping(path + "riches/edit")
	public Object richesEdit(@RequestBody FamilyRichesRecord param) {
		WuhanResp<FamilyRichesRecord> res = new WuhanResp<>();
		try {
			Map<Integer, Integer> map = WuhanMethods.getYMD(param.getRichesRecordTime());
			param.setRichesRecordYear(map.get(1));
			param.setRichesRecordMonth(map.get(2));
			param.setRichesRecordDay(map.get(3));
			if (WuhanConstants.EXPENSES.equals(param.getRichesRecordType()) && param.getRichesRecordAmount() > 0) {
				double amount = 0 - param.getRichesRecordAmount();
				param.setRichesRecordAmount(amount);
			}
			familyService.richesEdit(param);
			res.buildResult(param, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			res.buildError(WuhanConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return res;
	}

}
