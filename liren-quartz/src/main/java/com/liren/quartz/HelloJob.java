package com.liren.quartz;

import java.util.Date;

import org.quartz.Calendar;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

public class HelloJob implements Job {

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		
//		Date nextFireTime = jec.getNextFireTime();
//		System.out.println("nextFireTime" + nextFireTime);
//		System.out.println("---------------------");
//		Trigger trigger = jec.getTrigger();
//		Date nextFireTime2 = trigger.getNextFireTime();
//		System.out.println("nextFireTime2" + nextFireTime2);
//		JobDataMap jobDataMap = trigger.getJobDataMap();
//		Object object111 = jobDataMap.get("name");
//		System.out.println("jobDataMap:" + object111);
//		String name = (String)jec.get("name");
//		System.out.println("name:" + name);
//		JobDetail jobDetail = jec.getJobDetail();
//		String object = (String)jobDetail.getJobDataMap().get("name");
//		System.out.println("jobData:" + object);
		Calendar calendar = jec.getCalendar();
		JobDataMap mergedJobDataMap = jec.getMergedJobDataMap();
		Object object = mergedJobDataMap.get("name");
		System.out.println(object);
		Object password = mergedJobDataMap.get("password");
		System.out.println(password);
		
		
	}

}
