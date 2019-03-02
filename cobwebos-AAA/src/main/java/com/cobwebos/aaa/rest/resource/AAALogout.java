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

@Path(AAAConstants.AAA_LOGOUT_PATH)
public class AAALogout {
	Logger log = LoggerFactory.getLogger(AAALogout.class);

	@POST
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String postLogin(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);
		JSONObject input = new JSONObject(data);
		String who = input.getString("who");
		String where = input.getString("where");
		return HttpClientUtils.getClientInstance().doPost(AAACfg.getInstance().getDappServerUrl() + where + "/" + who,
				data);
	}
}
