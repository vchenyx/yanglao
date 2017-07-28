package com.phone.model;
/**
 * 定位信息上传的实体类
 * @author zhangbin
 */
public class PositionInfoBean {
	private String userId;		    //用户id
	private Double latitude;	    //纬度
	private Double lontitude;  //经度
	private String netType;	   //网络类型
	private Integer locType;	   //定位类型
	private Long everytime;  //每隔多长时间，按秒算
	
	public PositionInfoBean(){}
	public PositionInfoBean(String userId, Double latitude, Double lontitude,
			String netType,Integer locType,Long everyTime) {
		super();
		this.userId = userId;
		this.latitude = latitude;
		this.lontitude = lontitude;
		this.setNetType(netType);
		this.locType = locType;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLontitude() {
		return lontitude;
	}
	public void setLontitude(Double lontitude) {
		this.lontitude = lontitude;
	}
	public Integer getLocType() {
		return locType;
	}
	public void setLocType(Integer locType) {
		this.locType = locType;
	}
	public Long getEverytime() {
		return everytime;
	}
	public void setEverytime(Long everytime) {
		this.everytime = everytime;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	
	
}
