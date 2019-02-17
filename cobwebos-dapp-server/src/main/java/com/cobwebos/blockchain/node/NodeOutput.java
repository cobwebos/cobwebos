package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeOutput {
	private JSONObject obj;
	private Node node;
	private NodeError error;

	public NodeOutput(JSONObject obj,Node node,NodeError error) {
		this.obj = obj;
		this.node = node;
		this.error = error;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public NodeError getError() {
		return error;
	}

	public void setError(NodeError error) {
		this.error = error;
	}

}
