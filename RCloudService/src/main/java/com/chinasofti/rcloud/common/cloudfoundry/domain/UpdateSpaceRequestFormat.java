package com.chinasofti.rcloud.common.cloudfoundry.domain;

import java.util.List;

import com.google.gson.annotations.Expose;

public class UpdateSpaceRequestFormat {
	
	  @Expose
	  private String name;

	  @Expose
	  private String organization_guid;

	  @Expose
	  private List<String> developer_guids;

	  @Expose
	  private List<String> manager_guids;

	  @Expose
	  private List<String> auditor_guids;

	  @Expose
	  private List<String> app_guids;

	  @Expose
	  private List<String> domain_guids;

	  @Expose
	  private List<String> service_instance_guids;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganization_guid() {
		return organization_guid;
	}

	public void setOrganization_guid(String organization_guid) {
		this.organization_guid = organization_guid;
	}

	public List<String> getDeveloper_guids() {
		return developer_guids;
	}

	public void setDeveloper_guids(List<String> developer_guids) {
		this.developer_guids = developer_guids;
	}

	public List<String> getManager_guids() {
		return manager_guids;
	}

	public void setManager_guids(List<String> manager_guids) {
		this.manager_guids = manager_guids;
	}

	public List<String> getAuditor_guids() {
		return auditor_guids;
	}

	public void setAuditor_guids(List<String> auditor_guids) {
		this.auditor_guids = auditor_guids;
	}

	public List<String> getApp_guids() {
		return app_guids;
	}

	public void setApp_guids(List<String> app_guids) {
		this.app_guids = app_guids;
	}

	public List<String> getDomain_guids() {
		return domain_guids;
	}

	public void setDomain_guids(List<String> domain_guids) {
		this.domain_guids = domain_guids;
	}

	public List<String> getService_instance_guids() {
		return service_instance_guids;
	}

	public void setService_instance_guids(List<String> service_instance_guids) {
		this.service_instance_guids = service_instance_guids;
	}

}
