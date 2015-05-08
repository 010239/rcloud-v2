package com.chinasofti.rcloud.portal.userconsole.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.AccessKeyRecordEntity;


public interface AccessKeyRecordEntityMapperExt extends BaseDao{
	
	/**
	 * 计算结束时间大于当前时间的order
	 * @param orderId
	 * @return
	 */
	int validOrderCount(@Param("orderId")String orderId);
	
	/**
	 * 计算是否存在处于有效状态的服务订单
	 * @param userId
	 * @param serviceId
	 * @return
	 */
	int validOrderCount2(@Param("userId")String userId,@Param("serviceId")String serviceId);

	/**
	 * 更改服务状态为注销
	 * @param id
	 * @param a
	 * @return
	 */
	int cancelServiceCredential(@Param("id")String id, @Param("status")int a);

	/**
	 * 更改服务状态为激活
	 * @param entity
	 * @return
	 */
	int activateServiceCredential(AccessKeyRecordEntity entity);

	/**
	 * 根据id查询服务名称
	 * @param id
	 * @return
	 */
	String getServiceNameById(@Param("id")String id);
	
	/**
	 * 根据id查询密钥
	 * @param id
	 * @return
	 */
	HashMap<String, String> getAccessKeySecretById(@Param("id")String id);
	
}
