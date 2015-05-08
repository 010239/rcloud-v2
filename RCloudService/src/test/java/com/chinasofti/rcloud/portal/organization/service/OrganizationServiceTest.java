package com.chinasofti.rcloud.portal.organization.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chinasofti.rcloud.common.PinyinUtil;
import com.chinasofti.rcloud.common.UUIDHexGenerator;
import com.chinasofti.rcloud.common.cloudfoundry.CFClient;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CFClientToken;
import com.chinasofti.rcloud.dao.OrganizationEntityMapper;
import com.chinasofti.rcloud.dao.SpaceEntityMapper;
import com.chinasofti.rcloud.dao.SpaceToUserEntityMapper;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.AccessKeyRecordEntity;
import com.chinasofti.rcloud.domain.OrganizationEntity;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.SpaceToUserEntityExample;
import com.chinasofti.rcloud.domain.SpaceToUserEntityKey;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.domain.UserEntityExample;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class OrganizationServiceTest {
	@Autowired
	OrganizationManagerService organizationManagerService;
	
	@Autowired
	OrganizationEntityMapper organizationEntityMapper;
	
	@Autowired
	SpaceToUserEntityMapper spaceToUserEntityMapper; //机构管理员和工作空间的一对多关系
	
	@Autowired
	UserEntityMapper userEntityMapper;
	
	@Autowired
	SpaceEntityMapper spaceEntityMapper;
	
	@Autowired
	CFClient cfClient;
	
	public static String userId = "8a8be568477079a201477079d49a0001";
	public static final String ORG_DEVLOPER = "400002";     //机构开发者
	public static UUIDHexGenerator u = new UUIDHexGenerator();
	private Logger logger = Logger.getLogger(OrganizationServiceTest.class);

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * 测试注册工作空间方法
	 */
//	@Test	
//	public void testspaceRegister() {
//		try {
//			
//			UserEntity userEntity = userEntityMapper.selectByPrimaryKey(userId);
//			OrganizationEntity organizationEntity = organizationEntityMapper.selectByPrimaryKey(userEntity.getOrganizationId());
//			userEntity.setPassword("usb");
//			CFClientToken clientToken = cfClient.login(userEntity);
//			
//			
//			SpaceEntity spaceEntity=new SpaceEntity();
//			spaceEntity.setSpaceId((String)u.generate());
//			
//			spaceEntity.setCfOrgGuid(organizationEntity.getCfOrgGuid()); //
//			
//			//spaceEntity.setCfSpaceGuid("a");
//			spaceEntity.setOrganizationId(organizationEntity.getOrganizationId());
//			spaceEntity.setSpaceChname("测试数据10");
//			spaceEntity.setSpaceName(PinyinUtil.cn2Spell(spaceEntity.getSpaceChname()));
//			spaceEntity.setSpaceDescription("绑定到用户");
//			//organizationManagerService.spaceRegister(spaceEntity);
//			
//			organizationManagerService.spaceRegister(spaceEntity, clientToken);
//			//注册成功以后，保存到表中
//			SpaceToUserEntityKey paceToUserEntityKey = new SpaceToUserEntityKey();
//			paceToUserEntityKey.setSpaceId(spaceEntity.getSpaceId());
//			paceToUserEntityKey.setUserId(userEntity.getUserId());
//			spaceToUserEntityMapper.insert(paceToUserEntityKey);
//			
//			logger.debug("测试成功");
//		}catch(Error e){
//			logger.error(e.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 测试删除工作空间方法
	 */
//	@Test	
//	public void testdeleteByPrimaryKey() {
//		try {
//			organizationManagerService.deletespace("11");
//			logger.debug("测试成功");
//		}catch(Error e){
//			logger.error(e.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	//注册结构开发者
//	@Test
//	public void saveOrgDevloper(){
//try {
//			
//			//注册开发者
//			UserEntity userEntity = userEntityMapper.selectByPrimaryKey(userId);
//			OrganizationEntity organizationEntity = organizationEntityMapper.selectByPrimaryKey(userEntity.getOrganizationId());
//			
//			//userEntity.setPassword("usb");//登录
//			//CFClientToken clientToken = cfClient.login(userEntity);
//			
//			UserEntity testDevloper = new UserEntity(); //机构开发者
//			testDevloper.setUserEmail("112@1763.com");
//			testDevloper.setUserName("112@1763.com");
//			testDevloper.setPassword("112");
//			testDevloper.setUserId((String)u.generate());
//			testDevloper.setRoleCode(ORG_DEVLOPER);
//			testDevloper.setCreateTime(new Date());
//			testDevloper.setOrganizationId(organizationEntity.getOrganizationId());
//			
//			cfClient.createOrgDeveloper(testDevloper);//注册机构开发者
//			
//			userEntityMapper.insert(testDevloper);
//			
//		}catch(Error e){
//			logger.error(e.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 
	 * 测试机构开发者与工作空间绑定方法
	 */
	@Test	
	public void testspaceBind() {
		try {
			//1.获取机构管理员用户
			UserEntity userEntity = userEntityMapper.selectByPrimaryKey(userId);
			//2.获取机构管理员对应的组织
			//OrganizationEntity organizationEntity = organizationEntityMapper.selectByPrimaryKey(userEntity.getOrganizationId());
			//3.登录cf平台
			userEntity.setPassword("usb");
			CFClientToken clientToken = cfClient.login(userEntity);
			//4.获取机构管理员的工作空间
			SpaceToUserEntityExample example = new SpaceToUserEntityExample();
			example.createCriteria().andUserIdEqualTo(userEntity.getUserId()); //获取相应的组织空间
			List<SpaceToUserEntityKey> list = spaceToUserEntityMapper.selectByExample(example);
			SpaceToUserEntityKey spaceToUserEntityKey = list.get(0);//获取第一工作空间
			
			//5.从数据库中查找对应的工作空间
			SpaceEntity spaceEntity = spaceEntityMapper.selectByPrimaryKey(spaceToUserEntityKey.getSpaceId());
			//6.获取开发者
			UserEntityExample userex = new UserEntityExample();
			userex.createCriteria().andRoleCodeEqualTo(ORG_DEVLOPER).andOrganizationIdEqualTo(userEntity.getOrganizationId());
			List<UserEntity> listDev = userEntityMapper.selectByExample(userex);
			for (UserEntity userEntity2 : listDev) {
				cfClient.bindUserToSpace(userEntity2, spaceEntity, clientToken);
				
				SpaceToUserEntityKey paceToUserEntityKey = new SpaceToUserEntityKey();
				paceToUserEntityKey.setSpaceId(spaceEntity.getSpaceId());
				paceToUserEntityKey.setUserId(userEntity2.getUserId());
				spaceToUserEntityMapper.insert(paceToUserEntityKey);
				
			}
			
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试机构信息查询方法
	 */
//	@Test	
//	public void testgetOrganizationInfo() {
//		try {
//			
//			ArrayList<HashMap<String, String>> orgid=organizationManagerService.getOrganizationInfo("1");
//			logger.debug("测试成功");
//		}catch(Error e){
//			logger.error(e.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 测试机构信息查询方法
	 */
//	@Test	
//	public void testgetOrganizationDeveloperInfo() {
//		try {
//			
//			ArrayList<HashMap<String, String>> userList=organizationManagerService.getOrganizationDeveloperInfo("1");
//			logger.debug("测试成功");
//		}catch(Error e){
//			logger.error(e.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	

}
