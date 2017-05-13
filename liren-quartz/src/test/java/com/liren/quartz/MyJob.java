package com.liren.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;



/** 
* quartz定时器测试 
* 
* @author leizhimin 2009-7-23 8:49:01 
*/ 
public class MyJob implements Job { 
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException { 
                System.out.println(new Date() + ": doing something..."); 
        } 
} 

class Test {
	public static void main(String[] args) {
		// 1、创建JobDetial对象
//		JobDetail jobDetail = JobBuilder.newJob().build();
		
		
	}
}