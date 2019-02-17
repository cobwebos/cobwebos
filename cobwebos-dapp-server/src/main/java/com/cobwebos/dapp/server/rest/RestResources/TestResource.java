package com.cobwebos.dapp.server.rest.RestResources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.client.HttpClientUtils;
import com.cobwebos.dapp.server.common.Constants;

@Path(Constants.BLOCK_CHAIN_RESOURCE_TEST_URI)
public class TestResource {

	public Logger log = LoggerFactory.getLogger(TestResource.class);

	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@POST
	public String createBlockNode(@PathParam("schema") String schema, String data) {
		log.info("schema:{},data:{}", schema, data);
		JSONObject input = new JSONObject(data);
		JSONObject output = new JSONObject();
		JSONObject obj = new JSONObject();
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		String where = input.getString("node-where");
		String when = input.getString("node-which");
		JSONObject what = input.getJSONObject("node-what");
		String why = input.getString("node-why");
		String howToDo = input.getString("node-how-to-do");
		String howMuch = input.getString("node-how-much");
		int from = input.getInt("node-from");
		int to = input.getInt("node-to");
		
		JSONObject whatObj = new JSONObject(what);
		
		output.put("node-who", who);
		output.put("node-which", which);
		output.put("node-where", where);
		output.put("node-what", whatObj);
		output.put("node-when", when);
		output.put("node-why", why);
		output.put("node-how-to-do", howToDo);
		output.put("node-how-much", howMuch);
		output.put("node-from", from);
		output.put("node-to", to);

//		doPost(input);
		
		JSONObject inputObj = new JSONObject();		
		for (int i = input.getInt("node-from"); i < input.getInt("node-to"); i++) {
			JSONObject nodeWhatObj = new JSONObject();
			nodeWhatObj.put("node-id", "" + i);
			nodeWhatObj.put("node-name", "" + i);
			inputObj.put("node-who", "" + i);
			inputObj.put("node-which", input.getString("node-which"));
			inputObj.put("node-where", input.getString("node-where"));
			inputObj.put("node-why", input.getString("node-why"));
			inputObj.put("node-what", nodeWhatObj);
			inputObj.put("node-when", input.getString("node-when"));
			inputObj.put("node-how-to-do", input.getString("node-how-to-do"));
			inputObj.put("node-how-much", input.getString("node-how-much"));
			inputObj.put("node-from", input.getInt("node-from"));
			inputObj.put("node-to", input.getInt("node-to"));			
			HttpClientUtils.getClientInstance().doPost("http://192.168.0.2:2019/blockchain/"+input.getString("node-which")+"/"+i, inputObj.toString());
			log.info("inputObj:{}",inputObj.toString());
		}
		
		obj.put("input", input);
		obj.put("output", output);

		return obj.toString();
	}
	
	public void doPost(JSONObject input) {		
		JSONObject inputObj = new JSONObject();		
		for (int i = input.getInt("node-from"); i < input.getInt("node-to"); i++) {
			JSONObject nodeWhatObj = new JSONObject();
			nodeWhatObj.put("node-id", "" + i);
			nodeWhatObj.put("node-name", "" + i);
			inputObj.put("node-who", "" + i);
			inputObj.put("node-which", input.getString("node-which"));
			inputObj.put("node-where", input.getString("node-where"));
			inputObj.put("node-why", input.getString("node-why"));
			inputObj.put("node-what", nodeWhatObj);
			inputObj.put("node-when", input.getString("node-when"));
			inputObj.put("node-how-to-do", input.getString("node-how-to-do"));
			inputObj.put("node-how-much", input.getString("node-how-much"));
			inputObj.put("node-from", input.getInt("node-from"));
			inputObj.put("node-to", input.getInt("node-to"));			
			HttpClientUtils.getClientInstance().doPost("http://192.168.0.2:2019/blockchain/"+input.getString("node-which")+"/"+i, inputObj.toString());
			log.info("inputObj:{}",inputObj.toString());
		}
	
	}
	

}
