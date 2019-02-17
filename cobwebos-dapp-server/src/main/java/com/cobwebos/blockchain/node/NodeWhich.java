package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeWhich {
	private JSONObject obj;
	
	public NodeWhich(JSONObject obj) {
		this.obj=obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
}
