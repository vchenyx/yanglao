package com.jinkun.main.form;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录用户信息
 * @author adminx
 *
 */
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 6896513430738782912L;
	private Integer id;           // 用户id
	private String username;      // 当前用户名
	private String contantName;	  // 当前用户真实姓名
	private Integer userType;	  // 当前用户类型  同user表用户类型
	private String loginTime;     // 登录时间
	private Integer flag;		  // 状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContantName() {
		return contantName;
	}
	public void setContantName(String contantName) {
		this.contantName = contantName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}
