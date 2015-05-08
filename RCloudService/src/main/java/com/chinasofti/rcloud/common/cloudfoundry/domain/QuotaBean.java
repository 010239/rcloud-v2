package com.chinasofti.rcloud.common.cloudfoundry.domain;

import java.io.Serializable;

public class QuotaBean implements Serializable {
	
	public String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNon_basic_services_allowed() {
		return non_basic_services_allowed;
	}
	public void setNon_basic_services_allowed(String non_basic_services_allowed) {
		this.non_basic_services_allowed = non_basic_services_allowed;
	}
	public String getTotal_services() {
		return total_services;
	}
	public void setTotal_services(String total_services) {
		this.total_services = total_services;
	}
	public String getTotal_routes() {
		return total_routes;
	}
	public void setTotal_routes(String total_routes) {
		this.total_routes = total_routes;
	}
	public String getMemory_limit() {
		return memory_limit;
	}
	public void setMemory_limit(String memory_limit) {
		this.memory_limit = memory_limit;
	}
	public String getTrial_db_allowed() {
		return trial_db_allowed;
	}
	public void setTrial_db_allowed(String trial_db_allowed) {
		this.trial_db_allowed = trial_db_allowed;
	}
	public String non_basic_services_allowed;
	public String total_services;
	public String total_routes;
	public String memory_limit;
	public String trial_db_allowed;

}
