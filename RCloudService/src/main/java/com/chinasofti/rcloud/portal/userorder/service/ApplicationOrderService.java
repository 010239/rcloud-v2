package com.chinasofti.rcloud.portal.userorder.service;

import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.ApplicationOrderEntityWithBLOBs;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.cloudshop.vo.ApplicationMonthVo;
import com.chinasofti.rcloud.portal.userorder.domain.ApplicationOrderEntityExt;

/**
 * @author zhangjiaxing
 *
 * 2014年7月3日
 */
public interface ApplicationOrderService {	
	
	
	/**
	 * 云应用订单购买查询
	 * @param searchParam
	 * @return
	 * @throws Exception
	 */
	Pagination<ApplicationOrderEntityExt> getBuyApplicationOrderByPage(Map<String, Object> searchParams ) throws Exception;
	
	/**
	 * 云应用订单售出查询
	 * @param searchParam
	 * @return
	 * @throws Exception
	 */
	Pagination<ApplicationOrderEntityExt> getSaleApplicationOrderByPage(Map<String, Object> searchParams ) throws Exception;
	
	/**
	 * 根据当前登录用户id和订单号查询购买的应用订单详情查询
	 * @param userId
	 * @param orderNumber
	 * @return
	 * @throws Exception
	 */
	ApplicationOrderEntityExt getBuyApplicationOrderInfo(String userId,String orderNumber) throws Exception;
	
	/**
	 * 根据当前登录用户id和订单号查询售出的应用订单详情查询
	 * @param userId
	 * @param orderNumber
	 * @return
	 * @throws Exception
	 */
	ApplicationOrderEntityExt getSaleApplicationOrderInfo(String userId,String orderNumber) throws Exception;
	
	/**
	 * 购买应用，生成应用订单。
	 * 
	 * @param AppBuyInfo
	 * @param userId
	 */
	int insertApplicationOrderEntity(Map<String, Object> appBuyInfo, String userId) throws Exception;
	//smh 试用应用
	ApplicationMonthVo insertApplicationOrderEntityapp(Map<String, Object> appBuyInfo, UserEntity user) throws Exception;
	/**
	* @Title: sendMessage 
	* @Description: 发送站内消息
	* @author shimeihua
	* @param @param providerId
	* @param @param appname
	* @param @param userId
	* @return void  
	* @throws
	 */
	public void sendMessage(UserEntity user,String appname,String developId);
	/**
	* @Title: insertAoolicationOrder 
	* @Description: 插入订单信息
	* @author shimeihua
	* @param @param payPattern
	* @param @param orderDescription
	* @param @param application
	* @param @param dueTime
	* @param @param userId
	* @param @param applicationId
	* @param @return
	* @return ApplicationOrderEntityWithBLOBs  
	* @throws
	 */
	public ApplicationOrderEntityWithBLOBs  insertAoolicationOrder(int payPattern,String orderDescription,ApplicationEntity application,int dueTime,String userId,String applicationId);
	/**
	* @Title: getApplicationOrderByBuyerAppId 
	* @Description: 查询购买者在有效期内是否已经购买过此应用
	* @author shimeihua
	* @param @param searchParams
	* @param @return
	* @return List<ApplicationOrderEntityExt>  
	* @throws
	 */
	public List<ApplicationOrderEntityExt> getApplicationOrderByBuyerAppId(Map<String, Object> searchParams);
}
