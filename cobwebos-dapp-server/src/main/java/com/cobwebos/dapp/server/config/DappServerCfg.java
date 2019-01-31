package com.cobwebos.dapp.server.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DappServerCfg {

	private Properties p = new Properties();
	private String zkServerUrl;
	private String zkServerIP;
	private int zkServerPort;
	private int zkClientPort;
	private int zkClientSessionTimeout;

	private String hbaseServerUrl;
	private String hbaseServerIP;
	private int hbaseServerPort;
	private int hbaseClientPort;

	private String dappServerUrl;
	private String dappServerIP;
	private int dappServerPort;
	private int dappClientPort;

	private String juteMaxBuffer;
		
	//kafka server
	private String kafkaServerUrl;
	private String kafkaServerIP;
	private int kafkaServerPort;
	private int kafkaClientPort;	
	
	private String kafkaZKConnect;
	private String kafkaGroupId;
	private String kafkaTopic;
	

	private int kafkaProducerBufferSize;
	private int kafkaConnectionTimeOut;
	private int kafkaReconnectInterval;
	private String kafkaClientId;
	
	//MessageProducer
	private String acks;
	private int retries;
	private int batchSize;
	private int lingerMs;
	private int bufferMemory;
	
	//MessageConsumer
	private String enableAutoCommit;
	
	private String keySerializer;
	private String valueSerializer;
	private String keyDeserializer;
	private String valueDeserializer;
	private int autoCommitIntervalMs;
	private int sessionTimeoutMs;
	
	private static final DappServerCfg cfg = new DappServerCfg();
	
	
	private DappServerCfg() {	
		initDappServerCfg();
	}
	
	public static synchronized DappServerCfg getInstance() {
		return cfg;		
	}

	public void initDappServerCfg() {
		String cfgPath = System.getProperty("user.dir") + File.separator + "etc" + File.separator
				+ "dappServer.properties";
		InputStream io = null;
		try {
			io = new FileInputStream(cfgPath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			p.load(io);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				io.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Properties getP() {
		return p;
	}

	public String getZkServerUrl() {
		return p.getProperty("zkServerUrl");
	}

	public String getZkServerIP() {
		return p.getProperty("zkServerIP"); 
	}

	public int getZkServerPort() {
		return Integer.parseInt(p.getProperty("zkServerPort").trim());
	}

	public int getZkClientPort() {
		return Integer.parseInt(p.getProperty("zkClientPort").trim());
	}
	
	public int getZkClientSessionTimeout() {
		return Integer.parseInt(p.getProperty("zkClientSessionTimeout"));
	}
	

	public String getKafkaServerUrl() {
		return  p.getProperty("kafkaServerUrl");
	}

	public String getKafkaServerIP() {
		return  p.getProperty("kafkaServerIP");
	}

	public int getKafkaServerPort() {
		return  Integer.parseInt(p.getProperty("kafkaServerPort"));
	}

	public int getKafkaClientPort() {
		return Integer.parseInt(p.getProperty("kafkaClientPort"));
	}

	public String getHbaseServerUrl() {
		return p.getProperty("hbaseServerUrl");
	}

	public String getHbaseServerIP() {
		return p.getProperty("hbaseServerIP");
	}

	public int getHbaseServerPort() {
		return Integer.parseInt(p.getProperty("hbaseServerPort"));
	}

	public int getHbaseClientPort() {
		return Integer.parseInt(p.getProperty("hbaseClientPort"));
	}

	public String getDappServerUrl() {
		return p.getProperty("dappServerUrl");
	}

	public String getDappServerIP() {
		return p.getProperty("dappServerIP");
	}

	public int getDappServerPort() {
		return Integer.parseInt(p.getProperty("dappServerPort"));
	}

	public int getDappClientPort() {
		return Integer.parseInt(p.getProperty("dappClientPort"));
	}
	
	public String getJuteMaxBuffer() {
		return p.getProperty("jute.maxbuffer");
	}
	public String getKafkaZKConnect() {
		return p.getProperty("kafkaZKConnect");
	}

	public String getKafkaGroupId() {
		return p.getProperty("kafkaGroupId");
	}

	public String getKafkaTopic() {
		return  p.getProperty("kafkaTopic");
	}

	public int getKafkaProducerBufferSize() {
		return Integer.parseInt(p.getProperty("kafkaProducerBufferSize").trim());
	}

	public int getKafkaConnectionTimeOut() {
		return Integer.parseInt(p.getProperty("kafkaConnectionTimeOut").trim());
	}

	public int getKafkaReconnectInterval() {
		return Integer.parseInt(p.getProperty("kafkaReconnectInterval").trim());
	}

	public String getKafkaClientId() {
		return p.getProperty("kafkaClientId");
	}

	public String getAcks() {
		return p.getProperty("acks");
	}

	public int getRetries() {
		return Integer.parseInt(p.getProperty("retries").trim());
	}

	public int getBatchSize() {
		return Integer.parseInt(p.getProperty("batchSize").trim());
	}

	public int getLingerMs() {
		return Integer.parseInt(p.getProperty("lingerMs").trim());
	}

	public int getBufferMemory() {
		return Integer.parseInt(p.getProperty("bufferMemory").trim());
	}

	public String getEnableAutoCommit() {
		return p.getProperty("enableAutoCommit").trim();
	}

	public String getKeyDeserializer() {
		return p.getProperty("keyDeserializer").trim();
	}

	public String getValueDeserializer() {
		return p.getProperty("valueDeserializer").trim();
	}

	public int getAutoCommitIntervalMs() {
		return Integer.parseInt(p.getProperty("autoCommitIntervalMs").trim());
	}

	public int getSessionTimeoutMs() {
		return Integer.parseInt(p.getProperty("sessionTimeoutMs").trim());
	}

	public String getKeySerializer() {
		return p.getProperty("keySerializer").trim();
	}

	public String getValueSerializer() {
		return p.getProperty("valueSerializer").trim();
	}

	
}
