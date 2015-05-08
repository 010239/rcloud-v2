package com.chinasofti.rcloud.portal.cloudshop.vo;

/**
 * 应用部署到RAE 基本属性
 * 
 * @ClassName: RaeAppVo
 * @Description: TODO
 * @author shimeihua
 * @date 2014年11月28日 上午11:16:19
 *
 */
public class RaeAppVo {
	private String applicationName;// 应用中文名称
	private String appVersion;// 应用版本
	private String detailDescription;// 应用描述
	private String domainNam;// 二级域名 (二级域名和创建应用的名字一致，但是要判断二级域名是否重复)
	private Integer hiskSize;// 硬盘大小
	private Integer meorySize;// 内存大小
	private String appliId;//应用id
	public String getAppliId() {
		return appliId;
	}

	public void setAppliId(String appliId) {
		this.appliId = appliId;
	}



	private String orderNum;//订单号
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	

	// 向UAA传值属性
	private String spaceName; // 工作空间名:对于机构管理员和机构开发者，需要从前台传入值 对于独立开发者可传可不传
	private String appGuid;   //创建应用Apps时保存的guid
	private String routeGuid; //创建route时产生的route_guid
	
	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDetailDescription() {
		return detailDescription;
	}

	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}


	public String getDomainNam() {
		return domainNam;
	}

	public void setDomainNam(String domainNam) {
		this.domainNam = domainNam;
	}

	public Integer getHiskSize() {
		return hiskSize;
	}

	public void setHiskSize(Integer hiskSize) {
		this.hiskSize = hiskSize;
	}

	public Integer getMeorySize() {
		return meorySize;
	}

	public void setMeorySize(Integer meorySize) {
		this.meorySize = meorySize;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getAppGuid() {
		return appGuid;
	}

	public void setAppGuid(String appGuid) {
		this.appGuid = appGuid;
	}

	public String getRouteGuid() {
		return routeGuid;
	}

	public void setRouteGuid(String routeGuid) {
		this.routeGuid = routeGuid;
	}

}
