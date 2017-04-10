package com.liren.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerClient {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.153.139:9092");
//		props.put("bootstrap.servers", "192.168.153.136:9092,192.168.153.137:9092,192.168.153.138:9092");
//		props.put("group.id", "test");
		props.put("group.id", "console-consumer-62593");
		props.put("enable.auto.commit", "true"); // 自动commit
		props.put("auto.commit.interval.ms", "1000"); // 定时commit的周期
		props.put("session.timeout.ms", "30000"); // consumer活性超时时间
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
//		consumer.subscribe(Arrays.asList("foo", "bar")); // subscribe，foo，bar，两个topic
		consumer.subscribe(Arrays.asList("mytopic")); // subscribe，foo，bar，两个topic
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100); // poll
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
		}
	}
}