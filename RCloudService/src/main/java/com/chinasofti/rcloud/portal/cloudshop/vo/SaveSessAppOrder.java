package com.chinasofti.rcloud.portal.cloudshop.vo;
/**
* @ClassName: SaveSessAppOrder
* @Description: 预付费  要求暂存session的信息
* @author shimeihua
* @date 2014年11月28日 上午11:16:46
*
 */
public class SaveSessAppOrder {
	public SaveSessAppOrder(){}
	private String orderDescription;//描述
	private int dueTime;
	private int payPattern;
	public int getPayPattern() {
		return payPattern;
	}
	public void setPayPattern(int payPattern) {
		this.payPattern = payPattern;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public int getDueTime() {
		return dueTime;
	}
	public void setDueTime(int dueTime) {
		this.dueTime = dueTime;
	}

}
