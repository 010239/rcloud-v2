package com.chinasofti.rcloud.portal.userbill.dao;

import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.ApplicationBillEntity;
import com.chinasofti.rcloud.portal.userbill.domain.ApplicationBillEntityExt;

/**
 * @author zhangjiaxing
 *
 * 2014年7月8日
 */
public interface ApplicationBillEntityMapperExt extends BaseDao{
	
	
	/**
	 * 根据条件查询云应用账单列表
	 * @param searchParam
	 * @return
	 */
	List<ApplicationBillEntity> getApplicationBillListByPage(Map<String, Object> searchParams);
	
	
	/**
	 * 查询云应用账单总条数
	 * @param searchParam
	 * @return
	 */
	int countApplicationBill(Map<String, Object> searchParams);
	
	/**
	 * 获取应用账单欠费列表
	 * @param searchParams
	 * @return
	 */
	List<ApplicationBillEntityExt> getApplicationDebtsRecordListByPage(Map<String, Object> searchParams);
	
	/**
	 * 查询应用欠费条数
	 * @param userId
	 * @return
	 */
	int countApplicationDebtsRecord(Map<String, Object> searchParams);
}
