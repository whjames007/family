package com.whcdit.tlbb.utils;

import java.util.concurrent.ConcurrentHashMap;

import com.whcdit.tlbb.model.WhcditParameter;

public class WhcditBufferCache {
	
	public static WhcditParameter wcp = null;
	
	public static ConcurrentHashMap<Integer, String> chmap = new ConcurrentHashMap<>();
	
}
