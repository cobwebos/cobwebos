package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeWhere {
	private JSONObject obj;

	public NodeWhere(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

}
