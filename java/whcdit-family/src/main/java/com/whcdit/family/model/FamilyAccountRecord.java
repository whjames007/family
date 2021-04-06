package com.whcdit.family.model;

public class FamilyAccountRecord extends BaseModel {
    private Integer accountRecordId;

    private Integer familyInfoId;

    private String accountRecordType;

    private Integer memberUserId;

    private Double accountRecordAmount;

    private Integer accountRecordYear;

    private Integer accountRecordMonth;

    private Integer accountRecordDay;

    private String accountRecordDesc;

    public Integer getAccountRecordId() {
        return accountRecordId;
    }

    public void setAccountRecordId(Integer accountRecordId) {
        this.accountRecordId = accountRecordId;
    }

    public Integer getFamilyInfoId() {
        return familyInfoId;
    }

    public void setFamilyInfoId(Integer familyInfoId) {
        this.familyInfoId = familyInfoId;
    }

    public String getAccountRecordType() {
        return accountRecordType;
    }

    public void setAccountRecordType(String accountRecordType) {
        this.accountRecordType = accountRecordType;
    }

    public Integer getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(Integer memberUserId) {
        this.memberUserId = memberUserId;
    }

    public Double getAccountRecordAmount() {
        return accountRecordAmount;
    }

    public void setAccountRecordAmount(Double accountRecordAmount) {
        this.accountRecordAmount = accountRecordAmount;
    }

    public Integer getAccountRecordYear() {
        return accountRecordYear;
    }

    public void setAccountRecordYear(Integer accountRecordYear) {
        this.accountRecordYear = accountRecordYear;
    }

    public Integer getAccountRecordMonth() {
        return accountRecordMonth;
    }

    public void setAccountRecordMonth(Integer accountRecordMonth) {
        this.accountRecordMonth = accountRecordMonth;
    }

    public Integer getAccountRecordDay() {
        return accountRecordDay;
    }

    public void setAccountRecordDay(Integer accountRecordDay) {
        this.accountRecordDay = accountRecordDay;
    }

    public String getAccountRecordDesc() {
        return accountRecordDesc;
    }

    public void setAccountRecordDesc(String accountRecordDesc) {
        this.accountRecordDesc = accountRecordDesc;
    }
}