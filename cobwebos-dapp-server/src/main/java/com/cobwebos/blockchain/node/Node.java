package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class Node {
	private JSONObject obj;
	private NodeHeader header;
	private NodeBody body;	
	private NodeFooter footer;

	public Node(JSONObject obj,NodeHeader header,NodeBody body,NodeFooter footer) {
		this.obj = obj;		
		this.header = header;
		this.body = body;	
		this.footer = footer;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public NodeHeader getHeader() {
		return header;
	}

	public void setHeader(NodeHeader header) {
		this.header = header;
	}

	public NodeBody getBody() {
		return body;
	}

	public void setBody(NodeBody body) {
		this.body = body;
	}

	public NodeFooter getFooter() {
		return footer;
	}

	public void setFooter(NodeFooter footer) {
		this.footer = footer;
	}	

}
