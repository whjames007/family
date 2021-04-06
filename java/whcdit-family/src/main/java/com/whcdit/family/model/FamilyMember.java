package com.whcdit.family.model;

import java.util.Date;

public class FamilyMember extends BaseModel {
    private Integer familyMemberId;

    private String familyMemberType;

    private Integer memberUserId;

    private Date memberJoinTime;

    private Integer familyInfoId;

    public Integer getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Integer familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public String getFamilyMemberType() {
        return familyMemberType;
    }

    public void setFamilyMemberType(String familyMemberType) {
        this.familyMemberType = familyMemberType;
    }

    public Integer getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(Integer memberUserId) {
        this.memberUserId = memberUserId;
    }

    public Date getMemberJoinTime() {
        return memberJoinTime;
    }

    public void setMemberJoinTime(Date memberJoinTime) {
        this.memberJoinTime = memberJoinTime;
    }

    public Integer getFamilyInfoId() {
        return familyInfoId;
    }

    public void setFamilyInfoId(Integer familyInfoId) {
        this.familyInfoId = familyInfoId;
    }
}