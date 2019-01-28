package com.cobwebos.dapp.server.entry;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.server.rest.RestServer;

public class BlockNode {
   
    private String BlockNodeHashId;

    private String BlockNodeNextHashId;

   
    private String BlockNodeLastHashId;
   
    private String uuid;
  
    private String path;
    
    private String data;   

    private String cZxid;   

    private String ctime;
   
    private String mZxid;
   
    private String mtime;
   
    private String pZxid;
  
    private String cversion;
   
    private String dataVsersion;
    
    private String aclVersion;
    
    private String ephemeralOwner;
   
    private String dataLength;
   
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
