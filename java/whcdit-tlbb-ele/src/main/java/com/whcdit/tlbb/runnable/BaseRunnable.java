package com.whcdit.tlbb.runnable;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whcdit.tlbb.utils.WhcditBufferCache;
import com.whcdit.tlbb.utils.WhcditConstants;

import javafx.application.Platform;

public class BaseRunnable {
	
	protected String what = "...";
	
	private static Logger logger = LoggerFactory.getLogger(WorkMouseRunnable.class);

	private String mtmp = "【智慧人生】【{0}】";
	
	private String msg = "...";
	
	protected void showWindowPush(String msg) {
		what = msg;
		logger.info(what);
		Platform.runLater(() -> {
			String javascript = "javascript:showWindowPush('" + what + "')";
			WhcditBufferCache.webEngine.executeScript(javascript);
		});
	}
	
	protected void baseStop() {
		if (WhcditConstants.STOP.equals(WhcditBufferCache.chmap.get(1))) {
			this.showWindowPush("【Ctrl+Shift+S=停止】【当前已经是停止状态，请不要重复操作】");
		} else {
			WhcditBufferCache.chmap.put(1, WhcditConstants.STOP);
			this.showWindowPush("【Ctrl+Shift+S=停止】【停止运行】");
		}
	}
	
	protected void basePrevent(String param) {
		msg = MessageFormat.format(mtmp, param);
		logger.info(msg);
		this.showWindowPush(msg);
	}
	
}
