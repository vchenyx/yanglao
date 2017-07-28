package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 预定服务表
 * @author Administrator
 *
 */
@Entity
@Table(name="c_work_order_info")
@DynamicUpdate(true)
public class CWorkOrderInfo {

	private Integer id; // 主键
	
	private Integer pensionId;
	
	private Long createTime;
	
	private String orderNo; // 工单编号
	
	private String handleDept; // 受理部门
	
	private String acceptPerson; // 接单人
	
	private Long acceptTime; // 接单时间
	
	private String linkName; // 下单人
	
	private String linkPhone; // 下单人电话
	
	private Integer serviceType; // 0 服务商家, 1志愿者
	
	private String serviceClassify; // 服务大项
	
	private String serviceItem; // 服务项目
	
	private String serviceAddr; // 服务地址
	
	private String ownPhone; // 本机电话
	
	private Long serviceTime; // 服务时间
	
	private Integer isUrgency; // 是否紧急 1紧急 0一般
	
	private Integer payType; // 支付方式 0现金, 1余额
	
	private String remarks; // 工单备注
	
	private Integer state; // 工单状态 0初始录入 1已派单 2已接单 3已完成

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPensionId() {
		return pensionId;
	}

	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getHandleDept() {
		return handleDept;
	}

	public void setHandleDept(String handleDept) {
		this.handleDept = handleDept;
	}

	public String getAcceptPerson() {
		return acceptPerson;
	}

	public void setAcceptPerson(String acceptPerson) {
		this.acceptPerson = acceptPerson;
	}

	public Long getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Long acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceClassify() {
		return serviceClassify;
	}

	public void setServiceClassify(String serviceClassify) {
		this.serviceClassify = serviceClassify;
	}

	public String getServiceItem() {
		return serviceItem;
	}

	public void setServiceItem(String serviceItem) {
		this.serviceItem = serviceItem;
	}

	public String getServiceAddr() {
		return serviceAddr;
	}

	public void setServiceAddr(String serviceAddr) {
		this.serviceAddr = serviceAddr;
	}

	public String getOwnPhone() {
		return ownPhone;
	}

	public void setOwnPhone(String ownPhone) {
		this.ownPhone = ownPhone;
	}

	public Long getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Integer getIsUrgency() {
		return isUrgency;
	}

	public void setIsUrgency(Integer isUrgency) {
		this.isUrgency = isUrgency;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	
	
}
