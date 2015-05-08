package com.chinasofti.rcloud.portal.userbill.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.dao.ApplicationBillEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationBillEntity;
import com.chinasofti.rcloud.domain.ApplicationOrderToBillEntityKey;
import com.chinasofti.rcloud.portal.userbill.dao.ApplicationBillEntityMapperExt;
import com.chinasofti.rcloud.portal.userbill.dao.ApplicationOrderToBillEntityMapperExt;
import com.chinasofti.rcloud.portal.userbill.domain.ApplicationBillEntityExt;
import com.chinasofti.rcloud.portal.userbill.service.ApplicationBillService;
import com.chinasofti.rcloud.portal.userorder.dao.ApplicationOrderEntityMapperExt;

/**
 * @author zhangjiaxing
 *
 * 2014年7月8日
 */

@Service("applicationBillService")
public class ApplicationBillServiceImpl implements ApplicationBillService {
	
	@Autowired
	private ApplicationBillEntityMapperExt applicationBillEntityMapperExt;
	
	@Autowired
	private ApplicationBillEntityMapper applicationBillEntityMapper;
	
	@Autowired
	private ApplicationOrderToBillEntityMapperExt applicationOrderToBillEntityMapperExt;
	
	@Autowired
	private ApplicationOrderEntityMapperExt applicationOrderEntityMapperExt;
	
	private Logger logger = Logger.getLogger(ApplicationBillServiceImpl.class);

	@Override
	public Pagination<ApplicationBillEntity> getApplicationBillListByPage(
			Map<String, Object> searchParams) throws Exception {
		
		Pagination<ApplicationBillEntity> pagination = new Pagination<ApplicationBillEntity>();
		List<ApplicationBillEntity> applicationBillList = applicationBillEntityMapperExt.getApplicationBillListByPage(searchParams);
		if (applicationBillList == null) {
			logger.error("未查询到您的云应用账单！");			
		}
		int count = applicationBillEntityMapperExt.countApplicationBill(searchParams);
		pagination.setRows(applicationBillList);
		pagination.setTotal(count);
		return pagination;
	}

	@Override
	public List<ApplicationOrderToBillEntityKey> getOrderIdList(String billId)
			throws Exception {
		List<ApplicationOrderToBillEntityKey> orderIdList = applicationOrderToBillEntityMapperExt.getOrderIdList(billId);	
		return orderIdList;
	}

	@Override
	public ApplicationBillEntityExt getApplicationBillInfo(String billId,
			List<ApplicationOrderToBillEntityKey> orderIdList) throws Exception {
		
		ApplicationBillEntityExt applicationBillInfo = new ApplicationBillEntityExt();
		List<Map> appl = new ArrayList<Map>();
		ApplicationBillEntity applicationBill = applicationBillEntityMapper.selectByPrimaryKey(billId);
		applicationBillInfo.setBillNumber(applicationBill.getBillNumber());
		applicationBillInfo.setApplicationNames(applicationBill.getApplicationNames());
		applicationBillInfo.setCreatedTime(applicationBill.getCreatedTime());
		applicationBillInfo.setEndTime(applicationBill.getEndTime());
		applicationBillInfo.setBillCosts(applicationBill.getBillCosts());
		for (int i = 0; i< orderIdList.size();i++) {
			Map data = new HashMap();
			data.put("appName", applicationOrderEntityMapperExt.getApplicationOrderInfo(
					orderIdList.get(i).getOrderId()).getApplication().getChname());
			data.put("appPrice", applicationOrderEntityMapperExt.getApplicationOrderInfo(
					orderIdList.get(i).getOrderId()).getMaintenanceCosts());
			appl.add(data);
		}
		applicationBillInfo.setDataList(appl);
		return applicationBillInfo;
	}

	@Override
	public Pagination<ApplicationBillEntityExt> getAppDebtsListByPage(
			Map<String, Object> searchParams) throws Exception {
		Pagination<ApplicationBillEntityExt> pagination = new Pagination<ApplicationBillEntityExt>();
		List<ApplicationBillEntityExt> appDebtsList =applicationBillEntityMapperExt.getApplicationDebtsRecordListByPage(searchParams);
		int count = applicationBillEntityMapperExt.countApplicationDebtsRecord(searchParams);
		pagination.setRows(appDebtsList);
		pagination.setTotal(count);
		return pagination;
	}

}
