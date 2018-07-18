package com.liren.log.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Test1 {
	
	private static Logger logger =  LogManager.getLogger(Test1.class);
	
	public void test1() {
		logger.debug("-----------test1--------------");
	}
	
	public static void main(String[] args) {
		logger.debug("-----------test1 main--------------");
	}

}
