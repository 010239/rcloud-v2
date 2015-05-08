package com.chinasofti.rcloud.portal.userbill.service;

import java.util.Map;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.portal.userbill.domain.BossDebtsEntityExt;





/**
 * @author zhangjiaxing
 *
 * 2014年7月9日
 */
public interface DebtsRecordService {	
	
//	/**
//	 * 根据账单ID查询欠费详情
//	 * @param billId
//	 * @return
//	 * @throws Exception
//	 */
//	DebtsRecordEntityExt getAppDebtsRecordInfo(String billId) throws Exception;
//	
//	DebtsRecordEntityExt getSerDebtsRecordInfo(String billId) throws Exception;
	
	
	Pagination<BossDebtsEntityExt> getDebtsByPage(Map<String, Object> searchParams) throws Exception;
	
	
	BossDebtsEntityExt getSerDebtsInfo(String serviceBillId) throws Exception;
}
