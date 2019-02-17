package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeWho {
	private JSONObject obj;
	
	public NodeWho(JSONObject obj) {
		this.obj=obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
	
	
}
