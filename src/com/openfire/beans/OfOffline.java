package com.openfire.beans;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OfOffline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ofOffline")
public class OfOffline implements java.io.Serializable {

	// Fields

	private OfOfflineId id;
	private String creationDate;
	private Integer messageSize;
	private String stanza;

	// Constructors

	/** default constructor */
	public OfOffline() {
	}

	/** full constructor */
	public OfOffline(OfOfflineId id, String creationDate, Integer messageSize,
			String stanza) {
		this.id = id;
		this.creationDate = creationDate;
		this.messageSize = messageSize;
		this.stanza = stanza;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "username", column = @Column(name = "username", nullable = false, length = 64)),
			@AttributeOverride(name = "messageId", column = @Column(name = "messageID", nullable = false)) })
	public OfOfflineId getId() {
		return this.id;
	}

	public void setId(OfOfflineId id) {
		this.id = id;
	}

	@Column(name = "creationDate", nullable = false, length = 15)
	public String getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "messageSize", nullable = false)
	public Integer getMessageSize() {
		return this.messageSize;
	}

	public void setMessageSize(Integer messageSize) {
		this.messageSize = messageSize;
	}

	@Column(name = "stanza", nullable = false, length = 65535)
	public String getStanza() {
		return this.stanza;
	}

	public void setStanza(String stanza) {
		this.stanza = stanza;
	}

}