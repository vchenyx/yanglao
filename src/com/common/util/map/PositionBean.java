/**
 * 定位标签/基站等原始坐标和转换后坐标的bean
 * @author shuyi
 */
package com.common.util.map;

public class PositionBean {
	private double x_pos;		//x坐标	
	private double y_pos;		//y坐标
	private double z_pos;		//z坐标
	private int bid;			//所在建筑物编号
	private double x_pos_trans;		//x坐标	转换后
	private double y_pos_trans;		//y坐标	转换后
	private double z_pos_trans;		//z坐标	转换后
	
	public PositionBean(){
		this.x_pos = this.y_pos = this.z_pos = this.x_pos_trans = this.y_pos_trans = this.z_pos_trans = 0;
		this.bid = 0;
	}
	
	public double getX_pos() {
		return x_pos;
	}
	public void setX_pos(double x_pos) {
		this.x_pos = x_pos;
	}
	public double getY_pos() {
		return y_pos;
	}
	public void setY_pos(double y_pos) {
		this.y_pos = y_pos;
	}
	public double getZ_pos() {
		return z_pos;
	}
	public void setZ_pos(double z_pos) {
		this.z_pos = z_pos;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public double getX_pos_trans() {
		return x_pos_trans;
	}
	public void setX_pos_trans(double x_pos_trans) {
		this.x_pos_trans = x_pos_trans;
	}
	public double getY_pos_trans() {
		return y_pos_trans;
	}
	public void setY_pos_trans(double y_pos_trans) {
		this.y_pos_trans = y_pos_trans;
	}
	public double getZ_pos_trans() {
		return z_pos_trans;
	}
	public void setZ_pos_trans(double z_pos_trans) {
		this.z_pos_trans = z_pos_trans;
	}
}
