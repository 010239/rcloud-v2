package com.chinasofti.rcloud.portal.notice.service;

import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.chinasofti.rcloud.portal.notice.dao.NoticeDao;
import com.chinasofti.rcloud.portal.notice.domain.NoticeDomain;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class NoticeTest {
	
	private Logger logger = Logger.getLogger(NoticeTest.class);
	@Autowired NoticeService noticeService;
	
	@Autowired NoticeDao noticeDao;

	private String testId = "noticeservicetest-test";
	
	
	
	@Before
	public void initTest(){
		try{
			NoticeDomain notice = new NoticeDomain();
			notice.setNoticeKey(testId);
			notice.setPublishTime("2014-10-30");
			notice.setNotice(testId);
			noticeDao.saveOrUpdate(testId, notice);
			logger.info("测试用例初始化成功！");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAll(){	
		this.testGetAllNotice();
		this.testGetFirstNotice();
		this.testGetNoticeCountOfToday();
	}
	
	public void testGetFirstNotice() {
		try {
			Assert.assertNotNull(noticeService.getFirstNotice());
		    logger.debug("测试获取当天头一条公告成功");
			}catch(Error e){
				logger.error(e.getMessage());
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	public void testGetNoticeCountOfToday() {
		try {
			Assert.assertNotNull(noticeService.getNoticeCountOfToday());
		    logger.debug("测试获取当天公告条数成功");
			}catch(Error e){
				logger.error(e.getMessage());
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	public void testGetAllNotice() {
		try {
			Assert.assertNotNull(noticeService.getAllNotice(20));
		    logger.debug("测试获取前20条公告成功");
			}catch(Error e){
				logger.error(e.getMessage());
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	@After
	public void finish(){
		try{
			NoticeDomain notice = noticeDao.getByKey(testId);
			if ( notice != null) {
				noticeDao.deleteByKey(testId);
			
				logger.info("删除测试用例成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
