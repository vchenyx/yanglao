package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 老人定位轨迹
 * @author adminx
 *
 */
@Entity
@Table(name="l_location_track_info")
@DynamicUpdate(true)
public class LLocationTrackInfo {

	private Integer id;
	private String userId;
	private String deviceId;
	private Double latGps;
	private Double lonGps;
	private Double latGG;
	private Double lonGG;
	private Double latBD;
	private Double lonBD;
	private Long posiTime;
	private Integer posiMode;//定位类型, 0双模, 1GPS, 2北斗
	private Integer inOutDoor;//室内室外 0室外, 1室内
	private Integer isAlarm;  //是否报警，0正常定位, 1报警, 2取消报警
	private Integer isValid;  //是否有效，1有效，0无效
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Double getLatGps() {
		return latGps;
	}
	public void setLatGps(Double latGps) {
		this.latGps = latGps;
	}
	public Double getLonGps() {
		return lonGps;
	}
	public void setLonGps(Double lonGps) {
		this.lonGps = lonGps;
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
	public Long getPosiTime() {
		return posiTime;
	}
	public void setPosiTime(Long posiTime) {
		this.posiTime = posiTime;
	}
	public Double getLatBD() {
		return latBD;
	}
	public void setLatBD(Double latBD) {
		this.latBD = latBD;
	}
	public Double getLonBD() {
		return lonBD;
	}
	public void setLonBD(Double lonBD) {
		this.lonBD = lonBD;
	}
	public Integer getPosiMode() {
		return posiMode;
	}
	public void setPosiMode(Integer posiMode) {
		this.posiMode = posiMode;
	}
	public Integer getInOutDoor() {
		return inOutDoor;
	}
	public void setInOutDoor(Integer inOutDoor) {
		this.inOutDoor = inOutDoor;
	}
	public Integer getIsAlarm() {
		return isAlarm;
	}
	public void setIsAlarm(Integer isAlarm) {
		this.isAlarm = isAlarm;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
}
