package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeWhen {
	private JSONObject obj;

	public NodeWhen(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

}
