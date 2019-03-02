package com.cobwebos.aaa.pojo;

import java.io.Serializable;

import org.json.JSONObject;

public class Gruop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String name;
	private String desc;
	private String parentId;
	private String parentName;
	private JSONObject groupObj;

	public Gruop() {
	}

	public Gruop(JSONObject groupObj) {
		this.groupObj = groupObj;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public JSONObject getGroupObj() {
		return groupObj;
	}

	public void setGroupObj(JSONObject groupObj) {
		this.groupObj = groupObj;
	}

}
