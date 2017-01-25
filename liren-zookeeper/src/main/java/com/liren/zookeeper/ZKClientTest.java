package com.liren.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import com.liren.zookeeper.ZooKeeperHello.DemoWatcher;

public class ZKClientTest {
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		 ZooKeeper zk = new ZooKeeper("192.168.1.169:2181", 300000, new Watcher(){
			@Override
			public void process(WatchedEvent watchedevent) {
				
			}
		 });
		 
		String path = "";
		byte[] data = null ;
		List<ACL> acl = null;
		CreateMode createMode = null;
		zk.create(path, data, acl, createMode);
	}
	

}
