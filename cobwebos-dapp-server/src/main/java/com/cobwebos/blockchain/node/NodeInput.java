package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeInput {
	private JSONObject obj;

	public NodeInput(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
	
}
