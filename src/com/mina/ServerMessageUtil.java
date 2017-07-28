package com.mina;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.session.IoSession;

import com.common.util.global.BufferDataInit;
import com.jinkun.main.beans.JAlarmMessageInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.form.MessageBean;

import net.sf.json.JSONObject;

public class ServerMessageUtil {
	
	//手机session信息，key为sessionId，value为uid
	public static ConcurrentHashMap<Long, IoSession> mobileSessionMap = new ConcurrentHashMap<Long, IoSession>();
	public static ConcurrentHashMap<String, Long> mobileSessionIdMap = new ConcurrentHashMap<String, Long>();
	
	/**
	 * 接收并处理信息
	 * @param session
	 * @param message
	 */
	public static void messageReceived(IoSession session, Object reciveId){
		mobileSessionIdMap.put(reciveId.toString(), session.getId());
		mobileSessionMap.put(session.getId(), session);
		
		/*String userId = "";
		if(mobileSessionMap.containsKey(session.getId())){
			userId = mobileSessionMap.get(session.getId());
		} else {//1
			mobileSessionMap.put(session.getId(), reciveId.toString());
		}*/
		/*if("".equals(userId)){
			
		}else{
			List<JAlarmMessageInfo> amilist = BufferDataInit.commonService.findByHql(JAlarmMessageInfo.class, " receiveId = '" + userId + "'", "");
			for(int i = 0; i < amilist.size(); i++){
				JAlarmMessageInfo ami = amilist.get(i);
				//存储信息
				MessageBean bean = new MessageBean();
				bean.setTitle(ami.getTitle());
				bean.setMessage(ami.getMessage());
				bean.setSendTime(ami.getSendTime());
				bean.setAlarmId(ami.getAlarmId());
				//发送信息
				session.write(JSONObject.fromObject(bean).toString());
			}
			BufferDataInit.commonService.deleteData(JAlarmMessageInfo.class, " receiveId = '" + userId + "'");
		}*/
		
	}

	/**
	 * 关闭session
	 * @param session
	 */
	public static void sessionClosed(IoSession session) {
		if (mobileSessionMap.containsKey(session.getId())) {
			mobileSessionMap.remove(session.getId());
		}
		CloseFuture closeFuture = session.close(true);
		closeFuture.addListener(new IoFutureListener<IoFuture>() {
			@Override
			public void operationComplete(IoFuture future) {
				if(future instanceof CloseFuture){
					((CloseFuture) future).setClosed();
				}
			}
		});
	}
	/**
	 * 获取所有用户session
	 * @param session
	 * @return
	 */
	public static Collection<IoSession> getAllClient(IoSession session){
		return session.getService().getManagedSessions().values();
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getNowDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
}
