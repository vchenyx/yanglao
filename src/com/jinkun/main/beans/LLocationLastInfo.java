package com.jinkun.main.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
/**
 * 最后一次定位信息
 * @author adminx
 *
 */
@Entity
@Table(name="l_location_last_info")
@DynamicUpdate(true)
public class LLocationLastInfo {

	private String userId;
	private String deviceId;
	private Double latGps;
	private Double lonGps;
	private Double latGG;
	private Double lonGG;
	private Long posiTime;
	private Integer posiType;//定位类型
	private String netType;//网络类型
	
	@Id
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
	public Integer getPosiType() {
		return posiType;
	}
	public void setPosiType(Integer posiType) {
		this.posiType = posiType;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	
	
}
