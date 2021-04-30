package com.whcdit.tlbb.model;

public class WhcditParameter {
	private String name;
	private Integer width;
	private Integer height;

	@Override
	public String toString() {
		return "WhcditParameter [name=" + name + ", width=" + width + ", height=" + height + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}
