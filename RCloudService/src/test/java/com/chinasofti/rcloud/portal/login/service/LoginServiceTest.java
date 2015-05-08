package com.chinasofti.rcloud.portal.login.service;

//import junit.framework.Assert;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.domain.UserEntityExample;
import com.chinasofti.rcloud.domain.UserEntityExample.Criteria;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.portal.login.service.impl.UserServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class LoginServiceTest {
	
	private Logger logger = Logger.getLogger(LoginServiceTest.class);
	
	@Autowired
	UserEntityMapper mapper;
	
	@Autowired
	UserService service;
	
	UserEntity user = null;
	
	@Before
	public void setUp() throws Exception {
		UserEntityExample example = new UserEntityExample();
		example.createCriteria().andUserNameEqualTo("aa");
		List<UserEntity> list =  mapper.selectByExample(example);
		user = list.get(0);
		logger.info("获取用户信息成功！");
	}
	
	@Test
	public void testLogin(){
		String username = "xx@xx.com";
		String passwd = "xx";
		String code = "1234";
		String oldcode = "1234";
		long createTime = System.currentTimeMillis();
		try {
			UserEntityExt userEntityExt = service.login(username, passwd, code, oldcode, createTime);	
			if(userEntityExt != null){
				logger.info("获取token success");
			}
		} catch (RCloudException e) {
			e.printStackTrace();
		}
	}
}
