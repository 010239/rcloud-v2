package com.chinasofti.rcloud.portal.organization.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.chinasofti.rcloud.common.dao.BaseDao;

public interface OrganizationManagerMapperExt extends BaseDao{

	ArrayList<HashMap<String, String>> getOrganizationInfo(String orgId);

	ArrayList<HashMap<String, String>> getOrganizationDeveloperInfo(Map<String,Object> map);
	
	List<Map<String, String>> getUserOfOrganization(Map<String, Object> paramMap);
}
