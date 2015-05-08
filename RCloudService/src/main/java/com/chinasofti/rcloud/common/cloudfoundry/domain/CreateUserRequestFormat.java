package com.chinasofti.rcloud.common.cloudfoundry.domain;

import java.util.List;

import com.google.gson.annotations.Expose;

public class CreateUserRequestFormat {
	
	  @Expose
	  private String guid;

	  @Expose
	  private boolean admin;

	  @Expose
	  private String defaultspaceguid;

	  @Expose
	  private List<String> space_guids;

	  @Expose
	  private List<String> organization_guids;

	  @Expose
	  private List<String> managedorganizationguids;

	  @Expose
	  private List<String> billingmanagedorganization_guids;

	  @Expose
	  private List<String> auditedorganizationguids;

	  @Expose
	  private List<String> managedspaceguids;

	  @Expose
	  private List<String> auditedspaceguids;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getDefaultspaceguid() {
		return defaultspaceguid;
	}

	public void setDefaultspaceguid(String defaultspaceguid) {
		this.defaultspaceguid = defaultspaceguid;
	}

	public List<String> getSpace_guids() {
		return space_guids;
	}

	public void setSpace_guids(List<String> space_guids) {
		this.space_guids = space_guids;
	}

	public List<String> getOrganization_guids() {
		return organization_guids;
	}

	public void setOrganization_guids(List<String> organization_guids) {
		this.organization_guids = organization_guids;
	}

	public List<String> getManagedorganizationguids() {
		return managedorganizationguids;
	}

	public void setManagedorganizationguids(List<String> managedorganizationguids) {
		this.managedorganizationguids = managedorganizationguids;
	}

	public List<String> getBillingmanagedorganization_guids() {
		return billingmanagedorganization_guids;
	}

	public void setBillingmanagedorganization_guids(
			List<String> billingmanagedorganization_guids) {
		this.billingmanagedorganization_guids = billingmanagedorganization_guids;
	}

	public List<String> getAuditedorganizationguids() {
		return auditedorganizationguids;
	}

	public void setAuditedorganizationguids(List<String> auditedorganizationguids) {
		this.auditedorganizationguids = auditedorganizationguids;
	}

	public List<String> getManagedspaceguids() {
		return managedspaceguids;
	}

	public void setManagedspaceguids(List<String> managedspaceguids) {
		this.managedspaceguids = managedspaceguids;
	}

	public List<String> getAuditedspaceguids() {
		return auditedspaceguids;
	}

	public void setAuditedspaceguids(List<String> auditedspaceguids) {
		this.auditedspaceguids = auditedspaceguids;
	}

}
