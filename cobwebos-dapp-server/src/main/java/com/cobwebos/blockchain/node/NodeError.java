package com.cobwebos.blockchain.node;

import org.json.JSONObject;

import com.cobwebos.blockchain.node.entry.NodeErrorBean;

public class NodeError {
	private JSONObject obj;
	private NodeErrorBean error;

	public NodeError(JSONObject obj, NodeErrorBean error) {
		this.obj = obj;
		this.error = error;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public NodeErrorBean getError() {
		return error;
	}

	public void setError(NodeErrorBean error) {
		this.error = error;
	}

}
