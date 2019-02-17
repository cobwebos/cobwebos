package com.cobwebos.dapp.server.common;

public enum Operation {
	POST("post", 1), DELETE("delete", 2),PUT("put", 3), GET("get", 4),PATCH("patch", 5), MERGE("merge", 6),REMOVE("remove",7),
	HEAD("head", 8), OPTIONS("options", 9);

	private String operation;
	private int index;

	private Operation(String operation, int index) {
		this.operation = operation;
		this.index = index;
	}

	public static String getOperation(int index) {
		for (Operation op : Operation.values()) {
			if (op.getIndex() == index) {
				return op.operation;
			}
		}
		return null;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
