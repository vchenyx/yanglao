package com.jinkun.main.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
 
/**
 * 服务记录表
 * @author Administrator
 *
 */
public class POIServiceRecords {

	private String id;
	
	private String name;
	
	private String sex; //0男， 1女
	
	private String age;
	
	private String phone;
	
	private String community;  // 小区
	
	private String oldType; //0托底
	
	private String procuderName; //服务商名称 
	
	private String serviceAddress; // 服务地址
	
	private String serviceDate; //服务日期
	
	private String startEndTime; //服务起止时间
	
	private String serviceContent; //服务内容
	
	private String singlePrice; // 服务单价
	
	private String serviceUnit; // 时长/数量
	
	private String countPrice; // 服务总价
	
	private String serviceType; //服务方式
	
	private String oldComment; // 老人意见（服务反馈情况）
	
	private String remarks; // 备注
	
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getOldType() {
		return oldType;
	}

	public void setOldType(String oldType) {
		this.oldType = oldType;
	}

	public String getProcuderName() {
		return procuderName;
	}

	public void setProcuderName(String procuderName) {
		this.procuderName = procuderName;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getStartEndTime() {
		return startEndTime;
	}

	public void setStartEndTime(String startEndTime) {
		this.startEndTime = startEndTime;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(String singlePrice) {
		this.singlePrice = singlePrice;
	}

	public String getServiceUnit() {
		return serviceUnit;
	}

	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}

	public String getCountPrice() {
		return countPrice;
	}

	public void setCountPrice(String countPrice) {
		this.countPrice = countPrice;
	}

	public String getOldComment() {
		return oldComment;
	}

	public void setOldComment(String oldComment) {
		this.oldComment = oldComment;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


}
