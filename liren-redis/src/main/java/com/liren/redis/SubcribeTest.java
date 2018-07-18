package com.liren.redis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class SubcribeTest {

	private Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池

	@Test
	public void redisSubcribe() {
		// Jedis jedis1 = jedisPool.getResource();
		// Jedis jedis2 = jedisPool.getResource();
		// Publisher pub = new Publisher();
		// pub.publish(jedis1); // 发布一个频道
		// Subscriber sub = new Subscriber();
		// sub.psub(jedis2, new JedisPubSubListener()); // 订阅一个频道

		Jedis jedis1 = jedisPool.getResource();
		jedis1.subscribe(new JedisPubSubListener(), "news.it");
	}

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

}

class Publisher {
	public void publish(final Jedis redisClient) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("发布：news.share");
				redisClient.publish("roban:test:channel", "ok");
				redisClient.publish("roban:test:channel", "hello --word");
			}
		}).start();
	}
}

class Subscriber {
	public void psub(final Jedis redisClient, final JedisPubSubListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("订阅：news.share");
				// 订阅得到信息在lister的onMessage(...)方法中进行处理
				// 订阅多个频道
				// redisClient.subscribe(listener, "news.share",
				// "news.log");
				// redisClient.subscribe(listener, new
				// String[]{"news.share","news.log"});
				redisClient.subscribe(listener, new String[] { "roban:test:channel" });// 使用模式匹配的方式设置频道
			}
		}).start();
	}
}

class JedisPubSubListener extends JedisPubSub {

	@Override
	public void onMessage(String channel, String message) {
		System.out.println(channel + "=" + message);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub
	}
}
