package com.chinasofti.rcloud.portal.userbill.service;

import java.util.List;
import java.util.Map;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.domain.ApplicationBillEntity;
import com.chinasofti.rcloud.domain.ApplicationOrderToBillEntityKey;
import com.chinasofti.rcloud.portal.userbill.domain.ApplicationBillEntityExt;


/**
 * @author zhangjiaxing
 *
 * 2014年7月8日
 */
public interface ApplicationBillService {
	
	/**
	 * 根据条件查询应用账单列表
	 * @param searchParams
	 * @return
	 * @throws Exception
	 */
	Pagination<ApplicationBillEntity> getApplicationBillListByPage(Map<String, Object> searchParams ) throws Exception;
	
	/**
	 * 根据云应用账单ID查询所有订单ID
	 * @param billId
	 * @return
	 * @throws Exception
	 */
	List<ApplicationOrderToBillEntityKey> getOrderIdList(String billId) throws Exception;
	
	
	/**
	 * 获取应用账单详情
	 * @param billId
	 * @param orderIdList
	 * @return
	 * @throws Exception
	 */
	ApplicationBillEntityExt getApplicationBillInfo(String billId,List<ApplicationOrderToBillEntityKey> orderIdList) throws Exception;
	
	/**
	 * 查询应用欠费列表
	 * @param searchParams
	 * @return
	 * @throws Exception
	 */
	Pagination<ApplicationBillEntityExt> getAppDebtsListByPage(Map<String, Object> searchParams) throws Exception;
}
