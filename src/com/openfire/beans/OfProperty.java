package com.openfire.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OfProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ofProperty")
public class OfProperty implements java.io.Serializable {

	// Fields

	private String name;
	private String propValue;

	// Constructors

	/** default constructor */
	public OfProperty() {
	}

	/** full constructor */
	public OfProperty(String propValue) {
		this.propValue = propValue;
	}

	// Property accessors
	@Id
	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "propValue", nullable = false, length = 65535)
	public String getPropValue() {
		return this.propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

}