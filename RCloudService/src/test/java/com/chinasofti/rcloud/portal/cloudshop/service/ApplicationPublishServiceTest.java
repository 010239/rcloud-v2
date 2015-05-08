package com.chinasofti.rcloud.portal.cloudshop.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.chinasofti.rcloud.common.PageParameter;
import com.chinasofti.rcloud.dao.ApplicationEntityMapper;
import com.chinasofti.rcloud.dao.ApplicationOrderEntityMapper;
import com.chinasofti.rcloud.dao.ApplyEntityMapper;
import com.chinasofti.rcloud.dao.OrganizationEntityMapper;
import com.chinasofti.rcloud.dao.RoleEntityMapper;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationEntityExample;
import com.chinasofti.rcloud.domain.ApplicationEntityWithBLOBs;
import com.chinasofti.rcloud.domain.ApplicationOrderEntityWithBLOBs;
import com.chinasofti.rcloud.domain.ApplyEntity;
import com.chinasofti.rcloud.domain.ApplyEntityExample;
import com.chinasofti.rcloud.domain.OrganizationEntity;
import com.chinasofti.rcloud.domain.RoleEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.userorder.dao.ApplicationOrderEntityMapperExt;

/**
 * @author zhangjiaxing
 *
 * 2014年7月15日
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class ApplicationPublishServiceTest {
	
	@Autowired
	private ApplicationPublishService service;
	
	@Autowired
	private ApplicationEntityMapper applicationEntityMapper;
	
	@Autowired
	private ApplicationOrderEntityMapperExt applicationOrderEntityMapperExt;
	
	@Autowired
	private UserEntityMapper userMapper;
	
	@Autowired
	private RoleEntityMapper roleMapper;
	
	@Autowired
	private ApplicationOrderEntityMapper applicationOrderEntityMapper;
	
	@Autowired
	private OrganizationEntityMapper organizationMapper;
	
	@Autowired
	private ApplyEntityMapper applyEntityMapper;
		
	
	private Logger logger = Logger.getLogger(ApplicationPublishServiceTest.class);
	
	private String testId = "400003";
	
	

	@Before
	public void initTest(){
		try{
			RoleEntity j = roleMapper.selectByPrimaryKey(testId);
			if ( j == null ) {
				RoleEntity entity = new RoleEntity();
				entity.setRoleCode(testId);
				entity.setRoleName(testId);
				roleMapper.insertSelective(entity);
				logger.info("测试用例1初始化成功！");
			}
			OrganizationEntity k = organizationMapper.selectByPrimaryKey(testId);
			if ( k == null ) {
				OrganizationEntity entity = new OrganizationEntity();
				entity.setOrganizationId(testId);
				entity.setCfOrgGuid(testId);
				entity.setOrganizationName(testId);
				organizationMapper.insertSelective(entity);
				logger.info("测试用例2初始化成功！");
			}
			
			
			UserEntity i = userMapper.selectByPrimaryKey(testId);
			if ( i == null ) {
				UserEntity entity = new UserEntity();
				entity.setUserId(testId);
				entity.setUserName(testId);
				entity.setPassword(testId);
				entity.setUserEmail(testId);
				entity.setMobilePhone(testId);
				entity.setCreateTime(new Date());
				entity.setRoleCode(testId);
				entity.setUaaGuid(testId);
				entity.setOrganizationId(testId);
				userMapper.insertSelective(entity);
				logger.info("测试用例3初始化成功！");
			}		
			ApplicationEntityWithBLOBs l = applicationEntityMapper.selectByPrimaryKey(testId);
			if ( l==null ) {
				ApplicationEntityWithBLOBs entity = new ApplicationEntityWithBLOBs();
				entity.setApplicationId(testId);
				entity.setApplicationName(testId);
				entity.setCreatedTime(new Date());
				entity.setStatus(1);
				entity.setMarkDelete(0);
				entity.setProviderId(testId);
				applicationEntityMapper.insert(entity);
				logger.info("测试用例4初始化成功！");
			}
			ApplicationOrderEntityWithBLOBs m = applicationOrderEntityMapper.selectByPrimaryKey(testId);
			if ( m== null) {
				ApplicationOrderEntityWithBLOBs entity = new ApplicationOrderEntityWithBLOBs();
				entity.setOrderId(testId);
				entity.setOrderNumber(testId);
				entity.setApplicationId(testId);
				entity.setBuyerId(Integer.toString(1));
				entity.setProviderId(testId);
				entity.setMaintenanceCosts(new BigDecimal(1000));
				applicationOrderEntityMapper.insertSelective(entity);
				logger.info("测试用例5初始化成功！");			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAll(){	
		this.testGetApplicationPublish();
		this.testGetApplicationPublishInfo();
		this.testUpdateApplicationPublishInfo();
		this.testDeleteApplicationPublish();
		this.testPublishNewApplication();
	}
	
	
	public void testGetApplicationPublish() {
		try {
			Map<String, Object> searchParams = new HashMap<String,Object>();
			PageParameter page = new PageParameter();
			page.setCurrentPage(1);
			page.setPageSize(10);
			searchParams.put("page", page);
			searchParams.put("testId", testId);
			Assert.assertNotNull(service.getApplicationPublishByPage(searchParams));
			logger.info("测试1成功！");
		} catch(Error e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	public void testGetApplicationPublishInfo() {
		try {
			Assert.assertNotNull(service.getApplicationPublishInfo(testId));
			logger.info("测试2成功！");
		} catch(Error e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void testUpdateApplicationPublishInfo() {
		try {
			ApplicationEntityWithBLOBs newApp = new ApplicationEntityWithBLOBs();
			newApp.setApplicationName(testId);
			newApp.setSubDomain("WWW");
			newApp.setContactPerson(testId);
			newApp.setContactPhone("1111");
			newApp.setApplicationId(testId);
			newApp.setProviderId(testId);
			service.updateApplicationPublishInfo(newApp,"sss");
		} catch(Error e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void testDeleteApplicationPublish() {
		try {
			service.deleteApplicationPublish(testId,testId);
			logger.info("测试4成功！");
		} catch(Error e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void testPublishNewApplication() {
		try {
			ApplicationEntityWithBLOBs newApp = new ApplicationEntityWithBLOBs();
			newApp.setApplicationName(testId);
			newApp.setSubDomain("WWW");
			newApp.setContactPerson(testId);
			newApp.setContactPhone("1111");
			newApp.setApplicationName(testId);
			newApp.setChname(testId);
			service.publishNewApplication(newApp, testId, testId, testId,null);
			logger.info("测试新街口成功！");
		} catch(Error e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@After
	public void finish(){
		try{
			ApplyEntityExample entityExample = new ApplyEntityExample();
			ApplyEntityExample.Criteria criteria = entityExample.createCriteria();
			criteria.andApplyUserEqualTo(testId);
			List<ApplyEntity> n = applyEntityMapper.selectByExample(entityExample);
			if ( n != null ) {
				applyEntityMapper.deleteByExample(entityExample);
				logger.info("删除应用申请测试用例成功");
			}
			ApplicationOrderEntityWithBLOBs l = applicationOrderEntityMapper.selectByPrimaryKey(testId);
			if ( l != null) {
				applicationOrderEntityMapper.deleteByPrimaryKey(testId);
				logger.info("测试用例5删除成功！");
			}
			ApplicationEntityWithBLOBs m = applicationEntityMapper.selectByPrimaryKey(testId);
			ApplicationEntityExample example = new ApplicationEntityExample();
			ApplicationEntityExample.Criteria criter = example.createCriteria();
			criter.andApplicationNameEqualTo(testId);
			if ( m != null) {
				applicationEntityMapper.deleteByPrimaryKey(testId);
				applicationEntityMapper.deleteByExample(example);
				logger.info("测试用例4删除成功！");
			}	
			UserEntity i = userMapper.selectByPrimaryKey(testId);
			if ( i != null ) {
				userMapper.deleteByPrimaryKey(testId);
				logger.info("删除测试用例3成功");
			} 
			OrganizationEntity k = organizationMapper.selectByPrimaryKey(testId);
			if ( k != null ) {
				organizationMapper.deleteByPrimaryKey(testId);
				logger.info("删除测试用例2成功");
			}
			RoleEntity j = roleMapper.selectByPrimaryKey(testId);
			if ( j != null ) {
				roleMapper.deleteByPrimaryKey(testId);
				logger.info("删除测试用例1成功");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
