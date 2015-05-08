package com.chinasofti.rcloud.portal.userorder.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.dao.BusinessOrderEntityMapper;
import com.chinasofti.rcloud.portal.userorder.dao.BusinessOrderEntityMapperExt;
import com.chinasofti.rcloud.portal.userorder.domain.BusinessOrderEntityExt;
import com.chinasofti.rcloud.portal.userorder.service.BusinessOrderService;

@Service("businessOrderService")
public class BusinessOrderServiceImpl implements BusinessOrderService{

	@Autowired
	private BusinessOrderEntityMapperExt orderMapperExt;
	
	@Autowired
	private BusinessOrderEntityMapper orderMapper;
	
	@Override
	public Pagination<BusinessOrderEntityExt> searchOrderByPage(
			Map<String, Object> searchParams) throws Exception {
		
		Pagination<BusinessOrderEntityExt> pagination = new Pagination<BusinessOrderEntityExt>();
		int total = orderMapperExt.countSearchOrder(searchParams);
		List<BusinessOrderEntityExt> rows = orderMapperExt.searchOrderByPage(searchParams);
		pagination.setRows(rows);
		pagination.setTotal(total);
		
		return pagination;
	}

	@Override
	public BusinessOrderEntityExt getOrderInfo(String businessOrderId) throws Exception {
		
		BusinessOrderEntityExt orderInfo = orderMapperExt.getOrderInfo(businessOrderId);
		return orderInfo;
	}


}
