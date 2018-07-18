package com.liren.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestThread extends Thread {
	
//	private Jedis jedis;// 非切片额客户端连接
	private static JedisPool jedisPool;// 非切片连接池

	public static void main(String[] args) {
		
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		// config.setMaxActive(20);
		config.setMaxIdle(100);
		// config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		// jedisPool = new JedisPool(config, "192.168.1.177", 6379);
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		
		for(int i=0; i<100; i++){
			TestThread t = new TestThread();
			t.start();
		}

	}

	@Override
	public void run() {
		Jedis jedis = jedisPool.getResource();//{
//		jedis = jedisPool.getResource();
		System.out.println(jedis);
			Long hincrBy = jedis.hincrBy("test", "test", 1);
			System.out.println("Thread id:" +Thread.currentThread().getId()+ ",result:" + hincrBy);
			System.out.println("---------");
		jedis.close();
//		}
		
		
		
		
	}
	
	

}
