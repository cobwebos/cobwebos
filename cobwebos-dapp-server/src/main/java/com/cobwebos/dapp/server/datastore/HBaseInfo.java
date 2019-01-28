package com.cobwebos.dapp.server.datastore;


public class HBaseInfo {

    private String rowKey;
    private String family;
    private String column;
    private String value;
    private long timestamp ;


    public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
