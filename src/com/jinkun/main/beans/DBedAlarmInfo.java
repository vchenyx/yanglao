package com.jinkun.main.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 离床报警器
 * @author Administrator
 *
 */
@Entity
@Table(name="d_bed_alarm_info")
@DynamicUpdate(true)
public class DBedAlarmInfo {

}
