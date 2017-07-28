package com.jinkun.main.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.service.CommonService;
import com.common.service.RedisService;
import com.common.util.JSONUtil;
import com.common.util.global.DefalutRedisKey;
import com.jinkun.main.beans.MGuardianInfo;
import com.jinkun.main.beans.MNurseInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.beans.PensionPeopleInfo;
import com.jinkun.main.form.LoginUser;
import com.jinkun.main.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 跳转到登录页
	 * @param flag
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public ModelAndView toLogin(Integer flag) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login");
		mav.addObject("flag", flag);
		return mav;
	}
	
	/**
	 * 用户登录
	 * @param userInfo
	 * @param session
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Map<String, Object> login(MUserInfo userInfo, HttpSession session) {
		
		Map<String, Object> map = loginService.login(userInfo);
		String account = userInfo.getAccount();
		String flag = map.get("flag").toString();
		if ("5".equals(flag)) {
			Integer userType = Integer.parseInt(map.get("userType").toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MUserInfo sysUser = (MUserInfo) map.get("user");
			String id = sysUser.getId();
			LoginUser loginUser = new LoginUser();
			loginUser.setLoginTime(sdf.format(new Date()));
			loginUser.setUsername(sysUser.getAccount());
			loginUser.setUserType(userType);
			if ("admin".equals(account)) {
				loginUser.setUserType(9);
				session.setAttribute("userType", 9);
				session.setAttribute("user.name", "admin");
			} else {
				session.setAttribute("userType", userType);
				if (userType == 0) {//老人
					MOldPeopleInfo oldPeople = (MOldPeopleInfo) commonService.findByHql(MOldPeopleInfo.class, " userId = '" + id + "'", "").get(0);
					loginUser.setId(oldPeople.getId());
					loginUser.setContantName(oldPeople.getName());
					session.setAttribute("user", oldPeople);
				}
				if (userType == 1) {//监护人
					/*MGuardianInfo guardian = (MGuardianInfo) commonService.findByHql(MGuardianInfo.class, " userId = '" + id + "'", "").get(0);
					loginUser.setId(guardian.getId());
					loginUser.setContantName(guardian.getName());
					session.setAttribute("user", guardian);*/
				}
				if (userType == 2) {//护工
					MNurseInfo nurse = (MNurseInfo) commonService.findByHql(MNurseInfo.class, " userId = '" + id + "'", "").get(0);
					loginUser.setId(nurse.getId());
					loginUser.setContantName(nurse.getName());
					session.setAttribute("user", nurse);
				}
				if (userType == 5 || userType == 51 || userType == 52) {//机构、站点、客服部
					MPensionStationInfo pensionStation = (MPensionStationInfo) commonService.findByHql(MPensionStationInfo.class, " userId = '" + id + "'", "").get(0);
					loginUser.setId(pensionStation.getId());
					loginUser.setContantName(pensionStation.getName());
					session.setAttribute("user", pensionStation);
				}
				if (userType == 510 || userType == 520) { // 站点， 客服部
					PensionPeopleInfo pp = (PensionPeopleInfo) commonService.findByHql(PensionPeopleInfo.class, " userId = '" + id + "'", "").get(0);
					loginUser.setId(pp.getId());
					loginUser.setContantName(pp.getName());
					session.setAttribute("user", pp);
				}
			}
			String json = JSONUtil.beanToStr(loginUser);
			redisService.addToHash(DefalutRedisKey.loginKey, session.getId(), json);
		}
		return map;
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		redisService.delToHash(DefalutRedisKey.loginKey, session.getId());
		return "/index/index";
	}
}
