package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * JAlarmMessageInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "j_alarm_message_info")
@DynamicUpdate(true)
public class JAlarmMessageInfo implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 7768530305700268212L;
	private Long id;
	private String title;
	private String message;
	private String sendTime;
	private Integer isReceive;
	private String sendId;
	private String receiveId;
	private Long alarmId;

	// Constructors

	/** default constructor */
	public JAlarmMessageInfo() {
	}

	/** full constructor */
	public JAlarmMessageInfo(String title, String message, String sendTime,
			Integer isReceive, String sendId, String receiveId, Long alarmId) {
		this.title = title;
		this.message = message;
		this.sendTime = sendTime;
		this.isReceive = isReceive;
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.alarmId = alarmId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "title", length = 20)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "message", length = 255)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "send_time", length = 20)
	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "is_receive")
	public Integer getIsReceive() {
		return this.isReceive;
	}

	public void setIsReceive(Integer isReceive) {
		this.isReceive = isReceive;
	}

	@Column(name = "send_id", length = 20)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "receive_id", length = 20)
	public String getReceiveId() {
		return this.receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	@Column(name = "alarm_id")
	public Long getAlarmId() {
		return this.alarmId;
	}

	public void setAlarmId(Long alarmId) {
		this.alarmId = alarmId;
	}

}