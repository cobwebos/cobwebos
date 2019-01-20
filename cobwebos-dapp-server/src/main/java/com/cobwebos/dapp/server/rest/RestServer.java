package com.cobwebos.dapp.server.rest;

import com.cobwebos.dapp.server.common.ZookeeperUtils;
import com.cobwebos.dapp.server.rest.RestResources.Hello;
import com.cobwebos.dapp.server.rest.RestResources.RestconfResource;
import io.netty.channel.Channel;
import org.apache.zookeeper.CreateMode;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class RestServer {

    private static Logger log = LoggerFactory.getLogger(RestServer.class);
    private static Channel server;
    private static String PATH ="http://192.168.0.4:2019/";
    private static final URI uri = URI.create(PATH);

    public static ZookeeperUtils zk = new ZookeeperUtils();
    private static String hosts = "192.168.0.2:12181,192.168.0.2:12182,192.168.0.2:12183";
    private static int sessionTimeout = 30000;
    private static CreateMode createMode = CreateMode.PERSISTENT;


    public static void startZKClient(){
        log.info("starting the ZK Client...");
        System.setProperty("jute.maxbuffer", 41943040 + "");
        zk.connect(hosts, sessionTimeout);
        log.info("started the ZK Client...");
    }

    public static void startRestServer(){
        try {
            log.info("starting the restconf Server...");
            ResourceConfig resourceConfig = new AppResource();
            server = NettyHttpContainerProvider.createHttp2Server(uri,resourceConfig,null);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.close();
                }
            }));

            log.info("started the restconf Server...");
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

    }


    public static void stopRestServer(){
        if(server.isOpen()){
            server.close();
        }
    }
    public static void stopZKClient(){
        zk.close();

    }


    private static class AppResource extends ResourceConfig{
        public AppResource(){
            log.info("startting register restconf resource...");
            this.register(Hello.class);
            this.register(RestconfResource.class);
            log.info("started register restconf resource...");
        }

    }

}
