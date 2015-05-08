package com.chinasofti.rcloud.portal.cloudservice.service;

import java.util.HashMap;
import java.util.List;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.ServiceEntity;

public interface CloudService {

	public List<ServiceEntity> findForPortal()  throws RCloudException;

	public HashMap<String, String> getUserInfo(String userId);
	
	List<HashMap<String, String>> getManageUrlList() throws RCloudException;
	
	HashMap<String, String> getManageUrl(String serviceName) throws RCloudException;
}
