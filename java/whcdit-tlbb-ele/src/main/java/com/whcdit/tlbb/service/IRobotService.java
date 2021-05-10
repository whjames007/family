package com.whcdit.tlbb.service;

import java.awt.AWTException;

public interface IRobotService {
	
	public void initKeyboardListener();

	public void workMouse(String mtmp, String param) throws AWTException;
	

}
