package com.socket;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AsynchronousServerSocketChannel
 */
public class GpsAioServerHandler implements Runnable{

	private static final Logger log = LoggerFactory.getLogger(GpsAioServerHandler.class);
	
    protected AsynchronousChannelGroup asynchronousChannelGroup;
    protected AsynchronousServerSocketChannel serverChannel;
    public InetSocketAddress serverAddress = null;
	public long clientCount = 0;
    public int threadPoolSize = 0;
    
    public GpsAioServerHandler(int port) {
//    	 asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10);
//       serverChannel = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
    	this.serverAddress = new InetSocketAddress(port);
    	initialization();
//         try {
//			serverChannel = AsynchronousServerSocketChannel.open();
//			serverChannel.bind(new InetSocketAddress(port));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    }
    public void initialization(){
		threadPoolSize = threadPoolSize>0? threadPoolSize: Runtime.getRuntime().availableProcessors();
	}
    public void run() {
        try{
        	ExecutorService threadPool = Executors.newFixedThreadPool(this.threadPoolSize);
        	asynchronousChannelGroup = AsynchronousChannelGroup.withThreadPool(threadPool);
        	serverChannel = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
        	serverChannel.bind(serverAddress); 
			log.info("gpsserver listen:"+this.serverAddress);
            serverChannel.accept(this, new GpsAioAcceptHandler());
//            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}