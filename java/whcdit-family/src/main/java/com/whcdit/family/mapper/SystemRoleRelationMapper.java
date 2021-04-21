package com.whcdit.family.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whcdit.family.model.SystemRoleRelation;

@Mapper
public interface SystemRoleRelationMapper {
    int deleteByPrimaryKey(Integer relationId);

    int insert(SystemRoleRelation record);

    int insertSelective(SystemRoleRelation record);

    SystemRoleRelation selectByPrimaryKey(Integer relationId);

    int updateByPrimaryKeySelective(SystemRoleRelation record);

    int updateByPrimaryKey(SystemRoleRelation record);
    
    List<SystemRoleRelation> selectWithRoleId(Integer rid);

	int deleteWithCondition(SystemRoleRelation psrr);
    
    
}