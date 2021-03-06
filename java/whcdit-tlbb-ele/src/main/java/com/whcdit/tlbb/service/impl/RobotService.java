package com.whcdit.tlbb.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import com.whcdit.tlbb.runnable.WorkMouseRunnable;
import com.whcdit.tlbb.service.IRobotService;
import com.whcdit.tlbb.utils.WhcditBufferCache;
import com.whcdit.tlbb.utils.WhcditConstants;

import javafx.application.Platform;

public class RobotService implements IRobotService {

	private String what = "...";

	private static Logger logger = LoggerFactory.getLogger(RobotService.class);

	@Override
	public void initKeyboardListener() {
		// 第一步：注册热键，热键标识，组合键（shift），主要热键D
		JIntellitype.getInstance().registerHotKey(WhcditConstants.GLOBAL_HOT_KEY_RUN,
				JIntellitype.MOD_SHIFT + JIntellitype.MOD_CONTROL, (int) 'R');
		JIntellitype.getInstance().registerHotKey(WhcditConstants.GLOBAL_HOT_KEY_STOP,
				JIntellitype.MOD_SHIFT + JIntellitype.MOD_CONTROL, (int) 'S');
		// 第二步：添加热键监听器
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			public void onHotKey(int markCode) {
				switch (markCode) {
				case WhcditConstants.GLOBAL_HOT_KEY_RUN:
					jobRun();
					break;
				case WhcditConstants.GLOBAL_HOT_KEY_STOP:
					jobStop();
					break;
				}
			}
		});
		logger.info("【开始监听键盘】【Ctrl+Shift+R=运行】【Ctrl+Shift+S=停止】");
		WhcditBufferCache.chmap.put(1, WhcditConstants.DISABLE);
//					JIntellitype.getInstance().unregisterHotKey(GLOBAL_HOT_KEY_1);
//					JIntellitype.getInstance().unregisterHotKey(GLOBAL_HOT_KEY_2);
//					JIntellitype.getInstance().unregisterHotKey(GLOBAL_HOT_KEY_3);
	}

	private void jobRun() {
		if (WhcditConstants.RUN.equals(WhcditBufferCache.chmap.get(1))) {
			this.showWindowPush("【Ctrl+Shift+R=运行】【正在运行中，请不要重复操作】");
//		} else if (WhcditConstants.DISABLE.equals(WhcditBufferCache.chmap.get(1))) {
//			this.platformRunlater("【Ctrl+Shift+R=运行】【请先点击授权按钮进行授权操作】");
		} else {
			WhcditBufferCache.chmap.put(1, WhcditConstants.RUN);
			this.showWindowPush("【Ctrl+Shift+R=运行】【开始运行】");
			Thread work = new Thread(new WorkMouseRunnable());
			work.start();
		}
	}

	private void jobStop() {
		if (WhcditConstants.STOP.equals(WhcditBufferCache.chmap.get(1))) {
			this.showWindowPush("【Ctrl+Shift+S=停止】【当前已经是停止状态，请不要重复操作】");
		} else {
			WhcditBufferCache.chmap.put(1, WhcditConstants.STOP);
			this.showWindowPush("【Ctrl+Shift+S=停止】【停止运行】");
		}
	}

	private void showWindowPush(String msg) {
		what = msg;
		logger.info(what);
		Platform.runLater(() -> {
			String javascript = "javascript:showWindowPush('" + what + "')";
			WhcditBufferCache.webEngine.executeScript(javascript);
		});
	}

}
