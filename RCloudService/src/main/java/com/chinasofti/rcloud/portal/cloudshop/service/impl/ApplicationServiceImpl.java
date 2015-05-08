package com.chinasofti.rcloud.portal.cloudshop.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.constants.ExceptionContants;
import com.chinasofti.rcloud.dao.ApplicationEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.portal.cloudshop.dao.ApplicationMapperExt;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityExt;
import com.chinasofti.rcloud.portal.cloudshop.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	@Autowired
	private ApplicationMapperExt applicationMapperExt;
	
	@Autowired
	private ApplicationEntityMapper applicationEntityMapper;

	private Logger logger = Logger.getLogger(ApplicationServiceImpl.class);
	
	public ApplicationEntityExt selectApplicationById(String appId) throws Exception {
		ApplicationEntityExt applicationEntityExt = applicationMapperExt.selectApplicationById(appId);
		
		if (applicationEntityExt == null) {
			logger.error("查询的应用不存在，应用ID" + appId);
			throw new RCloudException(ExceptionContants.RTN_APPLICATION_NOT_FOUND);
		}
		
		return applicationEntityExt;
	}

	public ApplicationEntity selectAppById(String appId) throws Exception {
		ApplicationEntity applicationEntity = applicationEntityMapper.selectByPrimaryKey(appId);
		return applicationEntity;
	}

	public Pagination<ApplicationEntityExt> searchAppByPage(Map<String, Object> searchParam) throws Exception {
		Pagination<ApplicationEntityExt> pagination = new Pagination<ApplicationEntityExt>();
		List<ApplicationEntityExt> applicationList = applicationMapperExt.searchApplicationByPage(searchParam);
		int count = applicationMapperExt.countApplication(searchParam);
		pagination.setRows(applicationList);
		pagination.setTotal(count);
		
		return pagination;
	}


}
