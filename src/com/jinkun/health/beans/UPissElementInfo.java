package com.jinkun.health.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 尿成分表
 * @author Administrator
 *
 */
@Entity
@Table(name="u_piss_element_info")
@DynamicUpdate(true)
public class UPissElementInfo {

	private Integer id; // 主键
	
	private String deviceId; // 设备ID
	
	private Integer oldId; // 老人ID
	
	private Long measureDate; // 测量时间
	
	private Float proportion; // 尿比重
	
	private Float PH; // PH
	
	private Float portein; // 尿蛋白
	
	private Float sugar; // 尿糖
	
	private Float ketone; // 尿酮
	
	private Float bilirubin; // 胆红素
	
	private Float urobilinogen; // 尿胆原
	
	private Float nitrite; // 亚硝酸盐
	
	private Float hemameba; // 白细胞
	
	private Float erythrocyte; // 红细胞
	
	private Float vitamin; // 维生素
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "deviceId", nullable = false, length = 50)
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "oldId")
	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}

	@Column(name = "measureDate")
	public Long getMeasureDate() {
		return measureDate;
	}

	public void setMeasureDate(Long measureDate) {
		this.measureDate = measureDate;
	}

	@Column(name = "proportion")
	public Float getProportion() {
		return proportion;
	}

	public void setProportion(Float proportion) {
		this.proportion = proportion;
	}

	@Column(name = "PH")
	public Float getPH() {
		return PH;
	}

	public void setPH(Float pH) {
		PH = pH;
	}

	@Column(name = "portein")
	public Float getPortein() {
		return portein;
	}

	public void setPortein(Float portein) {
		this.portein = portein;
	}

	@Column(name = "sugar")
	public Float getSugar() {
		return sugar;
	}

	public void setSugar(Float sugar) {
		this.sugar = sugar;
	}

	@Column(name = "ketone")
	public Float getKetone() {
		return ketone;
	}

	public void setKetone(Float ketone) {
		this.ketone = ketone;
	}

	@Column(name = "bilirubin")
	public Float getBilirubin() {
		return bilirubin;
	}

	public void setBilirubin(Float bilirubin) {
		this.bilirubin = bilirubin;
	}

	@Column(name = "urobilinogen")
	public Float getUrobilinogen() {
		return urobilinogen;
	}

	public void setUrobilinogen(Float urobilinogen) {
		this.urobilinogen = urobilinogen;
	}

	@Column(name = "nitrite")
	public Float getNitrite() {
		return nitrite;
	}

	public void setNitrite(Float nitrite) {
		this.nitrite = nitrite;
	}

	@Column(name = "hemameba")
	public Float getHemameba() {
		return hemameba;
	}

	public void setHemameba(Float hemameba) {
		this.hemameba = hemameba;
	}

	@Column(name = "erythrocyte")
	public Float getErythrocyte() {
		return erythrocyte;
	}

	public void setErythrocyte(Float erythrocyte) {
		this.erythrocyte = erythrocyte;
	}

	@Column(name = "vitamin")
	public Float getVitamin() {
		return vitamin;
	}

	public void setVitamin(Float vitamin) {
		this.vitamin = vitamin;
	}

	
}
