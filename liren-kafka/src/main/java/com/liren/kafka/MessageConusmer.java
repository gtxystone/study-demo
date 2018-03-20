package com.liren.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

public class MessageConusmer {
	
	public static void main(String[] args) {
		Properties p = new Properties();
		p.put("zookeeper.connect", "192.168.153.181:2181");
//		p.put("zookeeper.connection.timeout.ms", 15000);
//		p.put("zookeeper.session.timeout.ms", 15000);
		p.put("group.id", "test");
//		p.put("auto.commit.interval.ms", 60000);
//		p.put("auto.offset.reset", "largest");
		
		ConsumerConfig consumerConfig = new ConsumerConfig(p);
		ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put("mytopic", 8);
		Map<String, List<KafkaStream<byte[], byte[]>>> createMessageStreams = consumerConnector.createMessageStreams(topicCountMap);
		for(List<KafkaStream<byte[], byte[]>> list : createMessageStreams.values()){
			for(KafkaStream<byte[], byte[]> k : list){
				ConsumerIterator<byte[], byte[]> it = k.iterator();
				while (it.hasNext()) {
					MessageAndMetadata<byte[], byte[]> mm = it.next();
					String topic = mm.topic();
					System.out.println("toptic name: " + topic);
					System.out.println("message : " +  new String(mm.message()));
					
				}
			}
		}
	}

}
