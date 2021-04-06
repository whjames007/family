package com.whcdit.family.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whcdit.family.model.SystemUserInfo;

@Mapper
public interface SystemUserInfoMapper {
    int deleteByPrimaryKey(Integer systemUserId);

    int insert(SystemUserInfo record);

    int insertSelective(SystemUserInfo record);

    SystemUserInfo selectByPrimaryKey(Integer systemUserId);

    int updateByPrimaryKeySelective(SystemUserInfo record);

    int updateByPrimaryKey(SystemUserInfo record);

	List<SystemUserInfo> selectWithCondition(SystemUserInfo param);

	List<SystemUserInfo> selectWithMorethan(SystemUserInfo param);
}