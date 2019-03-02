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

import com.cobwebos.aaa.common.AAA;
import com.cobwebos.aaa.common.AAAConstants;

@Path(AAAConstants.AAA_LOGIN_PATH)
public class AAALogin {
	Logger log = LoggerFactory.getLogger(AAALogin.class);

	@POST
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String postLogin(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);		
		JSONObject input = new JSONObject(data);
		String who = input.getString("who");
		String where = input.getString("where");
		String password = input.getJSONObject("what").getJSONObject("user").getString("passowrd");
		String url = input.getJSONObject("what").getJSONObject("user").getString("url");
//		boolean login = false;
//		boolean authorization = false;
		boolean permission = false;
		try {

//			login = AAA.getInstance().doLogin(who, password);
//			log.info(">>>>>>do login:{}", login);
//			authorization = AAA.getInstance().doAuthorization(who, password);
//			log.info(">>>>>>do authorization:{}", authorization);
			permission = AAA.getInstance().doPermission(who, password, url);
			log.info(">>>>>>do Permission:{}", permission);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.error("login failed!");
		} finally {
			
//			if (login) {
//				log.info(">>>>>>do login:{}", login);
//				log.info("login successed!");
//				
//			} else {
//				log.info(">>>>>>do login:{}", login);
//				log.info("login failed!");
//			}
//
//			if (authorization) {
//				log.info(">>>>>>do authorization:{}", authorization);
//				log.info("user authorization successed!");
//				
//			} else {
//				log.info(">>>>>>do authorization:{}", authorization);
//				log.info("user authorization failed!");
//			
//			}
			if (permission) {
				log.info(">>>>>>do permission:{}", permission);
				log.info("user permission successed!");
			} else {
				log.info(">>>>>>do permission:{}", permission);
				log.info("user permission failed!");
			}

			
		}
		input.put("permission", permission);

		return input.toString();
	}
	
	
}
