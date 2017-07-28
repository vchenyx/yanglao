package com.jinkun.main.form;

import java.util.List;
import java.util.Map;

import com.jinkun.main.beans.GDOrderItems;

public class OrderCountList {
	
	private String providerName; // 服务商名称
	
	private List<GDOrderItems> itemList; // 商品清单
	
	private Float countPrice = 0f; // 总价
	
	private Float deliveryCost = 0f; // 配送费
	
	private String dateStr; // 结算日期
	
	private Map<String, Integer> listCount; // 单子数量（需要区分社区）

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public List<GDOrderItems> getItemList() {
		return itemList;
	}

	public void setItemList(List<GDOrderItems> itemList) {
		this.itemList = itemList;
	}

	public Float getCountPrice() {
		return countPrice;
	}

	public void setCountPrice(Float countPrice) {
		this.countPrice = countPrice;
	}

	public Float getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(Float deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Map<String, Integer> getListCount() {
		return listCount;
	}

	public void setListCount(Map<String, Integer> listCount) {
		this.listCount = listCount;
	}
}
