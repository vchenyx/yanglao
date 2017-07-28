package com.jinkun.init;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.util.MD5;
import com.common.util.global.ConfigureFile;
import com.common.util.global.Log;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.service.RegisterService;

import net.sf.json.JSONObject;


/**
 * 系统ADMIN初始化
 * @author ZHANGBIN
 *
 */

@Component
public class AdminInit implements InitializingBean{
	
	@Autowired
	private RegisterService registerService;
	
	@Override
	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		adminUser();
	}
	
	//初始化admin用户
	@SuppressWarnings("static-access")
	private void adminUser(){
		try{
			//添加admin账户
			boolean flag = registerService.isNotExist(ConfigureFile.user_manager);
			if(flag){
				MUserInfo userInfo = new MUserInfo();
				userInfo.setAccount(ConfigureFile.user_manager);
				userInfo.setPwd(MD5.md5Encode(ConfigureFile.default_pwd));
				userInfo.setUserType(ConfigureFile.manager_type);
				userInfo.setCreateTime(new Date());
				MUserInfo rtnUser = registerService.saveUserInfo(userInfo);
				JSONObject json = new JSONObject().fromObject(userInfo);
				String hashvalue = json.toString();
				Log.logger.info("System Admin Init Success!");
			}
			boolean flag1 = registerService.isNotExist(ConfigureFile.xiaomianao);
			if (flag1) {
				MUserInfo userInfo = new MUserInfo();
				userInfo.setAccount(ConfigureFile.xiaomianao);
				userInfo.setPwd(MD5.md5Encode(ConfigureFile.default_pwd));
				userInfo.setUserType(5);
				userInfo.setCreateTime(new Date());
				MUserInfo rtnUser = registerService.saveUserInfo(userInfo);
				MPensionStationInfo pension = new MPensionStationInfo();
				pension.setName("小棉袄");
				pension.setAddress("东城区龙潭街道");
				pension.setAreaId(101101);
				pension.setEmail("xiaomianao@163.com");
				pension.setLinkName("于海洪");
				pension.setLinkPhone("15888888888");
				pension.setPid(0);
				pension.setStationType(5);
				pension.setUserId(rtnUser.getId());
				registerService.savePensionStation(pension);
				Log.logger.info("Pension Admin Init Success!");
			}
			
		}catch (Exception e) {
			Log.logger.info("System Admin Init Fail!");	
		}
	}
	

}
