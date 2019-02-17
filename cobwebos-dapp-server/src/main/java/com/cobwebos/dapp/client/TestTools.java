package com.cobwebos.dapp.client;

import org.json.JSONObject;

public class TestTools {

	public static void main(String[] args) {
		String input = null;
		JSONObject inputObj = new JSONObject();		
		for (int i = 0; i < 10; i++) {
			JSONObject nodeWhatObj = new JSONObject();
			nodeWhatObj.put("node-id", "" + i);
			nodeWhatObj.put("node-name", "" + i);
			inputObj.put("node-who", "" + i);
			inputObj.put("node-which", "inventory");
			inputObj.put("node-where", "node");
			inputObj.put("node-why", "cf");
			inputObj.put("node-what", nodeWhatObj);
			inputObj.put("node-when", "when");
			inputObj.put("node-how-to-do", "post");
			inputObj.put("node-how-much", "hash much");
			input = inputObj.toString();
			HttpClientUtils.getClientInstance().doPost("http://192.168.0.2:2019/blockchain/inventory/"+i, input);
		
		}
	}

}
