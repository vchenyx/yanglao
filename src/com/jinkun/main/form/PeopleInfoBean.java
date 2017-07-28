package com.jinkun.main.form;

public class PeopleInfoBean {

	private String userId;//用户id
	private String account;//用户名
	private String name;//姓名
	private Integer sex;//性别
	private Integer age;//年龄
	private String headImg;//头像
	private Integer pensionId;
	private String areaId;//所属医疗机构
	private Integer locType;//定位类型：0定位，1报警，-1解除报警
	private Integer userType;

	public PeopleInfoBean(){
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Integer getLocType() {
		return locType;
	}

	public void setLocType(Integer locType) {
		this.locType = locType;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getPensionId() {
		return pensionId;
	}

	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}

	
}
