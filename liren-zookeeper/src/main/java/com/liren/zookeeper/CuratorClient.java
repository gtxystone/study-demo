package com.liren.zookeeper;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.security.NoSuchAlgorithmException;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
import java.util.concurrent.CountDownLatch;  
  
import org.apache.curator.framework.CuratorFramework;  
import org.apache.curator.framework.CuratorFrameworkFactory;  
import org.apache.curator.framework.CuratorFrameworkFactory.Builder;  
import org.apache.curator.framework.api.ACLProvider;  
import org.apache.curator.framework.imps.DefaultACLProvider;  
import org.apache.curator.framework.recipes.cache.ChildData;  
import org.apache.curator.framework.recipes.cache.PathChildrenCache;  
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;  
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;  
import org.apache.curator.framework.state.ConnectionState;  
import org.apache.curator.framework.state.ConnectionStateListener;  
import org.apache.curator.retry.RetryNTimes;  
import org.apache.curator.utils.ZKPaths;  
import org.apache.zookeeper.ZooDefs.Perms;  
import org.apache.zookeeper.data.ACL;  
import org.apache.zookeeper.data.Id;  
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;  
import org.junit.After;  
import org.junit.Before;  
import org.junit.Test;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
import com.google.common.collect.Lists;  
import com.google.common.collect.Maps;  
  
  
public class CuratorClient {  
  
    private Logger logger = LoggerFactory.getLogger(getClass());  
    private final String url = "10.1.1.35:2181,10.1.1.35:2281";   
    private final int connectionTimeoutMs = 10 * 1000;  
    private final int sleepMsBetweenRetries = 5000;  
    private final int sessionTimeoutMs = 20 * 1000;  
    private final String path = "/app";  
    private final boolean cacheData = true;  
    private CuratorFramework client;  
      
    @Test  
    public void startup(){  
          
        final java.util.concurrent.CountDownLatch counter = new CountDownLatch(1);  
        ConnectionStateListener connectionStateListener = new ConnectionStateListener() {  
              
            public void stateChanged(CuratorFramework client, ConnectionState newState) {  
                // TODO Auto-generated method stub  
                if (newState == ConnectionState.CONNECTED) {  
                      
                    logger.info("state:{}", ConnectionState.CONNECTED);  
                    counter.countDown();  
                      
                }  
            }  
        };  
          
        client.getConnectionStateListenable().addListener(connectionStateListener);  
        client.start();  
        try {  
            counter.await();  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            logger.error("", e);  
        }  
          
        client.getConnectionStateListenable().removeListener(connectionStateListener);  
          
        String fullPath = ZKPaths.makePath(path, "/4"); // 监听节点/app/4的变化  
        final PathChildrenCache childrenCache = new PathChildrenCache(client, fullPath, cacheData);  
          
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {  
              
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)  
                    throws Exception {  
                // TODO Auto-generated method stub  
                logger.info("client:{}, event:{}", client, event);  
                  
                List<ChildData> currentData = childrenCache.getCurrentData();  
                for (ChildData childData : currentData) {  
                      
                    logger.info("path:{}, data:{}", childData.getPath(), new String(childData.getData()));  
                }  
                  
            }  
        });  
        try {  
            childrenCache.start();  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            logger.error("", e);  
        }  
          
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
        String command;  
        try {  
            while((command = reader.readLine()) != null){  
                logger.info("command:{}", command);  
                if (command.equalsIgnoreCase("quit")) {  
                    break;  
                }  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        logger.info("finashed!");  
          
    }  
      
      
    @Before  
    public void init() throws NoSuchAlgorithmException{  
        logger.info("init");  
        Builder builder = CuratorFrameworkFactory.builder().connectString(url).connectionTimeoutMs(connectionTimeoutMs);  
        // 设定重试策略  
        builder = builder.retryPolicy(new RetryNTimes(Integer.MAX_VALUE, sleepMsBetweenRetries)).sessionTimeoutMs(sessionTimeoutMs);  
          
        ArrayList<ZkConf> aclConf = Lists.newArrayList(  
                    new ZkConf("/app", "u1001", "p1001", Perms.READ),  
                    new ZkConf("/app/4", "u1001", "p1001", Perms.ALL)  
                );  
        ACLProvider aclProvider = new PathDefaultACLProvider(aclConf);  
        client = builder.aclProvider(aclProvider).build();  
          
    }  
      
    @After  
    public void destory(){  
        logger.info("destory");  
        if (client != null) {  
            client.close();  
        }  
    }  
      
      
    class PathDefaultACLProvider extends DefaultACLProvider{  
        private final Map<String, List<ACL>> aclMap = Maps.newHashMap();  
          
        public PathDefaultACLProvider(List<ZkConf> conf) throws NoSuchAlgorithmException {  
            // TODO Auto-generated constructor stub  
            for (ZkConf zkConf : conf) {  
                  
                String idPassword = zkConf.getUid() + ':' + zkConf.getPwd();  
                Id id = new Id("digest", DigestAuthenticationProvider.generateDigest(idPassword));  
                ACL acl = new ACL(zkConf.getPerms(), id);  
                aclMap.put(zkConf.getPath(), Lists.newArrayList(acl));  
                  
            }  
        }  
          
        @Override  
        public List<ACL> getDefaultAcl() {  
            // TODO Auto-generated method stub  
            return super.getDefaultAcl();  
        }  
          
        @Override  
        public List<ACL> getAclForPath(String path) {  
            // TODO Auto-generated method stub  
            return aclMap.get(path);  
        }  
          
    }  
      
      
    class ZkConf{  
        private final String path;  
        private final String uid;  
        private final String pwd;  
        private final int perms;  
          
        public ZkConf(String path, String uid, String pwd, int perms) {  
            super();  
            this.path = path;  
            this.uid = uid;  
            this.pwd = pwd;  
            this.perms = perms;  
        }  
        public String getPath() {  
            return path;  
        }  
        public String getUid() {  
            return uid;  
        }  
        public String getPwd() {  
            return pwd;  
        }  
        public int getPerms() {  
            return perms;  
        }  
          
    }  
      
}  