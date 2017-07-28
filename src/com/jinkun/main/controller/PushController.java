package com.jinkun.main.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.service.RedisService;
import com.common.util.global.DefalutRedisKey;
import com.jinkun.main.beans.AAlarmInfo;
import com.jinkun.main.beans.MNurseInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.form.LoginUser;
import com.jinkun.main.form.MessageBean;
import com.jinkun.main.service.PushService;
import com.mina.ServerMessageUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/informs")
public class PushController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/getAllAlarms.do")
	@ResponseBody
	public List<AAlarmInfo> getAllAlarms(HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		List<AAlarmInfo> list = commonService.findByHql(AAlarmInfo.class, " belongPension = " + pension.getId(), "");
		return list;
	}
	
	@RequestMapping("/getWaitAlarms.do")
	@ResponseBody
	public List<AAlarmInfo> getWaitAlarms(HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		List<AAlarmInfo> list = commonService.findByHql(AAlarmInfo.class, " belongPension = " + pension.getId() + " and handleTime is null", "");
		return list;
	}
	
	@RequestMapping("/getHandledAlarms.do")
	@ResponseBody
	public List<AAlarmInfo> getHandledAlarms(HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		List<AAlarmInfo> list = commonService.findByHql(AAlarmInfo.class, " belongPension = " + pension.getId() + " and handleTime is not null", "");
		return list;
	}
	/**
	 * 获取所有护工信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllNurse.do")
	@ResponseBody
	public List<MNurseInfo> getAllNurse(HttpSession session){
		String id = session.getId();
		String string = redisService.getHash(DefalutRedisKey.loginKey, id);
		LoginUser loginUser = (LoginUser) JSONObject.toBean(JSONObject.fromObject(string), LoginUser.class);
		List<MNurseInfo> nurseList = commonService.findByHql(MNurseInfo.class, " pensionStationId = '" + loginUser.getId() + "' ", "");
		return nurseList;
	}
	
	@RequestMapping("getOnlineNurse.do")
	@ResponseBody
	public List<MNurseInfo> getOnlineNurse(HttpSession session) {
		String id = session.getId();
		String string = redisService.getHash(DefalutRedisKey.loginKey, id);
		LoginUser loginUser = (LoginUser) JSONObject.toBean(JSONObject.fromObject(string), LoginUser.class);
		List<MNurseInfo> nurseList = commonService.findByHql(MNurseInfo.class, " pensionStationId = '" + loginUser.getId() + "' ", "");
		List<MNurseInfo> onLineList = new ArrayList<MNurseInfo>();
		for (MNurseInfo nurse : nurseList) {
			String userId = nurse.getUserId();
			Long mobileSessionId = ServerMessageUtil.mobileSessionIdMap.get(userId);
			if (mobileSessionId != null) {
				boolean b = ServerMessageUtil.mobileSessionMap.containsKey(mobileSessionId);
				if (b) {
					onLineList.add(nurse);
				}
			}
		}
		return onLineList;
	}
	
	@RequestMapping("/pushMsg.do")
	@ResponseBody
	public String pushMsg(MessageBean bean, String reciveId, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long mobileSessionId = ServerMessageUtil.mobileSessionIdMap.get(reciveId);
		IoSession ioSession = ServerMessageUtil.mobileSessionMap.get(mobileSessionId);
		if (null != ioSession) {
			bean.setSendTime(sdf.format(new Date()));
			bean.setSendUser(pension.getName());
			String string = JSONObject.fromObject(bean).toString();
			ioSession.write(string);
			return "{\"flag\": 1}";
		} else {//用户已下线
			return "{\"flag\": 0}";
		}
	}
	
}
