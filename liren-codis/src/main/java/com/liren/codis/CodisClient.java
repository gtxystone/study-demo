package com.liren.codis;

import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class CodisClient {

	public static void main(String[] args) {
		JedisResourcePool jedisResourcePool;
		String password = null;
		int maxTotal = 8;
		int maxIdle = 8;
		int minIdle = 0;
		int database = 0;
		int timeoutMs = 2000;
		int zkSessionTimeoutMs = 30000;
		boolean testOnBorrow = true;
		boolean testOnCreate = true;
		boolean testOnReturn = true;
		boolean testWhileIdle = true;
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMinIdle(minIdle);
		config.setMaxIdle(maxIdle);
		config.setMaxTotal(maxTotal);
		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnCreate(testOnCreate);
		config.setTestOnReturn(testOnReturn);
		config.setTestWhileIdle(testWhileIdle);

		jedisResourcePool = RoundRobinJedisPool.create()
//				.curatorClient("192.168.153.136:2181,192.168.153.137:2181,192.168.153.138:2181", zkSessionTimeoutMs)
				.curatorClient("192.168.153.171:2181,192.168.153.172:2181,192.168.153.173:2181", zkSessionTimeoutMs)
				.zkProxyDir("/jodis/liren").poolConfig(config).database(database).password(password)
				.timeoutMs(timeoutMs).build();
		
		Jedis jedis = jedisResourcePool.getResource();
		
//		jedis.set("test3", "test3");
		for(int i=0; i < 100000; i ++){
			jedis.set("test" + i, "test" + i);
//			jedis.del("test" + i);
//			String string = jedis.get("test" + i);
//			System.out.println(string);
//			Jedis jedis = jedisResourcePool.getResource();
		}
//		String string = jedis.get("test");
//		System.out.println(string);
		
	}

}
