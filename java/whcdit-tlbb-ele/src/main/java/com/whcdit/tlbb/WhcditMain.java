package com.whcdit.tlbb;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whcdit.tlbb.model.WhcditParameter;
import com.whcdit.tlbb.service.IWhcditBridgeService;
import com.whcdit.tlbb.service.impl.WhcditBridgeService;
import com.whcdit.tlbb.utils.WhcditBufferCache;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class WhcditMain extends Application {

	private IWhcditBridgeService whcditBridgeService = new WhcditBridgeService();

	private static Logger logger = LoggerFactory.getLogger(WhcditMain.class);

	public static void main(String[] args) {
		logger.info("【程序启动】");
		launch(args);
//		JIntellitype.getInstance().unregisterHotKey(WhcditConstants.GLOBAL_HOT_KEY_RUN);
//		JIntellitype.getInstance().unregisterHotKey(WhcditConstants.GLOBAL_HOT_KEY_STOP);
		logger.info("【程序关闭】");
	}

	@Override
	public void start(Stage stage) throws Exception {
		whcditBridgeService.initParameter();
		whcditBridgeService.initKeyboardListener();
		WhcditParameter wcp = WhcditBufferCache.wcp;
		stage.setTitle(wcp.getName());
		stage.getIcons().add(new Image("/templates/image/logobmw.png"));
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, wcp.getWidth(), wcp.getHeight());
		
		final WebView webView = new WebView();

		final WebEngine webEngine = webView.getEngine();
//		webEngine.load("http://192.168.20.197:27000");
		URL url = this.getClass().getResource("/templates/index.html");
		webEngine.load(url.toString());
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == Worker.State.SUCCEEDED) {
				JSObject jsobj = (JSObject) webEngine.executeScript("window");
				jsobj.setMember("BRIDGE", whcditBridgeService);
				logger.info("【回调已经成功】");
			} else {
				logger.error("【回调尚未成功】" + newValue.toString());
			}
		});
		scene.setRoot(webView);
		stage.setScene(scene);
		stage.setOnCloseRequest(e -> {
			logger.info("【用户直接关闭了程序窗口】");
			stage.close();
		});
		stage.show();
	}

}
