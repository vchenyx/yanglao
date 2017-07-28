package com.jinkun.health.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 设备信息表
 * @author Administrator
 *
 */
@Entity
@Table(name="u_device_info")
@DynamicUpdate(true)
public class UDeviceInfo {

	private Integer id; // 主键id
	
	private Integer deviceType; // 设备类型
	
	private String deviceNo; // 设备编号
	
	private Integer oldId; // 所属老人
	
	private Integer pensionId; // 所属机构
	
	private Long enterTime; // 录入时间
	
	private Integer state; // 设备状态 1可用, 0不可用
	
	private String deviceDesc; // 备注

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "deviceType")
	public Integer getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	
	@Column(name = "deviceNo", nullable = false, length = 100)
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	@Column(name = "oldId")
	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
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

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "deviceDesc", length = 200)
	public String getDeviceDesc() {
		return deviceDesc;
	}

	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}
	
}
