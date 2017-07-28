package com.openfire.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OfGroupPropId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class OfGroupPropId implements java.io.Serializable {

	// Fields

	private String groupName;
	private String name;

	// Constructors

	/** default constructor */
	public OfGroupPropId() {
	}

	/** full constructor */
	public OfGroupPropId(String groupName, String name) {
		this.groupName = groupName;
		this.name = name;
	}

	// Property accessors

	@Column(name = "groupName", nullable = false, length = 50)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OfGroupPropId))
			return false;
		OfGroupPropId castOther = (OfGroupPropId) other;

		return ((this.getGroupName() == castOther.getGroupName()) || (this
				.getGroupName() != null && castOther.getGroupName() != null && this
				.getGroupName().equals(castOther.getGroupName())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupName() == null ? 0 : this.getGroupName().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		return result;
	}

}