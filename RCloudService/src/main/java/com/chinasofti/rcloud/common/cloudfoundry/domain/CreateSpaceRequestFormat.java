package com.chinasofti.rcloud.common.cloudfoundry.domain;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateSpaceRequestFormat {
	
	  @SerializedName("name")
	  @Expose
	  private String name;

	  @SerializedName("organization_guid")
	  @Expose
	  private String organization_guid;

	  @SerializedName("developer_guids")
	  @Expose
	  private List<String> developer_guids;

	  @SerializedName("manager_guids")
	  @Expose
	  private List<String> manager_guids;

	  @SerializedName("auditor_guids")
	  @Expose
	  private List<String> auditor_guids;

	  @SerializedName("app_guids")
	  @Expose
	  private List<String> app_guids;

	  @SerializedName("domain_guids")
	  @Expose
	  private List<String> domain_guids;

	  @SerializedName("serviceinstanceguids")
	  @Expose
	  private List<String> serviceinstanceguids;

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

	public List<String> getServiceinstanceguids() {
		return serviceinstanceguids;
	}

	public void setServiceinstanceguids(List<String> serviceinstanceguids) {
		this.serviceinstanceguids = serviceinstanceguids;
	}

}
