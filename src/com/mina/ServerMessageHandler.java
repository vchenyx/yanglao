package com.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;


public class ServerMessageHandler extends IoHandlerAdapter{
	
	/**
	 * 接收数据
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("message:"+message);
		ServerMessageUtil.messageReceived(session,message);
	}
	/**
	 * 关闭连接
	 */
	@Override
	public void sessionClosed(IoSession session) 
			throws Exception {
		ServerMessageUtil.sessionClosed(session);
	}
	/**
	 * 空闲时间
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}

	/**
	 * 发送消息
	 */
	@Override
	public void messageSent(IoSession session, Object message) 
			throws Exception {
	}

	/**
	 * 创建连接
	 */
	@Override
	public void sessionCreated(IoSession session) 
			throws Exception {
	}

	/**
	 * 打开连接
	 */
	@Override
	public void sessionOpened(IoSession session) 
			throws Exception {
		session.write("mina connect success"); 
	}
	
	
	/**
	 * Invoked when any exception is thrown.
	 * 异常
	 * 
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("服务器发生异常："+cause.getMessage());
	}
	
	
	
}
