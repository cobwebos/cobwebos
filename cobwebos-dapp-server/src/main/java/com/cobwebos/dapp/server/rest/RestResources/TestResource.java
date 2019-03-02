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
		String who = input.getString("who");
		String which = input.getString("which");
		String where = input.getString("where");
		String when = input.getString("which");
		JSONObject what = input.getJSONObject("what");
		String why = input.getString("why");
		String howToDo = input.getString("howToDo");
		String howMuch = input.getString("howMuch");
		String howMany = input.getString("howMany");
		int from = input.getInt("from");
		int to = input.getInt("to");
		
		JSONObject whatObj = new JSONObject(what);
		
		output.put("who", who);
		output.put("which", which);
		output.put("where", where);
		output.put("what", whatObj);
		output.put("when", when);
		output.put("why", why);
		output.put("howToDo", howToDo);
		output.put("howMuch", howMuch);
		output.put("howMany", howMany);
		output.put("from", from);
		output.put("to", to);

//		doPost(input);
		
		JSONObject inputObj = new JSONObject();		
		for (int i = input.getInt("from"); i < input.getInt("to"); i++) {
			JSONObject nodeWhatObj = new JSONObject();
			nodeWhatObj.put("id", "" + i);
			nodeWhatObj.put("name", "" + i);
			inputObj.put("who", "" + i);
			inputObj.put("which", input.getString("which"));
			inputObj.put("where", input.getString("where"));
			inputObj.put("why", input.getString("why"));
			inputObj.put("what", nodeWhatObj);
			inputObj.put("when", input.getString("when"));
			inputObj.put("howToDo", input.getString("howToDo"));
			inputObj.put("howMuch", input.getString("howMuch"));
			inputObj.put("howMany", input.getString("howMany"));
			inputObj.put("from", input.getInt("from"));
			inputObj.put("to", input.getInt("to"));			
			String reponse = HttpClientUtils.getClientInstance().doPost("http://192.168.0.2:2019/blockchain/"+input.getString("where")+"/"+i, inputObj.toString());
			log.info("inputObj:{},reponse:{}",inputObj.toString(),reponse);
		}
		
		obj.put("input", input);
		obj.put("output", output);

		return obj.toString();
	}
	
	public void doPost(JSONObject input) {		
		JSONObject inputObj = new JSONObject();		
		for (int i = input.getInt("from"); i < input.getInt("to"); i++) {
			JSONObject nodeWhatObj = new JSONObject();
			nodeWhatObj.put("id", "" + i);
			nodeWhatObj.put("name", "" + i);
			inputObj.put("who", "" + i);
			inputObj.put("which", input.getString("which"));
			inputObj.put("where", input.getString("where"));
			inputObj.put("why", input.getString("why"));
			inputObj.put("what", nodeWhatObj);
			inputObj.put("when", input.getString("when"));
			inputObj.put("howToDo", input.getString("howToDo"));
			inputObj.put("howMuch", input.getString("howMuch"));
			inputObj.put("howMany", input.getString("howMany"));
			inputObj.put("from", input.getInt("from"));
			inputObj.put("to", input.getInt("to"));			
			HttpClientUtils.getClientInstance().doPost("http://192.168.0.2:2019/blockchain/"+input.getString("where")+"/"+i, inputObj.toString());
			log.info("inputObj:{}",inputObj.toString());
		}
	
	}
	

}
