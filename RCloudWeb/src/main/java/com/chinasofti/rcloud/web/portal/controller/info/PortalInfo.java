package com.chinasofti.rcloud.web.portal.controller.info;

import java.util.List;

public class PortalInfo {

	private String code;
	private String msg;
	private List<Service> service;
	private List<Store> store;
	private List<Slide> slide; 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Service> getService() {
		return service;
	}
	public void setService(List<Service> service) {
		this.service = service;
	}
	public List<Store> getStore() {
		return store;
	}
	public void setStore(List<Store> store) {
		this.store = store;
	}
	public List<Slide> getSlide() {
		return slide;
	}
	public void setSlide(List<Slide> slide) {
		this.slide = slide;
	}
	
	
}
