package com.chinasofti.rcloud.portal.cloudservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.cloudfoundry.CFClient;
import com.chinasofti.rcloud.dao.AccessKeyRecordEntityMapper;
import com.chinasofti.rcloud.dao.OrganizationEntityMapper;
import com.chinasofti.rcloud.portal.cloudservice.dao.CloudServiceEntityMapperExt;
import com.chinasofti.rcloud.portal.cloudservice.service.CloudServiceManagerService;
import com.chinasofti.rcloud.portal.userconsole.domain.AccessKeyRecordEntityExt;

@Service("cloudServiceManagerService")
public class CloudServiceManagerServiceImpl implements
		CloudServiceManagerService {

	@Autowired
	private CloudServiceEntityMapperExt cloudServiceEntityMapperExt;

	@Autowired
	private OrganizationEntityMapper organizationEntityMapper;

	@Autowired
	private CFClient cfClient;

	@Autowired
	private AccessKeyRecordEntityMapper accessKeyRecordEntityMapper;

	public List<HashMap<String, String>> getAllCloudService(String userId)
			throws Exception {
		return cloudServiceEntityMapperExt.getAllCloudService(userId);
	}

	@Override
	public HashMap<String, String> getServiceOrderInfo(String orderId) {
		return cloudServiceEntityMapperExt.getServiceOrderInfo(orderId);
	}

	@Override
	public Pagination<AccessKeyRecordEntityExt> getServiceCredentialList(
			Map<String, Object> paramMap) throws Exception {
		List<AccessKeyRecordEntityExt> accessKeyRecordList = cloudServiceEntityMapperExt
				.selectAccessKeyByPage(paramMap);
		int count = cloudServiceEntityMapperExt.countAccessKey(paramMap);

		Pagination<AccessKeyRecordEntityExt> pagination = new Pagination<AccessKeyRecordEntityExt>();
		pagination.setRows(accessKeyRecordList);
		pagination.setTotal(count);

		return pagination;
	}

	@Override
	public List<HashMap<String, String>> getCloudservicePlatform(String userId) {
		return cloudServiceEntityMapperExt.getCloudservicePlatform(userId);
	}

	@Override
	public List<HashMap<String, String>> getAllCloudServiceOrder(String userId) {
		return cloudServiceEntityMapperExt.getAllCloudServiceOrder(userId);
	}

	@Override
	public List<HashMap<String, String>> getCloudServiceOrder(String userId,
			String serviceId) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("serviceId", serviceId);
		return cloudServiceEntityMapperExt.getCloudServiceOrder(map);
	}

}
