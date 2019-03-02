package com.cobwebos.aaa.pojo;

import java.io.Serializable;

import org.json.JSONObject;

import com.cobwebos.aaa.common.DataNode;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String hashId;
	private String name;
	private String password;
	private String nickName;
	private String email;
	private String phoneNumber;
	private String publicKey;
	private String privateKey;
	private String walletAddress;
	private String identityCard;
	private String sex;
	private String birthday;
	private String bloodType;
	private String birthPlace;
	private String homePlace;
	private String bodyHeight;
	private String bodyShape;
	private String marriageStatus;
	private String educationDegree;
	private String occupational;
	private String contactAddress;
	private String schoolType;
	private String graduateInstitutions;
	private String yearEnrolment;
	private String yearGraduation;
	private String workUnit;
	private String incomeLevel;
	private String personalHabits;
	private String character;
	private String avatarSettingsUrl;
	private String loginTime;
	private String logoutTime;
	private String loginIp;
	private String loginClient;
	private String LoginType;
	private String LoginProduct;
	private String loginStatus;
	private String personalUrl;
	private String persionalsignature;
	private String freezeStatus;
	private String lockedStatus;
	private String calculus;

	private JSONObject userObj;
	private DataNode dataNode;

	public User() {

	}

	public User(JSONObject userObj) {
		this.userObj = userObj;

	}

	public User(DataNode dataNode) {
		this.dataNode = dataNode;

	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getHashId() {
		return hashId;
	}

	public void setHashId(String hashId) {
		this.hashId = hashId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getWalletAddress() {
		return walletAddress;
	}

	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getHomePlace() {
		return homePlace;
	}

	public void setHomePlace(String homePlace) {
		this.homePlace = homePlace;
	}

	public String getBodyHeight() {
		return bodyHeight;
	}

	public void setBodyHeight(String bodyHeight) {
		this.bodyHeight = bodyHeight;
	}

	public String getBodyShape() {
		return bodyShape;
	}

	public void setBodyShape(String bodyShape) {
		this.bodyShape = bodyShape;
	}

	public String getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public String getEducationDegree() {
		return educationDegree;
	}

	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}

	public String getOccupational() {
		return occupational;
	}

	public void setOccupational(String occupational) {
		this.occupational = occupational;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getGraduateInstitutions() {
		return graduateInstitutions;
	}

	public void setGraduateInstitutions(String graduateInstitutions) {
		this.graduateInstitutions = graduateInstitutions;
	}

	public String getYearEnrolment() {
		return yearEnrolment;
	}

	public void setYearEnrolment(String yearEnrolment) {
		this.yearEnrolment = yearEnrolment;
	}

	public String getYearGraduation() {
		return yearGraduation;
	}

	public void setYearGraduation(String yearGraduation) {
		this.yearGraduation = yearGraduation;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getIncomeLevel() {
		return incomeLevel;
	}

	public void setIncomeLevel(String incomeLevel) {
		this.incomeLevel = incomeLevel;
	}

	public String getPersonalHabits() {
		return personalHabits;
	}

	public void setPersonalHabits(String personalHabits) {
		this.personalHabits = personalHabits;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getAvatarSettingsUrl() {
		return avatarSettingsUrl;
	}

	public void setAvatarSettingsUrl(String avatarSettingsUrl) {
		this.avatarSettingsUrl = avatarSettingsUrl;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginClient() {
		return loginClient;
	}

	public void setLoginClient(String loginClient) {
		this.loginClient = loginClient;
	}

	public String getLoginType() {
		return LoginType;
	}

	public void setLoginType(String loginType) {
		LoginType = loginType;
	}

	public String getLoginProduct() {
		return LoginProduct;
	}

	public void setLoginProduct(String loginProduct) {
		LoginProduct = loginProduct;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getPersonalUrl() {
		return personalUrl;
	}

	public void setPersonalUrl(String personalUrl) {
		this.personalUrl = personalUrl;
	}

	public String getPersionalsignature() {
		return persionalsignature;
	}

	public void setPersionalsignature(String persionalsignature) {
		this.persionalsignature = persionalsignature;
	}

	public String getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(String freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

	public String getLockedStatus() {
		return lockedStatus;
	}

	public void setLockedStatus(String lockedStatus) {
		this.lockedStatus = lockedStatus;
	}

	public String getCalculus() {
		return calculus;
	}

	public void setCalculus(String calculus) {
		this.calculus = calculus;
	}

	public DataNode getDataNode() {
		return dataNode;
	}

	public void setDataNode(DataNode dataNode) {
		this.dataNode = dataNode;
	}

	public JSONObject getUserObj() {
		return userObj;
	}

	public void setUserObj(JSONObject userObj) {
		this.userObj = userObj;
	}

}
