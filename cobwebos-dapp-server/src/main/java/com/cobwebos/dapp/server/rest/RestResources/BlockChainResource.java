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
import com.cobwebos.dapp.server.datastore.HbaseConnection;

@Path(Constants.BLOCK_CHAIN_RESOURCE_URI)
public class BlockChainResource {
	public Logger log = LoggerFactory.getLogger(BlockChainResource.class);

	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@POST
	public String createBlockNode(@PathParam("schema") String schema, String data) {
		log.info("schema:{},data:{}", schema, data);
		JSONObject input = new JSONObject(data);
		JSONObject output = new JSONObject();
		JSONObject obj = new JSONObject();
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		String where = input.getString("node-where");
		String when = input.getString("node-which");
		JSONObject what = input.getJSONObject("node-what");
		String why = input.getString("node-why");
		String howToDo = input.getString("node-how-to-do");
		String howMuch = input.getString("node-how-much");

		if (null == ZookeeperUtils.isExistsNode(schema)) {
			// 写入主链
			if (null == ZookeeperUtils.isExistsNode("/" + which)) {
				ZookeeperUtils.createNode("/" + which, which);
			}
			ZookeeperUtils.createNode(schema, what.toString());
			// 写入从链
			if (!HbaseConnection.getInstance().tableExists(which)) {
				HbaseConnection.getInstance().createTable(which, why);
			} else {
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(which, who, why, where,
						what.toString());
			}

		} else {
			// 写入主链
			ZookeeperUtils.setNodeValue(schema, what.toString());
			// 写入从链
			if (!HbaseConnection.getInstance().tableExists(which)) {
				HbaseConnection.getInstance().createTable(which, why);
			} else {
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(which, who, why, where,
						what.toString());
			}

		}
		// 校验数据
		JSONObject whatObj = new JSONObject();
		// 校验主链数据
		JSONObject whatMasterObj = null;
		try {
			if (null == ZookeeperUtils.getNodeValue(schema) || null == ZookeeperUtils.isExistsNode(schema)) {
				whatMasterObj = new JSONObject();
			} else {
				whatMasterObj = new JSONObject(ZookeeperUtils.getNodeValue(schema));
			}
			whatObj.put("master-chain-node", whatMasterObj);
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}

		// 校验从链数据
		JSONObject whatSlaveObj = null;
		if (null == HbaseConnection.getInstance().getCellValueByRowKey(which, who, why, where)) {
			whatSlaveObj = new JSONObject();
			whatObj.put("slave-chain-node", whatSlaveObj);
		} else {
			whatSlaveObj = HbaseConnection.getInstance().getCellValueByRowKey(which, who, why, where);
			whatObj.put("slave-chain-node", whatSlaveObj);
		}

		output.put("who", who);
		output.put("which", which);
		output.put("where", where);
		output.put("what", whatObj);
		output.put("when", when);
		output.put("why", why);
		output.put("node-how-to-do", howToDo);
		output.put("node-how-much", howMuch);

		obj.put("input", input);
		obj.put("output", output);

		return obj.toString();
	}

	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@DELETE
	public String deleteBlockNode(@PathParam("schema") String schema, String data) {
		log.info("schema:{},data:{}", schema, data);
		JSONObject input = new JSONObject(data);
		JSONObject output = new JSONObject();
		JSONObject obj = new JSONObject();
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		String where = input.getString("node-where");
		String when = input.getString("node-which");
		JSONObject what = input.getJSONObject("node-what");
		String why = input.getString("node-why");
		String howToDo = input.getString("node-how-to-do");
		String howMuch = input.getString("node-how-much");
		// 删除主链
		ZookeeperUtils.rmrNode(schema);
		// 删除从链
		HbaseConnection.getInstance().deleteOneRowAll(which, who);

		output.put("who", "");
		output.put("which", which);
		output.put("where", where);
		output.put("what", "{}");
		output.put("when", when);
		output.put("why", why);
		output.put("node-how-to-do", howToDo);
		output.put("node-how-much", howMuch);

		obj.put("input", input);
		obj.put("output", output);

		return obj.toString();
	}

	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@PUT
	public String putBlockNode(@PathParam("schema") String schema, String data) {
		log.info("schema:{},data:{}", schema, data);
		JSONObject input = new JSONObject(data);
		JSONObject output = new JSONObject();
		JSONObject obj = new JSONObject();
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		String where = input.getString("node-where");
		String when = input.getString("node-which");
		JSONObject what = input.getJSONObject("node-what");
		String why = input.getString("node-why");
		String howToDo = input.getString("node-how-to-do");
		String howMuch = input.getString("node-how-much");

		if (ZookeeperUtils.isExistsNode(schema) != null) {
			// 写入主链
			ZookeeperUtils.setNodeValue(schema, what.toString());
			// 写入从链
			if (!HbaseConnection.getInstance().tableExists(which)) {
				HbaseConnection.getInstance().createTable(which, why);
			} else {
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(which, who, why, where,
						what.toString());
			}

		} else {
			// 写入主链
			if (null == ZookeeperUtils.isExistsNode("/" + which)) {
				ZookeeperUtils.createNode("/" + which, which);
			}
			ZookeeperUtils.createNode(schema, what.toString());
			// 写入从链
			if (!HbaseConnection.getInstance().tableExists(which)) {
				HbaseConnection.getInstance().createTable(which, why);
			} else {
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(which, who, why, where,
						what.toString());
			}

		}

		// 校验数据
		JSONObject whatObj = new JSONObject();
		// 校验主链数据
		JSONObject whatMasterObj = null;
		try {

			if (null == ZookeeperUtils.getNodeValue(schema) || null == ZookeeperUtils.isExistsNode(schema)) {
				whatMasterObj = new JSONObject();
			} else {
				whatMasterObj = new JSONObject(ZookeeperUtils.getNodeValue(schema));
			}

			whatObj.put("master-chain-node", whatMasterObj);

		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}

		// 校验从链数据
		JSONObject whatSlaveObj = null;
		if (null == HbaseConnection.getInstance().getCellValueByRowKey(which, who, why, where)) {
			whatSlaveObj = new JSONObject();
			whatObj.put("slave-chain-node", whatSlaveObj);
		} else {
			whatSlaveObj = HbaseConnection.getInstance().getCellValueByRowKey(which, who, why, where);
			whatObj.put("slave-chain-node", whatSlaveObj);
		}

		output.put("who", who);
		output.put("which", which);
		output.put("where", where);
		output.put("what", whatObj);
		output.put("when", when);
		output.put("why", why);
		output.put("node-how-to-do", howToDo);
		output.put("node-how-much", howMuch);

		obj.put("input", input);
		obj.put("output", output);

		return obj.toString();
	}

	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@GET
	public String getBlockNode(@PathParam("schema") String schema, String data) {
		log.info("schema:{},data:{}", schema, data);
		JSONObject input = new JSONObject(data);
		JSONObject output = new JSONObject();
		JSONObject obj = new JSONObject();
		String who = input.getString("node-who");
		String which = input.getString("node-which");
		String where = input.getString("node-where");
		String when = input.getString("node-which");
		JSONObject what = input.getJSONObject("node-what");
		String why = input.getString("node-why");
		String howToDo = input.getString("node-how-to-do");
		String howMuch = input.getString("node-how-much");

		// 校验数据
		JSONObject whatObj = new JSONObject();
		// 校验主链数据
		JSONObject whatMasterObj = null;
		try {
			if (null == ZookeeperUtils.getNodeValue(schema) || null == ZookeeperUtils.isExistsNode(schema)) {
				whatMasterObj = new JSONObject();
			} else {
				whatMasterObj = new JSONObject(ZookeeperUtils.getNodeValue(schema));
			}
			whatObj.put("master-chain-node", whatMasterObj);

		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}

		// 校验从链数据
		JSONObject whatSlaveObj = null;
		if (null == HbaseConnection.getInstance().getCellValueByRowKey(which, who, why, where)) {
			whatSlaveObj = new JSONObject();
			whatObj.put("slave-chain-node", whatSlaveObj);
		} else {
			whatSlaveObj = HbaseConnection.getInstance().getCellValueByRowKey(which, who, why, where);
			whatObj.put("slave-chain-node", whatSlaveObj);
		}

		output.put("who", who);
		output.put("which", which);
		output.put("where", where);
		output.put("what", whatObj);
		output.put("when", when);
		output.put("why", why);
		output.put("node-how-to-do", howToDo);
		output.put("node-how-much", howMuch);

		obj.put("input", input);
		obj.put("output", output);

		return obj.toString();
	}
}
