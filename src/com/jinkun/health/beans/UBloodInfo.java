package com.jinkun.health.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 血液表
 * @author Administrator
 *
 */
@Entity
@Table(name="u_blood_info")
@DynamicUpdate(true)
public class UBloodInfo {

	private Integer id; // 主键
	
	private String deviceId; // 设备ID
	
	private Integer oldId; // 老人ID
	
	private Long measureDate; // 测量时间
	
	private Float sugar; // 血糖值
	
	private Float HDL; // 高密度脂蛋白
	
	private Float LDL; // 低密度脂蛋白
	
	private Float cholesterol; // 总胆固醇
	
	private Float TAG; // 甘油三酯
	
	private Float uricAcid; // 血尿酸
	
	private Float ketone; // 血酮
	
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

	@Column(name = "sugar")
	public Float getSugar() {
		return sugar;
	}

	public void setSugar(Float sugar) {
		this.sugar = sugar;
	}

	@Column(name = "HDL")
	public Float getHDL() {
		return HDL;
	}

	public void setHDL(Float hDL) {
		HDL = hDL;
	}

	@Column(name = "LDL")
	public Float getLDL() {
		return LDL;
	}

	public void setLDL(Float lDL) {
		LDL = lDL;
	}

	@Column(name = "cholesterol")
	public Float getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Float cholesterol) {
		this.cholesterol = cholesterol;
	}

	@Column(name = "TAG")
	public Float getTAG() {
		return TAG;
	}

	public void setTAG(Float tAG) {
		TAG = tAG;
	}

	@Column(name = "uricAcid")
	public Float getUricAcid() {
		return uricAcid;
	}

	public void setUricAcid(Float uricAcid) {
		this.uricAcid = uricAcid;
	}

	@Column(name = "ketone")
	public Float getKetone() {
		return ketone;
	}

	public void setKetone(Float ketone) {
		this.ketone = ketone;
	}
	
	
	
	
}
