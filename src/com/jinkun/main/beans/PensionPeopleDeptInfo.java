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
@Table(name="f_pension_people_dept_info")
@DynamicUpdate(true)
public class PensionPeopleDeptInfo {

	private Integer id;//主键
	private String name;//类型名称
	private Long createTime;//创建时间
	private String createUsr; //创建人
	private Integer pensionId;//机构id
	
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Column(name="name",length = 40)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="create_usr")
	public String getCreateUsr() {
		return createUsr;
	}
	public void setCreateUsr(String createUsr) {
		this.createUsr = createUsr;
	}
	
	@Column(name="pension_id")
	public Integer getPensionId() {
		return pensionId;
	}
	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}
	


	
}
