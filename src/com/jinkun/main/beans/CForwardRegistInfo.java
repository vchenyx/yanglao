package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 预约挂号表
 * @author Administrator
 *
 */
@Entity
@Table(name="c_forward_regist_info")
@DynamicUpdate(true)
public class CForwardRegistInfo {

	private Integer id; // 主键
	
	private Integer pensionId; // 机构ID
	
	private Long createTime; // 工单创建时间
	
	private String orderNo; // 工单编号
	
	private String handleDept; // 受理部门
	
	private String acceptPerson; // 接单人
	
	private Long acceptTime; // 接单时间
	
	private String linkName; // 下单人
	
	private String linkPhone; // 下单人电话
	
	private Integer subObject; // 0合作医院, 1其他医院, 2家庭医生
	
	private String medicalItems; // 医院明细
	
	private Long subTime; // 预约日期
	
	private String remarks; // 备注
	
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

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public Integer getSubObject() {
		return subObject;
	}

	public void setSubObject(Integer subObject) {
		this.subObject = subObject;
	}

	public String getMedicalItems() {
		return medicalItems;
	}

	public void setMedicalItems(String medicalItems) {
		this.medicalItems = medicalItems;
	}

	public Long getSubTime() {
		return subTime;
	}

	public void setSubTime(Long subTime) {
		this.subTime = subTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
