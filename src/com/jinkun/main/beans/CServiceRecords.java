package com.jinkun.main.beans;

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
@Entity
@Table(name="c_service_records")
@DynamicUpdate(true)
public class CServiceRecords {

	private Integer id;
	private String name;
	private Integer sex; //0男， 1女
	private Integer age;
	private String phone;
	private String community;  // 小区
	private String oldType; //0托底
	private String procuderName; //服务商名称 
	private Integer providerId;
	private String serviceAddress; // 服务地址
	private Long serviceDate; //服务日期
	private String startEndTime; //服务起止时间
	private String serviceContent; //服务内容
	private Float singlePrice; // 服务单价  (1)
	private Integer serviceUnit; // 时长/数量 (1)
	private Float countPrice; // 服务总价 (1)
	private String serviceType; //服务方式    0助餐， 1助医， 2助洁， 3助浴
	private String oldComment; // 老人意见（服务反馈情况）
	private String remarks; // 备注
	private Long createTime;
	private Integer pensionId; // 机构id
	private Integer stationId;  // 站点id
	private Integer orderId; // 工单
	private Integer oldId; //老人id
	
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

	public Long getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Long serviceDate) {
		this.serviceDate = serviceDate;
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

	public Float getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(Float singlePrice) {
		this.singlePrice = singlePrice;
	}

	public Integer getServiceUnit() {
		return serviceUnit;
	}

	public void setServiceUnit(Integer serviceUnit) {
		this.serviceUnit = serviceUnit;
	}

	public Float getCountPrice() {
		return countPrice;
	}

	public void setCountPrice(Float countPrice) {
		this.countPrice = countPrice;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	
	
	
	
	
}
