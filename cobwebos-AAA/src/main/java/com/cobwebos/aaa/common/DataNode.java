package com.cobwebos.aaa.common;

import java.io.Serializable;

import org.json.JSONObject;

public class DataNode implements Serializable {

	private static final long serialVersionUID = 1L;
//	What--指要做的是什么及描述达成命令事项后的状态 value
//	When--指全部工作完成的时间及各步骤完成的时间 timestamp
//	Where--泛指各项活动发生的场所 table
//	Who--指与命令有关联的对象 rowkey
//	Why--指理由、目的、根据 column  errorCode、errorDesc
//	Which--根据前面5个W，做出各种备选方案 column famliy
//	How To Do--指方法、手段，也就是如何做 CRUDAN 
//	How Many --指需要多大、多少，以计量的方式让事情更具体化 version
//	How Much--指预算、费用	
	private String who;
	private String which;
	private String where;
	private String what;
	private String when;
	private String why;
	private String howToDo;
	private String howMany;
	private String howMuch;
	private JSONObject dataNode;

	public DataNode() {

	}

	public DataNode(String where, String who, String which, String why, String what, String when, String howToDo,
			String howMany, String howMuch) {
		this.where = where;
		this.who = who;
		this.which = which;
		this.why = why;
		this.what = what;
		this.when = when;
		this.howToDo = howToDo;
		this.howMuch = howMuch;
		this.howMany = howMany;
	}

	public DataNode(JSONObject dataNode) {
		this.dataNode = dataNode;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getWhich() {
		return which;
	}

	public void setWhich(String which) {
		this.which = which;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public String getHowToDo() {
		return howToDo;
	}

	public void setHowToDo(String howToDo) {
		this.howToDo = howToDo;
	}

	public String getHowMany() {
		return howMany;
	}

	public void setHowMany(String howMany) {
		this.howMany = howMany;
	}

	public String getHowMuch() {
		return howMuch;
	}

	public void setHowMuch(String howMuch) {
		this.howMuch = howMuch;
	}

	public JSONObject getDataNode() {
		return dataNode;
	}

	public void setDataNode(JSONObject dataNode) {
		this.dataNode = dataNode;
	}

}
