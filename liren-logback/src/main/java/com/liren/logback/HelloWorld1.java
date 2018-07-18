package com.liren.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld1 {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(HelloWorld1.class);
//		logger.debug("Hello world.");
//		logger.trace("======trace");
//		logger.debug("======debug");
//		logger.info("======info");
//		logger.warn("======warn");
//		logger.error("======error");
		
		User entry = new User(); 
		entry.setId(11L);
		entry.setName("sdafsad");
		logger.debug("id:{} name:{}", entry.getId(),entry.getName());
//		 LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		  // print logback's internal status
//		  StatusPrinter.print(lc);
	}
}