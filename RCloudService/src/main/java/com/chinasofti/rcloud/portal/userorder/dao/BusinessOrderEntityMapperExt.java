package com.chinasofti.rcloud.portal.userorder.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.BusinessOrderEntity;
import com.chinasofti.rcloud.portal.userorder.domain.BusinessOrderEntityExt;

public interface BusinessOrderEntityMapperExt extends BaseDao{
	
	List<BusinessOrderEntityExt> searchOrderByPage(Map<String, Object> searchParams) ;
	
	int countSearchOrder(Map<String, Object> searchParams);
	
	BusinessOrderEntityExt getOrderInfo(String businessOrderId);
	
	List<BusinessOrderEntity> getOrder(Map parameters);
	
	List<HashMap> getServiceList(String userId);
	/**
	* @Title: getOrderInfoByCon 
	* @Description: 条件查询portal_business_order 唯一
	* @param @param map
	* @param @return
	* @return BusinessOrderEntityExt  
	* @throws
	 */
	BusinessOrderEntityExt getOrderInfoByCon(Map<String, Object> map);
	/**
	* @Title: updateOrderInfoStatus 
	* @Description: 将查到的数据中的字段account_tag置为1-正常；status置为1-有效
	* @param @param map
	* @param @return
	* @return int  
	* @throws
	 */
	int updateOrderInfoStatus(Map<String, Object> map);
}
