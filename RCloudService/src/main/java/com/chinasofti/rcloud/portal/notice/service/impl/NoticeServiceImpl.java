package com.chinasofti.rcloud.portal.notice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.portal.message.dao.StringBaseDao;
import com.chinasofti.rcloud.portal.notice.dao.NoticeDao;
import com.chinasofti.rcloud.portal.notice.domain.NoticeDomain;
import com.chinasofti.rcloud.portal.notice.service.NoticeService;

/**
 * @author zhangjiaxing
 *
 * 2014年10月24日
 */
@Service
public class NoticeServiceImpl implements NoticeService {

	private static Logger logger = Logger.getLogger(NoticeServiceImpl.class);
	
	@Autowired
	NoticeDao noticeDao;
	
	@Autowired
	StringBaseDao stringBaseDao;

	@Override
	public NoticeDomain getFirstNotice() throws Exception {
		
		Date date = new Date();
		String dateStr = CommonUtil.dateToString(date);
		String noticeListKey = noticeDao.getListKey(dateStr);
		NoticeDomain notice = new NoticeDomain();
		List<String> keyList = stringBaseDao.range(noticeListKey, 0, 0);
		if (keyList.size() != 0) {
			notice =noticeDao.getByKey(keyList.get(0));
		} else {
			logger.error("今天所发布的公告不存在");
		}
		
		return notice;
	}

	@Override
	public int getNoticeCountOfToday() throws Exception {
		Date date = new Date();
		String dateStr = CommonUtil.dateToString(date);
		String noticeListKey = noticeDao.getListKey(dateStr);
		int count =Integer.parseInt(String.valueOf(stringBaseDao.length(noticeListKey))); 

		return count;
	}

	@Override
	public List<NoticeDomain> getAllNotice(int count) throws Exception {
		return noticeDao.getNoticeDomainList(count);
	}
	
	

}
