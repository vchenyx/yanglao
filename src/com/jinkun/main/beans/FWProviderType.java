package com.jinkun.main.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 服务商分类信息表
 * @author Administrator
 *
 */
@Entity
@Table(name="fw_provider_class")
@DynamicUpdate(true)
public class FWProviderType {
	private Integer id; // 主键Id
	private Integer pensionId;
	private Integer stationId;
	private String name;
	private Integer pid;
	private Long createTime;
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPensionId() {
		return pensionId;
	}
	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	

	
}
