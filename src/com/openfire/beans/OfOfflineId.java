package com.openfire.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OfOfflineId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class OfOfflineId implements java.io.Serializable {

	// Fields

	private String username;
	private long messageId;

	// Constructors

	/** default constructor */
	public OfOfflineId() {
	}

	/** full constructor */
	public OfOfflineId(String username, long messageId) {
		this.username = username;
		this.messageId = messageId;
	}

	// Property accessors

	@Column(name = "username", nullable = false, length = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "messageID", nullable = false)
	public long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OfOfflineId))
			return false;
		OfOfflineId castOther = (OfOfflineId) other;

		return ((this.getUsername() == castOther.getUsername()) || (this
				.getUsername() != null && castOther.getUsername() != null && this
				.getUsername().equals(castOther.getUsername())))
				&& (this.getMessageId() == castOther.getMessageId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result + (int) this.getMessageId();
		return result;
	}

}