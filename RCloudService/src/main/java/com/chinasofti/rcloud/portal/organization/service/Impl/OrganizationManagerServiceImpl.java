package com.chinasofti.rcloud.portal.organization.service.Impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.cloudfoundry.CFClient;
import com.chinasofti.rcloud.dao.OrganizationEntityMapper;
import com.chinasofti.rcloud.dao.SpaceEntityMapper;
import com.chinasofti.rcloud.dao.SpaceToUserEntityMapper;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.SpaceToUserEntityExample;
import com.chinasofti.rcloud.domain.UserEntityExample;
import com.chinasofti.rcloud.portal.login.service.UserService;
import com.chinasofti.rcloud.portal.organization.dao.OrganizationManagerMapperExt;
import com.chinasofti.rcloud.portal.organization.service.OrganizationManagerService;


@Service("organizationManagerService")
public class OrganizationManagerServiceImpl implements OrganizationManagerService{
	 
	@Autowired
	private SpaceEntityMapper spaceEntityMapper;
	
	@Autowired
	private SpaceToUserEntityMapper spaceToUserEntityMapper;
	
	@Autowired
	private OrganizationManagerMapperExt organizationManagerMapperExt;
	
	@Autowired
	private OrganizationEntityMapper organizationEntityMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserEntityMapper userMapper;
	
	@Autowired
	private CFClient cfClient;
	
	private Logger logger = Logger.getLogger(OrganizationManagerServiceImpl.class);

	
//	/**
//	 * 注册工作空间
//	 */
//	@Override
//	public void spaceRegister(SpaceEntity spaceEntity,CFClientToken clientToken) throws RCloudException {
//		
//		OrganizationEntity organizationEntity = organizationEntityMapper.selectByPrimaryKey(spaceEntity.getOrganizationId());
//		UUIDHexGenerator g = new UUIDHexGenerator();
//		spaceEntity.setSpaceId((String)g.generate());
//		spaceEntity.setCfOrgGuid(organizationEntity.getCfOrgGuid());
//		try {
//			cfClient.createSpace(spaceEntity, clientToken);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RCloudException(e);
//		}
//		
//		spaceEntityMapper.insertSelective(spaceEntity);
//	}
//	
//	/**
//	 * 删除工作空间(没有此方法)
//	 */
//	@Override
//	public void deletespace(String spaceId) throws RCloudException{
//		spaceEntityMapper.deleteByPrimaryKey(spaceId);
//	}
//
//
//	/**
//	 * 用户与工作空间绑定
//	 */
//	@Override
//	public void spaceBind(UserEntity user,ArrayList<String> userList, String spaceId,CFClientToken clientToken) throws RCloudException {
//		for(int i=0;i<userList.size();i++){
//			SpaceToUserEntityKey entity=new SpaceToUserEntityKey();
//			entity.setSpaceId(spaceId);
//			entity.setUserId(userList.get(i));
//			
//			SpaceEntity spaceEntity = spaceEntityMapper.selectByPrimaryKey(spaceId);
//			
//			try {
//				cfClient.bindUserToSpace(user, spaceEntity, clientToken);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RCloudException(e);
//			}
//			
//			spaceToUserEntityMapper.insertSelective(entity);
//		}
//		
//		//cfClient.b
//	}


	/**
	 * 机构信息查询
	 */
	@Override
	public ArrayList<HashMap<String, String>> getOrganizationInfo(String orgId) throws RCloudException {
		ArrayList<HashMap<String,String>> orgList=organizationManagerMapperExt.getOrganizationInfo(orgId);
		return orgList;
	}

	/**
	 * 机构开发者信息查询
	 */
	@Override
	public ArrayList<HashMap<String, String>> getOrganizationDeveloperInfo(Map<String,Object> map) throws RCloudException {
		ArrayList<HashMap<String, String>> userList=organizationManagerMapperExt.getOrganizationDeveloperInfo(map);
		return userList;
	}
	
	/**
	 * 机构开发者和机构管理员空间名和组织名查询
	 */
	@Override
	public Map<String, String> getOrgAndSpaInfo(String uaa_guid)
			throws RCloudException {
		Map<String, String> result = new HashMap<String, String>();
		
		//查询org_name
		UserEntityExample example = new UserEntityExample();
		UserEntityExample.Criteria criteria = example.createCriteria();
		criteria.andUaaGuidEqualTo(uaa_guid);
		String orgId =  userMapper.selectByExample(example).get(0).getOrganizationId();
		String orgName = organizationEntityMapper.selectByPrimaryKey(orgId).getOrganizationName();
		
		//查询space_name
		String userId = userMapper.selectByExample(example).get(0).getUserId();
		SpaceToUserEntityExample example1 = new SpaceToUserEntityExample();
		SpaceToUserEntityExample.Criteria criteria1 = example1.createCriteria();
		criteria1.andUserIdEqualTo(userId);
		String spaceId = spaceToUserEntityMapper.selectByExample(example1).get(0).getSpaceId();
		String spaceName = spaceEntityMapper.selectByPrimaryKey(spaceId).getSpaceName();
		
		result.put("ORGANIZATION_NAME", orgName);
		result.put("SPACE_NAME", spaceName);
		
		return result;
	}
	
	/**
	 * 独立开发者空间名和组织名查询
	 */
	@Override
	public Map<String, String> getInDevInfo(String uaa_guid)
			throws RCloudException {
		Map<String, String> result = new HashMap<String, String>();
		
		UserEntityExample example = new UserEntityExample();
		UserEntityExample.Criteria criteria = example.createCriteria();
		criteria.andUaaGuidEqualTo(uaa_guid);
		String email = userMapper.selectByExample(example).get(0).getUserEmail();
		
		result.put("ORGANIZATION_NAME", getCFName(email, "org_"));
		result.put("SPACE_NAME", getCFName(email, "space_"));
				
		return result;
	}
	
	private String getCFName(String str, String name) {		
		  name+=str.substring(str.indexOf("@")+1,str.length());
		  name+="_"+str.substring(0,str.indexOf("@"));
		  name=name.replaceAll("\\.", "_");
		return name;
	}
	
	/**
	 * 获取用户角色
	 * @param uaa_guid
	 * @return
	 * @throws RCloudException
	 */
	@Override
	public String getRoleCode(String uaa_guid) throws RCloudException {
		
		UserEntityExample example = new UserEntityExample();
		UserEntityExample.Criteria criteria = example.createCriteria();
		criteria.andUaaGuidEqualTo(uaa_guid);
		String roleCode= userMapper.selectByExample(example).get(0).getRoleCode();
		return roleCode;
	}

}
