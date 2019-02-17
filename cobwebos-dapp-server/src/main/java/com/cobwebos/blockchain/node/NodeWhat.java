package com.cobwebos.blockchain.node;

import org.json.JSONObject;

import com.cobwebos.blockchain.node.entry.NodeBean;

public class NodeWhat {
	private JSONObject obj;
	private NodeBean node;

	public NodeWhat(JSONObject obj,NodeBean node) {
		this.obj = obj;
		this.node = node;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public NodeBean getNode() {
		return node;
	}

	public void setNode(NodeBean node) {
		this.node = node;
	}
	
}
