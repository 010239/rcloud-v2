package com.chinasofti.rcloud.portal.userconsole.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class UseServiceCredentialServiceTest {
	@Autowired
	UseServiceCredentialService useServiceCredentialService;
	
	
	private Logger logger = Logger.getLogger(UseServiceCredentialServiceTest.class);

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * 测试生成服务凭证方法
	 */
	@Test	
	public void testinsertServiceCredential() {
		try {
			UserEntityExt user = new UserEntityExt();
			user.setOrganizationId("1");
			user.setUaaGuid("1");
			user.setUserId("1");
			useServiceCredentialService.insertServiceCredential(user);
			logger.debug("测试成功");
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试激活服务凭证方法
	 */
	@Test	
	public void testactivateServiceCredential() {
		try {
			String id="1";
			String orderId="1";
			useServiceCredentialService.activateServiceCredential(id,orderId);
			logger.debug("测试成功");
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试注销服务凭证方法
	 */
	@Test	
	public void testcancelServiceCredential() {
		try {
			UserEntityExt user = new UserEntityExt();
			user.setOrganizationId("1");
			user.setUaaGuid("1");
			user.setUserId("1");
			String id="1";
			useServiceCredentialService.cancelServiceCredential(id, user);
			logger.debug("测试成功");
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试销服务凭证验证
	 */
	
	
	@Test	
	public void testverifyServiceCredential() {
		try {
			String jsonStr= "{\"address\":\"chiaaaaaaaaaaaaaaan\",\"birthday\":{\"birthday\":\"2010-11-22\"},"+  
	        		"\"email\":\"email@123.com\",\"id\":22,\"name\":\"tom\"}";
			String accessKeyId="rcloud-mysql-1-5296";
			String desStr="c574bb612577931992c4d8254eb279dedff908d88bdf3cfdde38a841acd85666ed969cc2a90956a3";
			String serviceName = "rae";
			Map verify=useServiceCredentialService.verifyServiceCredential(jsonStr, accessKeyId, desStr,serviceName);
			
			logger.debug("测试成功"+verify);
		}catch(Error e){
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
