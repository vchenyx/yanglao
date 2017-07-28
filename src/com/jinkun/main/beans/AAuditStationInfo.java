package com.jinkun.main.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 机构审核情况表
 * @author adminx
 *
 */
@Entity
@Table(name="a_audit_station_info")
@DynamicUpdate(true)
public class AAuditStationInfo {

	private Integer id;
	private Integer stationId;
	private Integer stationType;
	private Integer isPass;//1通过, 2不通过
	private String msg;//审核信息
	private Date createTime;
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public Integer getStationType() {
		return stationType;
	}
	public void setStationType(Integer stationType) {
		this.stationType = stationType;
	}
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
