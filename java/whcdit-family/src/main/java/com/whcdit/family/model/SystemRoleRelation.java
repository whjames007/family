package com.whcdit.family.model;

import java.util.ArrayList;
import java.util.List;

public class SystemRoleRelation extends BaseModel {
    private Integer relationId;

    private Integer systemRoleId;

    private Integer systemMenuId;
    
    private String systemMenuName;

    private String systemMenuType;

    private String systemMenuIcon;

    private String systemMenuUrl;

    private Integer systemParentId;

    private String systemMenuClose;

    private String systemMenuDesc;
    
    private String systemUrlFirst; // 辅助前端导航菜单和选项卡，增加三级url属性
    
    private String systemUrlSecond; // 辅助前端导航菜单和选项卡，增加三级url属性
    
    private String systemUrlThird; // 辅助前端导航菜单和选项卡，增加三级url属性
    
    private boolean systemBoolClose; // 辅助前端选项卡，增加可关闭布尔标识

    private List<SystemRoleRelation> childs; // 辅助前端导航菜单和选项卡，增加子集合

    public void buildFromMenu(SystemMenuInfo m) {
    	this.systemMenuId = m.getSystemMenuId();
	    this.systemMenuName = m.getSystemMenuName();
	    this.systemMenuType = m.getSystemMenuType();
	    this.systemMenuIcon = m.getSystemMenuIcon();
	    this.systemMenuUrl = m.getSystemMenuUrl();
	    this.systemParentId = m.getSystemParentId();
	    this.systemMenuClose = m.getSystemMenuClose();
	    this.systemMenuDesc = m.getSystemMenuDesc();
	    this.childs = new ArrayList<>();
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

	public boolean isSystemBoolClose() {
		return systemBoolClose;
	}

	public void setSystemBoolClose(boolean systemBoolClose) {
		this.systemBoolClose = systemBoolClose;
	}

	public List<SystemRoleRelation> getChilds() {
		return childs;
	}

	public void setChilds(List<SystemRoleRelation> childs) {
		this.childs = childs;
	}

	public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getSystemRoleId() {
        return systemRoleId;
    }

    public void setSystemRoleId(Integer systemRoleId) {
        this.systemRoleId = systemRoleId;
    }

    public Integer getSystemMenuId() {
        return systemMenuId;
    }

    public void setSystemMenuId(Integer systemMenuId) {
        this.systemMenuId = systemMenuId;
    }

}