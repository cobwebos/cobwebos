package com.cobwebos.dapp.server.config;

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



	private String kafkaServerUrl;
	private String kafkaServerIP;
	private int kafkaServerPort;
	private int kafkaClientPort;

	private String hbaseServerUrl;
	private String hbaseServerIP;
	private int hbaseServerPort;
	private int hbaseClientPort;

	private String dappServerUrl;
	private String dappServerIP;
	private int dappServerPort;
	private int dappClientPort;
	
	private String juteMaxBuffer;

	public DappServerCfg() {				 
	}

	public DappServerCfg(String path) {
		InputStream io = null;
		try {
			io = new FileInputStream(path);
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
	public int getZkClientSessionTimeout() {
		return Integer.parseInt(p.getProperty("zkClientSessionTimeout"));
	}
}
