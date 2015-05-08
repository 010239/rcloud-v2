package com.chinasofti.rcloud.portal.cloudshop.service;

import java.util.Map;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityExt;

public interface ApplicationService {
	
	ApplicationEntityExt selectApplicationById(String appId) throws Exception;
	
	ApplicationEntity selectAppById(String appId) throws Exception;
	
	Pagination<ApplicationEntityExt> searchAppByPage(Map<String, Object> searchParam) throws Exception;

}
