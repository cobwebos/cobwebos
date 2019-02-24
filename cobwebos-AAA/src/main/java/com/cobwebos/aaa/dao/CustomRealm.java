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

	  // 用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	log.info("do GetAuthentication Info!");
        // token是用户输入的
        // 第一步从token中取出身份信息（token代表用户输入的传下来的信息）
        String user = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials()); 
        // 第二步：根据用户输入的userCode从数据库查询
        // ....从数据库查数据
        // 如果查询不到返回null
        // 数据库中用户账号是zhangsansan
        /*
         * if(!userCode.equals("zhangsansan")){// return null; }
         */

        // 模拟从数据库查询到密码
        
        JSONObject inputObj = new JSONObject();       
        inputObj.put("node-who", user);
        inputObj.put("node-which", "user");
        inputObj.put("node-where", "info");
        inputObj.put("node-what", "{}");
        inputObj.put("node-why", "cf");
        inputObj.put("node-when", "node-when");
        inputObj.put("node-how-to-do", "get");
        inputObj.put("node-how-much", "how-much");
        String output = HttpClientUtils.getClientInstance().doGet(AAACfg.getInstance().getDappServerUrl(), inputObj.toString());
        log.info("output:{}",output);
        
        JSONObject outputObj = new JSONObject(output);
        password = outputObj.getJSONObject("node-what").getString("passowrd");
        String dBUserName = outputObj.getJSONObject("node-who").getString("user");
        String dBUserPassword = outputObj.getJSONObject("node-what").getString("passowrd");
        
        if(!user.equalsIgnoreCase(dBUserName)) {
        	log.error("user name:{}  is error!",user);
        }
        if(!password.equalsIgnoreCase(dBUserPassword)) {
        	log.error("user password:{}  is error!",password);
        }       
        
        // 如果查询到返回认证信息AuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password,
                this.getName());

        return simpleAuthenticationInfo;
    }

    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	log.info("do GetAuthorization Info!");
        // 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        String user = (String) principals.getPrimaryPrincipal();

        // 根据身份信息获取权限信息
        // 连接数据库...
        // 模拟从数据库获取到数据
        
        List<String> permissions = new ArrayList<String>();
//        permissions.add("user:create");// 用户的创建       
//        permissions.add("user:delete");
//        permissions.add("user:update");
//        permissions.add("user:set");
//        permissions.add("user:get");
//        permissions.add("user:admin");
//        permissions.add("user:event");
//        permissions.add("user:acl");
//        permissions.add("items:add");// 商品添加权限
        // ....
        
        JSONObject inputObj = new JSONObject();       
        inputObj.put("node-who", user);
        inputObj.put("node-which", "user");
        inputObj.put("node-where", "info");
        inputObj.put("node-what", "{}");
        inputObj.put("node-why", "cf");
        inputObj.put("node-when", "node-when");
        inputObj.put("node-how-to-do", "get");
        inputObj.put("node-how-much", "how-much");
        String output = HttpClientUtils.getClientInstance().doGet(AAACfg.getInstance().getDappServerUrl(), inputObj.toString());
        log.info("output:{}",output);
        
        JSONObject outputObj = new JSONObject(output);
        permissions = (List<String>) outputObj.getJSONObject("node-what").getJSONObject("permission");

        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }
}
