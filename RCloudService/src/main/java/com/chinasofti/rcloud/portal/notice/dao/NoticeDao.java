package com.chinasofti.rcloud.portal.notice.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.common.client.CFClientResource;
import com.chinasofti.rcloud.common.dao.RedisBaseDao;
import com.chinasofti.rcloud.portal.notice.domain.NoticeDomain;

@Component
public class NoticeDao extends RedisBaseDao<NoticeDomain>{
	private static final String DEPLOY_ENV = CFClientResource.DEPLOY_ENV;
	private static final String ENV_PLACE_HOLDER = "DeployEnv";
	
	private static final String NOTICE_KEY_PARTTEN = "RCloud:DeployEnv:Notice:Public:NoticeGuid";
	private static final String NOTICE_PLACE_HOLDER = "NoticeGuid";
	
	private static final String NOTICE_LIST_KEY_PUBLISH_DATE_PARTTEN = "RCloud:DeployEnv:Notice:Public:PublishDate:List";
	private static final String DATE_PLACE_HOLDER = "PublishDate";
	
	private static final String NOTICE_LIST_KEY_PARTTEN = "RCloud:DeployEnv:Notice:Public:LIST";
	
	public String getNoticeKey() {
		String guid = CommonUtil.getId();
		return NOTICE_KEY_PARTTEN.replaceAll(ENV_PLACE_HOLDER, DEPLOY_ENV)
				.replaceAll(NOTICE_PLACE_HOLDER, guid);
	}
	
	public String getListKey(String dateStr) {
		return NOTICE_LIST_KEY_PUBLISH_DATE_PARTTEN.replaceAll(ENV_PLACE_HOLDER, DEPLOY_ENV)
				.replaceAll(DATE_PLACE_HOLDER, dateStr);
	}
	
	public String getAllNoticeListKey() {
		return NOTICE_LIST_KEY_PARTTEN.replaceAll(ENV_PLACE_HOLDER, DEPLOY_ENV);
	}
	
	/**
	 * 获取指定条数的公告
	 * 
	 * @param userId
	 * @param paginationDetail
	 * @param isAll true则返回全部，false则返回未读。
	 */
	public List<NoticeDomain> getNoticeDomainList(final int count) {
		
		final List<NoticeDomain> noticeList = new ArrayList<NoticeDomain>();
		
		SessionCallback sessionCallback = new SessionCallback() {

			@Override
			public Object execute(RedisOperations operations)
					throws DataAccessException {
                String listKey = getAllNoticeListKey();
				
                BoundListOperations oper1 = operations.boundListOps(listKey);
				List<Object> keyList = oper1.range(0, count - 1);
				for (Object key : keyList) {
					BoundValueOperations<String,Object> oper2 = operations.boundValueOps((String)key);
					NoticeDomain notice = (NoticeDomain) oper2.get();
					noticeList.add(notice);
				}
				
				return null;
			}
			
		};
		
		return noticeList;
	} 
	
}
