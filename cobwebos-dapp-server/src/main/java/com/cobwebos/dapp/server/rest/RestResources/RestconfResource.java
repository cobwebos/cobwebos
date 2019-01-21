package com.cobwebos.dapp.server.rest.RestResources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.zookeeper.KeeperException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.server.common.Constants;
import com.cobwebos.dapp.server.common.ZookeeperUtils;

@Path(Constants.DAPP_SERVER_REST_APP_PATH)
public class RestconfResource {
	Logger log = LoggerFactory.getLogger(RestconfResource.class);

	@POST
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String createNodeByPath(@PathParam("path") String path, @QueryParam("data") String data) {
		log.info("path:{},data:{}", path, data);
		JSONObject json = new JSONObject();
		if (path != null && data != null) {
			json = createBlockNodeByPath(path, data);
		}
		return json.toString();
	}

	@DELETE
	public void deletePathNode(@PathParam("path") String path, @QueryParam("op") String op) {
		String result = null;
		log.info("operation:{}, path:{}", op, path);
		if (path != null && op.equalsIgnoreCase("rmr")) {
			result = rmrBlockNodeByPath(path).toString();
		} else if (path != null && op.equalsIgnoreCase("delete")) {
			result = deleteBlockNodeByPath(path).toString();
		}
		log.info("operation:{}, path:{},result:{}", op, path, result);
	}

	@PUT
	public String PutPathNode(@PathParam("path") String path, @QueryParam("data") String data) {
		log.info("path:{}, data:{}", path, data);
		String result = null;
		if (path != null) {
			result = setBlockNodeByPath(path, data).toString();
		}
		result = getBlockNodeValueByPath(path).toString();
		log.info("path:{}, data:{},result:{}", path, data, result);
		return result;

	}

	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public String getNodeByPath(@PathParam("path") String path, @QueryParam("op") String op, @Context UriInfo ui) {
		log.info("path:{},operation:{}", path, op);
		String get = null;
		if (path != null && op.equalsIgnoreCase("get")) {
			get = getBlockNodeValueByPath(path).toString();
		} else if (path != null && op.equalsIgnoreCase("ls")) {
			get = lsBlockNodeByPath(path).toString();
		} else if ((path.equalsIgnoreCase("") && op.equalsIgnoreCase("ls"))
				|| (path.isEmpty() && op.equalsIgnoreCase("ls")) || (path == null && op.equalsIgnoreCase("ls"))) {
			get = lsBlockNodeByPath(path).toString();
		} else if ((path.equalsIgnoreCase("") && op.equalsIgnoreCase("get"))
				|| (path.isEmpty() && op.equalsIgnoreCase("get")) || (path == null && op.equalsIgnoreCase("get"))) {
			get = getBlockNodeValueByPath(path).toString();
		} else {
			log.warn(" path is error!!!");
		}

		return get;

	}

	public JSONObject getBlockNodeValueByPath(String path) {
		JSONObject stat =new JSONObject();
		try {
			stat.put("node", ZookeeperUtils.getNode(path));
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}

		return stat;
	}

	public JSONObject lsBlockNodeByPath(String path) {
		JSONObject blockNode = null;
		try {
			blockNode = new JSONObject();
			blockNode.put("path", path);
			blockNode.put("children", ZookeeperUtils.getChildrenNode(path));
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}
		return blockNode;
	}

	public JSONObject createBlockNodeByPath(String path, String data) {		
		ZookeeperUtils.createNode(path, data);		
		return getBlockNodeValueByPath(path);
	}

	public JSONObject deleteBlockNodeByPath(String path) {
		JSONObject blockNode = null;
		blockNode = new JSONObject();
		blockNode.put("path", path);
		blockNode.put("data", ZookeeperUtils.deleteNode(path));
		return blockNode;
	}

	public JSONObject rmrBlockNodeByPath(String path) {
		JSONObject blockNode = null;
		blockNode = new JSONObject();
		blockNode.put("path", path);
		blockNode.put("data", ZookeeperUtils.rmrNode(path));
		return blockNode;
	}

	public JSONObject setBlockNodeByPath(String path, String data) {		
		 ZookeeperUtils.setNodeValue(path, data);
		 return getBlockNodeValueByPath(path);
	}

}
