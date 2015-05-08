package com.chinasofti.rcloud.portal.userbill.service;

import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.portal.userbill.domain.BossBillInfoEntityExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossDebtsEntityExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossServiceBillEntityExt;
import com.chinasofti.rcloud.portal.userbill.vo.DebtsRecordVo;


/**
 * @author zhangjiaxing
 *
 * 2014年9月23日
 */
public interface BossServiceBillService {
	
	Pagination<BossServiceBillEntityExt> searchBillByPage(Map<String, Object> searchParams) throws Exception;
	
	List<BossBillInfoEntityExt> getBillInfo(Map<String, Object> searchParams) throws Exception;
	/**
	* @Title: updateArrearsBillingRenewals 
	* @Description: 续费
	* @param @param debtsRecordVo
	* @param @return
	* @param @throws Exception
	* @return String  
	* @throws
	 */
	public String updateArrearsBillingRenewals(DebtsRecordVo debtsRecordVo)throws Exception;
	/**
	* @Title: getDebtsInfoByServiceBillId 
	* @Description: 获取欠费账单信息
	* @param @param serviceBillId
	* @param @return
	* @param @throws Exception
	* @return BossDebtsEntityExt  
	* @throws
	 */
	public BossDebtsEntityExt getDebtsInfoByServiceBillId(String serviceBillId)
			throws Exception;

}
