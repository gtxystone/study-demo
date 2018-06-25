package com.liren.redis;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTestList {

	@Test
	public void pushList() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		// config.setMaxActive(20);
		config.setMaxIdle(5);
		// config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		
		JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.auth("123456");
		for(int i=0; i< 1000000 ; i++) {
			jedis.lpush("money", String.valueOf(i));
		}

	}

	@Test
	public void popList() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		// config.setMaxActive(20);
		config.setMaxIdle(5);
		// config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.auth("123456");
		int i = 0;
		long currentTimeMillis = System.currentTimeMillis();
		while (true) {
			List<String> brpop = jedis.brpop(0, "money");
			i ++;
			if(brpop != null && brpop.size() == 2) {
//				String string = brpop.get(0);
				String transactionMoney = brpop.get(1);
//				System.out.println("交易金额：" + transactionMoney);
				jedis.incrBy("mymoney", Long.valueOf(transactionMoney));
//				String mymoney = jedis.get("mymoney");
//				System.out.println(mymoney);
				if(i == 1000000) break;
			}
			
		}
		long time = System.currentTimeMillis() - currentTimeMillis ;
		System.out.println("time:" + time);

	}

}
