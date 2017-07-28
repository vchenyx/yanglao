/*package com.jinkun.main.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.HttpClient;
import com.common.service.RedisService;
import com.common.util.DateUtil;
import com.common.util.JSONUtil;
import com.common.util.global.ConfigureFile;
import com.common.util.global.DefalutRedisKey;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/darma")
public class DarmaController {

	@Autowired
	private HttpClient httpClient;
	
	@Autowired
	private RedisService redisService;
	
	*//**
	 * 大耳马登录
	 * @param userName
	 * @param password
	 * @return
	 *//*
	@RequestMapping("/login.do")
	@ResponseBody
	public String login(String userName, String password) {
		String url = ConfigureFile.darma_url + "/authorize";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", userName);
		map.put("password", password);
		map.put("grant_type", "password");
		map.put("scope", "default");
		String doPost = httpClient.doPost(url, map, null);
		if (doPost.contains("authorize")) {
			JSONObject fromObject = JSONObject.fromObject(doPost);
			JSONObject obj = (JSONObject) fromObject.get("authorize");
			redisService.addToHash(DefalutRedisKey.darmaKey, userName, fromObject.get("authorize").toString());
			String user_name = obj.get("user_name").toString();
			String authorize_time = obj.get("authorize_time").toString();
			String expires_in = obj.get("expires_in").toString();
			String user_id = obj.get("user_id").toString();
			String access_token = obj.get("access_token").toString();
		}
		return doPost;
	}
	
	*//**
	 * 获取设备最新30秒的数据
	 * @param deviceNos
	 * @return
	 *//*
	@RequestMapping("/getDeviceNew30s.do")
	@ResponseBody
	public String getDeviceNew30s(String deviceNos) {
		String url = ConfigureFile.darma_url + "/healthdata/device?con=" 
									+ ConfigureFile.darma_con;
		String token = getToken("");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryType", "queryLastData");
		params.put("deviceNos", deviceNos);
		params.put("con", ConfigureFile.darma_con);
		return httpClient.doPost(url, params, token);
	}
	
	*//**
	 * 获取用户行为数据
	 * @param type
	 * @param deviceNo
	 * @param startTime
	 * @param endTime
	 * @return
	 *//*
	@RequestMapping("/manBehaveData.do")
	@ResponseBody
	public String manBehaveData(Integer type, String deviceNo, String startTime, String endTime) {
		String url = ConfigureFile.darma_url + "/manBehaveData/" 
									+ type + "/" + deviceNo;
		long startLong = new Date().getTime() / 1000 - 86400;
		long endLong = new Date().getTime() / 1000;
		url += "?start_time=" + startLong + "&end_time=" + endLong;
		String token = getToken("");
		return httpClient.doGet(url, token);
	}
	
	*//**
	 * 获取睡眠状态
	 * @return
	 *//*
	@RequestMapping("/getSleepState")
	@ResponseBody
	public String getSleepState(String deviceNo, String lastDate) {
		String url = ConfigureFile.darma_url + "/sleepstate/" 
									+ deviceNo + "/" 
									+ DateUtil.strToDateDefault(lastDate).getTime();
		String token = getToken("");
		return httpClient.doGet(url, token);
	}
	
	*//**
	 * 通过设备号获取告警信息
	 * @param deviceNo
	 * @param starTime
	 * @param endTime
	 * @param pageSize
	 * @param pageCount
	 * @return
	 *//*
	@RequestMapping("/getWarningsByDeviceNo.do")
	@ResponseBody
	public String getWarningsByDeviceNo(String deviceNo, String starTime, 
								String endTime, Integer pageSize, Integer pageCount) {
		String url = ConfigureFile.darma_url + "/warnings/device"; 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("devices", deviceNo);
		params.put("starTime", new Date().getTime() - 86400000);
		params.put("endTime", new Date().getTime());
		params.put("pageSize", pageSize);
		params.put("pageCount", pageCount);
		String token = getToken("");
		return httpClient.doPost(url, params, token);
	}
	
	*//**
	 * 通过告警类型获取告警信息
	 * @param command
	 * @param starTime
	 * @param endTime
	 * @param pageSize
	 * @param pageCount
	 * @return
	 *//*
	@RequestMapping("/getWarningsByType.do")
	@ResponseBody
	public String getWarningsByType(String command, String starTime, 
			String endTime, Integer pageSize, Integer pageCount) {
		String url = ConfigureFile.darma_url + "/warnings/command"; 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("command", command);
		params.put("starTime", new Date().getTime() - 86400000);
		params.put("endTime", new Date().getTime());
		params.put("pageSize", pageSize);
		params.put("pageCount", pageCount);
		String token = getToken("");
		return httpClient.doPost(url, params, token);
	}
	
	*//**
	 * 查询设备状态
	 * @param deviceNo
	 * @return
	 *//*
	@RequestMapping("/getDeviceStatus.do")
	@ResponseBody
	public String getDeviceStatus(String deviceNo) {
		String url = ConfigureFile.darma_url + "/status/" + deviceNo; 
		String token = getToken("");
		return httpClient.doGet(url, token);
	}
	
	@RequestMapping("/push.do")
	@ResponseBody
	public String push(String command, String mattressId, Integer type, Long currentTime) {
		
		return "{\"\": }";
	}
	
	*//**
	 * 获取token
	 * @param userName
	 * @return
	 *//*
	public String getToken(String userName) {
		Boolean b = redisService.isHasKey(DefalutRedisKey.darmaKey, "jinkun");
		if (!b) {
			login("jinkun", "123456");
		}
		String hash = redisService.getHash(DefalutRedisKey.darmaKey, "jinkun");
		JSONObject jsonObject = JSONObject.fromObject(hash);
		String access_token = jsonObject.get("access_token").toString();
		return access_token;
	}
}
*/