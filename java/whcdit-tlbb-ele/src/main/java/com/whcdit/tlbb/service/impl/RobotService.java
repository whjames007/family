package com.whcdit.tlbb.service.impl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import com.whcdit.tlbb.model.WhcditParameter;
import com.whcdit.tlbb.service.IRobotService;
import com.whcdit.tlbb.utils.WhcditBufferCache;
import com.whcdit.tlbb.utils.WhcditConstants;

import javafx.application.Platform;

public class RobotService implements IRobotService {

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

	private String what = "aa";

	private void jobRun() {
		if (WhcditConstants.RUN.equals(WhcditBufferCache.chmap.get(1))) {
			this.platformRunlater("【Ctrl+Shift+R=运行】【正在运行中，请不要重复操作】");
//		} else if (WhcditConstants.DISABLE.equals(WhcditBufferCache.chmap.get(1))) {
//			this.platformRunlater("【Ctrl+Shift+R=运行】【请先点击授权按钮进行授权操作】");
		} else {
			WhcditBufferCache.chmap.put(1, WhcditConstants.RUN);
			this.platformRunlater("【Ctrl+Shift+R=运行】【开始运行】");
			try {
				String mtmp = "【智慧人生】【%s】";
				this.workMouse(mtmp, null);
			} catch (AWTException e) {
				e.printStackTrace();
				this.platformRunlater("【Ctrl+Shift+R=运行】【运行报错了】");
			}
		}
	}

	private void jobStop() {
		if (WhcditConstants.STOP.equals(WhcditBufferCache.chmap.get(1))) {
			this.platformRunlater("【Ctrl+Shift+S=停止】【当前已经是停止状态，请不要重复操作】");
		} else {
			WhcditBufferCache.chmap.put(1, WhcditConstants.STOP);
			this.platformRunlater("【Ctrl+Shift+S=停止】【停止运行】");
		}

	}

	private void platformRunlater(String msg) {
		what = msg;
		logger.info(what);
		Platform.runLater(() -> {
			String javascript = "javascript:jsshowMethodPush('" + what + "')";
			WhcditBufferCache.webEngine.executeScript(javascript);
//            logger.info("【Ctrl+Shift+R=运行】【{}】", aaa);
		});
	}

	@Override
	public void workMouse(String mtmp, String param) throws AWTException {
		String msg = "";
		Robot robot = new Robot();
		for (int i = 5; i > 0; i--) {
			String mdes = MessageFormat.format("五秒倒计时：{0}", i);
			robot.delay(1000);
			msg = String.format(mtmp, mdes);
			this.platformRunlater(msg);
		}
		WhcditParameter wcp = WhcditBufferCache.wcp;
		Integer mbx = wcp.getMousex();
		Integer mby = wcp.getMousey();
		Integer loops = wcp.getLoops();
		robot.delay(1000);
		robot.mouseMove(mbx, mby);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		String mclk = MessageFormat.format("鼠标移动到起始坐标[X:{0}，Y:{1}]，并使用左键点中目标程序",mbx,mby);
		msg = String.format(mtmp, mclk);
		this.platformRunlater(msg);
		for (int i = 0; i < loops; i++) {
			workMouse2(mtmp, robot, wcp);
		}
		robot.delay(1000);
		this.jobStop();
		robot.mouseMove(100, 100);
		msg = String.format(mtmp, "停止后，鼠标统一停放在屏幕左上角");
		this.platformRunlater(msg);
	}

	private void workMouse2(String mtmp, Robot robot, WhcditParameter wcp) {
		String msg = "";
		Integer mbx = wcp.getMousex();
		Integer mby = wcp.getMousey();
		Integer stepx = wcp.getStepx();
		Integer stepy = wcp.getStepy();
		for (int row = 0; row < 4; row++) {
			int mcx = mbx;
			int mcy = mby+ stepy * row;			
			for (int col = 0; col < 5; col++) {
				robot.delay(1000);
				mcx = mbx + stepx * col;
				robot.mouseMove(mcx, mcy);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				String mclk = MessageFormat.format("鼠标轻轻的点了一下坐标[X:{0}，Y:{1}]",mcx,mcy);
				msg = String.format(mtmp, mclk);				
				this.platformRunlater(msg);
			}
		}
	}

}
