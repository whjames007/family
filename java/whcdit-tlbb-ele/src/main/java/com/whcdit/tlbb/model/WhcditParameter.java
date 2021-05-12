package com.whcdit.tlbb.model;

public class WhcditParameter {
	private String name;
	private Integer width;
	private Integer height;
	
	private Integer mousex;
	private Integer mousey;
	private Integer stepx;
	private Integer stepy;
	private Integer movex;
	private Integer movey;
	private Integer loops;
	private Integer secs;
	
	private String wkey;
	
	
	@Override
	public String toString() {
		return "WhcditParameter [name=" + name + ", width=" + width + ", height=" + height + ", mousex=" + mousex
				+ ", mousey=" + mousey + ", stepx=" + stepx + ", stepy=" + stepy + ", loops=" + loops + "]";
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
	public Integer getMousex() {
		return mousex;
	}
	public void setMousex(Integer mousex) {
		this.mousex = mousex;
	}
	public Integer getMousey() {
		return mousey;
	}
	public void setMousey(Integer mousey) {
		this.mousey = mousey;
	}
	public Integer getStepx() {
		return stepx;
	}
	public void setStepx(Integer stepx) {
		this.stepx = stepx;
	}
	public Integer getStepy() {
		return stepy;
	}
	public void setStepy(Integer stepy) {
		this.stepy = stepy;
	}
	public Integer getLoops() {
		return loops;
	}
	public void setLoops(Integer loops) {
		this.loops = loops;
	}

	public Integer getMovex() {
		return movex;
	}

	public void setMovex(Integer movex) {
		this.movex = movex;
	}

	public Integer getMovey() {
		return movey;
	}

	public void setMovey(Integer movey) {
		this.movey = movey;
	}

	public String getWkey() {
		return wkey;
	}

	public void setWkey(String wkey) {
		this.wkey = wkey;
	}

	public Integer getSecs() {
		return secs;
	}

	public void setSecs(Integer secs) {
		this.secs = secs;
	}
	
	
}
