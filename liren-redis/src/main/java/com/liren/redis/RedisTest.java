package com.liren.redis;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	@Test
	public void testlist() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		// config.setMaxActive(20);
		config.setMaxIdle(5);
		// config.setMaxWait(1000l);
		config.setTestOnBorrow(false);

		JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();
		while (true) {
			List<String> brpop = jedis.brpop(0, "test");
			System.out.println("============");
			if(brpop != null && brpop.size() == 2){
				String string = brpop.get(1);
				System.out.println("增加：" + string);
				jedis.incrBy("mymoney", Long.valueOf(string));
				System.out.println("mymoney:" + jedis.get("mymoney"));
			}
			
		}
		
	}
	
	@Test
	public void listpush(){
		JedisPoolConfig config = new JedisPoolConfig();
		// config.setMaxActive(20);
		config.setMaxIdle(5);
		// config.setMaxWait(1000l);
		config.setTestOnBorrow(false);

		JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();
		for(int i=0; i < 1000000 ; i++){
			jedis.lpush("test", i+"");
		}
	}

}
