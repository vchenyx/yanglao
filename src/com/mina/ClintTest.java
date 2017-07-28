package com.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class ClintTest {
	public static void main(String[] args) {
		  // 创建客户端连接器. 
		  NioSocketConnector connector = new NioSocketConnector(); 
		  connector.getFilterChain().addLast("logger", new LoggingFilter() ); 
		  connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8")))); //设置编码过滤器 
//		  connector.setConnectTimeout(1); 
		  connector.setHandler(new ServerMessageHandler());//设置事件处理器 
		  ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 3100));//建立连接 
		  cf.awaitUninterruptibly();//等待连接创建完成 
		  cf.getSession().write("知识");//发送消息 
//		  cf.getSession().close();
		  cf.getSession().getCloseFuture().awaitUninterruptibly();//等待连接断开 
		  connector.dispose();

	 }
}
