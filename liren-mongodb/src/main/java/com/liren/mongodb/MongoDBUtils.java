package com.liren.mongodb;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtils {

	@Test
	public void test() {

		List<ServerAddress> asList = Arrays.asList(new ServerAddress("192.168.182.101", 20000),
	              new ServerAddress("192.168.182.102", 20000),
	              new ServerAddress("192.168.182.103", 20000));		
		MongoClient mongoClient = new MongoClient(asList);
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("users");	
		FindIterable<Document> find = collection.find();
		for(Document d: find) {
//			String id = d.getString("user_id");
			Object object = d.get("user_id");
			System.out.println(object);
		}
		

	}

}
