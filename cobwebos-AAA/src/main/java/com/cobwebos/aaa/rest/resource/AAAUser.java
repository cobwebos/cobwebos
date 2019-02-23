package com.cobwebos.aaa.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.aaa.common.AAACfg;
import com.cobwebos.aaa.common.AAAConstants;
import com.cobwebos.aaa.common.HttpClientUtils;

@Path(AAAConstants.AAA_USER_PATH)
public class AAAUser {
	Logger log = LoggerFactory.getLogger(AAAUser.class);

	@POST
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String postCreateUser(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);
		JSONObject input = new JSONObject(data);
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		return HttpClientUtils.getClientInstance().doPost(AAACfg.getInstance().getDappServerUrl() + which + "/" + who,
				data);
	}

	@DELETE
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String deleteUser(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);
		JSONObject input = new JSONObject(data);
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		return HttpClientUtils.getClientInstance().doPost(AAACfg.getInstance().getDappServerUrl() + which + "/" + who,
				data);
	}

	@PUT
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String putUser(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);
		JSONObject input = new JSONObject(data);
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		return HttpClientUtils.getClientInstance().doPost(AAACfg.getInstance().getDappServerUrl() + which + "/" + who,
				data);
	}

	@PATCH
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String patchUser(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);
		JSONObject input = new JSONObject(data);
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		return HttpClientUtils.getClientInstance().doPost(AAACfg.getInstance().getDappServerUrl() + which + "/" + who,
				data);
	}

	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getUser(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);
		JSONObject input = new JSONObject(data);
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		return HttpClientUtils.getClientInstance().doPost(AAACfg.getInstance().getDappServerUrl() + which + "/" + who,
				data);
	}
}
