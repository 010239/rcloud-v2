package com.chinasofti.rcloud.portal.cloudshop.service;

import java.util.Map;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.ApplicationEntityWithBLOBs;
import com.chinasofti.rcloud.domain.ApplyEntity;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityExt;
import com.chinasofti.rcloud.portal.cloudshop.vo.ApplicationEntityVo;

/**
 * @author zhangjiaxing
 *
 * 2014年7月14日
 */
public interface ApplicationPublishService {
	
	/**
	 * 展示发布的应用列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Pagination<ApplicationEntityExt> getApplicationPublishByPage(Map<String, Object> searchParams) throws Exception;
	
	
	/**
	 * 查询已发布应用详情
	 * @param applicationId
	 * @return
	 * @throws Exception
	 */
	ApplicationEntity getApplicationPublishInfo(String applicationId) throws Exception;
	
	
	/**
	 * 修改已发布应用信息 
	 * @param applicationEntity
	 * @throws Exception
	 */
	void updateApplicationPublishInfo(ApplicationEntityWithBLOBs applicationEntity,String applyDesc) throws Exception;
	
	
	/**
	 * 取消应用
	 * @param applicationId
	 * @throws Exception
	 */
	void deleteApplicationPublish(String applicationId,String userName) throws Exception;
	
	
//	/**
//	 * 发布新应用的公用调用方法
//	 * @param applicationEntity
//	 * @throws Exception
//	 */
//	void insertApplicationPublish(ApplicationEntityWithBLOBs applicationEntity, String userId) throws Exception;
	
	
	/**
	 * 发布应用方法，可内外调用
	 * @param applicationEntity
	 * @param userId
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	void publishNewApplication(ApplicationEntityWithBLOBs applicationEntity, String userId,
			String userName,String password,ApplyEntity applyEntity) throws Exception;
	/**
	* @Title: getUpdateApplicationPublishInfo 
	* @Description: 获取修改应用的信息
	* @author shimeihua
	* @param @param searchParam
	* @param @param applicationId
	* @param @return
	* @param @throws Exception
	* @return ApplicationEntityVo  
	* @throws
	 */
	public ApplicationEntityVo getUpdateApplicationPublishInfo(String applicationId,String userId ) throws Exception;
	/**
	* @Title: insertApplicationPublish 
	* @Description: TODO 插入订单信息
	* @author shimeihua
	* @param @param applicationEntity
	* @param @param userId
	* @param @param applEntity
	* @param @throws Exception
	* @return void  
	* @throws
	 */
	public void insertApplicationPublish(ApplicationEntityWithBLOBs applicationEntity, String userId,ApplyEntity applEntity) 
			throws Exception;
}
