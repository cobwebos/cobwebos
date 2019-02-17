package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeWhy {
	private JSONObject obj;

	public NodeWhy(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

}
