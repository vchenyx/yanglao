package com.jinkun.main.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 老人定位信息
 * @author adminx
 *
 */
public class PosiShowInfo {

	private String id;
	private String name;
	private String guardianName;
	private String guardianPhone;
	private String phone;
	private Integer age;
	private Integer sex;
	private String address;
	private String headImg;
	private Double latGps;
	private Double lonGps;
	private Double latGG;
	private Double lonGG;
	private Date posiTime;
	private String pensionName;
	private Integer posiType;
	private String netType;
	private String idCard;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Double getLatGps() {
		return latGps;
	}
	public void setLatGps(Double latGps) {
		this.latGps = latGps;
	}
	public Double getLonGps() {
		return lonGps;
	}
	public void setLonGps(Double lonGps) {
		this.lonGps = lonGps;
	}
	public Double getLatGG() {
		return latGG;
	}
	public void setLatGG(Double latGG) {
		this.latGG = latGG;
	}
	public Double getLonGG() {
		return lonGG;
	}
	public void setLonGG(Double lonGG) {
		this.lonGG = lonGG;
	}
	public Date getPosiTime() {
		return posiTime;
	}
	public void setPosiTime(Date posiTime) {
		this.posiTime = posiTime;
	}
	public String getPensionName() {
		return pensionName;
	}
	public void setPensionName(String pensionName) {
		this.pensionName = pensionName;
	}
	public Integer getPosiType() {
		return posiType;
	}
	public void setPosiType(Integer posiType) {
		this.posiType = posiType;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getGuardianPhone() {
		return guardianPhone;
	}
	public void setGuardianPhone(String guardianPhone) {
		this.guardianPhone = guardianPhone;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
}
