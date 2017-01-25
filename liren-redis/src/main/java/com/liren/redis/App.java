package com.liren.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Hello world!
 *
 */
public class App {
	
	private Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池
	private ShardedJedis shardedJedis;// 切片额客户端连接
	private ShardedJedisPool shardedJedisPool;// 切片连接池

	public App() {
		initialPool();
		//initialShardedPool();
//		shardedJedis = shardedJedisPool.getResource();
		jedis = jedisPool.getResource();

	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		//config.setMaxActive(20);
		config.setMaxIdle(5);
		//config.setMaxWait(1000l);
		config.setTestOnBorrow(false);

		jedisPool = new JedisPool(config, "192.168.1.177", 6379);
	}
	
	/**
	 * 初始化切片池
	 */
	private void initialShardedPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		//config.setMaxActive(20);
		config.setMaxIdle(5);
		//config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
//		app.jedis.set
//		app.jedis.set(SerializeUtility.serialize("byte11111"), SerializeUtility.serialize("test"));
//		app.jedis.hincrBy(SerializeUtility.serialize("1111"), SerializeUtility.serialize("test"), 1L);
		
		//byte[] hget = app.jedis.hget(SerializeUtility.serialize("1111"), SerializeUtility.serialize("test"));
//		Set<byte[]> keys = app.jedis.keys(SerializeUtility.serialize(" * "));
//		if(keys != null && keys.size() > 0){
//			for(byte[] b: keys){
//				System.out.println(SerializeUtility.unserialize(b));
//			}
//		}
		
		for (String a : app.jedis.keys("*")) {  
	          System.out.println("======================");  
	          System.out.println(a);  
	        }  
		//String s = new String(hget);
		//System.out.println(s);
	}
}
