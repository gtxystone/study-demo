package com.liren.zookeeper;
public class TransactionExamples {  
   
  
    public static void main(String[] args) {  
        /*try {  
            client.start();  
            // 开启事务  
            CuratorTransaction transaction = client.inTransaction();  
  
            Collection<CuratorTransactionResult> results = transaction.create()  
                    .forPath("/a/path", "some data".getBytes()).and().setData()  
                    .forPath("/another/path", "other data".getBytes()).and().delete().forPath("/yet/another/path")  
                    .and().commit();  
  
            for (CuratorTransactionResult result : results) {  
                System.out.println(result.getForPath() + " - " + result.getType());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // 释放客户端连接  
            CloseableUtils.closeQuietly(client);  
        }  */
    	
    	
  
    }  
}  