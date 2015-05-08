package com.chinasofti.rcloud.portal.notice.service;

import java.util.List;
import com.chinasofti.rcloud.portal.notice.domain.NoticeDomain;

/**
 * @author zhangjiaxing
 *
 * 2014年10月24日
 */
public interface NoticeService {

	/**
	 * 获取当天第一条Notice
	 * 
	 * @param messageKey
	 */
	NoticeDomain getFirstNotice() throws Exception;
	
	/**
	 * 获取当天Notice条数
	 * 
	 * @return
	 */
	int getNoticeCountOfToday() throws Exception;
	
	/**
	 * 获取前20条Notice
	 * 
	 * @param paginationDetail
	 * @return
	 */
	List<NoticeDomain> getAllNotice(int count) throws Exception;

}
