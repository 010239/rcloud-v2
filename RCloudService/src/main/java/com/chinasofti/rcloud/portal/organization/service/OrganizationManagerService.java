package com.chinasofti.rcloud.portal.organization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.chinasofti.rcloud.common.RCloudException;

public interface OrganizationManagerService {

	// /**
	// * 注册工作空间
	// */
	// void spaceRegister(SpaceEntity spaceEntity,CFClientToken clientToken)
	// throws RCloudException;;
	//
	// /**
	// * 删除工作空间
	// */
	// void deletespace(String spaceId) throws RCloudException;
	//
	// /**
	// * 用户与工作空间绑定
	// */
	// void spaceBind(UserEntity user,ArrayList<String> userList,String
	// spaceId,CFClientToken clientToken) throws RCloudException;;

	/**
	 * 机构信息查询
	 */
	ArrayList<HashMap<String, String>> getOrganizationInfo(String orgId)
			throws RCloudException;

	/**
	 * 机构开发者信息查询
	 */
	ArrayList<HashMap<String, String>> getOrganizationDeveloperInfo(
			Map<String, Object> map) throws RCloudException;

	/**
	 * 机构开发者和机构管理员空间名和组织名查询
	 */
	Map<String, String> getOrgAndSpaInfo(String uaa_guid)
			throws RCloudException;

	/**
	 * 独立开发者空间名和组织名查询
	 */
	Map<String, String> getInDevInfo(String uaa_guid) throws RCloudException;

	/**
	 * 判断用户角色
	 */
	String getRoleCode(String uaa_guid) throws RCloudException;

}
