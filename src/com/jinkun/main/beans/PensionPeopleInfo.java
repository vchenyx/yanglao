package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 养老机构信息
 * @author adminx
 *
 */
@Entity
@Table(name="f_pension_people_info")
@DynamicUpdate(true)
public class PensionPeopleInfo {

	private Integer id;//主键
	private String name; //姓名
	private Integer sex;//性别
	private Integer age; //年龄
	private Integer peopleDeptId;//人员类型id
	private String peopleDeptName;//人员类型名称
	private String idCard;//身份证号
	private String cellphone; //手机号
	private String dutyMs; // 职务描述
	private Long createTime; //创建时间
	private Integer pensionId;//机构id
	private Integer stationId; // 站点人员是站点ID， 客服人员是客服ID
	private String imgUrl;//图片路径
	private String userId; //账户id
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Column(name ="sex")
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Column(name ="name",length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Column(name ="age")
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Column(name ="people_dept_id")
	public Integer getPeopleDeptId() {
		return peopleDeptId;
	}
	public void setPeopleDeptId(Integer peopleDeptId) {
		this.peopleDeptId = peopleDeptId;
	}
	
	@Column(name ="people_dept_name",length = 30)
	public String getPeopleDeptName() {
		return peopleDeptName;
	}
	public void setPeopleDeptName(String peopleDeptName) {
		this.peopleDeptName = peopleDeptName;
	}
	
	@Column(name ="id_card",length = 30)
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Column(name ="cellphone",length = 30)
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	@Column(name ="create_time")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name ="pension_id",length = 30)
	public Integer getPensionId() {
		return pensionId;
	}
	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}
	
	@Column(name ="duty_ms",length = 30)
	public String getDutyMs() {
		return dutyMs;
	}
	public void setDutyMs(String dutyMs) {
		this.dutyMs = dutyMs;
	}
	
	@Column(name ="imgUrl",length = 100)
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Column(name ="userId",length = 50)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	
	
	

	
}
