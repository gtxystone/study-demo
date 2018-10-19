package com.liren.liren_etcd;

import java.util.List;

import com.coreos.jetcd.Watch;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;

public class Test {
	
	private static final String ETCD_CONFIG_FILE_NAME = null;
//V3 api配置初始化和监听
	public void init(){
	try {
	    //加载配置
	    getConfig(EtcdUtil.getEtclClient().getKVClient().get(ByteSequence.fromString(ETCD_CONFIG_FILE_NAME)).get().getKvs());
	//启动监听线程
	    new Thread(() -> {
	//对某一个配置进行监听
	        Watch.Watcher watcher = EtcdUtil.getEtclClient().getWatchClient().watch(ByteSequence.fromString("etcd_key"));
	        try {
	            while(true) {
	                watcher.listen().getEvents().stream().forEach(watchEvent -> {
	                    KeyValue kv = watchEvent.getKeyValue();
//	　　　　　　　　　　　　//获取事件变化类型
//	　　　　　　　　　　　　System.out.println(watchEvent.getEventType());
	                    System.out.println(watchEvent.getEventType());
//	　　               			//获取发生变化的key
	                    System.out.println(kv.getKey().toStringUtf8());
//	                  System.out.println(kv.getKey().toStringUtf8());
//	　　　　　　　　　　	//获取变化后的value
//	                    String afterChangeValue = kv.getValue().toStringUtf8();
//
	                });
	            }
	        } catch (Exception e) {
	            e.printStackTrace();

	        }
	    }).start();
	} catch (Exception e) {
	    e.printStackTrace();

	}

	}
	private String getConfig(List<KeyValue> kvs){
	    if(kvs.size()>0){
	        String config = kvs.get(0).getValue().toStringUtf8();
	        System.out.println("etcd 's config 's configValue is :"+config);
	        return config;
	    }
	    else {
	        return null;
	    }
	}

}
