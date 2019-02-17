package com.cobwebos.blockchain.node.entry;

public class NodeHeaderBean {
	private String version;
	private String parentHash;
	private String merkleRootHash;
	private String myselfHash;
	private String sequenceId;
	private String time;
	private String nonce;
	private String bits;

	public NodeHeaderBean() {

	}

	public NodeHeaderBean(String version, String parentHash, String merkleRootHash, String myselfHash, String sequenceId,
			String time, String nonce, String bits) {
		this.version = version;
		this.parentHash = parentHash;
		this.merkleRootHash = merkleRootHash;
		this.myselfHash = myselfHash;
		this.sequenceId = sequenceId;
		this.time = time;
		this.nonce = nonce;
		this.bits = bits;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getParentHash() {
		return parentHash;
	}

	public void setParentHash(String parentHash) {
		this.parentHash = parentHash;
	}

	public String getMerkleRootHash() {
		return merkleRootHash;
	}

	public void setMerkleRootHash(String merkleRootHash) {
		this.merkleRootHash = merkleRootHash;
	}

	public String getMyselfHash() {
		return myselfHash;
	}

	public void setMyselfHash(String myselfHash) {
		this.myselfHash = myselfHash;
	}

	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getBits() {
		return bits;
	}

	public void setBits(String bits) {
		this.bits = bits;
	}

}
