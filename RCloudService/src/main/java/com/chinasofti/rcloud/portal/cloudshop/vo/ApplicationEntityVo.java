package com.chinasofti.rcloud.portal.cloudshop.vo;

import com.chinasofti.rcloud.domain.ApplicationEntity;
/**
* @ClassName: ApplicationEntityVo
* @Description: 修改应用  --展示应用信息
* @author shimeihua
* @date 2014年11月27日 上午10:47:40
*
 */
public class ApplicationEntityVo  {
	public ApplicationEntityVo(){}
	 public ApplicationEntityVo(ApplicationEntity applicationEntity,
				String auditExplanation) {
			this.applicationEntity = applicationEntity;
			this.auditExplanation = auditExplanation;
		}
	 private ApplicationEntity applicationEntity;
	private String bassApp;
	private String applyExplanation;//申请说明
	public String getApplyExplanation() {
		return applyExplanation;
	}
	public void setApplyExplanation(String applyExplanation) {
		this.applyExplanation = applyExplanation;
	}
	public String getBassApp() {
		return bassApp;
	}
	public void setBassApp(String bassApp) {
		this.bassApp = bassApp;
	}
	private String auditExplanation;//最新的审核说明
	public ApplicationEntity getApplicationEntity() {
		return applicationEntity;
	}
	public void setApplicationEntity(ApplicationEntity applicationEntity) {
		this.applicationEntity = applicationEntity;
	}
	public String getAuditExplanation() {
		return auditExplanation;
	}
	public void setAuditExplanation(String auditExplanation) {
		this.auditExplanation = auditExplanation;
	}
	 

}
