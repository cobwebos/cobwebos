package com.cobwebos.aaa.rest;

import java.net.URI;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cobwebos.aaa.common.AAACfg;
import com.cobwebos.aaa.common.ZookeeperUtils;
import com.cobwebos.aaa.rest.resource.AAAGroup;
import com.cobwebos.aaa.rest.resource.AAAHello;
import com.cobwebos.aaa.rest.resource.AAAPermission;
import com.cobwebos.aaa.rest.resource.AAAResource;
import com.cobwebos.aaa.rest.resource.AAARole;
import com.cobwebos.aaa.rest.resource.AAAUser;
import io.netty.channel.Channel;

public class AAAServer {

	private static Logger log = LoggerFactory.getLogger(AAAServer.class);
	private static Channel server;
	public static ZookeeperUtils zk = new ZookeeperUtils();

	public static void startRestServer() {
		try {
			log.info("starting the AAA Server...");
			String PATH = AAACfg.getInstance().getAAAServerUrl();
			URI uri = URI.create(PATH);
			ResourceConfig resourceConfig = new AppResource();
			server = NettyHttpContainerProvider.createHttp2Server(uri, resourceConfig, null);
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					server.close();
				}
			}));

			log.info("started the AAA Server...");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	public static void stopRestServer() {
		log.info("stoping the AAA Server...");
		if (server.isOpen()) {
			server.close();
		}
		log.info("stoped the AAA Server...");
	}

	public static void startZKClient() {
		log.info("starting the ZK Client...");
		System.setProperty("jute.maxbuffer", AAACfg.getInstance().getJuteMaxBuffer());
		zk.connect(AAACfg.getInstance().getZkServerUrl(), AAACfg.getInstance().getZkClientSessionTimeout());
		log.info("started the ZK Client...");
	}

	public static void stopZKClient() {
		zk.close();

	}

	private static class AppResource extends ResourceConfig {
		public AppResource() {
			log.info("startting register AAA resource...");
			this.register(AAAUser.class);
			this.register(AAAGroup.class);
			this.register(AAARole.class);
			this.register(AAAPermission.class);
			this.register(AAAResource.class);
			this.register(AAAHello.class);
			log.info("started register AAA resource...");
		}

	}

}
