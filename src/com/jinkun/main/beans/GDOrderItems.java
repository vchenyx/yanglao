package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="gd_work_items")
@DynamicUpdate(true)
public class GDOrderItems {

	private Integer id; // 主键
	private String name; // 名称
	private Integer countNum; //数量
	private String itemsUnit; //单位
	private Float singlePrice; //单价
	private Float countPrice; //总价
	private Integer orderId; //所属工单
	private Long createTime; //创建时间
	private Long serviceDate; //服务日期
	private Integer providerId; //供应商ID
	private Integer stationId; //站点ID
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCountNum() {
		return countNum;
	}
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
	public String getItemsUnit() {
		return itemsUnit;
	}
	public void setItemsUnit(String itemsUnit) {
		this.itemsUnit = itemsUnit;
	}
	public Float getSinglePrice() {
		return singlePrice;
	}
	public void setSinglePrice(Float singlePrice) {
		this.singlePrice = singlePrice;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Float getCountPrice() {
		return countPrice;
	}
	public void setCountPrice(Float countPrice) {
		this.countPrice = countPrice;
	}
	public Long getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(Long serviceDate) {
		this.serviceDate = serviceDate;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	
	
}
