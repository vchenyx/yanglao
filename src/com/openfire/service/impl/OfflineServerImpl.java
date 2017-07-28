	package com.openfire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.openfire.beans.OfOffline;
import com.openfire.beans.OfOfflineId;
import com.openfire.service.OfflineServer;
import com.openfire.util.OfflineUtil;

@Service
public class OfflineServerImpl implements OfflineServer{
	@Autowired
	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	/*
	 * 添加离线信息(non-Javadoc)
	 * @see com.openfire.service.OfflineServer#saveOffline(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveOffline(String jid,String username, String message) {
//		synchronized(this){
			try {
				//休眠10毫秒
//				Thread.sleep(10);
				long messageID = OfflineUtil.messageID;
				String  message_id = OfflineUtil.message_id;
				String  message_from = OfflineUtil.message_from;
				String  message_type = OfflineUtil.message_type;
				String  message_thread = OfflineUtil.message_thread;
				
				Object obj = findBySqlGetColumn("ofOffline","messageID"," order by messageID desc");
				if(obj != null){
					messageID = Long.valueOf(obj.toString());
				}
				if(messageID - OfflineUtil.messageID < 0){
					messageID = OfflineUtil.messageID;
				}
				OfflineUtil.messageID = messageID+1;
				//拼信息
				String stanza = "<message id=\""+message_id+messageID+"\" to=\""+username+"@"+jid+"\" from=\""+message_from+"@"+jid+"\" type=\""+message_type+"\"><body>"+message+"</body><thread>"+message_thread+messageID+"</thread></message>";
				//创建离线id类
				OfOfflineId offlineId = new OfOfflineId();
				//创建离线信息
				OfOffline off = new OfOffline();
				//设置信息id
				offlineId.setMessageId(messageID);
				//设置用户名
				offlineId.setUsername(username);
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
		List list  = commonDao.findBySqlGetColumnPage(tableName, columns, subwhere, "", 1, 1);
		if(list.isEmpty() || list.size()==0){
			return null;
		}
		return list.get(0);
	}

	/*
	 * 删除离线消息(non-Javadoc)
	 * @see com.openfire.service.OfflineServer#deleteOffline(java.lang.String)
	 */
	@Override
	public void deleteOffline(String username) {
		commonDao.deleteTableMoreData("ofOffline", "username='"+username+"'");
	}

	/*
	 * 按用户名查离线信息(non-Javadoc)
	 * @see com.openfire.service.OfflineServer#getOffline(java.lang.String)
	 */
	@Override
	public List findByUsername(String username) {
		return commonDao.findBySql("ofOffline", "username = '"+username+"'", "");
	}

}
