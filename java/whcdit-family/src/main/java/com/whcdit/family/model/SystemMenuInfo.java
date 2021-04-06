package com.whcdit.family.model;

import java.util.List;

public class SystemMenuInfo extends BaseModel {
    private Integer systemMenuId;

    private String systemMenuName;

    private String systemMenuType;

    private String systemMenuIcon;

    private String systemMenuUrl;

    private Integer systemParentId;

    private String systemMenuClose;

    private String systemMenuDesc;
    
    private String systemUrlFirst; // 辅助前端导航菜单和选项卡，增加三级url属性
    
    private String systemUrlSecond;
    
    private String systemUrlThird;
    
    private boolean systemBoolClose; // 辅助前端选项卡，增加可关闭布尔标识

    private List<SystemMenuInfo> childs;

    public String getSystemUrlFirst() {
		return systemUrlFirst;
	}

	public void setSystemUrlFirst(String systemUrlFirst) {
		this.systemUrlFirst = systemUrlFirst;
	}

	public String getSystemUrlSecond() {
		return systemUrlSecond;
	}

	public void setSystemUrlSecond(String systemUrlSecond) {
		this.systemUrlSecond = systemUrlSecond;
	}

	public String getSystemUrlThird() {
		return systemUrlThird;
	}

	public void setSystemUrlThird(String systemUrlThird) {
		this.systemUrlThird = systemUrlThird;
	}

	public Integer getSystemMenuId() {
        return systemMenuId;
    }

    public void setSystemMenuId(Integer systemMenuId) {
        this.systemMenuId = systemMenuId;
    }

    public String getSystemMenuName() {
        return systemMenuName;
    }

    public void setSystemMenuName(String systemMenuName) {
        this.systemMenuName = systemMenuName;
    }

    public String getSystemMenuType() {
        return systemMenuType;
    }

    public void setSystemMenuType(String systemMenuType) {
        this.systemMenuType = systemMenuType;
    }

    public String getSystemMenuIcon() {
        return systemMenuIcon;
    }

    public void setSystemMenuIcon(String systemMenuIcon) {
        this.systemMenuIcon = systemMenuIcon;
    }

    public String getSystemMenuUrl() {
        return systemMenuUrl;
    }

    public void setSystemMenuUrl(String systemMenuUrl) {
        this.systemMenuUrl = systemMenuUrl;
    }

    public Integer getSystemParentId() {
        return systemParentId;
    }

    public void setSystemParentId(Integer systemParentId) {
        this.systemParentId = systemParentId;
    }

    public String getSystemMenuClose() {
        return systemMenuClose;
    }

    public void setSystemMenuClose(String systemMenuClose) {
        this.systemMenuClose = systemMenuClose;
    }

    public String getSystemMenuDesc() {
        return systemMenuDesc;
    }

    public void setSystemMenuDesc(String systemMenuDesc) {
        this.systemMenuDesc = systemMenuDesc;
    }

	public List<SystemMenuInfo> getChilds() {
		return childs;
	}

	public void setChilds(List<SystemMenuInfo> childs) {
		this.childs = childs;
	}

	public boolean isSystemBoolClose() {
		return systemBoolClose;
	}

	public void setSystemBoolClose(boolean systemBoolClose) {
		this.systemBoolClose = systemBoolClose;
	}
}