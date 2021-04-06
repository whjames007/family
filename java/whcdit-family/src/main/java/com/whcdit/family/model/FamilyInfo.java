package com.whcdit.family.model;

import java.util.Date;

public class FamilyInfo extends BaseModel {
    private Integer familyInfoId;

    private String familyInfoName;

    private String familyInfoDesc;

    private Integer leaderUserId;

    private Date leaderOfficeTime;

    private Double familyAccountAmount;

    private Integer lastRecordId;
    
    private String leaderUserName; // 现任族长名称

    public Integer getFamilyInfoId() {
        return familyInfoId;
    }

    public void setFamilyInfoId(Integer familyInfoId) {
        this.familyInfoId = familyInfoId;
    }

    public String getFamilyInfoName() {
        return familyInfoName;
    }

    public void setFamilyInfoName(String familyInfoName) {
        this.familyInfoName = familyInfoName;
    }

    public String getFamilyInfoDesc() {
        return familyInfoDesc;
    }

    public void setFamilyInfoDesc(String familyInfoDesc) {
        this.familyInfoDesc = familyInfoDesc;
    }

    public Integer getLeaderUserId() {
        return leaderUserId;
    }

    public void setLeaderUserId(Integer leaderUserId) {
        this.leaderUserId = leaderUserId;
    }

    public Date getLeaderOfficeTime() {
        return leaderOfficeTime;
    }

    public void setLeaderOfficeTime(Date leaderOfficeTime) {
        this.leaderOfficeTime = leaderOfficeTime;
    }

    public Double getFamilyAccountAmount() {
        return familyAccountAmount;
    }

    public void setFamilyAccountAmount(Double familyAccountAmount) {
        this.familyAccountAmount = familyAccountAmount;
    }

    public Integer getLastRecordId() {
        return lastRecordId;
    }

    public void setLastRecordId(Integer lastRecordId) {
        this.lastRecordId = lastRecordId;
    }

	public String getLeaderUserName() {
		return leaderUserName;
	}

	public void setLeaderUserName(String leaderUserName) {
		this.leaderUserName = leaderUserName;
	}
}