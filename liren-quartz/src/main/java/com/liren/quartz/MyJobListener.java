package com.liren.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {

    private String name;

    public MyJobListener(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void jobToBeExecuted(JobExecutionContext context) {
        // do something with the event
    	System.out.println("jobToBeExecuted");
    }

    public void jobWasExecuted(JobExecutionContext context,
            JobExecutionException jobException) {
        // do something with the event
    	System.out.println("jobWasExecuted");
    }

    public void jobExecutionVetoed(JobExecutionContext context) {
        // do something with the event
    	System.out.println("jobExecutionVetoed");
    }
}
