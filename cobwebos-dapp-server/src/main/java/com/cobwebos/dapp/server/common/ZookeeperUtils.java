package com.cobwebos.dapp.server.common;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZookeeperUtils {

	private static Logger log = LoggerFactory.getLogger(ZookeeperUtils.class);
	private static ZooKeeper zk;
	final CountDownLatch connectedSignal = new CountDownLatch(1);

	public static void createNode(String path, String data) {
		try {
			zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			log.info("create node path:{},data:{}", path, data);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void createNode(String path, byte[] data) {
		try {
			zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			log.info("create node path:{},data:{}", path, new String(data, "UTF-8"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void createNode(String path, String data, CreateMode createMode) {
		try {
			zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
			log.info("create node path:{},data:{}", path, data);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void createNode(String path, byte[] data, CreateMode createMode) {
		try {
			zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
			log.info("create node path:{},data:{}", path, new String(data, "UTF-8"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static Stat isExistsNode(String path) {
		Stat stat = null;
		try {
			stat = zk.exists(path, true);
			log.info("stat:{}", stat);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return stat;
	}

	public static boolean deleteNode(String path) {
		boolean result = false;
		try {
			zk.delete(path, zk.exists(path, true).getVersion());
			log.info("delete node path:{}", path);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result = false;
		}
		return result;
	}

	public static boolean rmrNode(String path) {
		boolean result = false;
		List<String> children;
		try {
			children = zk.getChildren(path, false);
			for (String pathCd : children) {
				String newPath = "";
				if (path.equals("/")) {
					newPath = "/" + pathCd;
				} else {
					newPath = path + "/" + pathCd;
				}
				rmrNode(newPath);
			}
			if (path != null && !path.trim().startsWith("/zookeeper") && !path.trim().equals("/")) {
				zk.delete(path, -1);
				log.info("rmr node path:{}", path);
			}
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result = false;
		}

		return result;
	}

	public static boolean setNodeValue(String path, byte[] data) {
		boolean result = false;
		try {
			zk.setData(path, data, zk.exists(path, true).getVersion());
			log.info("set node path:{},data:{}", path, new String(data, "UTF-8"));
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result = false;
		}
		return result;
	}

	public static boolean setNodeValue(String path, String data) {
		boolean result = false;
		try {
			zk.setData(path, data.getBytes(), zk.exists(path, true).getVersion());
			log.info("set node path:{},data:{}", path, data);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result = false;
		}
		return result;
	}

	public static String getNodeValue(String path) throws InterruptedException, KeeperException {
		String data = null;
		try {
			Stat stat = isExistsNode(path);
			if (stat != null) {
				byte[] b = zk.getData(path, true, isExistsNode(path));
				data = new String(b, "UTF-8");
				log.info("get node path:{},data:{}", path, data);

			} else {
				log.warn("node path:{} does not exists ! ", path);
				data = path + " does not exists ! ";

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return data;
	}


	public static String getNode(String path) throws InterruptedException, KeeperException {
		JSONObject statJSON = new JSONObject();
		try {
			Stat stat = isExistsNode(path);
			if (stat != null) {
				byte[] b = zk.getData(path, true, isExistsNode(path));
				String data = new String(b, "UTF-8");	
				JSONObject dataObj = new JSONObject(data);
				statJSON.put("path", path);
				statJSON.put("data", dataObj);				
				statJSON.put("cZxid", stat.getCzxid());
				statJSON.put("ctime", stat.getCtime());
				statJSON.put("mZxid", stat.getMzxid());
				statJSON.put("mtime", stat.getMtime());
				statJSON.put("pZxid", stat.getPzxid());
				statJSON.put("cversion", stat.getCversion());
				statJSON.put("dataVersion", stat.getVersion());
				statJSON.put("aclVersion", stat.getAversion());
				statJSON.put("ephemeralOwner ", stat.getEphemeralOwner());
				statJSON.put("dataLength", stat.getDataLength());
				statJSON.put("numChildren", stat.getNumChildren());
				
				log.info("get path:{},node:{}", path, statJSON);
			} else {
				log.warn("node path:{} does not exists ! ", path);

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return statJSON.toString();
	}

	public static List<String> getChildrenNode(String path) throws InterruptedException, KeeperException {
		List<String> children = null;
		try {
			Stat stat = isExistsNode(path);
			if (stat != null) {
				children = zk.getChildren(path, false);
				for (int i = 0; i < children.size(); i++)
					log.info(children.get(i));
			} else {
				log.warn("node path:{} does not exists ! ", path);

			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return children;

	}

	public ZooKeeper connect(String hosts) {
		try {
			zk = new ZooKeeper(hosts, 60000, new Watcher() {

				public void process(WatchedEvent we) {

					if (we.getState() == KeeperState.SyncConnected) {
						connectedSignal.countDown();
					}
				}
			});
			connectedSignal.await();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return zk;
	}

	public ZooKeeper connect(String hosts, int sessionTimeout) {
		try {
			zk = new ZooKeeper(hosts, sessionTimeout, new Watcher() {
				public void process(WatchedEvent we) {
					if (we.getState() == KeeperState.SyncConnected) {
						connectedSignal.countDown();
					}
				}
			});
			connectedSignal.await();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return zk;
	}

	public void close() {
		try {
			zk.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
