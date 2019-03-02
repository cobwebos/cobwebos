package com.cobwebos.aaa.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cobwebos.aaa.common.HBaseInfo;
import com.cobwebos.aaa.common.AAACfg;

public class HbaseConnection {
	private static final HbaseConnection hbase = new HbaseConnection();
	private static final Logger log = LoggerFactory.getLogger(HbaseConnection.class.getName());
	private static Configuration conf = null;
	private static Connection conn = null;

	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", AAACfg.getInstance().getHbaseServerIP());
		conf.set("hbase.zookeeper.property.clientPort", AAACfg.getInstance().getHbaseClientPort() + "");
	}

	private HbaseConnection() {

	}

	public synchronized static HbaseConnection getInstance() {
		return hbase;
	}

	private Connection getHbaseConn() {
		try {
			if (conn == null) {
				conn = ConnectionFactory.createConnection(conf);
			}
		} catch (IOException e) {
			log.error("hbase connection init faild conf:{}", conf, e);

		}
		return conn;

	}

	public void createTable(String tableName, String[] families) {
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			if (admin.tableExists(TableName.valueOf(tableName))) {
				log.info("Table existence:" + tableName);
			} else {
				TableDescriptorBuilder desc = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));

				for (String family : families) {
					ColumnFamilyDescriptorBuilder inv = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(family));
					desc.setColumnFamily(inv.build());
					log.info("tableName:{},family:{}", tableName, family);
				}
				admin.createTable(desc.build());
				log.info("Table create success:" + tableName);

			}
		} catch (IOException e) {
			log.error("create table faild tableName：{},families:{}", tableName, families, e);

		}

		// destroy(conn);
	}

	public void createTable(String tableName, String family) {
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			if (admin.tableExists(TableName.valueOf(tableName))) {
				log.info("Table existence:" + tableName);
			} else {
				TableDescriptorBuilder desc = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));

				ColumnFamilyDescriptorBuilder inv = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(family));
				desc.setColumnFamily(inv.build());
				log.info("tableName:{},family:{}", tableName, family);

				admin.createTable(desc.build());
				log.info("Table create success:" + tableName);

			}
		} catch (IOException e) {
			log.error("create table faild tableName：{},family:{}", tableName, family, e);

		}

		// destroy(conn);
	}

	public void addColumnFamily(String tableName, String family) {
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			TableDescriptorBuilder desc = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
			ColumnFamilyDescriptorBuilder columnFamily = ColumnFamilyDescriptorBuilder
					.newBuilder(Bytes.toBytes(family));
			desc.setColumnFamily(columnFamily.build());
			admin.addColumnFamily(TableName.valueOf(tableName), columnFamily.build());
			log.info("tableName: {} addColumnFamily:{} ", tableName, family);
		} catch (IOException e) {
			log.error("add faild, tableName:{},family:{}", tableName, family, e);
		}
		// destroy(conn);
	}

	public List<HBaseInfo> getTableByTableName(String tableName) {
		Connection conn = getHbaseConn();
		List<HBaseInfo> hBaseInfoList = new ArrayList<>();
		Scan scan = new Scan();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			ResultScanner scann = hTable.getScanner(scan);
			for (Result rs : scann) {
				String rowKey = new String(rs.getRow());
				for (Cell cell : rs.rawCells()) {
					HBaseInfo hBaseInfo = new HBaseInfo();
					String family = new String(CellUtil.cloneFamily(cell));
					String column = new String(CellUtil.cloneQualifier(cell));
					String value = new String(CellUtil.cloneValue(cell));
					long timestamp = cell.getTimestamp();
					log.info("tableName:{},rowKey:{},family:{},column:{},value:{} ,timestamp:{}", tableName, rowKey,
							family, column, value, timestamp);
					hBaseInfo.setRowKey(rowKey);
					hBaseInfo.setFamily(family);
					hBaseInfo.setColumn(column);
					hBaseInfo.setValue(value);
					hBaseInfo.setTimestamp(timestamp);
					hBaseInfoList.add(hBaseInfo);
				}
			}
			return hBaseInfoList;
		} catch (IOException e) {
			log.error("query faild, tableName:{}", tableName, e);
		} finally {
			// destroy(conn);
		}
		return hBaseInfoList;
	}

	public List<HBaseInfo> getTableByTableName(DataNode dataNode) {
		Connection conn = getHbaseConn();
		List<HBaseInfo> hBaseInfoList = new ArrayList<>();
		Scan scan = new Scan();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(dataNode.getWhere()));
			ResultScanner scann = hTable.getScanner(scan);
			for (Result rs : scann) {
				String rowKey = new String(rs.getRow());
				for (Cell cell : rs.rawCells()) {
					HBaseInfo hBaseInfo = new HBaseInfo();
					String family = new String(CellUtil.cloneFamily(cell));
					String column = new String(CellUtil.cloneQualifier(cell));
					String value = new String(CellUtil.cloneValue(cell));
					long timestamp = cell.getTimestamp();
					log.info("tableName:{},rowKey:{},family:{},column:{},value:{} ,timestamp:{}", dataNode.getWhere(), rowKey,
							family, column, value, timestamp);
					hBaseInfo.setRowKey(rowKey);
					hBaseInfo.setFamily(family);
					hBaseInfo.setColumn(column);
					hBaseInfo.setValue(value);
					hBaseInfo.setTimestamp(timestamp);
					hBaseInfoList.add(hBaseInfo);
				}
			}
			return hBaseInfoList;
		} catch (IOException e) {
			log.error("query faild, tableName:{}", dataNode.getWhere(), e);
		} finally {
			// destroy(conn);
		}
		return hBaseInfoList;
	}

	public List<HBaseInfo> scanTable(String tableName) {
		Connection conn = getHbaseConn();
		List<HBaseInfo> hBaseInfoList = new ArrayList<>();
		Scan scan = new Scan();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			ResultScanner scann = hTable.getScanner(scan);
			for (Result rs : scann) {
				String rowKey = new String(rs.getRow());

				for (Cell cell : rs.rawCells()) {
					HBaseInfo hBaseInfo = new HBaseInfo();
					String family = new String(CellUtil.cloneFamily(cell));
					String column = new String(CellUtil.cloneQualifier(cell));
					String value = new String(CellUtil.cloneValue(cell));
					long timestamp = cell.getTimestamp();
					log.info("tableName:{},rowKey:{},family:{},column:{},value:{} ,timestamp:{}", tableName, rowKey,
							family, column, value, timestamp);
					hBaseInfo.setRowKey(rowKey);
					hBaseInfo.setFamily(family);
					hBaseInfo.setColumn(column);
					hBaseInfo.setValue(value);
					hBaseInfo.setTimestamp(timestamp);
					hBaseInfoList.add(hBaseInfo);
				}
			}
			return hBaseInfoList;
		} catch (IOException e) {
			log.error("query faild, tableName:{}", tableName, e);
		} finally {
			// destroy(conn);
		}
		return hBaseInfoList;
	}

	public List<HBaseInfo> scanTable(DataNode dataNode) {
		Connection conn = getHbaseConn();
		List<HBaseInfo> hBaseInfoList = new ArrayList<>();
		Scan scan = new Scan();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(dataNode.getWhere()));
			ResultScanner scann = hTable.getScanner(scan);
			for (Result rs : scann) {
				String rowKey = new String(rs.getRow());

				for (Cell cell : rs.rawCells()) {
					HBaseInfo hBaseInfo = new HBaseInfo();
					String family = new String(CellUtil.cloneFamily(cell));
					String column = new String(CellUtil.cloneQualifier(cell));
					String value = new String(CellUtil.cloneValue(cell));
					long timestamp = cell.getTimestamp();
					log.info("tableName:{},rowKey:{},family:{},column:{},value:{} ,timestamp:{}", dataNode.getWhere(), rowKey,
							family, column, value, timestamp);
					hBaseInfo.setRowKey(rowKey);
					hBaseInfo.setFamily(family);
					hBaseInfo.setColumn(column);
					hBaseInfo.setValue(value);
					hBaseInfo.setTimestamp(timestamp);
					hBaseInfoList.add(hBaseInfo);
				}
			}
			return hBaseInfoList;
		} catch (IOException e) {
			log.error("query faild, tableName:{}", dataNode.getWhere(), e);
		} finally {
			// destroy(conn);
		}
		return hBaseInfoList;
	}

	public List<HBaseInfo> scanTableByColumnFamily(String tableName, String family) {
		Connection conn = getHbaseConn();
		List<HBaseInfo> hBaseInfoList = new ArrayList<>();
		Scan scan = new Scan();
		scan.addFamily(family.getBytes());
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			ResultScanner scann = hTable.getScanner(scan);
			for (Result rs : scann) {
				String rowKey = new String(rs.getRow());

				for (Cell cell : rs.rawCells()) {
					HBaseInfo hBaseInfo = new HBaseInfo();
					String column = new String(CellUtil.cloneQualifier(cell));
					String value = new String(CellUtil.cloneValue(cell));
					long timestamp = cell.getTimestamp();
					log.info("tableName:{},rowKey:{},family:{},column:{},value:{} ,timestamp:{}", tableName, rowKey,
							family, column, value, timestamp);
					hBaseInfo.setRowKey(rowKey);
					hBaseInfo.setFamily(family);
					hBaseInfo.setColumn(column);
					hBaseInfo.setValue(value);
					hBaseInfo.setTimestamp(timestamp);
					hBaseInfoList.add(hBaseInfo);
				}
			}
			return hBaseInfoList;
		} catch (IOException e) {
			log.error("get tableName:{},family:{}", tableName, family, e);
		} finally {
			// destroy(conn);
		}
		return hBaseInfoList;
	}

	public List<HBaseInfo> scanTableByColumnFamilyMoreColumns(String tableName, String family, String[] columns) {
		Connection conn = getHbaseConn();
		List<HBaseInfo> hBaseInfoList = new ArrayList<>();
		Scan scan = new Scan();
		scan.addFamily(family.getBytes());
		for (String column : columns) {
			scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
		}

		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));

			ResultScanner scann = hTable.getScanner(scan);
			for (Result rs : scann) {
				String rowKey = new String(rs.getRow());

				for (Cell cell : rs.rawCells()) {
					HBaseInfo hBaseInfo = new HBaseInfo();
					String column = new String(CellUtil.cloneQualifier(cell));
					String value = new String(CellUtil.cloneValue(cell));
					long timestamp = cell.getTimestamp();
					log.info("tableName:{},rowKey:{},family:{},column:{},value:{} ,timestamp:{}", tableName, rowKey,
							family, column, value, timestamp);
					hBaseInfo.setRowKey(rowKey);
					hBaseInfo.setFamily(family);
					hBaseInfo.setColumn(column);
					hBaseInfo.setValue(value);
					hBaseInfo.setTimestamp(timestamp);
					hBaseInfoList.add(hBaseInfo);
				}
			}
			return hBaseInfoList;
		} catch (IOException e) {
			log.error("get tableName:{},,family:{}", tableName, family, e);
		} finally {
			// destroy(conn);
		}
		return hBaseInfoList;
	}

	public List<HBaseInfo> getTableByRowKey(String tableName, String rowKey) {
		Connection conn = getHbaseConn();
		List<HBaseInfo> hBaseInfoList = new ArrayList<>();
		try {

			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			Result rs = hTable.get(new Get(Bytes.toBytes(rowKey)));
			for (Cell cell : rs.rawCells()) {
				HBaseInfo hBaseInfo = new HBaseInfo();
				String family = new String(CellUtil.cloneFamily(cell));
				String column = new String(CellUtil.cloneQualifier(cell));
				String value = new String(CellUtil.cloneValue(cell));
				long timestamp = cell.getTimestamp();
				log.info("tableName:{},rowKey:{},family:{},column:{},value:{} ,timestamp:{}", tableName, rowKey, family,
						column, value, timestamp);
				hBaseInfo.setRowKey(rowKey);
				hBaseInfo.setFamily(family);
				hBaseInfo.setColumn(column);
				hBaseInfo.setValue(value);
				hBaseInfo.setTimestamp(timestamp);
				hBaseInfoList.add(hBaseInfo);
			}
			return hBaseInfoList;
		} catch (IOException e) {
			log.error("get tableName:{},rowKey:{}", tableName, rowKey, e);
		} finally {
			// destroy(conn);
		}
		return hBaseInfoList;
	}

	public String getTableByRowKeyAndColumnFamilyAndColumn(String tableName, String rowKey, String family,
			String column) {
		Connection conn = getHbaseConn();
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Get get = new Get(rowKey.getBytes());
			if (!get.isCheckExistenceOnly()) {
				get.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
				Result res = table.get(get);
				byte[] resByte = res.getValue(Bytes.toBytes(family), Bytes.toBytes(column));
				log.info("tableName:{},family:{},column:{},value:{} ", tableName, family, column, new String(resByte));
				return new String(resByte);
			}
		} catch (IOException e) {
			log.error("get tableName:{},rowKey:{}", tableName, rowKey, e);
		} finally {
			// destroy(conn);
		}
		return null;
	}

	public JSONObject getCellValueByRowKey(String tableName, String rowKey, String family, String column) {
		JSONObject cell = null;
		Connection conn = getHbaseConn();
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Get get = new Get(rowKey.getBytes());
			if (!get.isCheckExistenceOnly()) {
				get.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
				Result res = table.get(get);
				byte[] resByte = res.getValue(Bytes.toBytes(family), Bytes.toBytes(column));
				if (resByte != null) {
					String value = new String(resByte);
					cell = new JSONObject(value);
					log.info("tableName:{},family:{},column:{},value:{} ", tableName, family, column, cell.toString());

				} else {
					log.warn("tableName:{},family:{},column:{},value:{} ", tableName, family, column, cell);

				}

				return cell;
			}
		} catch (IOException e) {
			log.error("get tableName:{},rowKey:{}", tableName, rowKey, e);
		} finally {
			// destroy(conn);
		}
		return null;
	}

	public JSONObject getCellValueByRowKey(DataNode dataNode) {
		JSONObject cell = null;
		Connection conn = getHbaseConn();
		try {
			Table table = conn.getTable(TableName.valueOf(dataNode.getWhere()));
			Get get = new Get(dataNode.getWho().getBytes());
			if (!get.isCheckExistenceOnly()) {
				get.addColumn(Bytes.toBytes(dataNode.getWhich()), Bytes.toBytes(dataNode.getWhy()));
				Result res = table.get(get);
				byte[] resByte = res.getValue(Bytes.toBytes(dataNode.getWhich()), Bytes.toBytes(dataNode.getWhy()));
				if (resByte != null) {
					String value = new String(resByte);
					cell = new JSONObject(value);
					log.info("tableName:{},family:{},column:{},value:{} ", dataNode.getWhere(), dataNode.getWhich(),
							dataNode.getWhy(), cell.toString());

				} else {
					log.warn("tableName:{},family:{},column:{},value:{} ", dataNode.getWhere(), dataNode.getWhich(),
							dataNode.getWhy(), cell);

				}

				return cell;
			}
		} catch (IOException e) {
			log.error("get tableName:{},rowKey:{}", dataNode.getWhere(), dataNode.getWho(), e);
		} finally {
			// destroy(conn);
		}
		return null;
	}

	public void insertAndUpdateOneRowOneColumnFamilyOneClumnValue(String tableName, String rowKey, String family,
			String column, String value) {
		Connection conn = getHbaseConn();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			Put p = new Put(Bytes.toBytes(rowKey));
			p.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));
			if (!p.isEmpty()) {
				hTable.put(p);
			}
			log.info("tableName:{},rowKey:{},family:{},column:{},value: {}", tableName, rowKey, family, column, value);
		} catch (IOException e) {
			log.error("insert table{},rowKey:{}", tableName, rowKey, e);
		}
		// destroy(conn);
	}

	public void insertAndUpdateOneRowOneColumnFamilyOneClumnValue(DataNode dataNode) {
		Connection conn = getHbaseConn();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(dataNode.getWhich()));
			Put p = new Put(Bytes.toBytes(dataNode.getWho()));
			p.addColumn(Bytes.toBytes(dataNode.getWhere()), Bytes.toBytes(dataNode.getWhy()),
					Bytes.toBytes(dataNode.getWhat()));
			if (!p.isEmpty()) {
				hTable.put(p);
			}
			log.info("tableName:{},rowKey:{},family:{},column:{},value: {}", dataNode.getWhere(), dataNode.getWho(),
					dataNode.getWhich(), dataNode.getWhy(), dataNode.getDataNode());
		} catch (IOException e) {
			log.error("insert table{},rowKey:{}", dataNode.getWhere(), dataNode.getWho(), e);
		}
		// destroy(conn);
	}

	public void insertAndUpdateOneRowOneColumnFamilyMoreClumnValue(String tableName, String rowKey, String family,
			HashMap<String, String> columnValue) {
		Connection conn = getHbaseConn();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			Put p = new Put(Bytes.toBytes(rowKey));
			for (String column : columnValue.keySet()) {
				String value = columnValue.get(column);
				if (StringUtils.isNotBlank(value)) {
					p.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));
				}
				log.info("tableName:{},rowKey:{},family:{},column:{},columnValue: {}", tableName, rowKey, family,
						columnValue);
			}

			if (!p.isEmpty()) {
				hTable.put(p);
			}
			log.info("tableName:{},rowKey:{},family:{},column:{},columnValue: {}", tableName, rowKey, family,
					columnValue);
		} catch (IOException e) {
			log.error("insert table{},rowKey:{}", tableName, rowKey, e);
		}
		// destroy(conn);
	}

	public void insertAndUpdateMoreRowMoreColumnFamilyMoreClumnValue(String tableName, Put put) {
		Connection conn = getHbaseConn();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			if (!put.isEmpty()) {
				hTable.put(put);
			}
			log.info("tableName:{},put:{}", tableName, put);
		} catch (IOException e) {
			log.error("insert tableName:{}", tableName + ",put:" + put, e);
		}
		// destroy(conn);
	}

	public void deleteColumnFamily(String tableName, String family) {
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			admin.deleteColumnFamily(TableName.valueOf(tableName), Bytes.toBytes(family));
			log.info("delete tableName:{},family:{}", tableName, family);
		} catch (IOException e) {
			log.error("delete tableName:{},family:{}", tableName, family, e);
		}
		// destroy(conn);
	}

	public void deleteOneRowAll(String tableName, String rowKey) {
		Connection conn = getHbaseConn();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			hTable.delete(new Delete(Bytes.toBytes(rowKey)));
			log.info("delete tableName:{},Rowkey:{}", tableName, rowKey);
		} catch (IOException e) {
			log.error("delete tableName:{},rowkey:{}", tableName, rowKey, e);
		}
		// destroy(conn);
	}

	public void deleteOneRowOneColumnFamilyOneCloumn(String tableName, String rowKey, String family, String column) {
		Connection conn = getHbaseConn();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			Delete delete = new Delete(Bytes.toBytes(rowKey));
			delete.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
			hTable.delete(delete);
			log.info("delete tableName:{},Row:{},family:{},Cloumn:{}", tableName, rowKey, family, column);
		} catch (IOException e) {
			log.error("delete tableName:{},Row:{},family:{},Cloumn:{}", tableName, rowKey, family, column, e);
		}
		// destroy(conn);
	}

	public void deleteOneRowOneColumnFamilyMoreCloumn(String tableName, String rowKey, String family,
			String[] columns) {
		Connection conn = getHbaseConn();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			Delete delete = new Delete(Bytes.toBytes(rowKey));
			for (String column : columns) {
				delete.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
			}

			hTable.delete(delete);
			log.info("delete tableName:{},Row:{},family:{},Cloumn:{}", tableName, rowKey, family, columns);
		} catch (IOException e) {
			log.error("delete tableName:{},Row:{},family:{},Cloumn:{}", tableName, rowKey, family, columns, e);
		}
		// destroy(conn);
	}

	public void deleteOneRowMoreColumnFamilyMoreCloumn(String tableName, Delete delete) {
		Connection conn = getHbaseConn();
		try {
			HTable hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			hTable.delete(delete);
			log.info("delete tableName:{},delete:{}", tableName, delete);
		} catch (IOException e) {
			log.error("delete tableName:{},delete", tableName, delete, e);
		}
		// destroy(conn);
	}

	public void deleteTable(String tableName) {
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();

			admin.disableTable(TableName.valueOf(tableName));
			admin.deleteTable(TableName.valueOf(tableName));
			log.info("delete tableName:{}", tableName);
		} catch (IOException e) {
			log.error("delete tableName:{}", tableName, e);
		}
		// destroy(conn);
	}

	public void truncateTable(String tableName) {
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			admin.truncateTable(TableName.valueOf(tableName), true);
			log.info("truncate Table:{}", tableName);
		} catch (IOException e) {
			log.error("truncate tableName:{}", tableName, e);
		}
		// destroy(conn);
	}

	public void enableTable(String tableName) {
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			boolean isEnable = admin.isTableEnabled(TableName.valueOf(tableName));
			if (!isEnable) {
				admin.enableTable(TableName.valueOf(tableName));
			}
			log.info("enabled tableName :{}", tableName);
		} catch (IOException e) {
			log.error("enable tableName:{}", tableName, e);
		}
		// destroy(conn);
	}

	public void disableTable(String tableName) {
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			boolean isDisable = admin.isTableDisabled(TableName.valueOf(tableName));
			if (!isDisable) {
				admin.disableTable(TableName.valueOf(tableName));
				log.info("disable table:{}", tableName);
			}
			log.info("disabled table:{}", tableName);

		} catch (IOException e) {
			log.error("disable Table:{}", tableName, e);
		}
		// destroy(conn);
	}

	public boolean tableExists(String tableName) {
		boolean tableExists = false;
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			tableExists = admin.tableExists(TableName.valueOf(tableName));
			if (!tableExists) {
				log.info("table:{},tableExists:{}", tableName, tableExists);
			}
			log.warn("table not exists!");

		} catch (IOException e) {
			log.error("table Exists:{}", tableName, e);
		}
		// destroy(conn);
		return tableExists;
	}

	public List<TableDescriptor> listTableDescriptor() {
		List<TableDescriptor> list = null;
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			list = admin.listTableDescriptors();
			for (int i = 0; i < list.size(); i++) {
				log.info("TableDescriptor:{}", list.get(i));
			}
		} catch (IOException e) {
			log.error("list TableDescriptor error", e);
		}
		// destroy(conn);
		return list;
	}

	public TableDescriptor getDescriptor(String tableName) {
		TableDescriptor desc = null;
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			desc = admin.getDescriptor(TableName.valueOf(tableName));
			log.info("tableName:{},TableDescriptor:{}", tableName, desc);
		} catch (IOException e) {
			log.error("list TableDescriptor error", e);
		}
		// destroy(conn);
		return desc;
	}

	public TableName[] listTableNames() {
		TableName[] list = null;
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			list = admin.listTableNames();
			for (int i = 0; i < list.length; i++) {
				log.info("list TableName:{}", list[i]);
			}
		} catch (IOException e) {
			log.error("list TableName error", e);
		}
		// destroy(conn);
		return list;
	}

	public TableName[] listTableNames(String nameSpace) {
		TableName[] list = null;
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			list = admin.listTableNamesByNamespace(nameSpace);
			for (int i = 0; i < list.length; i++) {
				log.info("list nameSpace:{},TableName:{}", nameSpace, list[i]);
			}
		} catch (IOException e) {
			log.error("list TableName error", e);
		}
		// destroy(conn);
		return list;
	}

	public boolean isTableAvailable(String tableName) {
		boolean isTableAvailable = false;
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			isTableAvailable = admin.isTableAvailable(TableName.valueOf(tableName));
			if (!isTableAvailable) {
				log.info("table:{},isTableAvailable:{}", tableName, isTableAvailable);
			}
			log.info("table:{},isTableAvailable:{}", tableName, isTableAvailable);
		} catch (IOException e) {
			log.error("Table Available:{}", tableName, e);
		}
		// destroy(conn);
		return isTableAvailable;
	}

	public void destroy(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (IOException e) {
				log.error("destroy hbase connection faild");
			}
		}

	}

	public void shutdownHbase() {
		Connection conn = getHbaseConn();
		try {
			Admin admin = conn.getAdmin();
			admin.shutdown();
			log.info("shutdown Hbase:{}", conn);

		} catch (IOException e) {
			log.error("shutdown Hbase:{}", conn, e);
		}
		destroy(conn);
	}

}
