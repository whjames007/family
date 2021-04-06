package com.whcdit.family.model;

import java.util.Date;

public class BaseModel {

	protected Integer uid; // 通用参数：用户ID

	protected Integer noid; // 通用参数：非本id（主要用于编辑功能的校验方法中）

	protected Integer pageNum;// 通用参数：分页插件的页数

	protected Integer pageSize;// 通用参数：分页插件的行数

	protected Date dateBegin; // 通用参数：查询的开始时间

	protected Date dateEnd; // 通用参数：查询的结束时间
	  
	protected Integer firstCreateBy; // 运维字段：初始创建人员

	protected Date firstCreateDate; // 运维字段：初始创建日期

	protected String dataStatus; // 运维字段：数据状态标识
	
	protected Integer dataDivideId; // 运维字段：数据隔离ID

	protected String description; // 运维字段：数据变化描述

	protected Integer lastUpdateBy; // 运维字段：最后修改人员

	protected Date lastUpdateDate; // 运维字段：最后修改日期

	protected String firstCreateName; // 辅助字段：初始创建者的昵称

	protected String lastUpdateName;// 辅助字段：最后修改者的昵称

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getNoid() {
		return noid;
	}

	public void setNoid(Integer noid) {
		this.noid = noid;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Integer getFirstCreateBy() {
		return firstCreateBy;
	}

	public void setFirstCreateBy(Integer firstCreateBy) {
		this.firstCreateBy = firstCreateBy;
	}

	public Date getFirstCreateDate() {
		return firstCreateDate;
	}

	public void setFirstCreateDate(Date firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getDataDivideId() {
		return dataDivideId;
	}

	public void setDataDivideId(Integer dataDivideId) {
		this.dataDivideId = dataDivideId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Integer lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getFirstCreateName() {
		return firstCreateName;
	}

	public void setFirstCreateName(String firstCreateName) {
		this.firstCreateName = firstCreateName;
	}

	public String getLastUpdateName() {
		return lastUpdateName;
	}

	public void setLastUpdateName(String lastUpdateName) {
		this.lastUpdateName = lastUpdateName;
	}
	
}