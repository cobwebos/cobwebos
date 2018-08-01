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
package com.cobwebos.restconf;

import java.net.URI;

import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import io.netty.channel.Channel;

public class Activator implements BundleActivator {
    
    public static final String ROOT_PATH = "cobwebos/restconf";
	private static final URI BASE_URI = URI.create("http://localhost:20000/");
	private Thread thread = null;

	public void start(BundleContext context) {
		 System.out.println("Starting the restconf bundle");
		initRestService();
	}

	public void stop(BundleContext context) {
		 System.out.println("Stopping the restconf bundle");
		if (thread.isAlive()) {
			thread.interrupt();
		}
	}

	public void initRestService() {
		System.out.println("init cobwebos restconf server...");
		ResourceConfig resourceConfig = new ResourceConfig(RestfulResources.class);
		final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, resourceConfig, null);
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				server.close();
			}
		});

		Runtime.getRuntime().addShutdownHook(thread);
		System.out.println(String.format(
				"cobwebos restconf started. (HTTP/2 enabled!)\nTry out %s%s\nStop the cobwebos restconf  using " + "CTRL+C.",
				BASE_URI, ROOT_PATH));
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("init cobwebos restconf server end.");
	}


}