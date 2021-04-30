package com.whcdit.tlbb.utils;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

public class WhcditResponse<T> implements Serializable {

	private static final long serialVersionUID = -8222852155469355278L;

	private String status;// 返回状态：成功、失败
	
	private T info;// 返回单一对象
	
	private List<T> list;// 返回集合对象
	
	private PageInfo<T> page;// 分页查询专用对象
	
	private String code;// 返回状态编码
	
	private Object msg;// 状态信息
	
	public void buildSuccess(T info, List<T> list, PageInfo<T> page) {
		this.setStatus(WhcditConstants.SUCCESS);
		this.setCode(WhcditConstants.WUHAN_RESPONSE_10000);
		this.setMsg("程序运行一切正常");
		this.setInfo(info);
		this.setList(list);
		this.setPage(page);
	}

	public void buildFailure(String code, Object msg) {
		this.setStatus(WhcditConstants.FAILURE);
		this.setCode(code);
		this.setMsg(msg);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
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
