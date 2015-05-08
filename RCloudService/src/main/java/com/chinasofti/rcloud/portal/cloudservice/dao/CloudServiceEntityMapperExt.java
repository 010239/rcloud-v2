package com.chinasofti.rcloud.portal.cloudservice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.OrganizationEntity;
import com.chinasofti.rcloud.portal.userconsole.domain.AccessKeyRecordEntityExt;

public interface CloudServiceEntityMapperExt extends BaseDao {

	List<HashMap<String, String>> getAllCloudService(String userId)
			throws Exception;

	HashMap<String, String> getServiceOrderInfo(String orderId);

	List<HashMap<String, String>> getServiceCredentialList(
			HashMap<String, String> map);

	List<HashMap<String, String>> getCloudservicePlatform(String userId);

	List<HashMap<String, String>> getAllCloudServiceOrder(String userId);

	HashMap<String, String> getUserInfo(String userId);

	OrganizationEntity getOrganizationEntity(String orgId);

	List<HashMap<String, String>> getCloudServiceOrder(
			HashMap<String, String> map);

	List<AccessKeyRecordEntityExt> selectAccessKeyByPage(
			Map<String, Object> paramMap);

	int countAccessKey(Map<String, Object> paramMap);

	/**
	 * 查询所有服务名称及url
	 * 
	 * @return
	 */
	List<HashMap<String, String>> getManageUrlList();

}
