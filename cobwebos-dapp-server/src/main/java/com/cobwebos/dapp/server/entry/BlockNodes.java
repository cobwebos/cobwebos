package com.cobwebos.dapp.server.entry;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.server.rest.RestServer;

public class BlockNodes {
	private Logger log = LoggerFactory.getLogger(BlockNodes.class);

	private List<BlockNode> blockNodes = new ArrayList<>();

	public List<BlockNode> getBlockNode() {
		return blockNodes;
	}

	public void setBlockNode(List<BlockNode> blockNode) {
		this.blockNodes = blockNode;
	}

	public void addBlockNode(BlockNode blockNode) {
		this.blockNodes.add(blockNode);
	}

	public JSONObject getChildrenBlockNode(String path) {
		;
		JSONObject json = null;
		try {			
			json = new JSONObject(RestServer.zk.getChildrenNode(path));
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}
		return json;
	}

	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject(blockNodes);
		return json;
	}

}
