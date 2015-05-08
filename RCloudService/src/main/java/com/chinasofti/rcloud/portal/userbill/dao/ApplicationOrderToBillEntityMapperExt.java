package com.chinasofti.rcloud.portal.userbill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.ApplicationOrderToBillEntityKey;

/**
 * @author zhangjiaxing
 *
 * 2014年7月10日
 */
public interface ApplicationOrderToBillEntityMapperExt extends BaseDao{
	
	
	/**
	 * 获取orderId合集
	 * @param billId
	 * @return
	 */
	List<ApplicationOrderToBillEntityKey> getOrderIdList(@Param("billId") String billId);
	
	/**
	 * 批量插入记录。
	 * 
	 * @param orderIdList
	 * @param billId
	 */
	void batchInsert(@Param("orderIdList") List<String> orderIdList, @Param("billId") String billId);

}
