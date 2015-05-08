package com.chinasofti.rcloud.portal.userconsole.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.dao.TransactionRecordEntityMapper;
import com.chinasofti.rcloud.domain.TransactionRecordEntity;
import com.chinasofti.rcloud.portal.userconsole.dao.TransactionRecordEntityMapperExt;
import com.chinasofti.rcloud.portal.userconsole.domain.TransactionRecordEntityExt;
import com.chinasofti.rcloud.portal.userconsole.service.TransactionManagerService;

@Service("transactionManagerService")
public class TransactionManagerServiceImpl implements TransactionManagerService {

	@Autowired
	private TransactionRecordEntityMapperExt transactionRecordEntityMapperExt;

	@Autowired
	private TransactionRecordEntityMapper TransactionRecordEntityMapper;

	@Override
	public Pagination<TransactionRecordEntityExt> findListByPage(
			Map<String, Object> searchParam) throws RCloudException {
		// TODO Auto-generated method stub
		Pagination<TransactionRecordEntityExt> pagination = new Pagination<TransactionRecordEntityExt>();
		int count = transactionRecordEntityMapperExt.countFindList(searchParam);
		if (count > 0) {
			List<TransactionRecordEntityExt> list = transactionRecordEntityMapperExt
					.findListByPage(searchParam);
			pagination.setRows(list);
		} else {
			pagination.setRows(new ArrayList<TransactionRecordEntityExt>());
		}
		pagination.setTotal(count);
		return pagination;
	}

	public TransactionRecordEntity getTransactionRecordEntityById(
			String recordId) throws RCloudException {
		return TransactionRecordEntityMapper.selectByPrimaryKey(recordId);
	}

}
