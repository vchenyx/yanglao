package com.common.util.global;


import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.service.CommonService;
import com.common.service.RedisService;
import com.jinkun.main.service.LocationService;

/**
 * service
 * @author ZHANGBIN
 *
 */
public class BufferDataInit {

	public static CommonService commonService;
	public static LocationService locationService;
	public static RedisService redisService;
	

	/**
	 * 初始化service
	 * @param sce
	 */
	public static void initDatabaseService(ServletContextEvent sce) {
		
		WebApplicationContext ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
		commonService = (CommonService) ctx.getBean("commonService");
		locationService = (LocationService) ctx.getBean("locationService");
		redisService = (RedisService) ctx.getBean("redisService");
	
	}

}
