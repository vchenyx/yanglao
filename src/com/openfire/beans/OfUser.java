package com.openfire.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OfUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ofUser")
public class OfUser implements java.io.Serializable {

	// Fields

	private String username;
	private String plainPassword;
	private String encryptedPassword;
	private String name;
	private String email;
	private String creationDate;
	private String modificationDate;
	private String storedKey;
	private String serverKey;
	private String salt;
	private Integer iterations;

	// Constructors

	/** default constructor */
	public OfUser() {
	}

	/** minimal constructor */
	public OfUser(String creationDate, String modificationDate) {
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}

	/** full constructor */
	public OfUser(String plainPassword, String encryptedPassword, String name,
			String email, String creationDate, String modificationDate,
			String storedKey, String serverKey, String salt, Integer iterations) {
		this.plainPassword = plainPassword;
		this.encryptedPassword = encryptedPassword;
		this.name = name;
		this.email = email;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.storedKey = storedKey;
		this.serverKey = serverKey;
		this.salt = salt;
		this.iterations = iterations;
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

	@Column(name = "plainPassword", length = 32)
	public String getPlainPassword() {
		return this.plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	@Column(name = "encryptedPassword")
	public String getEncryptedPassword() {
		return this.encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "creationDate", nullable = false, length = 15)
	public String getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "modificationDate", nullable = false, length = 15)
	public String getModificationDate() {
		return this.modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	@Column(name = "storedKey", length = 32)
	public String getStoredKey() {
		return this.storedKey;
	}

	public void setStoredKey(String storedKey) {
		this.storedKey = storedKey;
	}

	@Column(name = "serverKey", length = 32)
	public String getServerKey() {
		return this.serverKey;
	}

	public void setServerKey(String serverKey) {
		this.serverKey = serverKey;
	}

	@Column(name = "salt", length = 32)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "iterations")
	public Integer getIterations() {
		return this.iterations;
	}

	public void setIterations(Integer iterations) {
		this.iterations = iterations;
	}

}