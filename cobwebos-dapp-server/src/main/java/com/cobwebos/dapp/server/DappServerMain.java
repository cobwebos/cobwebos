package com.cobwebos.dapp.server;

import com.cobwebos.dapp.server.datastore.HbaseConnection;
import com.cobwebos.dapp.server.rest.RestServer;

public class DappServerMain {

	public void startHbase() {
		HbaseConnection.listTableDescriptor();
		HbaseConnection.createTable("topo", "node");
		HbaseConnection.insertAndUpdateOneRowOneColumnFamilyOneClumnValue("topo", "1", "node", "source", "node1");
		HbaseConnection.insertAndUpdateOneRowOneColumnFamilyOneClumnValue("topo", "2", "node", "source", "node2");
		HbaseConnection.getTableByTableName("topo");
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


}
