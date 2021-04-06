package com.whcdit.family.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whcdit.family.model.FamilyInfo;

@Mapper
public interface FamilyInfoMapper {
	int deleteByPrimaryKey(Integer familyInfoId);

	int insert(FamilyInfo record);

	int insertSelective(FamilyInfo record);

	FamilyInfo selectByPrimaryKey(Integer familyInfoId);

	int updateByPrimaryKeySelective(FamilyInfo record);

	int updateByPrimaryKey(FamilyInfo record);

	List<FamilyInfo> selectWithCondition(FamilyInfo param);

	List<FamilyInfo> selectWithMorethan(FamilyInfo param);

	List<FamilyInfo> selectWithSearch(FamilyInfo param);
}