package com.whcdit.family.model;

import java.util.Date;

public class FamilyRichesRecord extends BaseModel {
    private Integer richesRecordId;

    private Integer richesRecordFamily;

    private String richesRecordType;

    private Integer richesRecordUser;

    private Double richesRecordAmount;

    private Date richesRecordTime;

    private Integer richesRecordYear;

    private Integer richesRecordMonth;

    private Integer richesRecordDay;

    private String richesRecordDesc;
    
     private String familyMemberType;
	 private String systemUserNickname;
	 private String familyInfoName;

    public String getFamilyMemberType() {
		return familyMemberType;
	}

	public void setFamilyMemberType(String familyMemberType) {
		this.familyMemberType = familyMemberType;
	}

	public String getSystemUserNickname() {
		return systemUserNickname;
	}

	public void setSystemUserNickname(String systemUserNickname) {
		this.systemUserNickname = systemUserNickname;
	}

	public String getFamilyInfoName() {
		return familyInfoName;
	}

	public void setFamilyInfoName(String familyInfoName) {
		this.familyInfoName = familyInfoName;
	}

	public Integer getRichesRecordId() {
        return richesRecordId;
    }

    public void setRichesRecordId(Integer richesRecordId) {
        this.richesRecordId = richesRecordId;
    }

    public Integer getRichesRecordFamily() {
        return richesRecordFamily;
    }

    public void setRichesRecordFamily(Integer richesRecordFamily) {
        this.richesRecordFamily = richesRecordFamily;
    }

    public String getRichesRecordType() {
        return richesRecordType;
    }

    public void setRichesRecordType(String richesRecordType) {
        this.richesRecordType = richesRecordType;
    }

    public Integer getRichesRecordUser() {
        return richesRecordUser;
    }

    public void setRichesRecordUser(Integer richesRecordUser) {
        this.richesRecordUser = richesRecordUser;
    }

    public Double getRichesRecordAmount() {
        return richesRecordAmount;
    }

    public void setRichesRecordAmount(Double richesRecordAmount) {
        this.richesRecordAmount = richesRecordAmount;
    }

    public Date getRichesRecordTime() {
        return richesRecordTime;
    }

    public void setRichesRecordTime(Date richesRecordTime) {
        this.richesRecordTime = richesRecordTime;
    }

    public Integer getRichesRecordYear() {
        return richesRecordYear;
    }

    public void setRichesRecordYear(Integer richesRecordYear) {
        this.richesRecordYear = richesRecordYear;
    }

    public Integer getRichesRecordMonth() {
        return richesRecordMonth;
    }

    public void setRichesRecordMonth(Integer richesRecordMonth) {
        this.richesRecordMonth = richesRecordMonth;
    }

    public Integer getRichesRecordDay() {
        return richesRecordDay;
    }

    public void setRichesRecordDay(Integer richesRecordDay) {
        this.richesRecordDay = richesRecordDay;
    }

    public String getRichesRecordDesc() {
        return richesRecordDesc;
    }

    public void setRichesRecordDesc(String richesRecordDesc) {
        this.richesRecordDesc = richesRecordDesc;
    }
}