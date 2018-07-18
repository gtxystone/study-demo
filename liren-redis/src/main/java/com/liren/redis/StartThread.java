package com.liren.redis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class StartThread {
	
	private Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池

	@Before
	public void init() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		// config.setMaxActive(20);
		config.setMaxIdle(5);
		// config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		// jedisPool = new JedisPool(config, "192.168.1.177", 6379);
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}
	
	
	@Test
	public void start() {
		for(int i=0; i< 100; i++){
			jedis = jedisPool.getResource();
			MyThread myThread1 = new MyThread(jedis); 
			myThread1.start();
		}
	}


}
