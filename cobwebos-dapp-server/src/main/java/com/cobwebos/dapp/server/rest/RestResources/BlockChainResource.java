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
		String who = input.getString("who");
		String which = input.getString("which");
		String where = input.getString("where");
		String when = input.getString("when");
		JSONObject what = input.getJSONObject("what");
		String why = input.getString("why");
		String howToDo = input.getString("howToDo");
		String howMuch = input.getString("howMuch");
		String howMany = input.getString("howMany");

		if (null == ZookeeperUtils.isExistsNode("/" + where + "/" + who)) {
			// 写入主链
			if (null == ZookeeperUtils.isExistsNode("/" + where)) {
				ZookeeperUtils.createNode("/" + where, where);
			}
			ZookeeperUtils.createNode("/" + where + "/" + who, what.toString());
			// 写入从链 同步方案
			if (!HbaseConnection.getInstance().tableExists(where)) {
				HbaseConnection.getInstance().createTable(where, which);
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(where, who, which, why,
						what.toString());
			} else {
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(where, who, which, why,
						what.toString());
			}

			// 写入从链 异步方案
//			if (!HbaseConnection.getInstance().tableExists(where)) {
//				HbaseConnection.getInstance().createTable(where, which);
//			}
//			MessageProducer.getInstance().SendMessage(DappServerCfg.getInstance().getKafkaTopic(), input.toString(),
//					what.toString());

		} else {
			// 写入主链
			ZookeeperUtils.setNodeValue("/" + where + "/" + who, what.toString());
			// 写入从链
			if (!HbaseConnection.getInstance().tableExists(where)) {
				HbaseConnection.getInstance().createTable(where, which);
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(where, who, which, why,
						what.toString());
			} else {
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(where, who, which, why,
						what.toString());
			}

			// 写入从链 异步方案
//			if (!HbaseConnection.getInstance().tableExists(where)) {
//				HbaseConnection.getInstance().createTable(where, which);
//			}
//			MessageProducer.getInstance().SendMessage(DappServerCfg.getInstance().getKafkaTopic(), input.toString(),
//					what.toString());

		}
		// 校验数据
		JSONObject whatObj = new JSONObject();
		// 校验主链数据
		JSONObject whatMasterObj = null;
		try {
			if (null == ZookeeperUtils.getNodeValue("/" + where + "/" + who) || null == ZookeeperUtils.isExistsNode("/" + where + "/" + who)) {
				whatMasterObj = new JSONObject();
			} else {
				whatMasterObj = new JSONObject(ZookeeperUtils.getNodeValue("/" + where + "/" + who));
			}
			whatObj.put("master-chain", whatMasterObj);
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}

		// 校验从链数据
		JSONObject whatSlaveObj = null;
		if (null == HbaseConnection.getInstance().getCellValueByRowKey(where, who, which, why)) {
			whatSlaveObj = new JSONObject();
			whatObj.put("slave-chain", whatSlaveObj);
		} else {
			whatSlaveObj = HbaseConnection.getInstance().getCellValueByRowKey(where, who, which, why);
			whatObj.put("slave-chain", whatSlaveObj);
		}

		output.put("who", who);
		output.put("which", which);
		output.put("where", where);
		output.put("what", whatObj);
		output.put("when", when);
		output.put("why", why);
		output.put("howToDo", howToDo);
		output.put("howMuch", howMuch);
		output.put("howMany", howMany);
		
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
		String who = input.getString("who");
		String which = input.getString("which");
		String where = input.getString("where");
		String when = input.getString("when");
		JSONObject what = input.getJSONObject("what");
		String why = input.getString("why");
		String howToDo = input.getString("howToDo");
		String howMuch = input.getString("howMuch");
		String howMany = input.getString("howMany");
		
		String result = null;
		if (schema != null && howToDo.equalsIgnoreCase("drop")) {
			result = ZookeeperUtils.rmrBlockNodeByPath("/" + where).toString();
			// 删除主链
//			ZookeeperUtils.rmrNode("/" + where + "/" + who);
			// 删除从链同步方案
			HbaseConnection.getInstance().deleteOneRowAll(where, who);
			// 删除从链异步方案
//			MessageProducer.getInstance().SendMessage(DappServerCfg.getInstance().getKafkaTopic(), input.toString(),
//					what.toString());
			
		} else if (schema != null && howToDo.equalsIgnoreCase("delete") && who != null) {
			result = ZookeeperUtils.rmrBlockNodeByPath("/" + where + "/" + who).toString();
			// 删除主链
//			ZookeeperUtils.rmrNode("/" + where + "/" + who);
			// 删除从链同步方案
			HbaseConnection.getInstance().deleteOneRowAll(where, who);
			// 删除从链异步方案
//			MessageProducer.getInstance().SendMessage(DappServerCfg.getInstance().getKafkaTopic(), input.toString(),
//					what.toString());
		}
		log.info("delete schema:{},result:{}", schema, result);		

		output.put("who", "");
		output.put("which", which);
		output.put("where", where);
		output.put("what", "{}");
		output.put("when", when);
		output.put("why", why);
		output.put("howToDo", howToDo);
		output.put("howMuch", howMuch);
		output.put("howMany", howMany);
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
		String who = input.getString("who");
		String which = input.getString("which");
		String where = input.getString("where");
		String when = input.getString("when");
		JSONObject what = input.getJSONObject("what");
		String why = input.getString("why");
		String howToDo = input.getString("howToDo");
		String howMuch = input.getString("howMuch");
		String howMany = input.getString("howMany");
		
		if (ZookeeperUtils.isExistsNode("/" + where + "/" + who) != null) {
			// 写入主链
			ZookeeperUtils.setNodeValue("/" + where + "/" + who, what.toString());
			// 写入从链同步方案
			if (!HbaseConnection.getInstance().tableExists(where)) {
				HbaseConnection.getInstance().createTable(where, which);
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(where, who, which, why,
						what.toString());
			} else {
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(where, who, which, why,
						what.toString());
			}

			// 写入从链 异步方案
//			if (!HbaseConnection.getInstance().tableExists(where)) {
//				HbaseConnection.getInstance().createTable(where, which);
//			}
//			MessageProducer.getInstance().SendMessage(DappServerCfg.getInstance().getKafkaTopic(), input.toString(),
//					what.toString());

		} else {
			// 写入主链
			if (null == ZookeeperUtils.isExistsNode("/" + where)) {
				ZookeeperUtils.createNode("/" + where, where);
			}
			ZookeeperUtils.createNode("/" + where + "/" + who, what.toString());
			// 写入从链同步方案
			if (!HbaseConnection.getInstance().tableExists(where)) {
				HbaseConnection.getInstance().createTable(where, which);
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(where, who, which, why,
						what.toString());
			} else {
				HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(where, who, which, why,
						what.toString());
			}

			// 写入从链 异步方案
//			if (!HbaseConnection.getInstance().tableExists(where)) {
//				HbaseConnection.getInstance().createTable(where, which);
//			}
//			MessageProducer.getInstance().SendMessage(DappServerCfg.getInstance().getKafkaTopic(), input.toString(),
//					what.toString());

		}

		// 校验数据
		JSONObject whatObj = new JSONObject();
		// 校验主链数据
		JSONObject whatMasterObj = null;
		try {

			if (null == ZookeeperUtils.getNodeValue("/" + where + "/" + who) || null == ZookeeperUtils.isExistsNode("/" + where + "/" + who)) {
				whatMasterObj = new JSONObject();
			} else {
				whatMasterObj = new JSONObject(ZookeeperUtils.getNodeValue("/" + where + "/" + who));
			}

			whatObj.put("master-chain", whatMasterObj);

		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}

		// 校验从链数据
		JSONObject whatSlaveObj = null;
		if (null == HbaseConnection.getInstance().getCellValueByRowKey(where, who, which, why)) {
			whatSlaveObj = new JSONObject();
			whatObj.put("slave-chain", whatSlaveObj);
		} else {
			whatSlaveObj = HbaseConnection.getInstance().getCellValueByRowKey(where, who, which, why);
			whatObj.put("slave-chain", whatSlaveObj);
		}

		output.put("who", who);
		output.put("which", which);
		output.put("where", where);
		output.put("what", whatObj);
		output.put("when", when);
		output.put("why", why);
		output.put("howToDo", howToDo);
		output.put("howMuch", howMuch);
		output.put("howMany", howMany);
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
		String who = input.getString("who");
		String which = input.getString("which");
		String where = input.getString("where");
		String when = input.getString("when");
		JSONObject what = input.getJSONObject("what");
		String why = input.getString("why");
		String howToDo = input.getString("howToDo");
		String howMuch = input.getString("howMuch");
		String howMany = input.getString("howMany");
		// 校验数据
		JSONObject whatObj = new JSONObject();
		// 校验主链数据
		JSONObject whatMasterObj = null;
		try {
			if (null == ZookeeperUtils.getNodeValue("/" + where + "/" + who) || null == ZookeeperUtils.isExistsNode("/" + where + "/" + who)) {
				whatMasterObj = new JSONObject();
			} else {
				whatMasterObj = new JSONObject(ZookeeperUtils.getNodeValue("/" + where + "/" + who));
			}
			whatObj.put("master-chain", whatMasterObj);

		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (KeeperException e) {
			log.error(e.getMessage(), e);
		}

		// 校验从链数据
		JSONObject whatSlaveObj = null;
		if (null == HbaseConnection.getInstance().getCellValueByRowKey(where, who, which, why)) {
			whatSlaveObj = new JSONObject();
			whatObj.put("slave-chain", whatSlaveObj);
		} else {
			whatSlaveObj = HbaseConnection.getInstance().getCellValueByRowKey(where, who, which, why);
			whatObj.put("slave-chain", whatSlaveObj);
		}

		output.put("who", who);
		output.put("which", which);
		output.put("where", where);
		output.put("what", whatObj);
		output.put("when", when);
		output.put("why", why);
		output.put("howToDo", howToDo);
		output.put("howMuch", howMuch);
		output.put("howMany", howMany);
		
		obj.put("input", input);
		obj.put("output", output);

		return obj.toString();
	}
}
