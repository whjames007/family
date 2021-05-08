package com.whcdit.tlbb.service.impl;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.whcdit.tlbb.model.WhcditParameter;
import com.whcdit.tlbb.service.IFileService;
import com.whcdit.tlbb.service.IRobotService;
import com.whcdit.tlbb.service.IWhcditBridgeService;
import com.whcdit.tlbb.utils.WhcditConstants;
import com.whcdit.tlbb.utils.WhcditResponse;

public class WhcditBridgeService implements IWhcditBridgeService {

	private Gson gson = new GsonBuilder().create();

	private IFileService fileService = new FileService();
	
	private IRobotService robotService = new RobotService();

	private WhcditResponse<String> res = new WhcditResponse<>();

	private static Logger logger = LoggerFactory.getLogger(WhcditBridgeService.class);

	@Override
	public void initParameter() {
		fileService.init();
	}

	@Override
	public void initKeyboardListener() {
		robotService.initKeyboardListener();			
	}

	@Override
	public String readParameter() {
		WhcditParameter res = fileService.read();
		String json = gson.toJson(res);
		return json;
	}

	@Override
	public String saveParameter(String json) {
		logger.info(json);
		WhcditParameter wcp = gson.fromJson(json, WhcditParameter.class);
		try {
			fileService.writer(wcp);
			res.buildSuccess(json, null, null);
		} catch (IOException e) {
			e.printStackTrace();
			res.buildFailure(WhcditConstants.WUHAN_RESPONSE_20001, e.getMessage());
		}
		return gson.toJson(res);
	}

}
