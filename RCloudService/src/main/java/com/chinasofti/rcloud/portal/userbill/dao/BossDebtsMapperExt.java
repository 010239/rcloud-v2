package com.chinasofti.rcloud.portal.userbill.dao;

import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.portal.userbill.domain.BossDebtsEntityExt;


public interface BossDebtsMapperExt extends BaseDao{
	
	BossDebtsEntityExt getServiceDebtsRecordInfo(String serviceBillId) ;
	
	List<BossDebtsEntityExt> getSerDebtsByPage(Map<String, Object> searchParams) ;
	
	int countDebtsList(Map<String, Object> searchParams);
	/**
	* @Title: updateDebtOfStatus 
	* @Description: 账单续费   欠款记录状态为已还 ，补缴日期为今天
	* @param @param params
	* @param @return
	* @return int  
	* @throws
	 */
	int updateDebtOfStatus(Map<String, Object> params);
}
