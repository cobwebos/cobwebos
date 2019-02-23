package com.cobwebos.aaa.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.aaa.common.AAAConstants;

@Path(AAAConstants.AAA_HELLO)
public class AAAHello {
	Logger log = LoggerFactory.getLogger(AAAResource.class);

	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String hello(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);

		return data;
	}
}
