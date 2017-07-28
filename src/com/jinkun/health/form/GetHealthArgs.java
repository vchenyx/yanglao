package com.jinkun.health.form;

public class GetHealthArgs {

	private int oldId;
	
	private int typeFlag = 0; // 0 血压, 1血氧, 2心电, 3血液, 4体成分, 5尿成分, 6体温
	
	private int dateFlag = 0; // 0今天, 1本周, 2本月 , 3时间区间
	
	private String startTime;
	
	private String endTime;

	public int getOldId() {
		return oldId;
	}

	public void setOldId(int oldId) {
		this.oldId = oldId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(int typeFlag) {
		this.typeFlag = typeFlag;
	}

	public int getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(int dateFlag) {
		this.dateFlag = dateFlag;
	}
	
	
	
}
