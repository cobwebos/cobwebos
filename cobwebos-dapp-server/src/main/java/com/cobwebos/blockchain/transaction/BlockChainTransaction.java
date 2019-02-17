package com.cobwebos.blockchain.transaction;

import org.json.JSONObject;

public class BlockChainTransaction {
	private JSONObject obj;

	public BlockChainTransaction(JSONObject obj) {
		this.obj = obj;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

}
