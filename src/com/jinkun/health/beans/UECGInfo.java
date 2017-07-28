package com.jinkun.health.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 心电数据
 * @author Administrator
 *
 */
@Entity
@Table(name="u_ecg_info")
@DynamicUpdate(true)
public class UECGInfo {

	private Integer id; // 主键
	
	private String deviceId; // 设备ID
	
	private Integer oldId; // 老人ID
	
	private Long measureDate; // 测量时间
	
	private Integer heartRate; // 心率
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "deviceId", nullable = false, length = 50)
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "oldId")
	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}

	@Column(name = "measureDate")
	public Long getMeasureDate() {
		return measureDate;
	}

	public void setMeasureDate(Long measureDate) {
		this.measureDate = measureDate;
	}

	@Column(name = "heartRate")
	public Integer getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}
	
	
}
