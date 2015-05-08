package com.chinasofti.rcloud.portal.userorder.service;


import java.math.BigDecimal;
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
import com.chinasofti.rcloud.dao.ApplicationOrderEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationEntityWithBLOBs;
import com.chinasofti.rcloud.domain.ApplicationOrderEntityWithBLOBs;


/**
 * @author zhangjiaxing
 *
 * 2014年7月8日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class ApplicationOrderServiceTest {
	
	@Autowired
	private ApplicationOrderService service;
	
	@Autowired
	private ApplicationOrderEntityMapper applicationOrderEntityMapper;
	
	@Autowired
	private ApplicationEntityMapper applicationMapper;
	
	private Logger logger = Logger.getLogger(ApplicationOrderServiceTest.class);

	private String testId = "applicationOrderservicetest-test";

	@Before
	public void initTest(){
		try{
			ApplicationEntityWithBLOBs j = applicationMapper.selectByPrimaryKey(testId);
			if ( j==null ) {
				ApplicationEntityWithBLOBs entity = new ApplicationEntityWithBLOBs();
				entity.setApplicationId(testId);
				entity.setApplicationName(testId);
				entity.setCreatedTime(new Date());
				entity.setStatus(0);
				entity.setMarkDelete(0);
				entity.setProviderId(Integer.toString(1));
				applicationMapper.insert(entity);
				logger.info("测试用例1初始化成功！");
			}
			
			ApplicationOrderEntityWithBLOBs k = applicationOrderEntityMapper.selectByPrimaryKey(testId);
			if ( k== null) {
				ApplicationOrderEntityWithBLOBs entity = new ApplicationOrderEntityWithBLOBs();
				entity.setOrderId(testId);
				entity.setOrderNumber(testId);
				entity.setApplicationId(testId);
				entity.setBuyerId(Integer.toString(1));
				entity.setMaintenanceCosts(new BigDecimal(1000));
				entity.setOrderStatus(1);
				entity.setCreatedTime(new Date());
				applicationOrderEntityMapper.insertSelective(entity);
				logger.info("测试用例2初始化成功！");			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAll(){	
		this.testGetBuyApplicationOrder();
		this.testGetSaleApplicationOrder();
		this.testGetBuyApplicationOrderInfo();
		this.testGetSaleApplicationOrderInfo();
	}
	
	public void testGetBuyApplicationOrder() {
		try{
			Map<String, Object> searchParams = new HashMap<String,Object>();
			PageParameter page = new PageParameter();
			page.setCurrentPage(1);
			page.setPageSize(10);
			searchParams.put("page", page);
			searchParams.put("userId", 1);
			searchParams.put("orderStatus", 1);
		    Assert.assertNotNull(service.getBuyApplicationOrderByPage(searchParams));
		    logger.debug("测试1成功");
		}catch(Error e){
			logger.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public void testGetSaleApplicationOrder() {
		try{
			Map<String, Object> searchParams = new HashMap<String,Object>();
			PageParameter page = new PageParameter();
			page.setCurrentPage(1);
			page.setPageSize(10);
			searchParams.put("page", page);
			searchParams.put("userId", 1);
			searchParams.put("orderStatus", 1);
		    Assert.assertNotNull(service.getBuyApplicationOrderByPage(searchParams));
		    logger.debug("测试2成功");
		}catch(Error e){
			logger.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void testGetBuyApplicationOrderInfo() {
		try{
			Assert.assertNotNull(service.getBuyApplicationOrderInfo(Integer.toString(1), testId));
		    logger.debug("测试3成功");
		}catch(Error e){
			logger.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void testGetSaleApplicationOrderInfo() {
		try{
		    Assert.assertNotNull(service.getSaleApplicationOrderInfo(Integer.toString(1), testId));
		    logger.debug("测试4成功");
		}catch(Error e){
			logger.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@After
	public void finish(){
		try{
			ApplicationOrderEntityWithBLOBs k = applicationOrderEntityMapper.selectByPrimaryKey(testId);
			if ( k != null) {
				applicationOrderEntityMapper.deleteByPrimaryKey(testId);
				logger.info("测试用例2删除成功！");
			}
			ApplicationEntityWithBLOBs j = applicationMapper.selectByPrimaryKey(testId);
			if ( j != null) {
				applicationMapper.deleteByPrimaryKey(testId);
				logger.info("测试用例1删除成功！");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
