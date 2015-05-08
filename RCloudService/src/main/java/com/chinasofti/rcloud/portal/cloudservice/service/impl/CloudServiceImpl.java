package com.chinasofti.rcloud.portal.cloudservice.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.dao.ServiceEntityMapper;
import com.chinasofti.rcloud.dao.ServiceRportalEntityMapper;
import com.chinasofti.rcloud.domain.ServiceEntity;
import com.chinasofti.rcloud.domain.ServiceEntityExample;
import com.chinasofti.rcloud.domain.ServiceRportalEntityExample;
import com.chinasofti.rcloud.portal.cloudservice.dao.CloudServiceEntityMapperExt;
import com.chinasofti.rcloud.portal.cloudservice.service.CloudService;

@Service
public class CloudServiceImpl implements CloudService {

	@Autowired
	private ServiceEntityMapper serviceEntityMapper;

	@Autowired
	private CloudServiceEntityMapperExt cloudServiceEntityMapperExt;

	@Autowired
	private ServiceRportalEntityMapper serviceRportalEntityMapper;

	@Override
	public List<ServiceEntity> findForPortal() throws RCloudException {
		// TODO Auto-generated method stub
		ServiceEntityExample example = new ServiceEntityExample();
		example.or(example.createCriteria().andMarkDeleteEqualTo(0));// 未删除
		return serviceEntityMapper.selectByExample(example);
	}

	@Override
	public HashMap<String, String> getUserInfo(String userId) {
		return cloudServiceEntityMapperExt.getUserInfo(userId);
	}

	@Override
	public List<HashMap<String, String>> getManageUrlList()
			throws RCloudException {
		List<HashMap<String, String>> result = cloudServiceEntityMapperExt
				.getManageUrlList();
		return result;
	}

	@Override
	public HashMap<String, String> getManageUrl(String serviceName)
			throws RCloudException {
		HashMap<String, String> result = new HashMap<String, String>();

		ServiceRportalEntityExample example = new ServiceRportalEntityExample();
		ServiceRportalEntityExample.Criteria criteria = example
				.createCriteria();
		criteria.andServiceNameEqualTo(serviceName);
		String manageUrl = serviceRportalEntityMapper.selectByExample(example)
				.get(0).getManageUrl();

		result.put("MANAGE_URL", manageUrl);
		result.put("SERVICE_NAME", serviceName);

		return result;
	}
}
