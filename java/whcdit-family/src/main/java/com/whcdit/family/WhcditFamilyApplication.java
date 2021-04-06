package com.whcdit.family;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WhcditFamilyApplication {
	private static Logger logger = LoggerFactory.getLogger(WhcditFamilyApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WhcditFamilyApplication.class, args);
		logger.info("【SpringBoot完成启动】");
	}

}
