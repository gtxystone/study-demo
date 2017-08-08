package com.liren.redis;

import redis.clients.jedis.Jedis;

public class MyThread extends Thread{
	
	private Jedis jedis;

	public MyThread(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public void run() {
		Long hincrBy = jedis.hincrBy("test", "test", 1);
		System.out.println("Thread id:" +Thread.currentThread().getId()+ ",result:" + hincrBy);
	}

}
