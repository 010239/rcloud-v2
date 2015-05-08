package com.chinasofti.rcloud.portal.userorder.service;

import java.util.Map;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.portal.userorder.domain.BusinessOrderEntityExt;



public interface BusinessOrderService {
	
	Pagination<BusinessOrderEntityExt> searchOrderByPage(Map<String, Object> searchParams) throws Exception;
	
	BusinessOrderEntityExt getOrderInfo(String businessOrderId) throws Exception;
}
