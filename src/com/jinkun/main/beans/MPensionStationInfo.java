package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 养老机构信息
 * @author adminx
 *
 */
@Entity
@Table(name="m_pension_station_info")
@DynamicUpdate(true)
public class MPensionStationInfo {

	private Integer id;//主键
	private String userId;//用户id
	private String name;//机构名称
	private String address;//地址
	private String linkName;//联系人
	private String linkPhone;//联系电话
	private Long creationTime;//创建时间
	private Integer state;//状态
	private Long requestTime;//请求时间
	private String email;//邮箱
	private String certificate1;//资质1
	private String certificate2;//资质2
	private String certificate3;//资质3
	private Double latBD;//维度
	private Double lngBD;//经度
	private Double latGG;//维度
	private Double lonGG;//经度
	private Double latGps;//转换后维度
	private Double lonGps;//转换后经度
	private Integer areaId;
	private Integer pid;  //  
	private Integer stationType; // 5 机构， 51站点， 52客服
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getCertificate1() {
		return certificate1;
	}
	public void setCertificate1(String certificate1) {
		this.certificate1 = certificate1;
	}
	public String getCertificate2() {
		return certificate2;
	}
	public void setCertificate2(String certificate2) {
		this.certificate2 = certificate2;
	}
	public String getCertificate3() {
		return certificate3;
	}
	public void setCertificate3(String certificate3) {
		this.certificate3 = certificate3;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Double getLatGps() {
		return latGps;
	}
	public void setLatGps(Double latGps) {
		this.latGps = latGps;
	}
	public Double getLatBD() {
		return latBD;
	}
	public void setLatBD(Double latBD) {
		this.latBD = latBD;
	}
	public Double getLngBD() {
		return lngBD;
	}
	public void setLngBD(Double lngBD) {
		this.lngBD = lngBD;
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
	public Double getLonGps() {
		return lonGps;
	}
	public void setLonGps(Double lonGps) {
		this.lonGps = lonGps;
	}
	public Long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	public Long getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Long requestTime) {
		this.requestTime = requestTime;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getStationType() {
		return stationType;
	}
	public void setStationType(Integer stationType) {
		this.stationType = stationType;
	}
	
	
	
}
