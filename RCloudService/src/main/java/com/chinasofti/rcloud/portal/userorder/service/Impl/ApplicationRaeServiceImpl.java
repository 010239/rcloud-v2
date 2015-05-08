package com.chinasofti.rcloud.portal.userorder.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinasofti.rcloud.dao.ApplicationEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationEntityWithBLOBs;
import com.chinasofti.rcloud.portal.userorder.service.ApplicationRaeService;
/**
* @ClassName: ApplicationRaeServiceImpl
* @Description: 部署应用  调用rae接口
* @author shimeihua
* @date 2014年11月12日 下午4:18:52
*
 */
public class ApplicationRaeServiceImpl implements ApplicationRaeService {
	/**
	* @Title: selectByPrimaryKey 
	* @Description: 获取应用信息
	* @author shimeihua
	* @param @param applicationId
	* @param @return
	* @return ApplicationEntityWithBLOBs  
	* @throws
	 */
	
	private  ApplicationEntityWithBLOBs selectByPrimaryKey(String applicationId){
		ApplicationEntityWithBLOBs application = applicationEntityMapper.selectByPrimaryKey(applicationId);
		return application;
		
	}
	@Autowired
	private ApplicationEntityMapper applicationEntityMapper;

}
