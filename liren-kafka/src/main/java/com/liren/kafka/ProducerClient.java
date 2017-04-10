package com.liren.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Hello world!
 *
 */
public class ProducerClient {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.153.139:9092");
//		props.put("bootstrap.servers", "192.168.153.136:9092,192.168.153.137:9092,192.168.153.138:9092");
		props.put("acks", "all"); // ack方式，all，会等所有的commit最慢的方式
		props.put("retries", 0); // 失败是否重试，设置会有可能产生重复数据
		props.put("batch.size", 16384); // 对于每个partition的batch buffer大小
		props.put("linger.ms", 1); // 等多久，如果buffer没满，比如设为1，即消息发送会多1ms的延迟，如果buffer没满
		props.put("buffer.memory", 33554432); // 整个producer可以用于buffer的内存大小
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(props);
		
		
		for (int i = 0; i < 100; i++)
			producer.send(new ProducerRecord<String, String>("mytopic", Integer.toString(i), Integer.toString(i)));

		producer.close();
	}

}