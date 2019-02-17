package com.cobwebos.dapp.server.rest.RestResources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cobwebos.dapp.server.common.Constants;


@Path(Constants.DAPP_SERVER_REST_BASE_HELLO)
@Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class Hello {
    @GET
    public String hello (){
        return "ok";
    }
    
}
