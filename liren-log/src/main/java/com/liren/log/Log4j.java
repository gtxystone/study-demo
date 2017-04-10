package com.liren.log;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4j {
	
	private static Logger logger =  LogManager.getLogger(Log4j.class);
	
	public static void main(String[] args) {
		logger.debug("org.aien.test----package");
		
		System.out.println(Log4j.class.getName());
	}

}
