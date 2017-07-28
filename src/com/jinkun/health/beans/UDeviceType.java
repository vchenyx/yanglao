package com.jinkun.health.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 设备类型表
 * @author Administrator
 *
 */
@Entity
@Table(name="u_device_type")
@DynamicUpdate(true)
public class UDeviceType {

	private Integer id; // 主键id
	
	private Integer pensionId; // 所属机构
	
	private String deviceType; // 设备名称

	private Long enterTime; // 录入时间
	
	private String deviceDesc; // 备注
	
	private String deviceImg; // 设备图片
	
	private String proFactory; // 生产厂家
	
	private String proFunction; // 产品功能
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "deviceType", nullable = false, length = 100)
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "pensionId")
	public Integer getPensionId() {
		return pensionId;
	}

	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}

	@Column(name = "enterTime")
	public Long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Long enterTime) {
		this.enterTime = enterTime;
	}

	@Column(name = "deviceImg", nullable = false, length = 100)
	public String getDeviceImg() {
		return deviceImg;
	}

	public void setDeviceImg(String deviceImg) {
		this.deviceImg = deviceImg;
	}

	@Column(name = "deviceDesc", nullable = false, length = 200)
	public String getDeviceDesc() {
		return deviceDesc;
	}

	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}

	@Column(name = "proFactory", nullable = false, length = 200)
	public String getProFactory() {
		return proFactory;
	}

	public void setProFactory(String proFactory) {
		this.proFactory = proFactory;
	}

	@Column(name = "proFunction", nullable = false, length = 200)
	public String getProFunction() {
		return proFunction;
	}

	public void setProFunction(String proFunction) {
		this.proFunction = proFunction;
	}
	
	
	
	
	
	
}
