package com.jinkun.main.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 服务商信息表
 * @author Administrator
 *
 */
@Entity
@Table(name="fw_provider")
@DynamicUpdate(true)
public class FWProvider {
	private Integer id;//主键
	private Integer typeId;//类型ID
	private String name;//名称
	private String address;//地址
	private String linkName;//联系人
	private String linkPhone;//联系电话
	private String providerCertificate;//服务商资质
	private String staffCertificate;//服务人员资质
	private String serviceScope; // 服务范围
	private Double latGG;//地图定位
	private Double lonGG;//地图定位
	private Float grade;//评分  初始5分
	private Integer state;//状态  0不可用，1可用
	private Long createTime; // 创建时间
	
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Float getGrade() {
		return grade;
	}
	public void setGrade(Float grade) {
		this.grade = grade;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getProviderCertificate() {
		return providerCertificate;
	}
	public void setProviderCertificate(String providerCertificate) {
		this.providerCertificate = providerCertificate;
	}
	public String getStaffCertificate() {
		return staffCertificate;
	}
	public void setStaffCertificate(String staffCertificate) {
		this.staffCertificate = staffCertificate;
	}
	public String getServiceScope() {
		return serviceScope;
	}
	public void setServiceScope(String serviceScope) {
		this.serviceScope = serviceScope;
	}
	
	
	
}
