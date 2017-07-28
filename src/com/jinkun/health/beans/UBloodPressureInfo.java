package com.jinkun.health.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 血压表
 * @author Administrator
 *
 */
@Entity
@Table(name="u_blood_pressure_info")
@DynamicUpdate(true)
public class UBloodPressureInfo {

	private Integer id; // id
	
	private String deviceId; // 设备ID
	
	private Integer oldId; //老人ID
	
	private Integer highPressure; // 高压
	
	private Integer lowPressure; // 低压
	
	private Float pressureDiff; // 压差
	
	private Float pluseRate; // 脉率
	
	private Long measureDate; // 测量时间

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

	@Column(name = "highPressure")
	public Integer getHighPressure() {
		return highPressure;
	}

	public void setHighPressure(Integer highPressure) {
		this.highPressure = highPressure;
	}

	@Column(name = "lowPressure")
	public Integer getLowPressure() {
		return lowPressure;
	}

	public void setLowPressure(Integer lowPressure) {
		this.lowPressure = lowPressure;
	}

	@Column(name = "pressureDiff")
	public Float getPressureDiff() {
		return pressureDiff;
	}

	public void setPressureDiff(Float pressureDiff) {
		this.pressureDiff = pressureDiff;
	}

	@Column(name = "pluseRate")
	public Float getPluseRate() {
		return pluseRate;
	}

	public void setPluseRate(Float pluseRate) {
		this.pluseRate = pluseRate;
	}

	@Column(name = "measureDate")
	public Long getMeasureDate() {
		return measureDate;
	}

	public void setMeasureDate(Long measureDate) {
		this.measureDate = measureDate;
	}
	
	
	
	
	
}
