package com.cobwebos.dapp.server.rest.RestResources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.zookeeper.KeeperException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.server.common.Constants;
import com.cobwebos.dapp.server.common.ZookeeperUtils;
import com.cobwebos.dapp.server.config.DappServerCfg;
import com.cobwebos.dapp.server.datastore.HbaseConnection;
import com.cobwebos.dapp.server.event.MessageProducer;

@Path(Constants.DAPP_SERVER_REST_APP_PATH)
public class RestconfResource {
	Logger log = LoggerFactory.getLogger(RestconfResource.class);

	@POST
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String createNodeByPath(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);
		JSONObject value = null;
		JSONObject json = null;
		if (path != null && data != null) {
			json = createBlockNodeByPath(path, data);
			String topic = DappServerCfg.getInstance().getKafkaTopic();
			String key = json.getJSONObject("data").getString("key");
			value = new JSONObject(json.toString());
			MessageProducer.getInstance().SendMessage(topic, key, value.toString());

		}
		return json.toString();
	}

	@DELETE
	public String deletePathNode(@PathParam("path") String path, String data) {
		String result = null;
		log.info("path:{},data:{}", path, data);
		JSONObject dataObj = new JSONObject(data);
		String cmd = dataObj.getString("cmd");
		String key = dataObj.getString("key");
		if (path != null && cmd.equalsIgnoreCase("rmr")) {
			result = rmrBlockNodeByPath(path).toString();
			HbaseConnection.getInstance().deleteOneRowAll("inv", key);
		} else if (path != null && cmd.equalsIgnoreCase("delete") && key != null) {
			result = deleteBlockNodeByPath(path).toString();
			HbaseConnection.getInstance().deleteOneRowAll("inv", key);
		}
		log.info("delete path:{},result:{}", path, result);

		return "200";
	}

	@PUT
	public String PutPathNode(@PathParam("path") String path, String data) {
		log.info("path:{}, data:{}", path, data);
		JSONObject value = null;
		JSONObject json = null;
		if (path != null && data != null) {
			json = setBlockNodeByPath(path, data);
			String topic = DappServerCfg.getInstance().getKafkaTopic();
			String key = json.getJSONObject("data").getString("key");
			value = new JSONObject(json.toString());
			MessageProducer.getInstance().SendMessage(topic, key, value.toString());

		}
		return json.toString();

	}

	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public String getNodeByPath(@PathParam("path") String path, String data) {
		log.info("path:{},data:{}", path, data);
		String get = null;
		JSONObject input = new JSONObject(data);
		JSONObject output = new JSONObject();
		JSONObject nodes = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("errorCode", 200);
		result.put("errorDesc", "success");
		String key = input.getString("key");

		JSONObject masterNode = null;
		JSONObject slaveNode = null;
		if (path != null && data != null && input.getString("cmd").equalsIgnoreCase("get")) {
			get = getBlockNodeValueByPath(path).toString();
			masterNode = new JSONObject(get);
			nodes.put("master", masterNode);
			if (!key.contains("/")) {
				slaveNode = getBlockNodeValueByRowKey(input.getString("key"));
				nodes.put("slave", slaveNode);
			}
			output.put("input", input);
			output.put("output", nodes);
			output.put("result", result);
		} else if (path != null && data != null && input.getString("cmd").equalsIgnoreCase("ls")) {
			get = lsBlockNodeByPath(path).toString();
			masterNode = new JSONObject(get);
			nodes.put("master", masterNode);
			output.put("input", input);
			output.put("output", nodes);
			output.put("result", result);
		} else {
			log.warn(" path is error!!!");
		}

		return output.toString();

	}

	public JSONObject getBlockNodeValueByPath(String path) {
		JSONObject stat = null;
		try {
			stat = new JSONObject(ZookeeperUtils.getNode(path));

		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}

		return stat;
	}

	public JSONObject getBlockNodeValueByRowKey(String rowkey) {
		JSONObject stat = null;
		try {
			stat = HbaseConnection.getInstance().getCellValueByRowKey("inv", rowkey, "tp", "source");

		} catch (JSONException e) {
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
