package com.jinkun.main.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author adminx
 *
 */
@Entity
@Table(name="f_complaint")
@DynamicUpdate(true)
public class FComplaintInfo {

	private Integer id;//主键
	private String complaintNumbers;				 //投诉单号
	private Integer stationId; // 所属站点
	private Integer pensionId; // 所属机构
	private Integer acceptId; // 接单人
	private Long acceptTime; // 接单时间
	private String linkOrderNo; // 关联工单编号
	private String complaintPeople; 			    //投诉人
	private String cellphone;					   //手机号
	private String cellphoneTape;  			   	  //电话录音
	private Integer manageState;   			     //状态  0 是未处理 1 是 在处理 2 已处理
	private String 	servicePeople;	  		    //服务人员
	private Integer returnVisitState;          //回访状态  0 是未回访 1 是 已回访
	private String complaintContent;          //投诉内容
	private String managePeople;             //处理人
	private Long 	manageDate;		        //处理时间	
	private Long 	registerDate;          //登记时间
	private String returnVisitContent;    //回访内容
	private String 	registePeople;  	 //登记人	
	private String 	returnVisitPeople;  //回访人
	private Long 	returnVisitDate;   //回访时间
	private String  fuWuS; 			  //服务商
	private String fuWuSCellphone;	 //服务商电话
	private String manageContent;  //处理内容
	
	//处理内容
	
	// 回访内容
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "complaint_numbers",length = 30)
	public String getComplaintNumbers() {
		return complaintNumbers;
	}
	public void setComplaintNumbers(String complaintNumbers) {
		this.complaintNumbers = complaintNumbers;
	}
	
	@Column(name = "complaint_people",length = 30)
	public String getComplaintPeople() {
		return complaintPeople;
	}
	public void setComplaintPeople(String complaintPeople) {
		this.complaintPeople = complaintPeople;
	}
	
	@Column(name = "cellphone",length = 30)
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	@Column(name = "cellphone_tape",length = 40)
	public String getCellphoneTape() {
		return cellphoneTape;
	}
	public void setCellphoneTape(String cellphoneTape) {
		this.cellphoneTape = cellphoneTape;
	}
	
	@Column(name = "manage_state",length = 5)
	public Integer getManageState() {
		return manageState;
	}
	public void setManageState(Integer manageState) {
		this.manageState = manageState;
	}
	
	@Column(name = "service_people",length = 20)
	public String getServicePeople() {
		return servicePeople;
	}
	public void setServicePeople(String servicePeople) {
		this.servicePeople = servicePeople;
	}
	
	@Column(name = "return_visit_state",length = 5)
	public Integer getReturnVisitState() {
		return returnVisitState;
	}
	public void setReturnVisitState(Integer returnVisitState) {
		this.returnVisitState = returnVisitState;
	}
	
	@Column(name = "complaint_content",length = 100)
	public String getComplaintContent() {
		return complaintContent;
	}
	public void setComplaintContent(String complaintContent) {
		this.complaintContent = complaintContent;
	}
	
	@Column(name = "manage_people",length = 20)
	public String getManagePeople() {
		return managePeople;
	}
	public void setManagePeople(String managePeople) {
		this.managePeople = managePeople;
	}
	
	@Column(name = "manage_date",length = 45)
	public Long getManageDate() {
		return manageDate;
	}
	public void setManageDate(Long manageDate) {
		this.manageDate = manageDate;
	}
	
	@Column(name = "register_date",length = 45)
	public Long getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Long registerDate) {
		this.registerDate = registerDate;
	}
	
	@Column(name = "registe_people",length = 20)
	public String getRegistePeople() {
		return registePeople;
	}
	public void setRegistePeople(String registePeople) {
		this.registePeople = registePeople;
	}
	
	@Column(name = "return_visit_people",length = 20)
	public String getReturnVisitPeople() {
		return returnVisitPeople;
	}
	public void setReturnVisitPeople(String returnVisitPeople) {
		this.returnVisitPeople = returnVisitPeople;
	}
	
	@Column(name = "return_visit_date",length = 45)
	public Long getReturnVisitDate() {
		return returnVisitDate;
	}
	public void setReturnVisitDate(Long returnVisitDate) {
		this.returnVisitDate = returnVisitDate;
	}
	
	@Column(name = "pension_id",length = 5)
	public Integer getPensionId() {
		return pensionId;
	}
	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}
	
	@Column(name = "return_visit_content",length = 100)
	public String getReturnVisitContent() {
		return returnVisitContent;
	}
	public void setReturnVisitContent(String returnVisitContent) {
		this.returnVisitContent = returnVisitContent;
	}
	
	@Column(name = "fu_wu_s",length = 50)
	public String getFuWuS() {
		return fuWuS;
	}
	public void setFuWuS(String fuWuS) {
		this.fuWuS = fuWuS;
	}
	
	@Column(name = "fu_wu_s_cellphone",length = 30)
	public String getFuWuSCellphone() {
		return fuWuSCellphone;
	}
	public void setFuWuSCellphone(String fuWuSCellphone) {
		this.fuWuSCellphone = fuWuSCellphone;
	}
	
	@Column(name = "manage_content",length = 100)
	public String getManageContent() {
		return manageContent;
	}
	public void setManageContent(String manageContent) {
		this.manageContent = manageContent;
	}
	
	
	
	@Column(name = "accept_time")
	public Long getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Long acceptTime) {
		this.acceptTime = acceptTime;
	}
	
	@Column(name = "link_order_no", length = 50)
	public String getLinkOrderNo() {
		return linkOrderNo;
	}
	public void setLinkOrderNo(String linkOrderNo) {
		this.linkOrderNo = linkOrderNo;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public Integer getAcceptId() {
		return acceptId;
	}
	public void setAcceptId(Integer acceptId) {
		this.acceptId = acceptId;
	}
	
	
	
	
}
