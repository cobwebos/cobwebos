package com.cobwebos.aaa.common;

public class AAAConstants {
	/**
	 * 
	 * AAA-身份认证(Authentication)、授权 (Authorization)和统计 (Accounting)
	   *    是一个cobwebos的数字身份认证网络安全系统
	 */
	private static final String AAA_BASE = "aaa/";
	private static final String AAA_USER = AAA_BASE + "user";
	private static final String AAA_GROUP = AAA_BASE + "group";
	private static final String AAA_ROLE = AAA_BASE + "role";
	private static final String AAA_PERMISSION = AAA_BASE + "permission";
	private static final String AAA_RESOURCE = AAA_BASE + "resource";
	private static final String AAA_LOGIN = AAA_BASE + "login";
	private static final String AAA_LOGOUT = AAA_BASE + "logout";

	public static final String AAA_HELLO = AAA_BASE + "hello";
	public static final String AAA_USER_PATH = AAA_USER + "{path: /.*}";
	public static final String AAA_GROUP_PATH = AAA_GROUP + "{path: /.*}";
	public static final String AAA_ROLE_PATH = AAA_ROLE + "{path: /.*}";
	public static final String AAA_PERMISSION_PATH = AAA_PERMISSION + "{path: /.*}";
	public static final String AAA_RESOURCE_PATH = AAA_RESOURCE + "{path: /.*}";
	public static final String AAA_LOGIN_PATH = AAA_LOGIN + "{path: /.*}";
	public static final String AAA_LOGOUT_PATH = AAA_LOGOUT + "{path: /.*}";

}
