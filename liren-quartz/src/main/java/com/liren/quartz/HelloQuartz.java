package com.liren.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

public class HelloQuartz {
	
	

	public static void main(String[] args) throws SchedulerException {

		// define the job and tie it to our HelloJob class
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob", "group1").usingJobData("name", "liren").build();
		
		// Trigger the job to run now, and then every 40 seconds
//		Trigger trigger = TriggerBuilder
//				.newTrigger()
//				.withIdentity("myTrigger", "group1")
//				.startNow()
//				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(40).repeatForever())
//				.build();
		
		String cronExpression = "0/5 * * * * ?";
		CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(cronExpression);
		
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(cronSchedule).usingJobData("password", "password")
				.build();
		//JobDataMap jobDataMap = trigger.getJobDataMap();
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		//添加job，以及其关联的trigger
		scheduler.scheduleJob(jobDetail, trigger);
		
		JobListener myJobListener = new MyJobListener("jfjj");
//		scheduler.getListenerManager().addJobListener(myJobListener , EverythingMatcher.allJobs());
		Matcher<JobKey> matcher = KeyMatcher.keyEquals(new JobKey("myJob","group1")); 
		scheduler.getListenerManager().addJobListener(myJobListener ,matcher);
//		Calendar calendar;
//		scheduler.addCalendar("test", calendar, flag, flag1);
		//启动job
		scheduler.start();
	}
	
	
	
	

}
