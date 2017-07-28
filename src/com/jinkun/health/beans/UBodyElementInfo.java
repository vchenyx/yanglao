package com.jinkun.health.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 体成分表
 * @author Administrator
 *
 */
@Entity
@Table(name="u_body_element_info")
@DynamicUpdate(true)
public class UBodyElementInfo {

	private Integer id; // 主键
	
	private String deviceId; // 设备ID
	
	private Integer oldId; // 老人ID
	
	private Long measureDate; // 测量时间
	
	private Float weight; // 体重
	
	private Float fat; // 脂肪
	
	private Float muscle; // 肌肉
	
	private Float water; // 水
	
	private Float skeleton; // 骨骼
	
	private Float visceralFat; // 内脏脂肪
	
	private Float BMR;
	
	private Float BMI;
	
	
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

	@Column(name = "weight")
	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	@Column(name = "fat")
	public Float getFat() {
		return fat;
	}

	public void setFat(Float fat) {
		this.fat = fat;
	}

	@Column(name = "muscle")
	public Float getMuscle() {
		return muscle;
	}

	public void setMuscle(Float muscle) {
		this.muscle = muscle;
	}

	@Column(name = "water")
	public Float getWater() {
		return water;
	}

	public void setWater(Float water) {
		this.water = water;
	}

	@Column(name = "skeleton")
	public Float getSkeleton() {
		return skeleton;
	}

	public void setSkeleton(Float skeleton) {
		this.skeleton = skeleton;
	}

	@Column(name = "visceralFat")
	public Float getVisceralFat() {
		return visceralFat;
	}

	public void setVisceralFat(Float visceralFat) {
		this.visceralFat = visceralFat;
	}

	@Column(name = "BMR")
	public Float getBMR() {
		return BMR;
	}

	public void setBMR(Float bMR) {
		BMR = bMR;
	}

	@Column(name = "BMI")
	public Float getBMI() {
		return BMI;
	}

	public void setBMI(Float bMI) {
		BMI = bMI;
	}
	
}
