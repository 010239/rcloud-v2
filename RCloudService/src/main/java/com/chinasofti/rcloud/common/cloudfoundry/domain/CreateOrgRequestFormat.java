package com.chinasofti.rcloud.common.cloudfoundry.domain;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateOrgRequestFormat {
	
	  @SerializedName("name")
	  @Expose
	  private String name;

	  @SerializedName("billing_enabled")
	  @Expose
	  private boolean billing_enabled;

	  @SerializedName("quotadefinitionguid")
	  @Expose
	  private String quotadefinitionguid;

	  @SerializedName("domain_guids")
	  @Expose
	  private List<String> domain_guids;

	  @SerializedName("user_guids")
	  @Expose
	  private List<String> user_guids;

	  @SerializedName("manager_guids")
	  @Expose
	  private List<String> manager_guids;

	  @SerializedName("billingmanagerguids")
	  @Expose
	  private List<String> billingmanagerguids;

	  @SerializedName("auditor_guids")
	  @Expose
	  private List<String> auditor_guids;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBilling_enabled() {
		return billing_enabled;
	}

	public void setBilling_enabled(boolean billing_enabled) {
		this.billing_enabled = billing_enabled;
	}

	public String getQuotadefinitionguid() {
		return quotadefinitionguid;
	}

	public void setQuotadefinitionguid(String quotadefinitionguid) {
		this.quotadefinitionguid = quotadefinitionguid;
	}

	public List<String> getDomain_guids() {
		return domain_guids;
	}

	public void setDomain_guids(List<String> domain_guids) {
		this.domain_guids = domain_guids;
	}

	public List<String> getUser_guids() {
		return user_guids;
	}

	public void setUser_guids(List<String> user_guids) {
		this.user_guids = user_guids;
	}

	public List<String> getManager_guids() {
		return manager_guids;
	}

	public void setManager_guids(List<String> manager_guids) {
		this.manager_guids = manager_guids;
	}

	public List<String> getBillingmanagerguids() {
		return billingmanagerguids;
	}

	public void setBillingmanagerguids(List<String> billingmanagerguids) {
		this.billingmanagerguids = billingmanagerguids;
	}

	public List<String> getAuditor_guids() {
		return auditor_guids;
	}

	public void setAuditor_guids(List<String> auditor_guids) {
		this.auditor_guids = auditor_guids;
	}

}
