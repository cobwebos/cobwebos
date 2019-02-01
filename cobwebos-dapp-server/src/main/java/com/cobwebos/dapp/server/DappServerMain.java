package com.cobwebos.dapp.server;

import com.cobwebos.dapp.server.config.DappServerCfg;
import com.cobwebos.dapp.server.datastore.HbaseConnection;
import com.cobwebos.dapp.server.event.MessageConsumer;
import com.cobwebos.dapp.server.event.MessageProducer;
import com.cobwebos.dapp.server.rest.RestServer;

public class DappServerMain {

	public void startHbase() {
		HbaseConnection.getInstance().listTableDescriptor();
		HbaseConnection.getInstance().createTable("inv", "tp");
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
		MessageConsumer.getInstance().ReceiveMessage(DappServerCfg.getInstance().getKafkaTopic());
		
	}

	public void stopMessageService() {

	}

}
