/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cobwebos.restconf;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author cobwebos
 * 
 */

@Path("cobwebos/restconf")

public class RestfulResources {
	
	public static final String HelloMsg = "hello";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return HelloMsg;

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/nodes")
	public String getNodes() {
		return "{\"nodes\":[{},{}]}";

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/nodes/node")
	public String getNodeById(@QueryParam("id") String id) {
		return "{\"node\":{}}";

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/nodes/node")
	public String createNodeById(@QueryParam("id") String id) {
		return "{\"node\":{}}";

	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/nodes/node")
	public String updateNodeById(@QueryParam("id") String id) {
		return "{\"node\":{}}";

	}
	
	@PATCH
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/nodes/node")
	public String patchNodeById(@QueryParam("id") String id) {
		return "{\"node\":{}}";

	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/nodes/node")
	public String deleteNodeById(@QueryParam("id") String id) {
		return "{\"node\":{}}";

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/rpc/deleteNode")
	public String deleteNodeByIdRpc(@QueryParam("id") String id) {
		return "{\"node\":{}}";

	}

}
