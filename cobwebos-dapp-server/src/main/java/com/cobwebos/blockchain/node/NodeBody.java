package com.cobwebos.blockchain.node;

import org.json.JSONObject;

public class NodeBody {
	private JSONObject obj;
	private NodeWho who;
	private NodeWhich which;
	private NodeWhere where;
	private NodeWhen when;
	private NodeWhat what;
	private NodeWhy why;
	private NodeHowToDo howToDo;
	private NodeHowMuch howMuch;

	public NodeBody(JSONObject obj, NodeWho who, NodeWhich which, NodeWhere where, NodeWhen when, NodeWhat what,
			NodeWhy why, NodeHowToDo howToDo, NodeHowMuch howMuch) {
		this.obj = obj;
		this.who = who;
		this.which = which;
		this.where = where;
		this.when = when;
		this.what = what;
		this.why =  why;
		this.howToDo = howToDo;
		this.howMuch = howMuch;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public NodeWho getWho() {
		return who;
	}

	public void setWho(NodeWho who) {
		this.who = who;
	}

	public NodeWhich getWhich() {
		return which;
	}

	public void setWhich(NodeWhich which) {
		this.which = which;
	}

	public NodeWhere getWhere() {
		return where;
	}

	public void setWhere(NodeWhere where) {
		this.where = where;
	}

	public NodeWhen getWhen() {
		return when;
	}

	public void setWhen(NodeWhen when) {
		this.when = when;
	}

	public NodeWhat getWhat() {
		return what;
	}

	public void setWhat(NodeWhat what) {
		this.what = what;
	}

	public NodeWhy getWhy() {
		return why;
	}

	public void setWhy(NodeWhy why) {
		this.why = why;
	}

	public NodeHowToDo getHowToDo() {
		return howToDo;
	}

	public void setHowToDo(NodeHowToDo howToDo) {
		this.howToDo = howToDo;
	}

	public NodeHowMuch getHowMuch() {
		return howMuch;
	}

	public void setHowMuch(NodeHowMuch howMuch) {
		this.howMuch = howMuch;
	}

}
