package com.common.util.map;

public class LatLngPoint {
	private double lat;// 纬度
	private double lng;// 经度

	public LatLngPoint() {
	}

	public LatLngPoint(double lng, double lat) {
		this.lng = lng;
		this.lat = lat;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LatLngPoint) {
			LatLngPoint bmapPoint = (LatLngPoint) obj;
			return (bmapPoint.getLng() == lng && bmapPoint.getLat() == lat) ? true
					: false;
		} else {
			return false;
		}
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Point [lat=" + lat + ", lng=" + lng + "]";
	}
}
