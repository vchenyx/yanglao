package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 供应商信息
 * @author Administrator
 *
 */
@Entity
@Table(name="u_supplier_info")
@DynamicUpdate(true)
public class MSupplierInfo {

	private Integer id; // 主键
	
	private Integer areaId; // 区域ID

	private String name; // 名称
	
	private String phone; // 电话
	
	private String address; // 地址
	
	private Double lonGG;
	
	private Double latGG;
	
	private String userId; // 用户ID
	
	private String linkName; // 联系人
	
	private String linkPhone; // 联系电话
	
	private String introduce; // 公司介绍
	
	private long creationTime; // 创建时间
	
	private long requestTime; // 申请时间
	
	private String email;

	private String certificate1; // 资质1
	
	private String certificate2; // 资质2
	
	private String certificate3; // 资质3

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "areaId")
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	@Column(name = "lonGG")
	public Double getLonGG() {
		return lonGG;
	}

	public void setLonGG(Double lonGG) {
		this.lonGG = lonGG;
	}

	@Column(name = "latGG")
	public Double getLatGG() {
		return latGG;
	}

	public void setLatGG(Double latGG) {
		this.latGG = latGG;
	}

	@Column(name = "userId", length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "linkName", length = 20)
	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	@Column(name = "linkPhone", length = 20)
	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	@Column(name = "introduce", length = 500)
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Column(name = "creationTime")
	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name = "requestTime")
	public long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "certificate1", length = 100)
	public String getCertificate1() {
		return certificate1;
	}

	public void setCertificate1(String certificate1) {
		this.certificate1 = certificate1;
	}

	@Column(name = "certificate2", length = 100)
	public String getCertificate2() {
		return certificate2;
	}

	public void setCertificate2(String certificate2) {
		this.certificate2 = certificate2;
	}

	@Column(name = "certificate3", length = 100)
	public String getCertificate3() {
		return certificate3;
	}

	public void setCertificate3(String certificate3) {
		this.certificate3 = certificate3;
	}
	
	
}
