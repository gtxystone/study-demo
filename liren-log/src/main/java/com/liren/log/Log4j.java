package com.liren.log;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.liren.log.test.Test1;
import com.test.log.Test;

public class Log4j {
	
	private static Logger logger =  LogManager.getLogger(Log4j.class);
	
	public static void main(String[] args) {
		logger.debug("------------main----------------");
		//System.out.println(Log4j.class.getName());
		Test test = new Test();
		test.test();
		Test1 test1 =new Test1();
		test1.test1();
	}

}
