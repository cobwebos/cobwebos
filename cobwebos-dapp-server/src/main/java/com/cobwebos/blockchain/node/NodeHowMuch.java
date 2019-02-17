package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeHowMuch {
	private JSONObject obj;

	public NodeHowMuch(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
}
