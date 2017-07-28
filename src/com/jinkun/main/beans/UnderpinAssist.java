package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 托底扶助表单
 * @author Administrator
 *
 */

@Entity
@Table(name="ass_underpin")
@DynamicUpdate(true)
public class UnderpinAssist {
	private Integer id;//主键
	private String name;//姓名
	private Integer sex;//性别 0男， 1女
	private String idCrad;//身份证号
	private Long stateBirthday;//出生日期
	private Integer age;//年龄
	private String phone;//电话
	private Integer managerClass;//老人类别   0托底， 1扶助
	private String communitys;//所属社区
	private String address;//居住地址
	private String urgencyName;//紧急联系人
	private String urgencyPhone;//紧急联系人电话
	private String maritalStatus;//婚姻状况/居住情况
	private String income;//收入/资金困难
	private Long createTime; // 创建时间
	private Integer pensionId; // 所属机构
	private Integer stationId; // 所属站点
	private Integer oldFildId; //老人档案Id
	
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getIdCrad() {
		return idCrad;
	}
	public void setIdCrad(String idCrad) {
		this.idCrad = idCrad;
	}
	public Long getStateBirthday() {
		return stateBirthday;
	}
	public void setStateBirthday(Long stateBirthday) {
		this.stateBirthday = stateBirthday;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getManagerClass() {
		return managerClass;
	}
	public void setManagerClass(Integer managerClass) {
		this.managerClass = managerClass;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUrgencyName() {
		return urgencyName;
	}
	public void setUrgencyName(String urgencyName) {
		this.urgencyName = urgencyName;
	}
	public String getUrgencyPhone() {
		return urgencyPhone;
	}
	public void setUrgencyPhone(String urgencyPhone) {
		this.urgencyPhone = urgencyPhone;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Integer getPensionId() {
		return pensionId;
	}
	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public Integer getOldFildId() {
		return oldFildId;
	}
	public void setOldFildId(Integer oldFildId) {
		this.oldFildId = oldFildId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCommunitys() {
		return communitys;
	}
	public void setCommunitys(String communitys) {
		this.communitys = communitys;
	}
	
}
