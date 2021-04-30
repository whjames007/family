package com.whcdit.tlbb.service;

import java.io.IOException;

import com.whcdit.tlbb.model.WhcditParameter;

public interface IFileService {
	
	public void init();
	
	public WhcditParameter read();
	
	public void writer(WhcditParameter wcp) throws IOException;
	

}
