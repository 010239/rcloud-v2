package com.chinasofti.rcloud.portal.cloudservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.portal.userconsole.domain.AccessKeyRecordEntityExt;



public interface CloudServiceManagerService {
	
	List<HashMap<String, String>>  getAllCloudService (String userId) throws Exception;

	HashMap<String, String> getServiceOrderInfo(String orderId);

	Pagination<AccessKeyRecordEntityExt> getServiceCredentialList(Map<String, Object> paramMap) throws Exception;

	List<HashMap<String, String>> getCloudservicePlatform(String userId);

	List<HashMap<String, String>> getAllCloudServiceOrder(String userId);

	List<HashMap<String, String>> getCloudServiceOrder(String userId,
			String serviceId);
	
}
