package com.liren.liren_etcd;

import java.util.List;

import com.coreos.jetcd.Client;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;

public class EtcdUtil {
    //etcl客户端链接
    private static Client client = null;
    //链接初始化
    public static synchronized Client getEtclClient(){
        if(null == client){
        	
            client = Client.builder().endpoints("http://127.0.0.1:12379,http://127.0.0.1:22379,http://127.0.0.1:32379").build();
        }
            return client;
    }

    /**
     * 根据指定的配置名称获取对应的value
     * @param key 配置项
     * @return
     * @throws Exception
     */
    public static String getEtcdValueByKey(String key) throws Exception {
        List<KeyValue> kvs = EtcdUtil.getEtclClient().getKVClient().get(ByteSequence.fromString(key)).get().getKvs();
        if(kvs.size()>0){
            String value = kvs.get(0).getValue().toStringUtf8();
            return value;
        }
        else {
            return null;
        }
    }

    /**
     * 新增或者修改指定的配置
     * @param key
     * @param value
     * @return
     */
    public static void putEtcdValueByKey(String key,String value) throws Exception{
        EtcdUtil.getEtclClient().getKVClient().put(ByteSequence.fromString(key),ByteSequence.fromBytes(value.getBytes("utf-8")));

    }

    /**
     * 删除指定的配置
     * @param key
     * @return
     */
    public static void deleteEtcdValueByKey(String key){
         EtcdUtil.getEtclClient().getKVClient().delete(ByteSequence.fromString(key));

    }
}