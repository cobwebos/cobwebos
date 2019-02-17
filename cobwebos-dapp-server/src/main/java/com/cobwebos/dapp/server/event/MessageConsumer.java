package com.cobwebos.dapp.server.event;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.json.JSONObject;
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

class MessageConsumerImpl implements Runnable {
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
					JSONObject headerObj = null;
					String head = record.key();
					if (head != null) {
						headerObj = new JSONObject(head);
					}

					JSONObject bodyObj = null;
					String body = record.value();
//					if (body != null) {
//						bodyObj = new JSONObject(body);
//					}
					String todo = headerObj.getString("node-how-to-do");
					if (todo != null) {
						if (todo.equalsIgnoreCase("merge") || todo.equalsIgnoreCase("post")
								|| todo.equalsIgnoreCase("put") || todo.equalsIgnoreCase("patch")) {
							HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(
									headerObj.getString("node-which"), headerObj.getString("node-who"),
									headerObj.getString("node-why"), headerObj.getString("node-where"),
									body);
						} else if (todo.equalsIgnoreCase("delete") || todo.equalsIgnoreCase("remove")
								|| todo.equalsIgnoreCase("rmr")) {
							HbaseConnection.getInstance().deleteOneRowAll(headerObj.getString("node-which"),
									headerObj.getString("node-who"));
						}
						log.info("message consumer record topic:{},offset:{}, msgHeader:{}, msgBody:{}", topic,
								record.offset(), headerObj.toString(), body);
					} else {
						log.warn("message consumer record topic:{},offset:{}, msgHeader:{}, msgBody:{}", topic,
								record.offset(), headerObj.toString(), body);
					}

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
