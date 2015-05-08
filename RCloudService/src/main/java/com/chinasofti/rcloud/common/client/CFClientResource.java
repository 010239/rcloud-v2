package com.chinasofti.rcloud.common.client;

import java.util.ResourceBundle;

public class CFClientResource {

	private static ResourceBundle resource = ResourceBundle
			.getBundle("deploymodel");

	public static String DEPLOY_ENV = resource.getString("deploy.env").trim();

	public static String PAAS_PLATFORM_ADDRESS = resource.getString(
			"paas.platform.address").trim();

	public static String PAAS_PLATFORM_UAA = resource.getString(
			"paas.platform.uaa").trim();

	public static String PAAS_PLATFORM_URLPATH = resource.getString(
			"paas.platform.urlPath").trim();

	public static String PAAS_PLATFORM_ADMIN_USER = resource.getString(
			"paas.platform.admin_user").trim();

	public static String PAAS_PLATFORM_ADMIN_PW = resource.getString(
			"paas.platform.admin_pw").trim();

	public static String PAAS_PLATFORM_AUTHORIZATION_HEADER_KEY = resource
			.getString("paas.platform.authorization_header_key").trim();

	public static String PAAS_PLATFORM_ADMIN_SCOPE = resource.getString(
			"paas.platform.admin_scope").trim();

	public static String PAAS_PLATFORM_COOKIE_DOMAIN = resource.getString(
			"paas.platform.cookie.domain").trim();

	public static String PAAS_PLATFORM_GRANT_TYPE = "password";

	public static String PAAS_PLATFORM_TOKEN_URL = PAAS_PLATFORM_UAA
			+ "/oauth/token";

	public static String PAAS_PLATFORM_CREATE_USER_URL = PAAS_PLATFORM_UAA
			+ "/Users";

	public static String PAAS_PLATFORM_CREATE_USER_GUID_URL = PAAS_PLATFORM_ADDRESS
			+ PAAS_PLATFORM_URLPATH + "/users";

	public static String PAAS_PLATFORM_CREATE_ORG_URL = PAAS_PLATFORM_ADDRESS
			+ PAAS_PLATFORM_URLPATH + "/organizations";

	public static String PAAS_PLATFORM_QUOTA_LIST_URL = PAAS_PLATFORM_ADDRESS
			+ PAAS_PLATFORM_URLPATH + "/quota_definitions";

	public static String getOrgManagerUrl(String userGuid, String orgGuid) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH
				+ "/organizations/" + orgGuid + "/managers/" + userGuid;
	}

	public static String getOrgUserUrl(String userGuid, String orgGuid) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH
				+ "/organizations/" + orgGuid + "/users/" + userGuid;
	}

	public static String getCreateSpaceUrl(boolean async, int depth) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/spaces?async="
				+ async + "&inline-relations-depth=" + depth;
	}

	public static String getCreateSpaceUrl() {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/spaces";
	}

	public static String getSpaceDeveloperUrl(String spaceGuid, String userGuid) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/spaces/"
				+ spaceGuid + "/developers/" + userGuid;
	}

	public static String getSpaceManagerUrl(String spaceGuid, String userGuid) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/spaces/"
				+ spaceGuid + "/managers/" + userGuid;
	}

	public static String getSpaceDeleteUrl(String spaceGuid, String userGuid) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/spaces/"
				+ spaceGuid + "/developers/" + userGuid;
	}

	public static String getModifyUserPasswordUrl(String userGuid) {
		return PAAS_PLATFORM_UAA + "/Users/" + userGuid + "/password";
	}

	public static String getUpdataUserUrl(String userGuid) {
		return PAAS_PLATFORM_UAA + "Users/" + userGuid;
	}

	public static String getAdjustPlanUrl(String orgGuid) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH
				+ "/organizations/" + orgGuid;
	}

	/**
	 * 
	 * @Title: getCreateAppsUrl
	 * @Description: 获取创建app的url地址
	 * @author sunlulu
	 * @param @param async
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getCreateAppsUrl(boolean async) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/apps";
		// + async;
	}

	public static String uploadAppsUrl(String guid) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/apps/" + guid
				+ "/bits";
	}

	public static String PAAS_PLATFORM_APPS_LIST_URL = PAAS_PLATFORM_ADDRESS
			+ PAAS_PLATFORM_URLPATH + "/apps";

	// /v2/spaces
	/**
	 * 
	 * @Title: getListSpaceUrl 获取所有space的URL
	 * @Description: TODO
	 * @author sunlulu
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getListSpaceUrl() {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/spaces";
	}

	public static String getUploadAppUrl(String appGuid) {// /v2/apps/:guid/bits
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/apps/"
				+ appGuid + "/bits";
	}

	public static String getDomainsUrl() {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH
				+ "/domains?inline-relations-depth=1";
	}

	public static String getRoutes() {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/routes";
	}

	public static String createRoutesUrl() {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH
				+ "/routes?async=true&inline-relations-depth=1";
	}

	/**
	 * /v2/routes/:guid/apps/:app_guid
	 * 
	 * @Title: bingAppandRoute
	 * @Description: TODO
	 * @author sunlulu
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String bingAppandRoute(String routeGuid, String appGuid) {
		return PAAS_PLATFORM_ADDRESS + PAAS_PLATFORM_URLPATH + "/routes/"
				+ routeGuid + "/apps/" + appGuid;
	}

}
