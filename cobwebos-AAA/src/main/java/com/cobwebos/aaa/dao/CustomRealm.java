package com.cobwebos.aaa.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.aaa.common.AAACfg;
import com.cobwebos.aaa.common.HttpClientUtils;

public class CustomRealm extends AuthorizingRealm {

	Logger log = LoggerFactory.getLogger(CustomRealm.class);

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("begin to token authentication ...");
		String user = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		SimpleAuthenticationInfo simpleAuthenticationInfo = null;
		JSONObject inputObj = new JSONObject();
		inputObj.put("node-who", user);
		inputObj.put("node-which", "user");
		inputObj.put("node-where", "info");
		JSONObject whatObj = new JSONObject();
		JSONObject userObj = new JSONObject();
		userObj.put("name", user);
		userObj.put("passowrd", password);
		userObj.put("mail", user+"@163.com");
		userObj.put("url", user+":create");
		whatObj.put("user", userObj);
		inputObj.put("node-what", whatObj);
		inputObj.put("node-why", "cf");
		inputObj.put("node-when", "node-when");
		inputObj.put("node-how-to-do", "get");
		inputObj.put("node-how-much", "how-much");
		String output = HttpClientUtils.getClientInstance().doPost(AAACfg.getInstance().getDappServerUrl(),
				inputObj.toString());
		log.info("output:{}", output);
		if (null != output) {
			JSONObject outputObj = new JSONObject(output);
			String dBUserName = outputObj.getJSONObject("output").getJSONObject("node-what")
					.getJSONObject("slave-chain-node").getJSONObject("user").getString("name");
			String dBUserPassword = outputObj.getJSONObject("output").getJSONObject("node-what")
					.getJSONObject("slave-chain-node").getJSONObject("user").getString("passowrd");

			if (!user.equalsIgnoreCase(dBUserName)) {
				log.error("user name:{}  is error!", user);
			} else {
				log.info("user:{} successed!：", user);
			}
			if (!password.equalsIgnoreCase(dBUserPassword)) {
				log.error("user password:{}  is error!", password);
			} else {
				log.info("password:{} successed!：", password);
			}
			simpleAuthenticationInfo = new SimpleAuthenticationInfo(dBUserName, dBUserPassword, this.getName());
		} else {
			log.info("get output data: {} from db failed!：", output);			
		}

		log.info("end to token authentication ...");
		return simpleAuthenticationInfo;
	}

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("begin to principal authorizationInfo ...");
		String user = (String) principals.getPrimaryPrincipal();		
		SimpleAuthorizationInfo simpleAuthorizationInfo = null;
		JSONObject inputObj = new JSONObject();
		inputObj.put("node-who", user);
		inputObj.put("node-which", "user");
		inputObj.put("node-where", "info");
		JSONObject whatObj = new JSONObject();
		JSONObject userObj = new JSONObject();
		userObj.put("name", user);
		userObj.put("passowrd", "passowrd");
		userObj.put("mail", user+"@163.com");
		userObj.put("url", user+":create");
		whatObj.put("user", userObj);
		inputObj.put("node-what", whatObj);
		inputObj.put("node-why", "cf");
		inputObj.put("node-when", "node-when");
		inputObj.put("node-how-to-do", "get");
		inputObj.put("node-how-much", "how-much");
		String output = HttpClientUtils.getClientInstance().doPost(AAACfg.getInstance().getDappServerUrl(),
				inputObj.toString());
		log.info("output:{}", output);
		if(null!=output) {
			JSONObject outputObj = new JSONObject(output);
			
			String url = outputObj.getJSONObject("output").getJSONObject("node-what").getJSONObject("slave-chain-node")
					.getJSONObject("user").getString("url");

			List<String> permissions = new ArrayList<String>();
			permissions.add(url);
			simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			simpleAuthorizationInfo.addStringPermissions(permissions);
		}else {
			log.info("get output data from db principal authorizationInfo failed!");
		}		
		log.info("end to principal authorizationInfo ...");
		return simpleAuthorizationInfo;
	}
}
