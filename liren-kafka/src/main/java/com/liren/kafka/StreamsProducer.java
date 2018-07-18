package com.liren.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.state.KeyValueStore;

public class StreamsProducer {
	
	public static void main(String[] args) {
        
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.153.181:9092,192.168.153.182:9092,192.168.153.183:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
 
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> textLines = builder.stream("TextLinesTopic");
        KTable<String, Long> wordCounts = textLines.flatMapValues(new ValueMapper<String, Iterable<String>>() {
                @Override
                public Iterable<String> apply(String textLine) {
                    return Arrays.asList(textLine.toLowerCase().split("\\W+"));
                }
            })
            .groupBy(new KeyValueMapper<String, String, String>() {
                @Override
                public String apply(String key, String word) {
                    return word;
                }
            })
            .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
 
 
        wordCounts.toStream().to("", Produced.with(Serdes.String(), Serdes.Long()));
 
        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
	}

}
