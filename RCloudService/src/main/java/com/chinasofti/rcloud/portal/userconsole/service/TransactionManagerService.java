package com.chinasofti.rcloud.portal.userconsole.service;

import java.util.Map;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.TransactionRecordEntity;
import com.chinasofti.rcloud.portal.userconsole.domain.TransactionRecordEntityExt;

public interface TransactionManagerService {

	/**
	 * 分页查询交易记录
	 * 
	 * @param searchParam
	 * @return
	 */
	Pagination<TransactionRecordEntityExt> findListByPage(
			Map<String, Object> searchParam) throws RCloudException;

	/**
	 * 
	 * @Title: getTransactionRecordEntityById
	 * @Description: 根据recordId返回单个校验记录
	 * @author sunlulu
	 * @param @param recordId
	 * @param @return
	 * @param @throws RCloudException
	 * @return TransactionRecordEntity
	 * @throws
	 */
	TransactionRecordEntity getTransactionRecordEntityById(String recordId)
			throws RCloudException;
}
