package com.chinasofti.rcloud.portal.userconsole.service;

import java.util.Date;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.chinasofti.rcloud.dao.OrganizationEntityMapper;
import com.chinasofti.rcloud.dao.RoleEntityMapper;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.OrganizationEntity;
import com.chinasofti.rcloud.domain.RoleEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.userconsole.dao.UserEntityMapperExt;




/**
 * @author zhangjiaxing
 *
 * 2014年7月3日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class UserInfoServiceTest {
	@Autowired
	private UserInfoService userService;
	
	@Autowired
	private UserEntityMapper userMapper;
	
	@Autowired
	private RoleEntityMapper roleMapper;
	
	@Autowired
	private UserEntityMapperExt userEntityMapperExt;
	
	@Autowired
	private OrganizationEntityMapper organizationMapper;
	
	private UserEntity user;
	
	private Logger logger = Logger.getLogger(UserInfoServiceTest.class);

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
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAll(){	
		this.testFindByUserId();
		this.testUpdatePassword();
		this.testUpdateEmail();
		this.testUpdatePhone();
		this.testUpdateUserInfo();
	}
	
	/**
	 * 测试展示用户信息方法
	 */
	public void testFindByUserId() {
		try {
			Assert.assertNotNull(userService.findByUserId(testId));
			logger.debug("测试1成功");
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试修改密码
	 */
	public void testUpdatePassword() {
		try {
			userService.updatePassword(testId, "cccc");
			logger.debug("测试2成功");
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试修改邮箱
	 */
	public void testUpdateEmail() {
		try {
			userService.updateEmail(testId, "bbbb@126.com");
			logger.debug("测试3成功");
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试修改手机号码
	 */	
	public void testUpdatePhone() {
		try {
			userService.updatePhone(testId, "22222");
			logger.debug("测试4成功");
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试修改用户信息
	 */	
	public void testUpdateUserInfo() {
		user = new UserEntity();
		user.setUserId(testId);
		user.setRealName("bbb");
		try {
			userService.updateUser(user);
			logger.debug("测试5成功");
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void finish(){
		try{
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
