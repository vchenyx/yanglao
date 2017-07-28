package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="fw_service_items")
@DynamicUpdate(true)
public class FWServiceItems {

	private Integer id;//主键
	private String servicePro;//服务项
	private String serviceContent;//内容
	private String unitPrice;//服务价格
	private String serviceUnit;//单位
	private String serviceStandard;//服务标准
	private Integer providerId;//所属服务商
	private Long createTime;//创建时间
	private Integer state;//状态 0下架， 1上架
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServicePro() {
		return servicePro;
	}
	public void setServicePro(String servicePro) {
		this.servicePro = servicePro;
	}
	public String getServiceContent() {
		return serviceContent;
	}
	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getServiceUnit() {
		return serviceUnit;
	}
	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}
	public String getServiceStandard() {
		return serviceStandard;
	}
	public void setServiceStandard(String serviceStandard) {
		this.serviceStandard = serviceStandard;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
