package com.jinkun.main.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 护工信息表
 * @author adminx
 *
 */
@Entity
@Table(name="m_nurse_info")
@DynamicUpdate(true)
public class MNurseInfo {

	private Integer id;
	private String userId;
	private String name;
	private Integer sex;
	private Integer age;
	private String phone;
	private String address;
	private String idCard;
	private String headImg;
	private Integer pensionStationId;
	private Integer eduBackground;
	private Date createTime;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Integer getPensionStationId() {
		return pensionStationId;
	}
	public void setPensionStationId(Integer pensionStationId) {
		this.pensionStationId = pensionStationId;
	}
	public Integer getEduBackground() {
		return eduBackground;
	}
	public void setEduBackground(Integer eduBackground) {
		this.eduBackground = eduBackground;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
