package com.cobwebos.dapp.server;

import com.cobwebos.dapp.server.datastore.HbaseConnection;
import com.cobwebos.dapp.server.event.MessageConsumer;
import com.cobwebos.dapp.server.event.MessageProducer;
import com.cobwebos.dapp.server.rest.RestServer;

public class DappServerMain {

	public void startHbase() {
		HbaseConnection.getInstance().listTableDescriptor();
		HbaseConnection.getInstance().createTable("topo", "node");
		HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue("topo", "1", "node", "source", "node1");
		HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue("topo", "2", "node", "source", "node2");
		HbaseConnection.getInstance().getTableByTableName("topo");
	}

	public void startZKClient() {
		RestServer.startZKClient();
	}

	public void startRestServer() {
		RestServer.startRestServer();
	}

	public void stopHbase() {
		
	}

	public void stopZKClient() {
		RestServer.stopZKClient();
	}

	public void stopRestServer() {
		RestServer.stopRestServer();
	}

	public void startMessageService() {
		MessageProducer.getInstance().initProducer();		
		MessageProducer.getInstance().SendMessage("cobwebos", "URL", "http://www.cobwebos.com");
		
		MessageConsumer.getInstance().ReceiveMessage("topic1");
		MessageConsumer.getInstance().ReceiveMessage("cobwebos");

		
	}

	public void stopMessageService() {

	}

}
