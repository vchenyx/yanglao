package com.openfire.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OfGroupUserId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class OfGroupUserId implements java.io.Serializable {

	// Fields

	private String groupName;
	private String username;
	private short administrator;

	// Constructors

	/** default constructor */
	public OfGroupUserId() {
	}

	/** full constructor */
	public OfGroupUserId(String groupName, String username, short administrator) {
		this.groupName = groupName;
		this.username = username;
		this.administrator = administrator;
	}

	// Property accessors

	@Column(name = "groupName", nullable = false, length = 50)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "username", nullable = false, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "administrator", nullable = false)
	public short getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(short administrator) {
		this.administrator = administrator;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OfGroupUserId))
			return false;
		OfGroupUserId castOther = (OfGroupUserId) other;

		return ((this.getGroupName() == castOther.getGroupName()) || (this
				.getGroupName() != null && castOther.getGroupName() != null && this
				.getGroupName().equals(castOther.getGroupName())))
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())))
				&& (this.getAdministrator() == castOther.getAdministrator());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupName() == null ? 0 : this.getGroupName().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result + this.getAdministrator();
		return result;
	}

}