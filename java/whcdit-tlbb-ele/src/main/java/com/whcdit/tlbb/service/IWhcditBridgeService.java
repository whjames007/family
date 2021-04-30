package com.whcdit.tlbb.service;

public interface IWhcditBridgeService {
	
	public void initParameter();
	
	public String readParameter();
	
	public String saveParameter(String json);
	
}
