package com.openfire.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OfRosterGroupsId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class OfRosterGroupsId implements java.io.Serializable {

	// Fields

	private long rosterId;
	private short rank;

	// Constructors

	/** default constructor */
	public OfRosterGroupsId() {
	}

	/** full constructor */
	public OfRosterGroupsId(long rosterId, short rank) {
		this.rosterId = rosterId;
		this.rank = rank;
	}

	// Property accessors

	@Column(name = "rosterID", nullable = false)
	public long getRosterId() {
		return this.rosterId;
	}

	public void setRosterId(long rosterId) {
		this.rosterId = rosterId;
	}

	@Column(name = "rank", nullable = false)
	public short getRank() {
		return this.rank;
	}

	public void setRank(short rank) {
		this.rank = rank;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OfRosterGroupsId))
			return false;
		OfRosterGroupsId castOther = (OfRosterGroupsId) other;

		return (this.getRosterId() == castOther.getRosterId())
				&& (this.getRank() == castOther.getRank());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getRosterId();
		result = 37 * result + this.getRank();
		return result;
	}

}