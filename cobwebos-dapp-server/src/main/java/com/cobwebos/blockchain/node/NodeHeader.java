package com.cobwebos.blockchain.node;

import org.json.JSONObject;

import com.cobwebos.blockchain.node.entry.NodeHeaderBean;

public class NodeHeader {
	private JSONObject obj;
	private NodeHeaderBean header;

	public NodeHeader(JSONObject obj,NodeHeaderBean header) {
		this.obj = obj;
		this.header = header;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public NodeHeaderBean getHeader() {
		return header;
	}

	public void setHeader(NodeHeaderBean header) {
		this.header = header;
	}

}
