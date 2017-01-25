package com.liren.codis;

import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;

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
				.curatorClient("192.168.153.136:2181,192.168.153.137:2181,192.168.153.138:2181", zkSessionTimeoutMs)
				.zkProxyDir("/jodis/codis-demo").poolConfig(config).database(database).password(password)
				.timeoutMs(timeoutMs).build();
		
		Jedis jedis = jedisResourcePool.getResource();
		//jedis.set("test", "test");
		String string = jedis.get("test");
		System.out.println(string);
		
	}

}
