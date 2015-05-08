package com.chinasofti.rcloud.portal.userbill.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.chinasofti.rcloud.dao.ApplicationBillEntityMapper;
import com.chinasofti.rcloud.dao.ApplicationEntityMapper;
import com.chinasofti.rcloud.dao.ApplicationOrderEntityMapper;
import com.chinasofti.rcloud.dao.ApplicationOrderToBillEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationBillEntity;
import com.chinasofti.rcloud.domain.ApplicationEntityWithBLOBs;
import com.chinasofti.rcloud.domain.ApplicationOrderEntityWithBLOBs;
import com.chinasofti.rcloud.domain.ApplicationOrderToBillEntityKey;
import com.chinasofti.rcloud.portal.userbill.dao.ApplicationBillEntityMapperExt;
import com.chinasofti.rcloud.portal.userorder.dao.ApplicationOrderEntityMapperExt;

/**
 * @author zhangjiaxing
 *
 * 2014年7月11日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class ApplicationBillServiceTest {
	
	@Autowired
	private ApplicationBillService service;
	
	@Autowired
	private ApplicationBillEntityMapperExt applicationBillEntityMapperExt;
	
	@Autowired
	private ApplicationBillEntityMapper applicationBillEntityMapper;
	
	@Autowired
	private ApplicationOrderToBillEntityMapper applicationOrderToBillEntityMapper;
	
	@Autowired
	private ApplicationOrderEntityMapperExt applicationOrderEntityMapperExt;
	
	@Autowired
	private ApplicationEntityMapper applicationMapper;
	
	@Autowired
	private ApplicationOrderEntityMapper applicationOrderEntityMapper;
	
	private Logger logger = Logger.getLogger(ApplicationBillServiceTest.class);
	
	private String testId = "applicationBillservicetest-test";
	
	@Before
	public void initTest(){
		try{
			ApplicationBillEntity i = applicationBillEntityMapper.selectByPrimaryKey(testId);
			if ( i == null) {
				ApplicationBillEntity applicationEntity = new ApplicationBillEntity();
				applicationEntity.setBillId(testId);
				applicationEntity.setBillNumber(testId);
				applicationEntity.setApplicationNames(testId);
				applicationEntity.setCreatedTime(new Date());
				applicationEntity.setBillCosts(new BigDecimal(1000));
				applicationEntity.setStartTime(new Date());
				applicationEntity.setEndTime(new Date());
				applicationEntity.setUserId(Integer.toString(1));
				applicationEntity.setBillType(1);
				applicationEntity.setMarkDelete(0);
				applicationBillEntityMapper.insert(applicationEntity);
				logger.info("测试用例1初始化成功！");
			}	
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
				logger.info("测试用例2初始化成功！");
			}
			ApplicationOrderEntityWithBLOBs k = applicationOrderEntityMapper.selectByPrimaryKey(testId);
			if ( k== null) {
				ApplicationOrderEntityWithBLOBs entity = new ApplicationOrderEntityWithBLOBs();
				entity.setOrderId(testId);
				entity.setOrderNumber(testId);
				entity.setApplicationId(testId);
				entity.setBuyerId(Integer.toString(1));
				entity.setMaintenanceCosts(new BigDecimal(1000));
				applicationOrderEntityMapper.insertSelective(entity);
				logger.info("测试用例3初始化成功！");			
			}
		
			ApplicationOrderToBillEntityKey entity = new ApplicationOrderToBillEntityKey();
			entity.setOrderId(testId);
			entity.setBillId(testId);
			applicationOrderToBillEntityMapper.insert(entity);
			logger.info("测试用例4初始化成功！");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Test
	public void testAll(){	
		this.testGetApplicationBillList();
		this.testGetOrderIdList();
		this.testGetApplicationBillInfo();
	}
	
	public void testGetApplicationBillList() {
		try {
			Map<String, Object> searchParams = new HashMap<String,Object>();
			PageParameter page = new PageParameter();
			page.setCurrentPage(1);
			page.setPageSize(10);
			searchParams.put("page", page);
			searchParams.put("userId", 1);
			searchParams.put("billType", 1);
			searchParams.put("endTime", new Date());
			Assert.assertNotNull(service.getApplicationBillListByPage(searchParams));
			logger.debug("测试1成功！");
		} catch(Error e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetOrderIdList() {
		try{
			Assert.assertNotNull(service.getOrderIdList(testId));
			logger.debug("测试2成功！");
		}catch(Error e) {
			logger.error(e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetApplicationBillInfo() {
		try{
			List<ApplicationOrderToBillEntityKey> orderIdList = new ArrayList<ApplicationOrderToBillEntityKey>();
			ApplicationOrderToBillEntityKey key = new ApplicationOrderToBillEntityKey();
			key.setOrderId(testId);
			orderIdList.add(key);
			Assert.assertNotNull(service.getApplicationBillInfo(testId, orderIdList));
			logger.debug("测试3成功！");
		}catch(Error e) {
			logger.error(e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void finish(){
		try{
			ApplicationOrderToBillEntityKey entity = new ApplicationOrderToBillEntityKey();
			entity.setOrderId(testId);
			entity.setBillId(testId);
			applicationOrderToBillEntityMapper.deleteByPrimaryKey(entity);
			logger.info("测试用例4删除成功！");
			
			ApplicationOrderEntityWithBLOBs k = applicationOrderEntityMapper.selectByPrimaryKey(testId);
			if ( k != null) {
				applicationOrderEntityMapper.deleteByPrimaryKey(testId);
				logger.info("测试用例3删除成功！");
			}
			
			ApplicationEntityWithBLOBs j = applicationMapper.selectByPrimaryKey(testId);
			if ( j != null) {
				applicationMapper.deleteByPrimaryKey(testId);
				logger.info("测试用例2删除成功！");
			}			
			
			ApplicationBillEntity i = applicationBillEntityMapper.selectByPrimaryKey(testId);
			if ( i != null) {
				applicationBillEntityMapper.deleteByPrimaryKey(testId);
				logger.info("测试用例1删除成功！");
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
