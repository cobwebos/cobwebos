package com.cobwebos.aaa.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserLogin {
	Logger log = LoggerFactory.getLogger(UserLogin.class);

	public boolean doLogin(String user, String password) {
		boolean isLogin = false;

		// 创建SecurityManager工厂
//		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:AAA-Custom-Cfg.properties");
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(AAACfg.getInstance().AAACfgPath);
		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// 将SecurityManager设置到系统运行环境，和spring后将SecurityManager配置spring容器中，一般单例管理
		SecurityUtils.setSecurityManager(securityManager);

		// 创建subject
		Subject subject = SecurityUtils.getSubject();

		// 创建token令牌
		UsernamePasswordToken token = new UsernamePasswordToken(user, password);

		// 执行认证
		try {
			subject.login(token);
			isLogin = false;
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			log.error("认证状态失败");
			isLogin = false;
//			e.printStackTrace();
		}

		log.error("认证状态：", subject.isAuthenticated());
		// 认证通过后执行授权
		return isLogin;

	}

	public boolean doAuthorization(String user, String password) {
		boolean isAuthorization = false;
		// 创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(AAACfg.getInstance().AAACfgPath);

		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// 将SecurityManager设置到系统运行环境，和spring后将SecurityManager配置spring容器中，一般单例管理
		SecurityUtils.setSecurityManager(securityManager);

		// 创建subject
		Subject subject = SecurityUtils.getSubject();

		// 创建token令牌
		UsernamePasswordToken token = new UsernamePasswordToken(user, password);

		// 执行认证
		try {
			subject.login(token);
			isAuthorization = subject.isAuthenticated();
			log.info("认证状态：", isAuthorization);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			log.error("认证状态：失败");
			isAuthorization = false;
		}

		// 认证通过后执行授权

		// 基于资源的授权，调用isPermitted方法会调用CustomRealm从数据库查询正确权限数据
		// isPermitted传入权限标识符，判断user:create:1是否在CustomRealm查询到权限数据之内
//		boolean isPermitted = subject.isPermitted("user:create");
//		log.info("单个权限判断：" , isPermitted);

//		boolean isPermittedAll = subject.isPermittedAll("user:create", "user:update", "items:add");
//		log.info("多个权限判断：" + isPermittedAll);
//
//		// 使用check方法进行授权，如果授权不通过会抛出异常
//		try {
//			subject.checkPermission("user:delete");
//			log.info("有权限！！！");
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.error("无权限！！！");
//		}
		return isAuthorization;

	}

	public boolean doPermission(String user, String password, String url) {
		boolean isPermission = false;
		// 创建SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(AAACfg.getInstance().AAACfgPath);

		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// 将SecurityManager设置到系统运行环境，和spring后将SecurityManager配置spring容器中，一般单例管理
		SecurityUtils.setSecurityManager(securityManager);

		// 创建subject
		Subject subject = SecurityUtils.getSubject();

		// 创建token令牌
		UsernamePasswordToken token = new UsernamePasswordToken(user, password);

		// 执行认证
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			log.error("认证状态失败");
//			e.printStackTrace();
		}

		log.error("认证状态：", subject.isAuthenticated());
		// 认证通过后执行授权

		// 基于资源的授权，调用isPermitted方法会调用CustomRealm从数据库查询正确权限数据
		// isPermitted传入权限标识符，判断user:create:1是否在CustomRealm查询到权限数据之内
		isPermission = subject.isPermitted(url);
//		log.info("单个权限判断：" , isPermitted);
//
//		boolean isPermittedAll = subject.isPermittedAll("user:create", "user:update", "items:add");
//		log.info("多个权限判断：" + isPermittedAll);

		// 使用check方法进行授权，如果授权不通过会抛出异常
//		try {
//			subject.checkPermission("user:delete");
//			log.info("有权限！！！");
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.error("无权限！！！");
//		}
		return isPermission;

	}
}
