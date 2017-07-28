/*package com.jinkun.main.phone;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.service.RedisService;
import com.common.util.JSONUtil;
import com.common.util.MD5;
import com.common.util.global.DefalutRedisKey;
import com.common.util.global.GlobalMap;
import com.common.util.map.CoordinateConversion;
import com.common.util.map.LatLngPoint;
import com.jinkun.main.beans.AAlarmInfo;
import com.jinkun.main.beans.LLocationLastInfo;
import com.jinkun.main.beans.LLocationTrackInfo;
import com.jinkun.main.beans.MGuardianInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.form.PeopleInfoBean;
import com.jinkun.main.service.AlarmService;
import com.jinkun.main.service.LocationService;
import com.jinkun.main.service.LoginService;
import com.jinkun.main.service.RegisterService;
import com.openfire.util.PropertyUtil;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

@Controller
@RequestMapping("/phone")
public class PhoenController {

	@Autowired
	private CommonService commonService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private AlarmService alarmService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		//获取用户名
		String account = request.getParameter("account");
		//获取密码
		String password = request.getParameter("pwd");
		MUserInfo user = new MUserInfo();
		user.setAccount(account);
		user.setPwd(password);
		//验证用户名和密码
		JSONObject json = new JSONObject();
		Map<String, Object> map = loginService.login(user);
		String flag = map.get("flag").toString();
		if (!"5".equals(flag)) {//有帐号
			json.put("state", 0);//用户或密码错误
		} else {
			MUserInfo sysUser = (MUserInfo) map.get("user");
			Integer userType = sysUser.getUserType();
			//查找openfire用户
			List<OfUser> list = commonService.findByHql(OfUser.class, " username = '" + account + "' ", "");
			if(list.isEmpty()){//如果查不出来,注册新用户
				registerService.saveOpenFireUser(sysUser);
			}else{//更新openfire用户
				registerService.updateOpenFireUser(sysUser);
			}
			json.put("state", 1);//登陆成功，openfire已注册，直接登陆
			//openfire服务器ip
			json.put("serverIP", PropertyUtil.server_ip);
			//获取用户信息
			PeopleInfoBean pib = loginService.getPeopleInfo(sysUser);
			String token = MD5.md5Encode("phone" + account);
			json.put("peopleInfo", pib);
			json.put("token", token);
			redisService.addToHash(DefalutRedisKey.phoneUserKey, token, JSONUtils.valueToString(pib));
		}
		//返回手机信息
		return json.toString();
	}
	
	@RequestMapping("/uploadLocation.do")
	@ResponseBody
	public String uploadLocation(HttpServletRequest request, HttpServletResponse response) {
		try {
			//获取用户id
			String userId = request.getParameter("userId");
			//设备id
			String deviceId = request.getParameter("deviceId");
			//获取地图纬度
			String latitudeMap = request.getParameter("latitudeMap");
			//获取地图经度
			String lontitudeMap = request.getParameter("lontitudeMap");
			//获取地图纬度
			String latitudeGps = request.getParameter("latitudeGps");
			//获取地图经度
			String lontitudeGps = request.getParameter("lontitudeGps");
			//获取网络类型
			String netType = request.getParameter("netType");
			//获取定位类型：0普通定位，1报警, 2护工
			Integer locType = Integer.parseInt(request.getParameter("locType"));
			//把百度定位信息转换为谷歌
			LatLngPoint point = CoordinateConversion.bd_google_encrypt(Double.parseDouble(latitudeMap), Double.parseDouble(lontitudeMap));
			//获取定位时间：计算到秒
			LLocationLastInfo lastLoca = new LLocationLastInfo();
			LLocationTrackInfo trackLoca = new LLocationTrackInfo();
			lastLoca.setUserId(userId);
			lastLoca.setDeviceId(deviceId);
			lastLoca.setPosiType(locType);
			lastLoca.setPosiTime(new Date().getTime());
			lastLoca.setNetType(netType);
			lastLoca.setLatGps(Double.parseDouble(latitudeGps));
			lastLoca.setLonGps(Double.parseDouble(lontitudeGps));
			lastLoca.setLatGG(point.getLat());
			lastLoca.setLonGG(point.getLng());
			trackLoca.setUserId(userId);
			trackLoca.setDeviceId(deviceId);
			trackLoca.setPosiType(locType);
			trackLoca.setNetType(netType);
			trackLoca.setPosiTime(new Date().getTime());
			trackLoca.setLatGps(Double.parseDouble(latitudeGps));
			trackLoca.setLonGps(Double.parseDouble(lontitudeGps));
			trackLoca.setLatGG(point.getLat());
			trackLoca.setLonGG(point.getLng());
//			LLocationLastInfo sysLast = (LLocationLastInfo) commonService.findByStringId(LLocationLastInfo.class, lastLoca.getUserId());
			redisService.addToHash(DefalutRedisKey.peoplePosiKey, userId, JSONUtil.beanToStr(lastLoca));
			if (sysLast == null) {
				locationService.saveLocationLast(lastLoca);
			} else {
				locationService.updateLocationLast(lastLoca);
			}
			locationService.saveLocationTrack(trackLoca);
			boolean b = GlobalMap.alarmMap.containsKey(userId);
			if (locType == 1) {//老人求救
				if (!b) {//告警map中没有该求救时
					AAlarmInfo alarmInfo = new AAlarmInfo();
					MOldPeopleInfo oldInfo = (MOldPeopleInfo) commonService.findByHqlUnique(MOldPeopleInfo.class, " userId = '" + userId + "'");
					if (oldInfo != null) {
						alarmInfo.setOldName(oldInfo.getName());
						alarmInfo.setOldPhone(oldInfo.getPhone());
						String guardianPhone = oldInfo.getGuardianPhone();
						if (guardianPhone != null) {
							MGuardianInfo guardian = (MGuardianInfo) commonService.findByHqlUnique(MGuardianInfo.class, " phone = '" + guardianPhone + "' ");
							if (guardian != null) {
								alarmInfo.setGuradianName(guardian.getName());
								alarmInfo.setGuradianPhone(guardian.getPhone());
								alarmInfo.setBelongPension(oldInfo.getPensionStationId());
							}
						}
					}
					alarmInfo.setUserId(userId);
					alarmInfo.setDeviceNo(deviceId);
					alarmInfo.setUploadTime(new Date());
					alarmInfo.setLat(point.getLat());
					alarmInfo.setLng(point.getLng());
					alarmInfo.setLatGps(Double.parseDouble(latitudeGps));
					alarmInfo.setLngGps(Double.parseDouble(lontitudeGps));
					Long id = alarmService.saveAlarmInfo(alarmInfo);
					GlobalMap.alarmMap.put(userId, id);//放入该条求救
				}
			} else {//正常定位
				if (b) {//删除缓存中求救信息
					GlobalMap.alarmMap.remove(userId);
				}
			}
			return "{\"state\": 1}";
		} catch (Exception e) {
			System.out.println(e);
			return "{\"state\": 0}";
		}
	}
	
	@RequestMapping("/nurseHandleAlarm.do")
	@ResponseBody
	public String nurseHandleAlarm(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String alarmId = request.getParameter("alarmId");
		String isHandle = request.getParameter("isHandle");
		long longAlarmId = Long.parseLong(alarmId);
		int intHandle = Integer.parseInt(isHandle);
		List<AAlarmInfo> list = commonService.findByHql(AAlarmInfo.class, " id = " + longAlarmId, "");
		if (!list.isEmpty()) {
			AAlarmInfo alarm = list.get(0);
			if (null == alarm.getHandleId()) {//如果未处理
				if (intHandle == 1) {//选择处理,可以处理,设置处理人未自己
					alarm.setHandleId(userId);
					alarmService.updateAlarmHandle(longAlarmId, userId);
				}
				return "{\"state\": 1}";
			} else {//如果已处理
				if (intHandle == 1) {//选择处理,提示已被处理
					return "{\"state\": 0}";
				} else {//选择不处理
					return "{\"state\": 1}";
				}
			}
		}
		return "{\"state\": 0}";
	}
	
}
*/