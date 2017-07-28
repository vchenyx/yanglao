package com.jinkun.main.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.service.CommonService;
import com.common.util.MD5;
import com.common.util.RandomUtil;
import com.common.util.global.ConfigureFile;
import com.common.util.global.ResponseBean;
import com.common.util.imgs.ImageUtils;
import com.jinkun.main.beans.FWProvider;
import com.jinkun.main.beans.MGuardianInfo;
import com.jinkun.main.beans.MNurseInfo;
import com.jinkun.main.beans.MOldPeopleInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.service.RegisterService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	@Autowired
	private CommonService commonService;
	/**
	 * 注册跳转页面
	 * @param flag
	 * @return
	 */
	@RequestMapping("toRegister.do")
	public ModelAndView toRegister(Integer flag) {
		ModelAndView mav = new ModelAndView();
		if (flag == 0) {
			mav.setViewName("register/oldRegister");
		}
		if (flag == 1) {
			mav.setViewName("register/guardianRegister");
		}
		if (flag == 2) {
			mav.setViewName("register/nurseRegister");
		}
		if (flag == 5) {
			mav.setViewName("register/pensionRegister");
		}
		mav.addObject("flag", flag);
		return mav;
	}
	
	/**
	 * 验证手机号是否可以注册
	 * @param phone
	 * @return
	 */
	@RequestMapping("/phoneIsRegister.do")
	@ResponseBody
	public ResponseBean phoneIsRegister(String phone) {
		boolean notExist = registerService.isNotExist(phone);
		if (notExist) {
			return new ResponseBean("1", "手机号可以注册！");
		}
		return new ResponseBean("0", "该手机号已被注册，请更换手机号！");
	}
	
	/**
	 * 机构注册时上传资质照片
	 * @param file
	 * @param session
	 * @return
	 */
	@RequestMapping("uploadFile.do")
	@ResponseBody
	public ResponseBean uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) {
		ResponseBean rb = new ResponseBean();
		String realPath = session.getServletContext().getRealPath("/certificates/");
		String sourceName = file.getOriginalFilename();
		String suffix = sourceName.substring(sourceName.lastIndexOf("."));
		String fileName = System.currentTimeMillis() + RandomUtil.getRandom5();
		File targetFile = new File(realPath, fileName + suffix);
		Map<String, String> map = new HashMap<String, String>();
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			String srsPath = realPath + "\\" + fileName + suffix;
			System.out.println(srsPath);
			ImageUtils.cut5(srsPath, srsPath);
			rb.setData("certificates/" + fileName + suffix);
			rb.setMsg("上传成功！");
			rb.setCode("1");
		} catch (IllegalStateException e) {
			rb.setMsg("上传失败！");
			rb.setCode("0");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rb;
	}
	
	/**
	 * 用户注册
	 * @param jsonStr
	 * @param flag
	 * @param pwd
	 * @return
	 */
	@RequestMapping("userRegisiter.do")
	@ResponseBody
	public String userRegisiter(String jsonStr, Integer flag, String pwd) {
		MUserInfo userInfo = new MUserInfo();
		userInfo.setCreateTime(new Date());
		userInfo.setPwd(MD5.md5Encode(ConfigureFile.default_pwd));
		userInfo.setUserType(flag);
		if (flag == 0) {
			MOldPeopleInfo oldPeople = (MOldPeopleInfo) JSONObject.toBean(JSONObject.fromObject(jsonStr), MOldPeopleInfo.class);
			if (registerService.isNotExist(oldPeople.getPhone())) {
				userInfo.setAccount(oldPeople.getPhone());
				userInfo = registerService.saveUserInfo(userInfo);
				oldPeople.setUserId(userInfo.getId());
				Integer id = registerService.saveOldPeople(oldPeople);
			}
		}
		if (flag == 1) {
			MGuardianInfo giuardian = (MGuardianInfo) JSONObject.toBean(JSONObject.fromObject(jsonStr), MGuardianInfo.class);
			if (registerService.isNotExist(giuardian.getPhone())) {
				userInfo.setAccount(giuardian.getPhone());
				userInfo = registerService.saveUserInfo(userInfo);
				giuardian.setUserId(userInfo.getId());
				Integer id = registerService.saveGuardian(giuardian);
			}
		}
		if (flag == 2) {
			MNurseInfo nurse = (MNurseInfo) JSONObject.toBean(JSONObject.fromObject(jsonStr), MNurseInfo.class);
			if (registerService.isNotExist(nurse.getPhone())) {
				userInfo.setAccount(nurse.getPhone());
				userInfo = registerService.saveUserInfo(userInfo);
				nurse.setUserId(userInfo.getId());
				nurse.setCreateTime(new Date());
				Integer id = registerService.saveNurse(nurse);
			}
		}
		if (flag == 5) {
			MPensionStationInfo pensionStation = (MPensionStationInfo) JSONObject.toBean(JSONObject.fromObject(jsonStr), MPensionStationInfo.class);
			pensionStation.setState(1);
			pensionStation.setRequestTime(new Date().getTime());
			Integer id = registerService.savePensionStation(pensionStation);
		}
		return "{\"rtn\": 1}";
	
	}
}
