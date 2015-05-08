package com.chinasofti.rcloud.portal.userbill.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.dao.BillingMonthEntityMapper;
import com.chinasofti.rcloud.domain.BillingMonthEntityExample;
import com.chinasofti.rcloud.portal.userbill.dao.BillingMonthEntityMapperExt;
import com.chinasofti.rcloud.portal.userbill.dao.BossDebtsMapperExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossDebtsEntityExt;
import com.chinasofti.rcloud.portal.userbill.service.DebtsRecordService;

/**
 * @author zhangjiaxing
 *
 * 2014年7月9日
 */
@Service("debtsRecordService")
public class DebtsRecordServiceImpl implements DebtsRecordService{
	
	@Autowired
	private BossDebtsMapperExt bossDebtsMapperExt;
	
	@Autowired
	private BillingMonthEntityMapper billingMonthEntityMapper;
	
	@Autowired
	private BillingMonthEntityMapperExt billingMonthEntityMapperExt;

	@Override
	public Pagination<BossDebtsEntityExt> getDebtsByPage(
			Map<String, Object> searchParams) throws Exception {
		
		Pagination<BossDebtsEntityExt> pagination = new Pagination<BossDebtsEntityExt>();
		
		List<BossDebtsEntityExt> rows = bossDebtsMapperExt.getSerDebtsByPage(searchParams);
		int total = bossDebtsMapperExt.countDebtsList(searchParams);
		pagination.setRows(rows);
		pagination.setTotal(total);
		return pagination;
	}

	@Override
	public BossDebtsEntityExt getSerDebtsInfo(String serviceBillId)
			throws Exception {
		BossDebtsEntityExt debtsInfo = bossDebtsMapperExt.getServiceDebtsRecordInfo(serviceBillId);
		
		List<HashMap> list =  billingMonthEntityMapperExt.getProdNameList(
				debtsInfo.getUserId(), debtsInfo.getChargeMonth(), debtsInfo.getChargeYear());
		List<Map> prodList = new ArrayList<Map>();
		
		for (int i =0;i<list.size();i++) {
			Map data = new HashMap();
			data.put("prodName", list.get(i).get("prodName"));
			data.put("charge", list.get(i).get("charge"));
			prodList.add(data);
		}
		debtsInfo.setDataList(prodList);
		return debtsInfo;
	}
	
}
