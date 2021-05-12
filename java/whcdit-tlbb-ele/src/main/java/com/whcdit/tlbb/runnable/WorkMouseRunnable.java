package com.whcdit.tlbb.runnable;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whcdit.tlbb.model.WhcditParameter;
import com.whcdit.tlbb.utils.WhcditBufferCache;
import com.whcdit.tlbb.utils.WhcditConstants;

public class WorkMouseRunnable extends BaseRunnable implements Runnable {

	private String mtmp = "【智慧人生】【{0}】";

	private String msg = "...";

	private static Logger logger = LoggerFactory.getLogger(WorkMouseRunnable.class);

	@Override
	public void run() {
		try {
			Robot robot = new Robot();
			run1(robot);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void run1(Robot robot) {
		for (int i = 5; i >= 0; i--) {
			if (WhcditConstants.STOP.equals(WhcditBufferCache.chmap.get(1))) {
				super.basePrevent("人工干预之阻止循倒计时");
				return;
			}
			String mdes = MessageFormat.format("倒计时，请尽快将鼠标移动到目标程序：{0}", i);
			robot.delay(1000);
			msg = MessageFormat.format(mtmp, mdes);
			super.showWindowPush(msg);
		}
		run2(robot);
	}

	private void run2(Robot robot) {
		WhcditParameter wcp = WhcditBufferCache.wcp;
		Integer loops = wcp.getLoops();
		Integer mbx = wcp.getMousex();
		Integer mby = wcp.getMousey();
		Integer stepx = wcp.getStepx();
		Integer stepy = wcp.getStepy();
		Integer movex = wcp.getMovex();
		Integer movey = wcp.getMovey();
		String wkey = wcp.getWkey();
		Integer sec = wcp.getSecs() * 1000;
// 循环操作鼠标
		firstFor: for (int i = 0; i < loops; i++) {
			for (int row = 0; row < movey; row++) {
				int mcx = mbx;
				int mcy = mby + stepy * row;
				for (int col = 0; col < movex; col++) {
					if (WhcditConstants.STOP.equals(WhcditBufferCache.chmap.get(1))) {
						super.basePrevent("人工干预之阻止鼠标移动");
						break firstFor;
					}
					robot.delay(sec);
					mcx = mbx + stepx * col;
					robot.mouseMove(mcx, mcy);
					if (WhcditConstants.LEFT.equals(wkey)) {
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
					} else {
						robot.mousePress(InputEvent.BUTTON3_MASK);
						robot.mouseRelease(InputEvent.BUTTON3_MASK);
					}
					String mclk = MessageFormat.format("鼠标轻轻的点了一下坐标[X:{0}，Y:{1}]", mcx, mcy);
					msg = MessageFormat.format(mtmp, mclk);
					super.showWindowPush(msg);
				}
			}

		}
		run3(robot);
	}

	private void run3(Robot robot) {
		robot.delay(1000);
		super.baseStop();
		robot.mouseMove(100, 100);
		msg = MessageFormat.format(mtmp, "停止后，鼠标统一停放在屏幕左上角");
		logger.info(msg);
		super.showWindowPush(msg);
	}

}
