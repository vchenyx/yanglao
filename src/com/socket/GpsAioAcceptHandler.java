package com.socket;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GpsAioAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, GpsAioServerHandler>{

	private static final Logger log = LoggerFactory.getLogger(GpsAioAcceptHandler.class);
	
	protected int bufSize = 1024;
	@Override
	public void completed(AsynchronousSocketChannel channel, GpsAioServerHandler serverHandler) {
		 //继续接受其他客户端的请求  
//        Log.logger.info("gps conect num:" + (++serverHandler.clientCount));  
        serverHandler.serverChannel.accept(serverHandler, this);  
        
        //创建新的Buffer  
        ByteBuffer echoBuffer = ByteBuffer.allocate(bufSize);  
        //异步读  第三个参数为接收消息回调的业务Handler   
        channel.read(echoBuffer, echoBuffer, new GpsAioReadHandler(channel,bufSize)); 
		
	}

	@Override
	public void failed(Throwable exc, GpsAioServerHandler serverHandler) {
        serverHandler.serverChannel.accept(serverHandler, this);
        log.info(exc.getMessage());
	}
}
