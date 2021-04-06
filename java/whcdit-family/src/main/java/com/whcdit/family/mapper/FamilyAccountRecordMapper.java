package com.whcdit.family.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.whcdit.family.model.FamilyAccountRecord;
@Mapper
public interface FamilyAccountRecordMapper {
    int deleteByPrimaryKey(Integer accountRecordId);

    int insert(FamilyAccountRecord record);

    int insertSelective(FamilyAccountRecord record);

    FamilyAccountRecord selectByPrimaryKey(Integer accountRecordId);

    int updateByPrimaryKeySelective(FamilyAccountRecord record);

    int updateByPrimaryKey(FamilyAccountRecord record);
}