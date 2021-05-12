package com.whcdit.tlbb.service.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.whcdit.tlbb.model.WhcditParameter;
import com.whcdit.tlbb.service.IFileService;
import com.whcdit.tlbb.utils.WhcditBufferCache;
import com.whcdit.tlbb.utils.WhcditConstants;

 
public class FileService implements IFileService {
	
private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private static Logger logger = LoggerFactory.getLogger(FileService.class);
	
	@Override
	public void init() {
		File file = new File(WhcditConstants.PARAM_PATH_NAME);
		try {
			if (!file.exists()) {
				FileWriter fw = new FileWriter(file);
				WhcditParameter initp = new WhcditParameter();
				initp.setWidth(600);
				initp.setHeight(800);
				initp.setName("街道口的风");
				initp.setMousex(500);
				initp.setMousey(500);
				initp.setStepx(50);
				initp.setStepy(50);
				initp.setMovex(3);
				initp.setMovey(3);
				initp.setLoops(2);
				initp.setSecs(3);
				initp.setWkey(WhcditConstants.LEFT);
				String json = gson.toJson(initp);
				fw.write(json);
				fw.close();
			}
			FileReader fr = new FileReader(file);
			char[] buff = new char[1024];
			int hav = 0;
			StringBuffer sbf = new StringBuffer();
			while ( (hav = fr.read(buff)) > 0 ) {
				String str = new String(buff, 0, hav);
				sbf.append(str);
			}
			fr.close();
			WhcditParameter wcp = gson.fromJson(sbf.toString(), WhcditParameter.class);
			WhcditBufferCache.wcp = wcp;
			logger.info(WhcditBufferCache.wcp.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 

	@Override
	public WhcditParameter read() {
		return WhcditBufferCache.wcp;
	}

	@Override
	public void writer(WhcditParameter wcp) throws IOException {
			FileWriter fw=new FileWriter(WhcditConstants.PARAM_PATH_NAME);
			String json = gson.toJson(wcp);
			fw.write(json);
			fw.close();
			WhcditBufferCache.wcp = wcp;
	}

	 

}
