package com.cobwebos.dapp.server.common;

import org.json.JSONObject;

import com.cobwebos.dapp.server.entry.BlockNode;

public class Output {

	private int errorCode;
	private String errorDesc;
	private BlockNode blockNode;

	public Output(int errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.putOpt("output", this);
		return obj;
	}
	
	

}
