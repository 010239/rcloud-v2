package com.chinasofti.rcloud.portal.cloudservice.service;


import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.domain.BusinessOrderEntity;
import com.chinasofti.rcloud.domain.ProductsEntity;
import com.chinasofti.rcloud.domain.UserEntity;

/**
 * @author zhangjiaxing
 *
 * 2014年9月24日
 */
public interface RcloudService {

	
	/**
	 * 服务开通页面显示产品选择列表
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	ProductsEntity getserviceList(String serviceId) throws Exception;
	
	/**
	 * 点击开通服务插入businessOrder表中插入数据
	 * @param userId
	 * @param prodId
	 * @param pkgId
	 * @throws Exception
	 */
	void insertBusinessOrder(String userId,String prodId,
			String pkgId,UserEntity user,String prodName,String serviceId) throws Exception;

	
	/**
	 * 显示是否开通页面的服务列表
	 * @return
	 * @throws Exception
	 */
	Map<String,List> getServicePlatform(String userId) throws Exception;
	
	/**
	 * @param userId
	 * @param serviceId
	 * @return
	 */
	List<BusinessOrderEntity> getCloudServiceOrder(String userId,
			String serviceId);
	 
}
