package com.jinkun.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.service.RedisService;
import com.common.util.global.ConfigureFile;
import com.common.util.global.DefalutRedisKey;
import com.jinkun.main.form.LoginUser;

import net.sf.json.JSONObject;

/**
 * spring 拦截器
 * @author ZHANGBIN
 *
 */

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private RedisService redisService;
	/**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */    
	@Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {   
    	HttpSession session = request.getSession();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	boolean flag = redisService.isHasKey(DefalutRedisKey.loginKey, session.getId());
    	if(!flag){
    		request.getRequestDispatcher("/index/index.do").forward(request, response); 
    		return false;
    	}
    	Map<String,String> map = redisService.getHash(DefalutRedisKey.loginKey);
    	LoginUser login = (LoginUser)JSONObject.toBean(JSONObject.fromObject(map.get(session.getId())), LoginUser.class);
    	if(login.getLoginTime() != null
				&& new Date().getTime() - sdf.parse(login.getLoginTime()).getTime() > ConfigureFile.session_time_second * 60 * 1000){
			request.getRequestDispatcher("/WEB-INF/error/session.jsp").forward(request, response);  
			return false;  
		}
    	return true;
    }    
    
    /** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {     
    }    
    
    /**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     *   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
    }    
  
}
