package com.cobwebos.dapp.server.common;

import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		Logger log = LoggerFactory.getLogger(App.class);
		System.setProperty("jute.maxbuffer", 41943040 + "");
		String hosts = "192.168.0.2:12181,192.168.0.2:12182,192.168.0.2:12183";
		int sessionTimeout = 3000;
		String path = "/";
		String data = "newwork topology";
		CreateMode createMode = CreateMode.PERSISTENT;
		ZookeeperUtils zk = new ZookeeperUtils();
		// 连接zk服务器
		zk.connect(hosts, sessionTimeout);
		// 删除根节点
//		zk.rmrNode("/");
//		// 创建节点
//		zk.createNode(path, data, createMode);
//		// 查询节点
		zk.getChildrenNode("/topo");
//
//		zk.getNodeValue("/topo");
//
//		// 修改节点数据
//		zk.setNodeValue(path, "inventory");
//
//		// 查询节点
//		zk.getNodeValue("/topo");
		
//		log.info(System.currentTimeMillis() + "begin...");
//		for (int i = 0; i<100000000; i++) {			
//			zk.createNode(path+path + i, data + i, createMode);			
//		}
	
	}

}
