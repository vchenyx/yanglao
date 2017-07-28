package com.jinkun.init;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.util.global.BufferDataInit;
import com.common.util.global.ConfigureFile;
import com.socket.GpsAioServerHandler;

public class StartInit implements ServletContextListener {

	private static Logger log = LoggerFactory.getLogger(StartInit.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("System stop!");		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		BufferDataInit.initDatabaseService(sce);//连接service
//		startOutdoorServer();//启动GPS定位
//		startWifiServer();//启动WIFI定位
		startGpsAioServer(); //启动GPS定位-AIO模式
	}
	
	private void startGpsAioServer(){
		try {
			new Thread(new GpsAioServerHandler(ConfigureFile.outdoor_server_port)).start();
			log.info("AIO start success");
		} catch (Exception e) {
			log.info("AIO start error");
		}
	}
}
