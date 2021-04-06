package com.whcdit.family.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whcdit.family.model.SystemMenuInfo;

@Mapper
public interface SystemMenuInfoMapper {
    int deleteByPrimaryKey(Integer systemMenuId);

    int insert(SystemMenuInfo record);

    int insertSelective(SystemMenuInfo record);

    SystemMenuInfo selectByPrimaryKey(Integer systemMenuId);

    int updateByPrimaryKeySelective(SystemMenuInfo record);

    int updateByPrimaryKey(SystemMenuInfo record);

	List<SystemMenuInfo> selectWithCondition(SystemMenuInfo param);
}