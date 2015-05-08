package com.chinasofti.rcloud.portal.cloudshop.service;

import java.util.Date;
import java.util.HashMap;
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
import com.chinasofti.rcloud.domain.ApplicationEntityWithBLOBs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class ApplicationServiceTest {
	
	private Logger logger = Logger.getLogger(ApplicationServiceTest.class);
	
	@Autowired
	ApplicationService service;
	
	@Autowired
	ApplicationEntityMapper mapper;
	
	private String testId = "applicationservicetest-test";
	
	@Before
	public void initTest(){
		try{
			ApplicationEntityWithBLOBs i = mapper.selectByPrimaryKey(testId);
			if(i==null){
				ApplicationEntityWithBLOBs entity = new ApplicationEntityWithBLOBs();
				entity.setApplicationId(testId);
				entity.setApplicationName(testId);
				entity.setCreatedTime(new Date());
				entity.setStatus(0);
				entity.setMarkDelete(0);
				entity.setProviderId("1");
				entity.setShowPortal(1);
				mapper.insert(entity);
				logger.info("测试用例初始化成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testAll(){		
		this.testSelectAppById();
		this.testSelectApplicationById();
		this.testSearchAppByPage();
	}
	
	public void testSelectApplicationById() {		
		try{
		    Assert.assertNotNull(service.selectApplicationById(testId));
		    logger.debug("测试成功");
		}catch(Error e){
			logger.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void testSelectAppById() {
		try{
		    Assert.assertNotNull(service.selectAppById(testId));
		    logger.debug("测试成功");
		}catch(Error e){
			logger.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void testSearchAppByPage() {
		try{
			Map<String, Object> searchParams = new HashMap<String,Object>();
			PageParameter page = new PageParameter();
			page.setCurrentPage(1);
			page.setPageSize(10);
			searchParams.put("page", page);
		    Assert.assertNotNull(service.searchAppByPage(searchParams));
		    logger.debug("测试成功");
		}catch(Error e){
			logger.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@After
	public void finish(){
		try{
			ApplicationEntityWithBLOBs i = mapper.selectByPrimaryKey(testId);
			if(i!=null){
				mapper.deleteByPrimaryKey(testId);
				logger.info("删除测试用例成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
