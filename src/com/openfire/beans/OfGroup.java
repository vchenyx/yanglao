package com.openfire.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OfGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ofGroup")
public class OfGroup implements java.io.Serializable {

	// Fields

	private String groupName;
	private String description;

	// Constructors

	/** default constructor */
	public OfGroup() {
	}

	/** full constructor */
	public OfGroup(String description) {
		this.description = description;
	}

	// Property accessors
	@Id
	@Column(name = "groupName", unique = true, nullable = false, length = 50)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}