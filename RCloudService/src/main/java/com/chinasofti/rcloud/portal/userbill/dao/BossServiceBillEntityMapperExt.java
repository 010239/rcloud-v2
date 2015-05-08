package com.chinasofti.rcloud.portal.userbill.dao;

import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.portal.userbill.domain.BossBillInfoEntityExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossServiceBillEntityExt;

/**
 * @author zhangjiaxing
 *
 * 2014年9月23日
 */
public interface BossServiceBillEntityMapperExt extends BaseDao{

	
	List<BossServiceBillEntityExt> getBillByPage(Map<String, Object> searchParams) ;
	
	int countBillList(Map<String, Object> searchParams);
	
	List<BossBillInfoEntityExt> getBillInfo(Map<String, Object> searchParams) ;
}
