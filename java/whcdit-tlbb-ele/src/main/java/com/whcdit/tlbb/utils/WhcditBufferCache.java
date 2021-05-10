package com.whcdit.tlbb.utils;

import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.whcdit.tlbb.model.WhcditParameter;

import javafx.scene.web.WebEngine;

public class WhcditBufferCache {
	
	public static WebEngine webEngine = null;
	
	public static WhcditParameter wcp = null;
	
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static ConcurrentHashMap<Integer, String> chmap = new ConcurrentHashMap<>();
	
}
