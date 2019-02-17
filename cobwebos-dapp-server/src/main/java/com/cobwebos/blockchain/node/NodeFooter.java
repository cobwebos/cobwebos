package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeFooter {
	private JSONObject obj;

	public NodeFooter(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

}
