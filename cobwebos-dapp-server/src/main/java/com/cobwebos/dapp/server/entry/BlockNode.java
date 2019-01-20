package com.cobwebos.dapp.server.entry;

import org.apache.zookeeper.KeeperException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.server.rest.RestServer;

public class BlockNode {
    /**
     *   鍖哄潡閾捐妭鐐瑰敮涓�鏍囪瘑ID
     */
    private String BlockNodeHashId;

    /**
     *   鍓嶄竴鍖哄潡閾捐妭鐐瑰敮涓�鏍囪瘑ID
     */
    private String BlockNodeNextHashId;

    /**
     *   鍚庝竴鍖哄潡閾捐妭鐐瑰敮涓�鏍囪瘑ID
     */
    private String BlockNodeLastHashId;
    /**
     *   鍖哄潡閾捐妭鐐筓UID
     */
    private String uuid;
    /**
     *   鍖哄潡閾捐妭鐐规墍鍦≒ATH璺緞
     */
    private String path;
    /**
     *   鍖哄潡閾捐妭鐐规暟鎹�
     */
    private String data;
    /**
     *   鍖哄潡閾捐妭鐐筩Zxid
     */

    private String cZxid;
    /**
     *   鍖哄潡閾捐妭鐐� ctime
     */

    private String ctime;
    /**
     *   鍖哄潡閾捐妭鐐� mZxid
     */
    private String mZxid;
    /**
     *   鍖哄潡閾捐妭鐐� mtime
     */
    private String mtime;
    /**
     *   鍖哄潡閾捐妭鐐� pZxid
     */
    private String pZxid;
    /**
     *   鍖哄潡閾捐妭鐐� cversion
     */
    private String cversion;
    /**
     *   鍖哄潡閾捐妭鐐� dataVsersion
     */
    private String dataVsersion;
    /**
     *   鍖哄潡閾捐妭鐐� aclVersion
     */
    private String aclVersion;
    /**
     *   鍖哄潡閾捐妭鐐� ephemeralOwner
     */
    private String ephemeralOwner;
    /**
     *   鍖哄潡閾捐妭鐐� dataLength
     */
    private String dataLength;
    /**
     *   鍖哄潡閾捐妭鐐� numChildren
     */
    private String numChildren;
    
    private Logger log = LoggerFactory.getLogger(BlockNode.class);
    
    public BlockNode(){
             
    }
    
    
    public BlockNode(String path,String data){
        this.path =path;
        this.data = data;
        RestServer.zk.createNode(path, data);
    }
    
   

    public String getBlockNodeHashId() {
        return BlockNodeHashId;
    }

    public void setBlockNodeHashId(String blockNodeHashId) {
        BlockNodeHashId = blockNodeHashId;
    }

    public String getBlockNodeNextHashId() {
        return BlockNodeNextHashId;
    }

    public void setBlockNodeNextHashId(String blockNodeNextHashId) {
        BlockNodeNextHashId = blockNodeNextHashId;
    }

    public String getBlockNodeLastHashId() {
        return BlockNodeLastHashId;
    }

    public void setBlockNodeLastHashId(String blockNodeLastHashId) {
        BlockNodeLastHashId = blockNodeLastHashId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getcZxid() {
        return cZxid;
    }

    public void setcZxid(String cZxid) {
        this.cZxid = cZxid;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getmZxid() {
        return mZxid;
    }

    public void setmZxid(String mZxid) {
        this.mZxid = mZxid;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getpZxid() {
        return pZxid;
    }

    public void setpZxid(String pZxid) {
        this.pZxid = pZxid;
    }

    public String getCversion() {
        return cversion;
    }

    public void setCversion(String cversion) {
        this.cversion = cversion;
    }

    public String getDataVsersion() {
        return dataVsersion;
    }

    public void setDataVsersion(String dataVsersion) {
        this.dataVsersion = dataVsersion;
    }

    public String getAclVersion() {
        return aclVersion;
    }

    public void setAclVersion(String aclVersion) {
        this.aclVersion = aclVersion;
    }

    public String getEphemeralOwner() {
        return ephemeralOwner;
    }

    public void setEphemeralOwner(String ephemeralOwner) {
        this.ephemeralOwner = ephemeralOwner;
    }

    public String getDataLength() {
        return dataLength;
    }

    public void setDataLength(String dataLength) {
        this.dataLength = dataLength;
    }

    public String getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(String numChildren) {
        this.numChildren = numChildren;
    }
    
    public JSONObject toJSONObject () {		
    	String blockNode= "path:" +path+",data" + data;
		JSONObject json = new JSONObject(blockNode);		
		return json;				
	}

}
