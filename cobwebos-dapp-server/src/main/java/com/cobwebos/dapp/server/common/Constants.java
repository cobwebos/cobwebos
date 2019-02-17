package com.cobwebos.dapp.server.common;

public class Constants {

	public static final String DAPP_SERVER_REST_BASE_HELLO = "hello";
	private static final String DAPP_SERVER_REST_BASE = "cobwebos/v1";
	public static final String DAPP_SERVER_REST_APP_PATH = DAPP_SERVER_REST_BASE + "{path: /.*}";
	private static final String BLOCK_CHAIN_RESOURCE = "blockchain";
	public static final String BLOCK_CHAIN_RESOURCE_URI = BLOCK_CHAIN_RESOURCE + "{schema: /.*}";
	
	private static final String BLOCK_CHAIN_RESOURCE_TEST = "test";
	public static final String BLOCK_CHAIN_RESOURCE_TEST_URI = BLOCK_CHAIN_RESOURCE_TEST + "{schema: /.*}";
	
	public static final String SCHEMA = "inventory";
	public static final String CF = "cf";
	public static final String col = "inv";


}
