package com.jinkun.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.CommonDao;
import com.jinkun.main.beans.PPushInfo;
import com.jinkun.main.service.PushService;
import com.openfire.beans.OfOffline;
import com.openfire.beans.OfOfflineId;
import com.openfire.util.OfflineUtil;

@Service
@Transactional
public class PushServiceImpl implements PushService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void savePushInfo(PPushInfo pushInfo) {
		commonDao.saveTable(pushInfo);
	}
	
	@Override
	public void saveOffline(String jid, String account, String send_msg) {
//		synchronized(this){
			try {
				//休眠10毫秒
//				Thread.sleep(10);
				long messageID = OfflineUtil.messageID;
				String  message_id = OfflineUtil.message_id;
				String  message_from = OfflineUtil.message_from;
				String  message_type = OfflineUtil.message_type;
				String  message_thread = OfflineUtil.message_thread;
				
				Object obj = findBySqlGetColumn("ofOffline", "messageID", " order by messageID desc");
				if(obj != null){
					messageID = Long.valueOf(obj.toString());
				}
				if(messageID - OfflineUtil.messageID < 0){
					messageID = OfflineUtil.messageID;
				}
				OfflineUtil.messageID = messageID+1;
				//拼信息
				String stanza = "<message id=\""+message_id+messageID+"\" to=\""+account+"@"+jid+"\" from=\""+message_from+"@"+jid+"\" type=\""+message_type+"\"><body>"+send_msg+"</body><thread>"+message_thread+messageID+"</thread></message>";
				//创建离线id类
				OfOfflineId offlineId = new OfOfflineId();
				//创建离线信息
				OfOffline off = new OfOffline();
				//设置信息id
				offlineId.setMessageId(messageID);
				//设置用户名
				offlineId.setUsername(account);
				//设置主键
				off.setId(offlineId);
				//设置创建时间
				off.setCreationDate(String.valueOf(System.currentTimeMillis()));
				//设置信息长度
				off.setMessageSize(stanza.length());
				//设置信息
				off.setStanza(stanza);
				//保存到数据库
				commonDao.saveTable(off);
			}catch (Exception e) {
			}
			
//		}
	}
	
	public Object findBySqlGetColumn(String tableName,String columns,String subwhere){
		List list  = commonDao.findByHqlGetColumnPage(OfOffline.class, subwhere, "", columns, 1, 1);
		if(list.isEmpty() || list.size()==0){
			return null;
		}
		return list.get(0);
	}
}
