package com.cobwebos.dapp.server.rest.RestResources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("blocksfc/hello")
@Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
@Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
public class RestconfResource {
//    private static RestconfResource ourInstance = new RestconfResource();
//
//    public static RestconfResource getInstance() {
//        return ourInstance;
//    }
//
//    private RestconfResource() {
//    }

    @GET
    public String hello (){
    return "200";
    }


}
