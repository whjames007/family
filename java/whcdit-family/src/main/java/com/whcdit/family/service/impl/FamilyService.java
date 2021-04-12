package com.whcdit.family.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.whcdit.family.mapper.FamilyAccountRecordMapper;
import com.whcdit.family.mapper.FamilyInfoMapper;
import com.whcdit.family.mapper.FamilyMemberMapper;
import com.whcdit.family.model.FamilyAccountRecord;
import com.whcdit.family.model.FamilyInfo;
import com.whcdit.family.model.FamilyMember;
import com.whcdit.family.model.SystemUserInfo;
import com.whcdit.family.service.IFamilyService;
import com.whcdit.family.service.IUserService;
import com.whcdit.family.utils.WuhanConstants;
import com.whcdit.family.utils.WuhanMethods;

@Service
public class FamilyService extends BaseService implements IFamilyService {

	@Autowired
	private FamilyInfoMapper familyInfoMapper;
	@Autowired
	private FamilyMemberMapper familyMemberMapper;
	@Autowired
	private FamilyAccountRecordMapper familyAccountRecordMapper;

	private static Logger logger = LoggerFactory.getLogger(FamilyService.class);

	@Autowired
	private IUserService userService;

	@Override
	@Transactional
	public int add(FamilyInfo param) {
		// 先构造家族
		Integer uid = param.getUid();
		super.settingWithInsert(param, "创建一个家族");
		param.setLeaderUserId(uid);
		param.setLeaderOfficeTime(new Date());
		param.setLastRecordId(0);
		int row = familyInfoMapper.insertSelective(param);
		Integer fid = param.getFamilyInfoId();
		// 再构造家族第一成员：族长
		FamilyMember fm = new FamilyMember();
		fm.setUid(uid);
		super.settingWithInsert(fm, "构造家族第一成员：族长");
		fm.setFamilyMemberType(WuhanConstants.LEADER);
		fm.setMemberUserId(uid);
		fm.setMemberJoinTime(new Date());
		fm.setFamilyInfoId(fid);
		familyMemberMapper.insertSelective(fm);
		// 再构造一条账户记录
		FamilyAccountRecord far = new FamilyAccountRecord();
		fm.setUid(uid);
		far.setUid(uid);
		super.settingWithInsert(far, "构造一条账户记录：家族创始资金");
		far.setFamilyInfoId(fid);
		far.setAccountRecordType(WuhanConstants.INIT);
		far.setMemberUserId(uid);
		far.setAccountRecordAmount(param.getFamilyAccountAmount());
		Map<Integer, Integer> dmap = WuhanMethods.getYMD(null);
		far.setAccountRecordYear(dmap.get(1));
		far.setAccountRecordMonth(dmap.get(2));
		far.setAccountRecordDay(dmap.get(3));
		far.setAccountRecordDesc("家族创始资金");
		familyAccountRecordMapper.insertSelective(far);
		// 再回刷家族lastRecordId
		FamilyInfo callback = new FamilyInfo();
		callback.setUid(uid);
		super.settingWithUpdate(callback, "族长回刷家族创始资金");
		callback.setFamilyInfoId(fid);
		callback.setLastRecordId(far.getAccountRecordId());
		familyInfoMapper.updateByPrimaryKeySelective(callback);
		// 最后升级用户角色为族长
		SystemUserInfo user = new SystemUserInfo();
		user.setUid(uid);
		super.settingWithUpdate(user, "升级用户角色为族长");
		user.setSystemUserId(uid);
		user.setSystemRoleId(30);
		user.setDataDivideId(fid);
		userService.edit(user);
		logger.info("成功创建新家族：" + param.getFamilyInfoName());

		return row;
	}

	@Override
	public int del(FamilyInfo param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edit(FamilyInfo param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FamilyInfo> list(FamilyInfo param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<FamilyInfo> page(FamilyInfo param) {
		super.pageInit(param);
		List<FamilyInfo> list = familyInfoMapper.selectWithMorethan(param);
		PageInfo<FamilyInfo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public boolean repeat(FamilyInfo param) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageInfo<FamilyInfo> search(FamilyInfo param) {
		super.pageInit(param);
		List<FamilyInfo> list = familyInfoMapper.selectWithSearch(param);
		PageInfo<FamilyInfo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	@Transactional
	public FamilyMember join(FamilyMember param) {
		Integer uid = param.getUid();
		super.settingWithInsert(param, "成功加入一个家族");
		param.setFamilyMemberType(WuhanConstants.MEMBER);
		param.setMemberJoinTime(new Date());
		familyMemberMapper.insertSelective(param);
		// 最后升级用户角色为族长
		SystemUserInfo user = new SystemUserInfo();
		user.setUid(uid);
		super.settingWithUpdate(user, "成功加入一个家族，升级用户角色为家族成员");
		user.setSystemUserId(uid);
		user.setSystemRoleId(40);
		user.setDataDivideId(param.getFamilyInfoId());
		userService.edit(user);
		return param;
	}

	@Override
	public PageInfo<FamilyAccountRecord> richesPage(FamilyAccountRecord param) {
		super.pageInit(param);
		List<FamilyAccountRecord> list = familyAccountRecordMapper.selectWithMorethan(param);
		PageInfo<FamilyAccountRecord> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public void richesAdd(FamilyAccountRecord param) {
		super.settingWithInsert(param, "新增一条资产记录");
		familyAccountRecordMapper.insertSelective(param);
	}

}