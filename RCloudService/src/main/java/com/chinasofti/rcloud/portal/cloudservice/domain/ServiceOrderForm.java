package com.chinasofti.rcloud.portal.cloudservice.domain;

public class ServiceOrderForm {

	private String orderId;

	private String inclusivePriceId;

	private String servicePrice;

	private String serviceInclusivePrice;

	private String totalPrice;

	private String totalMonth;

	private int payPattern;

	private String serviceId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInclusivePriceId() {
		return inclusivePriceId;
	}

	public void setInclusivePriceId(String inclusivePriceId) {
		this.inclusivePriceId = inclusivePriceId;
	}

	public String getServiceInclusivePrice() {
		return serviceInclusivePrice;
	}

	public void setServiceInclusivePrice(String serviceInclusivePrice) {
		this.serviceInclusivePrice = serviceInclusivePrice;
	}

	public String getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(String totalMonth) {
		this.totalMonth = totalMonth;
	}

	public int getPayPattern() {
		return payPattern;
	}

	public void setPayPattern(int payPattern) {
		this.payPattern = payPattern;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}
