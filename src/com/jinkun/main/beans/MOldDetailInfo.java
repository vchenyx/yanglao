package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 老人信息表
 * @author adminx
 *
 */
@Entity
@Table(name="m_old_detail_info")
@DynamicUpdate(true)
public class MOldDetailInfo {

	private Integer id;//主键
	
	private Integer oldId;//老人ID
	
	private String name;
	
	
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
