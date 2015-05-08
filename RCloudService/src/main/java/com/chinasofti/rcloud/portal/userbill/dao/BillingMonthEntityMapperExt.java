package com.chinasofti.rcloud.portal.userbill.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.BillingMonthEntity;

public interface BillingMonthEntityMapperExt extends BaseDao{
	
	List<HashMap> getProdNameList(@Param("userId") String userId,@Param("chargeMonth") int chargeMonth,
			@Param("chargeYear") int chargeYear);
	/**
	* @Title: getBillingMonthPro 
	* @Description: 查询产品id   取值为boss_service_bill表中的user_id,charge_month,charge_year;
	* @param @param map
	* @param @return
	* @return BillingMonthEntity  
	* @throws
	 */
	List<BillingMonthEntity> getBillingMonthPro(Map<String, Object> map);
}
