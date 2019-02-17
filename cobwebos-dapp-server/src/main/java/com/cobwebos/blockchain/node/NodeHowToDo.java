package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeHowToDo {
	private JSONObject obj;

	public NodeHowToDo(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
}
