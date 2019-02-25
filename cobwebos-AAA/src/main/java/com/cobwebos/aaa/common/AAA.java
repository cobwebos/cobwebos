package com.cobwebos.aaa.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.aaa.dao.CustomRealm;

public class AAA {
	private Logger log = LoggerFactory.getLogger(AAA.class);
	public static final AAA aaa = new AAA();

	static {
		CustomRealm real = new CustomRealm();
		SecurityManager securityManager =new DefaultSecurityManager(real);		
		SecurityUtils.setSecurityManager(securityManager);
	}

	private AAA() {

	}

	public synchronized static AAA getInstance() {
		return aaa;
	}

	public boolean doLogin(String user, String password) {
		boolean isLogin = false;
 		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user, password);
		try {
			subject.login(token);
			isLogin = subject.isAuthenticated();
		} catch (AuthenticationException e) {
			log.error(e.getMessage(), e);
			isLogin = false;
		}
		log.info("login status:{}", isLogin);
		return isLogin;
	}

	public boolean doAuthorization(String user, String password) {
		boolean isAuthorization = false;
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user, password);
		try {
			subject.login(token);
			isAuthorization = subject.isAuthenticated();
			log.info("authorization status：{}", isAuthorization);
		} catch (AuthenticationException e) {
			log.error(e.getMessage(), e);
			isAuthorization = false;
		}
		return isAuthorization;

	}

	public boolean doPermission(String user, String password, String url) {
		boolean isPermission = false;
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(user, password);
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				log.info("There is no user with username of " + token.getPrincipal());
			} catch (IncorrectCredentialsException ice) {
				log.info("Password for account " + token.getPrincipal() + " was incorrect!");
			} catch (LockedAccountException lae) {
				log.info("The account for username " + token.getPrincipal() + " is locked.  "
						+ "Please contact your administrator to unlock it.");
			}
			catch (AuthenticationException ae) {			
				log.error("Authentication Exception" + ae.getMessage(), ae);
			}
		}
		log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

		if (currentUser.hasRole("admin")) {
			log.info("has admin Role!");
		} else {
			log.info("has not admin Role!");
		}

		isPermission = currentUser.isPermitted(url);
		if (isPermission) {
			log.info("permission:{} successed!", url);
		} else {
			log.info("permission:{} failed!", url);
		}

		try {
			currentUser.checkPermission(url);
			log.info("check permission:{} successed!", url);
		} catch (Exception e) {
			log.error("check permission:{} failed!", url);
		}

		currentUser.logout();

		return isPermission;

	}
	
	

}
