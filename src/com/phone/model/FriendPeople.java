package com.phone.model;

/**
 * 搜索好友
 * @author zhangbin
 *
 */
public class FriendPeople {
	
	private String username;//用户名
	private String peopleid;//工号
	private String peoplename;//姓名
	private String department;//部门
	private Integer state;//状态：-1：异常，0未添加，1已邀请，2需要同意，3已添加
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPeopleid() {
		return peopleid;
	}
	public void setPeopleid(String peopleid) {
		this.peopleid = peopleid;
	}
	public String getPeoplename() {
		return peoplename;
	}
	public void setPeoplename(String peoplename) {
		this.peoplename = peoplename;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}


}
