package com.liren.mongodb;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBJDBC {
	public static void main(String[] args) {
		/*
		 * try { //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
		 * //ServerAddress()两个参数分别为 服务器地址 和 端口 ServerAddress serverAddress = new
		 * ServerAddress("localhost",27017); List<ServerAddress> addrs = new
		 * ArrayList<ServerAddress>(); addrs.add(serverAddress);
		 * 
		 * //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
		 * MongoCredential credential =
		 * MongoCredential.createScramSha1Credential("username", "databaseName",
		 * "password".toCharArray()); List<MongoCredential> credentials = new
		 * ArrayList<MongoCredential>(); credentials.add(credential);
		 * 
		 * //通过连接认证获取MongoDB连接 MongoClient mongoClient = new
		 * MongoClient(addrs,credentials);
		 * 
		 * //连接到数据库 MongoDatabase mongoDatabase =
		 * mongoClient.getDatabase("databaseName"); System.out.println(
		 * "Connect to database successfully"); } catch (Exception e) {
		 * System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		 * }
		 */

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		FindIterable<Document> find = collection.find();
		MongoCursor<Document> mongoCursor = find.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next());
		}

		 BasicDBObject queryObject = new BasicDBObject().append(QueryOperators.OR,  
	                new BasicDBObject[] { new BasicDBObject("id", 1),  
	                        new BasicDBObject("id", 2) });  
		 collection.find(queryObject);
//	        find(queryObject, "(查询id等于1或者id等于2的数据)");  
	  
	        // $and(查询id等于10并且name等于10的数据)  
	        queryObject = new BasicDBObject().append(QueryOperators.AND,  
	                new BasicDBObject[] { new BasicDBObject("id", 10),  
	                        new BasicDBObject("name", "10") });  
//	        find(queryObject, "(查询id等于10并且name等于10的数据)");  
	  
	        // $gt（查询id大于10的数据）  
	        queryObject = new BasicDBObject().append("id",  
	                new BasicDBObject().append(QueryOperators.GT, 10));  
//	        find(queryObject, "（查询id大于10的数据）");  
	        // $gte （查询id大于等于10的数据）  
	        queryObject = new BasicDBObject().append("id",  
	                new BasicDBObject().append(QueryOperators.GTE, 11));  
//	        find(queryObject, "（查询id大于等于11的数据）");  
	        // $lt  
	        queryObject = new BasicDBObject().append("id",  
	                new BasicDBObject().append(QueryOperators.LT, 2));  
//	        find(queryObject, "（查询id小于2的数据）");  
	        // $lte  
	        queryObject = new BasicDBObject().append("id",  
	                new BasicDBObject().append(QueryOperators.LTE, 2));  
//	        find(queryObject, "（查询id小于等于2的数据）");  
	  
	        // $in  
	        queryObject = new BasicDBObject().append("id", new BasicDBObject(  
	                QueryOperators.IN, new int[] { 1, 2 }));  
//	        find(queryObject, "（查询id为1和2的数据）");  
	        // $nin  
	        queryObject = new BasicDBObject().append("id", new BasicDBObject(  
	                QueryOperators.NIN, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));  
//	        find(queryObject, "（查询id不为1,2,3,4,5,6,7,8,9的数据）");  
	  
	        // 还有很多其他的高级查询方式可以参见QueryOperators类  
	}
	
	
	
}