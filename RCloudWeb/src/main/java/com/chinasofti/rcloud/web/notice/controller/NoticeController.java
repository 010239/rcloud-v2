package com.chinasofti.rcloud.web.notice.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.portal.notice.domain.NoticeDomain;
import com.chinasofti.rcloud.portal.notice.service.NoticeService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;


/**
 * @author zhangjiaxing
 *
 * 2014年10月24日
 */
@Controller
public class NoticeController extends BasicController{
	
	private static Logger logger = Logger.getLogger(NoticeController.class);
	
	@Autowired
	NoticeService noticeService;

	
	/**
	 * 返回20条公告信息
	 * @return
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value="/rest/notice/showAllNotice", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<NoticeDomain>> getAllNotice() {
		ResponseEntity<List<NoticeDomain>> responseEntity = new ResponseEntity<List<NoticeDomain>>();
		try {
			int count = 20;
			List<NoticeDomain> noticeList = noticeService.getAllNotice(count);
			responseEntity.setEntity(noticeList);
		}  catch (Exception e) {
			logger.error("getAllNotice", e);
			handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	
	/**
	 * 返回当天第一条公告
	 * @return
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value="/rest/notice/getFirstNotice", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<NoticeDomain> getFirstNotice(HttpServletRequest request) {
		ResponseEntity<NoticeDomain> responseEntity = new ResponseEntity<NoticeDomain>();
		HttpSession session = request.getSession(); 
		try {
			if (session.getAttribute("firstVist") ==null || session.getAttribute("firstVist").equals("")) {
				NoticeDomain notice = noticeService.getFirstNotice();
				responseEntity.setEntity(notice);
				session.setAttribute("firstVist","firstVist");
			} 
			
		}  catch (Exception e) {
			logger.error("getFirstNotice", e);
			handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	
	/**
	 * 显示当天公告的条数
	 * @return
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value="/rest/notice/countTodayNotice", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> countTodayNotice(HttpServletRequest request) {
		ResponseEntity<Integer> responseEntity = new ResponseEntity<Integer>();
		try {
			int count = noticeService.getNoticeCountOfToday();

			responseEntity.setEntity(count);
		}  catch (Exception e) {
			logger.error("getFirstNotice", e);
			handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
}
