package com.openfire.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OfRoster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ofRoster")
public class OfRoster implements java.io.Serializable {

	// Fields

	private long rosterId;
	private String username;
	private String jid;
	private short sub;
	private short ask;
	private short recv;
	private String nick;

	// Constructors

	/** default constructor */
	public OfRoster() {
	}

	/** minimal constructor */
	public OfRoster(String username, String jid, short sub, short ask,
			short recv) {
		this.username = username;
		this.jid = jid;
		this.sub = sub;
		this.ask = ask;
		this.recv = recv;
	}

	/** full constructor */
	public OfRoster(String username, String jid, short sub, short ask,
			short recv, String nick) {
		this.username = username;
		this.jid = jid;
		this.sub = sub;
		this.ask = ask;
		this.recv = recv;
		this.nick = nick;
	}

	// Property accessors
	@Id
	@Column(name = "rosterID", unique = true, nullable = false)
	public long getRosterId() {
		return this.rosterId;
	}

	public void setRosterId(long rosterId) {
		this.rosterId = rosterId;
	}

	@Column(name = "username", nullable = false, length = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "jid", nullable = false, length = 1024)
	public String getJid() {
		return this.jid;
	}

	public void setJid(String jid) {
		this.jid = jid;
	}

	@Column(name = "sub", nullable = false)
	public short getSub() {
		return this.sub;
	}

	public void setSub(short sub) {
		this.sub = sub;
	}

	@Column(name = "ask", nullable = false)
	public short getAsk() {
		return this.ask;
	}

	public void setAsk(short ask) {
		this.ask = ask;
	}

	@Column(name = "recv", nullable = false)
	public short getRecv() {
		return this.recv;
	}

	public void setRecv(short recv) {
		this.recv = recv;
	}

	@Column(name = "nick")
	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}