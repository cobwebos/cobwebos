package com.cobwebos.aaa.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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

@Path(AAAConstants.AAA_LOGIN_PATH)
public class AAALogin {
	Logger log = LoggerFactory.getLogger(AAALogin.class);

	@POST
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String postLogin(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);
		JSONObject input = new JSONObject(data);
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		return HttpClientUtils.getClientInstance().doPost(AAACfg.getInstance().getDappServerUrl() + which + "/" + who,
				data);
	}
}
