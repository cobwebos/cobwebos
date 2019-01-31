package com.cobwebos.dapp.server.event;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.server.config.DappServerCfg;

public class MessageProducer {
	private Logger log = LoggerFactory.getLogger(MessageProducer.class);
	private String TOPIC = DappServerCfg.getInstance().getKafkaTopic();
	private static final MessageProducer messageProducer = new MessageProducer();
	private Properties props = new Properties();
	private Producer<String, String> producer;

	private MessageProducer() {
		props.put("bootstrap.servers", DappServerCfg.getInstance().getKafkaServerUrl());
		props.put("acks", "all");// DappServerCfg.getInstance().getAcks());
		props.put("retries", DappServerCfg.getInstance().getRetries());
		props.put("batch.size", DappServerCfg.getInstance().getBatchSize());
		props.put("linger.ms", DappServerCfg.getInstance().getLingerMs());
		props.put("buffer.memory", DappServerCfg.getInstance().getBufferMemory());
		props.put("key.serializer", DappServerCfg.getInstance().getKeySerializer());
		props.put("value.serializer", DappServerCfg.getInstance().getValueSerializer());

	}

	public void initProducer() {
		Thread.currentThread().setContextClassLoader(null);
		producer = new KafkaProducer<>(props);
		for (int i = 0; i < 10; i++) {
			producer.send(new ProducerRecord<String, String>(TOPIC, Integer.toString(i), Integer.toString(i)));
			log.info("producer record topic:{} body:{} ", TOPIC, "hello " + i + " !");
		}
		producer.close();

	}

	public synchronized static MessageProducer getInstance() {
		return messageProducer;

	}

	public void SendMessage(String topic, String value) {
		Thread.currentThread().setContextClassLoader(null);
		producer = new KafkaProducer<>(props);
		producer.send(new ProducerRecord<String, String>(topic, value));
		log.info("producer record topic:{},value:{} ", topic, value);
//		producer.close();

	}

	public void SendMessage(String topic, String key, String value) {
		Thread.currentThread().setContextClassLoader(null);
		producer = new KafkaProducer<>(props);
		producer.send(new ProducerRecord<String, String>(topic, key, value));
		log.info("producer record topic:{},key:{},value:{} ", topic, key, value);
//		producer.close();

	}
	
	public void shutdown() {
		producer.close();
	}

}
