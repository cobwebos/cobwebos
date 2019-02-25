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
package com.cobwebos.aaa;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.aaa.common.AAA;
import com.cobwebos.aaa.common.AAACfg;
import com.cobwebos.aaa.rest.AAAServer;

public class Activator implements BundleActivator {
	Logger log = LoggerFactory.getLogger(Activator.class);
	AAAServer aaa = new AAAServer();
    public void start(BundleContext context) {
    	AAACfg.getInstance().initAAALog();
        System.out.println("Starting the AAA bundle...");
        log.info("Starting the AAA bundle...");
        aaa.startRestServer();       
        System.out.println("Started the AAA bundle...");
        log.info("Started the AAA bundle...");
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the AAA bundle...");
        log.info("Stopping the AAA bundle...");
        aaa.stopRestServer();
        System.out.println("Stoped the AAA bundle...");
        log.info("Stoped the AAA bundle...");
    }
    
    public static void main(String []args) {
//    	 boolean login = AAA.getInstance().doLogin("user4", "123456");
//         System.out.println("Starting login "+ login);
//         boolean doAuthorization = AAA.getInstance().doAuthorization("user4", "123456");
//         System.out.println("Starting doAuthorization "+ doAuthorization);
//         boolean doPermission = AAA.getInstance().doPermission("user4", "123456", "user4:create");
//         System.out.println("Starting doAuthorization "+ doPermission);
    }
}