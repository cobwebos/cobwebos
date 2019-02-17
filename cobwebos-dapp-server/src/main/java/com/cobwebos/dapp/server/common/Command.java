package com.cobwebos.dapp.server.common;

public enum Command {
	CREATE("create", 1), DELETE("delete", 2), UPDATE("update", 3), GET("get", 4), LS("ls", 5), REMOVE("remove", 6),
	RMR("rmr", 7), SETACL("setAcl", 8), GETACL("getAcl", 9);

	private String command;
	private int index;

	private Command(String command, int index) {
		this.command = command;
		this.index = index;
	}

	public static String getCommand(int index) {
		for (Command op : Command.values()) {
			if (op.getIndex() == index) {
				return op.command;
			}
		}
		return null;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
