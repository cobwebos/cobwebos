package com.cobwebos.blockchain.node.entry;

public class NodeBean {

	private String schema;
	private String columnFamily;
	private String column;
	private String key;
	private String value;
	private String path;
	private String url;
	private String number;

	public NodeBean() {
	}
	
	public NodeBean(String schema, String key, String value) {
		this.schema = schema;
		this.key = key;
		this.value = value;
		
	}

	public NodeBean(String schema, String key, String value, String number) {
		this.schema = schema;
		this.key = key;
		this.value = value;
		this.number = number;
	}
	
	public NodeBean(String schema, String key, String columnFamily, String column, String value) {
		this.schema = schema;
		this.key = key;
		this.columnFamily = columnFamily;
		this.column = column;
		this.value = value;
		
	}

	public NodeBean(String schema, String key, String columnFamily, String column, String value, String path, String url,
			String number) {
		this.schema = schema;
		this.key = key;
		this.columnFamily = columnFamily;
		this.column = column;
		this.value = value;
		this.path = path;
		this.url = url;
		this.number = number;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getColumnFamily() {
		return columnFamily;
	}

	public void setColumnFamily(String columnFamily) {
		this.columnFamily = columnFamily;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
