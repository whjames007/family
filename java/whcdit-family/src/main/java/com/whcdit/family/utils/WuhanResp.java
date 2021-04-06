package com.whcdit.family.utils;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

public class WuhanResp<T> implements Serializable {

	private static final long serialVersionUID = -8222852155469355278L;

	private String status;// 返回状态：成功、失败
	
	private T data;// 返回单一对象
	
	private List<T> list;// 返回集合对象
	
	private PageInfo<T> page;// 分页查询专用对象
	
	private String code;// 返回状态编码
	
	private Object msg;// 状态信息
	
//	private Object rparam;// 入参回传

	public void buildResult(T resultInfo, List<T> resultList, PageInfo<T> resultPage) {
		this.setStatus(WuhanConstants.SUCCESS);
		this.setCode(WuhanConstants.WUHAN_RESPONSE_10000);
		this.setMsg("操作一切正常");
		this.setData(resultInfo);
		this.setList(resultList);
		this.setPage(resultPage);
	}

	public void buildError(String errorCode, Object errorMsg) {
		this.setStatus(WuhanConstants.FAILURE);
		this.setCode(errorCode);
		this.setMsg(errorMsg);
		this.setData(null);
		this.setList(null);
		this.setPage(null);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageInfo<T> getPage() {
		return page;
	}

	public void setPage(PageInfo<T> page) {
		this.page = page;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

}
