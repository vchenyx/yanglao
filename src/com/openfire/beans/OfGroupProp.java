package com.openfire.beans;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OfGroupProp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ofGroupProp")
public class OfGroupProp implements java.io.Serializable {

	// Fields

	private OfGroupPropId id;
	private String propValue;

	// Constructors

	/** default constructor */
	public OfGroupProp() {
	}

	/** full constructor */
	public OfGroupProp(OfGroupPropId id, String propValue) {
		this.id = id;
		this.propValue = propValue;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "groupName", column = @Column(name = "groupName", nullable = false, length = 50)),
			@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, length = 100)) })
	public OfGroupPropId getId() {
		return this.id;
	}

	public void setId(OfGroupPropId id) {
		this.id = id;
	}

	@Column(name = "propValue", nullable = false, length = 65535)
	public String getPropValue() {
		return this.propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

}