package com.whcdit.family.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.whcdit.family.model.FamilyMember;
@Mapper
public interface FamilyMemberMapper {
    int deleteByPrimaryKey(Integer familyMemberId);

    int insert(FamilyMember record);

    int insertSelective(FamilyMember record);

    FamilyMember selectByPrimaryKey(Integer familyMemberId);

    int updateByPrimaryKeySelective(FamilyMember record);

    int updateByPrimaryKey(FamilyMember record);
}