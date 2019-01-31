package com.cobwebos.dapp.server.rest;

import java.net.URI;

import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobwebos.dapp.server.common.ZookeeperUtils;
import com.cobwebos.dapp.server.config.DappServerCfg;
import com.cobwebos.dapp.server.rest.RestResources.Hello;
import com.cobwebos.dapp.server.rest.RestResources.RestconfResource;

import io.netty.channel.Channel;

public class RestServer {

	private static Logger log = LoggerFactory.getLogger(RestServer.class);
	private static Channel server;
	public static ZookeeperUtils zk = new ZookeeperUtils();

	

	public static void startZKClient() {
		log.info("starting the ZK Client...");
		//init zk
		System.setProperty("jute.maxbuffer",DappServerCfg.getInstance().getJuteMaxBuffer());		
		zk.connect(DappServerCfg.getInstance().getZkServerUrl(), DappServerCfg.getInstance().getZkClientSessionTimeout());
				
		log.info("started the ZK Client...");
	}

	public static void startRestServer() {
		try {
			log.info("starting the restconf Server...");
			String PATH = DappServerCfg.getInstance().getDappServerUrl();
			URI uri = URI.create(PATH);
			ResourceConfig resourceConfig = new AppResource();
			server = NettyHttpContainerProvider.createHttp2Server(uri, resourceConfig, null);
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					server.close();
				}
			}));

			log.info("started the restconf Server...");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	public static void stopRestServer() {
		if (server.isOpen()) {
			server.close();
		}
	}

	public static void stopZKClient() {
		zk.close();

	}

	private static class AppResource extends ResourceConfig {
		public AppResource() {
			log.info("startting register restconf resource...");
			this.register(Hello.class);
			this.register(RestconfResource.class);
			log.info("started register restconf resource...");
		}

	}

}
