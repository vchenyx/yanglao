package com.openfire.beans;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OfRosterGroups entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ofRosterGroups")
public class OfRosterGroups implements java.io.Serializable {

	// Fields

	private OfRosterGroupsId id;
	private String groupName;

	// Constructors

	/** default constructor */
	public OfRosterGroups() {
	}

	/** full constructor */
	public OfRosterGroups(OfRosterGroupsId id, String groupName) {
		this.id = id;
		this.groupName = groupName;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "rosterId", column = @Column(name = "rosterID", nullable = false)),
			@AttributeOverride(name = "rank", column = @Column(name = "rank", nullable = false)) })
	public OfRosterGroupsId getId() {
		return this.id;
	}

	public void setId(OfRosterGroupsId id) {
		this.id = id;
	}

	@Column(name = "groupName", nullable = false)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}