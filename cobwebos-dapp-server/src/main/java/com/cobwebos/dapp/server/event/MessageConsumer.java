package com.cobwebos.dapp.server.event;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.server.config.DappServerCfg;
import com.cobwebos.dapp.server.datastore.HbaseConnection;

public class MessageConsumer {
	private static final MessageConsumer messageConsumer = new MessageConsumer();
	private Logger log = LoggerFactory.getLogger(MessageConsumer.class);
	private MessageConsumerImpl messageConsumerImpl;
	Thread t;
	
	private MessageConsumer() {
		
	}
	
	public synchronized static MessageConsumer getInstance() {
		return messageConsumer;
		
	}
	
	public void ReceiveMessage(String topic) {
		messageConsumerImpl = new MessageConsumerImpl(topic);
		t = new Thread(messageConsumerImpl);
		t.start();
		
	}
	

}

class MessageConsumerImpl implements Runnable{
	private String topic;
	private final AtomicBoolean closed = new AtomicBoolean(false);
	private KafkaConsumer<String, String> consumer;
	private Logger log = LoggerFactory.getLogger(MessageConsumerImpl.class);
	private static Properties props = new Properties();	
	
	static {
		props.put("bootstrap.servers", DappServerCfg.getInstance().getKafkaServerUrl());
		props.put("group.id", DappServerCfg.getInstance().getKafkaGroupId());
		props.put("enable.auto.commit", DappServerCfg.getInstance().getEnableAutoCommit());
		props.put("auto.commit.interval.ms", DappServerCfg.getInstance().getAutoCommitIntervalMs());
		props.put("session.timeout.ms", DappServerCfg.getInstance().getSessionTimeoutMs());
		props.put("key.deserializer", DappServerCfg.getInstance().getKeyDeserializer());
		props.put("value.deserializer", DappServerCfg.getInstance().getValueDeserializer());
		
	}

	public MessageConsumerImpl(String topic) {
		this.topic = topic;
		
	}
	
	
	public void ReceiveMessage(String topic) {
		Thread.currentThread().setContextClassLoader(null);
		try {
			consumer = new KafkaConsumer<>(props);
			consumer.subscribe(Arrays.asList(topic));
			while (!closed.get()) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
				for (ConsumerRecord<String, String> record : records) {
					String rowKey = record.key();
					String value = record.value();					
					log.info("consumer record.offset:{}, record.key:{}, record.value:{}", record.offset(),rowKey ,value);
					HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue("inv", rowKey, "tp", "source", value);					
					
				}

			}
		} catch (WakeupException e) {

			if (!closed.get())
				throw e;
		} finally {
			consumer.close();
		}
	}
	
	
//	public void ReceiveMessage(String topic) {
//		Thread.currentThread().setContextClassLoader(null);
//		try {
//			consumer = new KafkaConsumer<>(props);
//			consumer.subscribe(Arrays.asList(topic));
//			while (!closed.get()) {
//				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
//				for (ConsumerRecord<String, String> record : records) {
//					log.info("consumer record.offset:{}, record.key:{}, record.value:{}", record.offset(), record.key(),record.value());
//				}
//
//			}
//		} catch (WakeupException e) {
//
//			if (!closed.get())
//				throw e;
//		} finally {
//			consumer.close();
//		}
//	}

	public void shutdown() {
		closed.set(true);
		consumer.wakeup();
		consumer.close();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ReceiveMessage(topic);
		
	}
	
	

}

