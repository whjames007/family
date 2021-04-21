package com.whcdit.family.model;

import java.util.Date;

public class SystemUserInfo extends BaseModel {
    private Integer systemUserId;

    private String systemUserAccount;

    private String systemUserType;

    private String systemUserPassword;

    private String systemUserNickname;

    private String systemUserTelephone;

    private String systemUserEmail;

    private Integer systemRoleId;

    private Date lastLoginTime;
    
    private String systemRoleName;
    
    private String familyInfoName;
    
    private SystemRoleRelation menu;

    public Integer getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Integer systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getSystemUserAccount() {
        return systemUserAccount;
    }

    public void setSystemUserAccount(String systemUserAccount) {
        this.systemUserAccount = systemUserAccount;
    }

    public String getSystemUserType() {
        return systemUserType;
    }

    public void setSystemUserType(String systemUserType) {
        this.systemUserType = systemUserType;
    }

    public String getSystemUserPassword() {
        return systemUserPassword;
    }

    public void setSystemUserPassword(String systemUserPassword) {
        this.systemUserPassword = systemUserPassword;
    }

    public String getSystemUserNickname() {
        return systemUserNickname;
    }

    public void setSystemUserNickname(String systemUserNickname) {
        this.systemUserNickname = systemUserNickname;
    }

    public String getSystemUserTelephone() {
        return systemUserTelephone;
    }

    public void setSystemUserTelephone(String systemUserTelephone) {
        this.systemUserTelephone = systemUserTelephone;
    }

    public String getSystemUserEmail() {
        return systemUserEmail;
    }

    public void setSystemUserEmail(String systemUserEmail) {
        this.systemUserEmail = systemUserEmail;
    }

    public Integer getSystemRoleId() {
        return systemRoleId;
    }

    public void setSystemRoleId(Integer systemRoleId) {
        this.systemRoleId = systemRoleId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

	public String getSystemRoleName() {
		return systemRoleName;
	}

	public void setSystemRoleName(String systemRoleName) {
		this.systemRoleName = systemRoleName;
	}

	public SystemRoleRelation getMenu() {
		return menu;
	}

	public void setMenu(SystemRoleRelation menu) {
		this.menu = menu;
	}

	public String getFamilyInfoName() {
		return familyInfoName;
	}

	public void setFamilyInfoName(String familyInfoName) {
		this.familyInfoName = familyInfoName;
	}
}