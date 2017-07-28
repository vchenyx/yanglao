package com.jinkun.main.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="a_alarm_info")
@DynamicUpdate(true)
public class AAlarmInfo {

	private Long id;//主键
	private String userId;//用户id
	private String oldName;
	private String oldPhone;
	private String guradianName;
	private String guradianPhone;
	private String deviceNo;//设备编号
	private Double lat;//维度
	private Double lng;//经度
	private Double latGps;//转换后维度
	private Double lngGps;//转换后经度
	private Integer belongPension;
	private Integer areaId;
	private Long startTime; // 报警开始时间
	private Long endTime; // 报警结束时间
	
	private Date handleTime;//处理时间
	private String handleId;//处理人
	private Integer handleResult;//1十分满意， 2满意， 3一般， 4不满意
	private String handleDesc;//处理描述
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLatGps() {
		return latGps;
	}
	public void setLatGps(Double latGps) {
		this.latGps = latGps;
	}
	public Double getLngGps() {
		return lngGps;
	}
	public void setLngGps(Double lngGps) {
		this.lngGps = lngGps;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	public String getHandleId() {
		return handleId;
	}
	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}
	public Integer getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(Integer handleResult) {
		this.handleResult = handleResult;
	}
	public String getHandleDesc() {
		return handleDesc;
	}
	public void setHandleDesc(String handleDesc) {
		this.handleDesc = handleDesc;
	}
	public Integer getBelongPension() {
		return belongPension;
	}
	public void setBelongPension(Integer belongPension) {
		this.belongPension = belongPension;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getOldPhone() {
		return oldPhone;
	}
	public void setOldPhone(String oldPhone) {
		this.oldPhone = oldPhone;
	}
	public String getGuradianName() {
		return guradianName;
	}
	public void setGuradianName(String guradianName) {
		this.guradianName = guradianName;
	}
	public String getGuradianPhone() {
		return guradianPhone;
	}
	public void setGuradianPhone(String guradianPhone) {
		this.guradianPhone = guradianPhone;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	
	
	
}
