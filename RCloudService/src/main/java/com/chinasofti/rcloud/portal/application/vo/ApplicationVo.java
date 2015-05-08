package com.chinasofti.rcloud.portal.application.vo;

public class ApplicationVo {
	private int payPattern;//状态
	private String applicationId;//应用id
	private String applicationName;//应用名称
	private String maintenanceCosts;//服务费用
	private String raeUrl;//返回的连接
	private String subDomain;//二级域名
	private Integer deployType;
	private String contactPerson;
	private String providerId;//供应商
	private String appVersion;//版本
	private String detailDescription;//描述
	public String getDetailDescription() {
		return detailDescription;
	}
	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public Integer getDeployType() {
		return deployType;
	}
	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}
	public Integer getIsWeb() {
		return isWeb;
	}
	public void setIsWeb(Integer isWeb) {
		this.isWeb = isWeb;
	}
	private Integer isWeb;
	
	public String getSubDomain() {
		return subDomain;
	}
	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}
	public int getPayPattern() {
		return payPattern;
	}
	public void setPayPattern(int payPattern) {
		this.payPattern = payPattern;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getMaintenanceCosts() {
		return maintenanceCosts;
	}
	public void setMaintenanceCosts(String maintenanceCosts) {
		this.maintenanceCosts = maintenanceCosts;
	}
	public String getRaeUrl() {
		return raeUrl;
	}
	public void setRaeUrl(String raeUrl) {
		this.raeUrl = raeUrl;
	}
	

}
