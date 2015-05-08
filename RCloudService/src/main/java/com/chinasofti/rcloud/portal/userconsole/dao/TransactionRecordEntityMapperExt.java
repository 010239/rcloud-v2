package com.chinasofti.rcloud.portal.userconsole.dao;

import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.portal.userconsole.domain.TransactionRecordEntityExt;

public interface TransactionRecordEntityMapperExt extends BaseDao {
    
	/**
	 * 按条件查询交易列表
	 * @param searchParam
	 * @return
	 */
	List<TransactionRecordEntityExt> findListByPage(Map<String,Object> searchParam);
	
	/**
	 * 统计按条件查询交易列表总数
	 * @param searchParam
	 * @return
	 */
	int countFindList(Map<String,Object> searchParam);
	
}