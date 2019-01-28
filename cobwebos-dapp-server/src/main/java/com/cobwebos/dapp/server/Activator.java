/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cobwebos.dapp.server;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.server.config.DappServerCfg;

public class Activator implements BundleActivator {

	public static String logPath = System.getProperty("user.dir") + File.separator + "etc" + File.separator
			+ "log4j.properties";
	public static Logger log = LoggerFactory.getLogger("dapp-server");
	public static DappServerCfg cfg;

	public static String cfgPath = System.getProperty("user.dir") + File.separator + "etc" + File.separator
			+ "dappServer.properties";
	private DappServerMain main = new DappServerMain();

	static {

		PropertyConfigurator.configure(logPath);
		log.info("cfgPath:", cfgPath);

	}

	public void start(BundleContext context) {

		log.info("Starting the dapp-server bundle...");
		cfg = new DappServerCfg(cfgPath);
		main.startHbase();
		main.startZKClient();
		main.startRestServer();
		log.info("Starting the dapp-server bundle end...");
	}

	public void stop(BundleContext context) {
		log.info("Stopping the dapp-server bundle...");
		main.stopZKClient();
		main.stopRestServer();
		log.info("Stopping the dapp-server bundle end...");
	}

}