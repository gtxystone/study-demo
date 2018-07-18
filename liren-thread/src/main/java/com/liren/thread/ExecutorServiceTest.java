package com.liren.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class ExecutorServiceTest {

	@Test
	public void testExecutor() throws InterruptedException, ExecutionException {
		ExecutorService executors = Executors.newFixedThreadPool(2);
		executors.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("--------------");
			}
		});
		Future<Void> submit = executors.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				System.out.println("线程回调");
				return null;
			}
			
		});
		submit.get();
		executors.shutdown();
	}

}
