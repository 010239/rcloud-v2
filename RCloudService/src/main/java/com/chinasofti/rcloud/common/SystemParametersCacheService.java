package com.chinasofti.rcloud.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinasofti.rcloud.common.constants.BusinessConstants;
import com.chinasofti.rcloud.dao.PermissionEntityMapper;
import com.chinasofti.rcloud.dao.PermissionToRoleEntityMapper;
import com.chinasofti.rcloud.dao.RoleEntityMapper;
import com.chinasofti.rcloud.dao.ServiceEntityMapper;
import com.chinasofti.rcloud.domain.PermissionEntity;
import com.chinasofti.rcloud.domain.PermissionToRoleEntityKey;
import com.chinasofti.rcloud.domain.RoleEntity;
import com.chinasofti.rcloud.domain.ServiceEntity;
import com.chinasofti.rcloud.domain.ServiceEntityExample;
import com.google.code.ssm.api.ReadThroughAssignCache;

@Component
public class SystemParametersCacheService {
	
	@Autowired
	PermissionEntityMapper permission;
	
	@Autowired
	RoleEntityMapper role;
	
	@Autowired
	PermissionToRoleEntityMapper permissionToRole;
	
	@Autowired
	ServiceEntityMapper serviceMapper;

	@ReadThroughAssignCache(assignedKey = "_role_permission", expiration = 500, namespace = BusinessConstants.SYSTEM_INIT_KEY_ROLE) 
	public Map getRoleToPermissionMap() {
		 List<PermissionEntity> permissionList = permission.selectByExample(null);
		 List<RoleEntity> roleList = role.selectByExample(null);
		 List<PermissionToRoleEntityKey> permissionToRoleList = permissionToRole.selectByExample(null);
		 Map roleMap = new HashMap();
		 Map perMap = new HashMap();
		 Map roMap = new HashMap();
		 for(PermissionEntity per : permissionList){
			 perMap.put(per.getPermissionCode(), per);
		 }
		 for(RoleEntity ro:roleList){
			 roMap.put(ro.getRoleCode(), ro);
		 }
		 for(PermissionToRoleEntityKey ele:permissionToRoleList){
			 String percode = ele.getPermissionCode();
			 String rocode = ele.getRoleCode();
			 if(roleMap.containsKey(rocode)){
				 Map tempMap = (Map)roleMap.get(rocode);
				 tempMap.put(percode, perMap.get(percode));
			 }else{
				 Map tempMap = new HashMap();
				 tempMap.put(percode, perMap.get(percode));
				 roleMap.put(rocode, tempMap);
			 }
		 }
		
		return roleMap;  
	}
	
	@ReadThroughAssignCache(assignedKey = "_role", expiration = 500, namespace = BusinessConstants.SYSTEM_INIT_KEY_ROLE) 
	public Map getRoleMap() {
		 List<RoleEntity> roleList = role.selectByExample(null);
		 Map roMap = new HashMap();
		 for(RoleEntity ro:roleList){
			 roMap.put(ro.getRoleCode(), ro);
		 }		
		return roMap;  
	}
	
	@ReadThroughAssignCache(assignedKey = "_permission", expiration = 500, namespace = BusinessConstants.SYSTEM_INIT_KEY_ROLE) 
	public Map getPermissionMap() {
		 List<PermissionEntity> permissionList = permission.selectByExample(null);
		 Map perMap = new HashMap();
		 for(PermissionEntity per : permissionList){
			 perMap.put(per.getPermissionCode(), per);
		 }		
		return perMap;  
	}
	
	@ReadThroughAssignCache(assignedKey = "_service", expiration = 500, namespace = BusinessConstants.SYSTEM_INIT_KEY_ROLE) 
	public Map<String, String> getServiceMap() {
		ServiceEntityExample example = new ServiceEntityExample();
		example.createCriteria().andMarkDeleteEqualTo(CommonConstants.NOT_DELETE);
		List<ServiceEntity> serviceList = serviceMapper.selectByExample(example);
		
		Map<String, String> serviceMap = new HashMap<String, String>();
		for (ServiceEntity serviceEntity : serviceList) {
			serviceMap.put(serviceEntity.getServiceName(), serviceEntity.getServiceId());
		}
		
		return serviceMap;
	}
}
