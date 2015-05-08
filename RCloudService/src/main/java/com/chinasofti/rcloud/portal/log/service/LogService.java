package com.chinasofti.rcloud.portal.log.service;

import com.chinasofti.rcloud.domain.UserEntity;


/**
 * @author zhangjiaxing
 *
 * 2014年10月27日
 */
public interface LogService {


	
	/**
	 * 插入审计日志公共接口
	 * @param userId
	 * @param roleCode
	 * @param operation
	 * @param description
	 * @throws Exception
	 */
	void insertLog(UserEntity user,
			String operation, String description) throws Exception;
}
