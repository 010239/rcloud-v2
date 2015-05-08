package com.chinasofti.rcloud.portal.cloudshop.dao;

import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityBlobExt;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityExt;

public interface ApplicationMapperExt extends BaseDao {
	
	ApplicationEntityExt selectApplicationById(String appId);
	
	List<ApplicationEntityExt> searchApplicationByPage(Map<String, Object> searchParam);
	
	int countApplication(Map<String, Object> searchParam);
		
	List<ApplicationEntity> selectApplicationPublishByPage(Map<String, Object> searchParams);
	
	int countApplicationPublish(Map<String, Object> searchParams);
	
    List<ApplicationEntityBlobExt> selectApplicationbytypeByPage(Map<String, Object> searchParams);
	
	int countApplicationbytype(Map<String, Object> searchParams);
}
