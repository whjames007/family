package com.whcdit.tlbb.service;

public interface IWhcditBridgeService {

	public void initParameter();

	public void initKeyboardListener();

	public String readParameter();

	public String saveParameter(String json);
	
	public String grant(String param);
	
	public String testMouse(String param);

}
