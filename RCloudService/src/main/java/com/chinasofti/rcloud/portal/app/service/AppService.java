package com.chinasofti.rcloud.portal.app.service;

import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.ApplicationTypeEntity;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityBlobExt;

public interface AppService {

	public List<ApplicationEntity> findForPortal() throws RCloudException;
	
	public List<ApplicationTypeEntity> findAppType() throws RCloudException;
	
	public Pagination<ApplicationEntityBlobExt> getApplicationList(Map<String, Object> searchParams) throws RCloudException;
}
