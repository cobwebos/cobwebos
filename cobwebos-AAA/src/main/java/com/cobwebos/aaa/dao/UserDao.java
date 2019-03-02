package com.cobwebos.aaa.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.aaa.common.DataNode;
import com.cobwebos.aaa.common.HBaseInfo;
import com.cobwebos.aaa.common.HbaseConnection;
import com.cobwebos.aaa.pojo.User;

public class UserDao {
	private Logger log = LoggerFactory.getLogger(UserDao.class);
	public List<User> userList = null;

	public User createUserByJSONObject(DataNode dataNode) {
		User user = new User(dataNode);
		HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(dataNode);
		log.info("create user:{}", dataNode.getWhat());
		System.out.println("create user:" + dataNode.getWhat());
		return user;
	}

	public void deleteUserByJSONObject(DataNode dataNode) {
		HbaseConnection.getInstance().deleteOneRowAll(dataNode.getWhere(), dataNode.getWho());
		log.info("delete user:", dataNode.getWho());
	}

	public void putUserByJSONObject(DataNode dataNode) {
		HbaseConnection.getInstance().insertAndUpdateOneRowOneColumnFamilyOneClumnValue(dataNode);
		log.info("update user:{}", dataNode.getWhat());
		System.out.println("update user:" + dataNode.getWhat());
	}

	public List<User> getAllUsersByJSONObject(DataNode dataNode) {
		JSONObject userObj = new JSONObject();
		userList = new ArrayList<User>();
		List<HBaseInfo> result = HbaseConnection.getInstance().getTableByTableName(dataNode);
		User user = null;
		for (HBaseInfo info : result) {
			JSONObject userValue = new JSONObject(info.getValue());
			user = new User(userValue);
			userList.add(user);
		}
		log.info("get All Users JSONObject:",dataNode.getDataNode().toString());
		System.out.println("get All Users JSONObject:" + dataNode.getDataNode().toString());
		return userList;
	}

	public User getUserByJSONObject(DataNode dataNode) {
		HbaseConnection.getInstance().getTableByRowKey(dataNode.getWhere(), dataNode.getWho());
		User user = new User(dataNode);
		log.info("get user:{}", dataNode.getWhat());
		System.out.println("get User By JSONObject:" + dataNode.getWhat());
		return user;
	}

	public static void main(String[] args) throws JSONException, NoSuchFieldException, SecurityException {

		String where = "user";
		String who = "111";
		String which = "info";
		String why = "node";
		String when = System.currentTimeMillis() + "";
		String howToDo = "merge";
		String howMany = "v1";
		String howMuch = "v1";

		JSONObject whatObj = new JSONObject();
		whatObj.put(User.class.getDeclaredField("uuid").getName(), who);
		whatObj.put(User.class.getDeclaredField("name").getName(), "cobwebos");
		whatObj.put(User.class.getDeclaredField("password").getName(), "cobwebos");
		whatObj.put(User.class.getDeclaredField("email").getName(), "cobwebos@163.com");
		whatObj.put("qq", "304844809");

		String what = whatObj.toString();

		JSONObject userObj = new JSONObject();
		userObj.put(User.class.getDeclaredField("where").getName(), where);
		userObj.put(User.class.getDeclaredField("who").getName(), who);
		userObj.put(User.class.getDeclaredField("which").getName(), which);
		userObj.put(User.class.getDeclaredField("why").getName(), why);
		userObj.put(User.class.getDeclaredField("what").getName(), what);
		userObj.put(User.class.getDeclaredField("when").getName(), when);
		userObj.put(User.class.getDeclaredField("howToDo").getName(), howToDo);
		userObj.put(User.class.getDeclaredField("howMuch").getName(), howMuch);
		userObj.put(User.class.getDeclaredField("howMany").getName(), howMany);

		DataNode dataNode = new DataNode();
		dataNode.setDataNode(userObj);
//		DataNode data = new DataNode(where, who, which, why, what, when, howToDo, howMuch, howMany);
		UserDao dao = new UserDao();
		dao.createUserByJSONObject(dataNode);
		dao.getUserByJSONObject(dataNode);
		dao.deleteUserByJSONObject(dataNode);
		dao.putUserByJSONObject(dataNode);
		dao.getUserByJSONObject(dataNode);
	}

}
