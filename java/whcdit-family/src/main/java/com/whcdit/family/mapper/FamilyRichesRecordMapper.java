package com.whcdit.family.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whcdit.family.model.FamilyRichesRecord;
@Mapper
public interface FamilyRichesRecordMapper {
    int deleteByPrimaryKey(Integer richesRecordId);

    int insert(FamilyRichesRecord record);

    int insertSelective(FamilyRichesRecord record);

    FamilyRichesRecord selectByPrimaryKey(Integer richesRecordId);

    int updateByPrimaryKeySelective(FamilyRichesRecord record);

    int updateByPrimaryKey(FamilyRichesRecord record);

  	List<FamilyRichesRecord> selectWithMorethan(FamilyRichesRecord param);
}