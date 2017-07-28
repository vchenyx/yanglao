package com.phone.model;

/**
 * 
 * 人员信息的实体类
 * @author zhangbin
 *
 */
public class PeopleInfoBean {
	
	private String userid;//员工id
	private String username;//员工用户名
	private String peopleid;//员工工号
	private String peoplename;//员工姓名
	private String peoplesex;//员工性别
	private String headimg;//员工头像
	private String deptid;//员工所属部门id
	private String deptname;//员工所属部门名字
	private Integer locType;//定位类型：0定位，1报警，-1解除报警

	public PeopleInfoBean(){
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

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

	public String getPeoplesex() {
		return peoplesex;
	}

	public void setPeoplesex(String peoplesex) {
		this.peoplesex = peoplesex;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public Integer getLocType() {
		return locType;
	}

	public void setLocType(Integer locType) {
		this.locType = locType;
	}


}
