package com.openfire.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OfPresence entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ofPresence")
public class OfPresence implements java.io.Serializable {

	// Fields

	private String username;
	private String offlinePresence;
	private String offlineDate;

	// Constructors

	/** default constructor */
	public OfPresence() {
	}

	/** minimal constructor */
	public OfPresence(String offlineDate) {
		this.offlineDate = offlineDate;
	}

	/** full constructor */
	public OfPresence(String offlinePresence, String offlineDate) {
		this.offlinePresence = offlinePresence;
		this.offlineDate = offlineDate;
	}

	// Property accessors
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "offlinePresence", length = 65535)
	public String getOfflinePresence() {
		return this.offlinePresence;
	}

	public void setOfflinePresence(String offlinePresence) {
		this.offlinePresence = offlinePresence;
	}

	@Column(name = "offlineDate", nullable = false, length = 15)
	public String getOfflineDate() {
		return this.offlineDate;
	}

	public void setOfflineDate(String offlineDate) {
		this.offlineDate = offlineDate;
	}

}