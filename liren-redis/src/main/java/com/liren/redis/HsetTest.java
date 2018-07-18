package com.liren.redis;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class HsetTest {

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
	public void MultiRequestsTest1() {
		Jedis jedis = jedisPool.getResource();
		Long hincrBy = jedis.hincrBy("test", "test", 1);
		System.out.println(hincrBy);
	}

	@Test
	public void MultiRequestsTest() {
		// 构造一个Runner
		TestRunnable runner = new TestRunnable() {
			@Override
			public void runTest() throws Throwable {
				// 测试内容
				//System.out.println(Thread.currentThread().getId());
				Jedis jedis = jedisPool.getResource();
				Long hincrBy = jedis.hincrBy("test", "test", 1);
				System.out.println("Thread id:" +Thread.currentThread().getId()+ ",result:" + hincrBy);
			}
		};
		int runnerCount = 100;
		// Rnner数组，想当于并发多少个。
		TestRunnable[] trs = new TestRunnable[runnerCount];
		for (int i = 0; i < runnerCount; i++) {
			trs[i] = runner;
		}
		// 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
		MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
		try {
			// 开发并发执行数组里定义的内容
			mttr.runTestRunnables();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
