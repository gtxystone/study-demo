package com.liren.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisClient4 {
	
	public static void main(String[] args) {
		
		//创建一连接，JedisCluster对象,在系统中是单例存在  
	    Set<HostAndPort> nodes = new HashSet<HostAndPort>();  
	    nodes.add(new HostAndPort("192.168.153.161", 7000));  
	    nodes.add(new HostAndPort("192.168.153.161", 7001));  
	    nodes.add(new HostAndPort("192.168.153.162", 7000));  
	    nodes.add(new HostAndPort("192.168.153.162", 7001));  
	    nodes.add(new HostAndPort("192.168.153.163", 7000));  
	    nodes.add(new HostAndPort("192.168.153.163", 7001));  
	    JedisCluster cluster = new JedisCluster(nodes);  
	    //执行JedisCluster对象中的方法，方法和redis一一对应。  
	   
//	    cluster.set("cluster-test", "my jedis cluster test");  
//	    String result = cluster.get("cluster-test");
//	    cluster.set("test3", "test3"); 
//	    List<String> mget = cluster.mget("test1","test2");
//	    for (String s : mget) {
//			System.out.println(s);
//		}
	   
//	    String result = cluster.get("cluster-test");
//	    System.out.println(result);  
	    //程序结束时需要关闭JedisCluster对象  
	    try {
			cluster.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}

}
