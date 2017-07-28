package com.jinkun.main.beans;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="zs_knowledge")
@DynamicUpdate(true)
public class KnowledgeBase {
	private Integer id;//主键
	private String name;//标题
	private String knowId;//类型名称
	private String content;//内容
	private String readTimes;//阅读次数  
	private String creation;//创建人
	private String stateTime;//创建时间
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

	@Column(name = "name", length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "content", length = 50)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "readTimes",length = 30)
	public String getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(String readTimes) {
		this.readTimes = readTimes;
	}
	
	@Column(name = "creation",length = 30)
	public String getCreation() {
		return creation;
	}
	public void setCreation(String creation) {
		this.creation = creation;
	}
	
	@Column(name = "knowId",length = 20)
	public String getKnowId() {
		return knowId;
	}
	public void setKnowId(String knowId) {
		this.knowId = knowId;
	}
	
	@Column(name = "stateTime") 
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	
	@Column(name = "pension_id") 
	public Integer getPensionId() {
		return pensionId;
	}
	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}
	
	
	
	
}
