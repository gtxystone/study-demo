package com.liren.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class PipelinedTest {
	
	private Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池
	
	@Test
	public void pipelined(){
		Jedis jedis = jedisPool.getResource();
//		Set<String> keys = jedis.keys("*");
//		for(String s: keys){
//			System.out.println(s);
//		}
//		Map<String,Response<Long>> respones = new HashMap<String,Response<Long>>();
//		Pipeline pipelined = jedis.pipelined();
//		respones.put("hpipe1", pipelined.hset("hpipe1", "test", "test"));
//		respones.put("lpipe1", pipelined.lpush("lpipe1", "test1", "test1"));
//		pipelined.sync();
//		Set<Entry<String, Response<Long>>> entrySet = respones.entrySet();
//		for(Entry<String, Response<Long>> entry: entrySet){
//			System.out.println(entry.getKey() + ":" + entry.getValue().get());
//		}
//		Transaction multi = jedis.multi();
//		for (int i = 0; i < 100; i++) { 
//			multi.set("t" + i, "t" + i);
//	    } 
//		List<Object> exec = multi.exec();
//		
//		multi.discard();
//		System.out.println();
		
//		jedis.publish("news.it", "my name is redis");
//		jedis.watch("test");
		Transaction multi = jedis.multi();
		Response<String> response = multi.get("test");
//		System.out.println(response.get());
		multi.set("test", "change");
		multi.exec();
		
	}
	
	
	
	@Before
	public void init(){
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		//config.setMaxActive(20);
		config.setMaxIdle(5);
		//config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
//		jedisPool = new JedisPool(config, "192.168.1.177", 6379);
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}

}
