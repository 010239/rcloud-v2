package com.chinasofti.rcloud.portal.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.dao.ApplicationEntityMapper;
import com.chinasofti.rcloud.dao.ApplicationTypeEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.ApplicationEntityExample;
import com.chinasofti.rcloud.domain.ApplicationEntityExample.Criteria;
import com.chinasofti.rcloud.domain.ApplicationTypeEntity;
import com.chinasofti.rcloud.domain.ApplicationTypeEntityExample;
import com.chinasofti.rcloud.portal.app.service.AppService;
import com.chinasofti.rcloud.portal.cloudshop.dao.ApplicationMapperExt;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityBlobExt;

@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private ApplicationEntityMapper applicationEntityMapper;
	@Autowired
	private ApplicationMapperExt applicationMapperExt;
	@Autowired
	private ApplicationTypeEntityMapper applicationTypeEntityMapperExt;

	@Override
	public List<ApplicationEntity> findForPortal()  throws RCloudException{
		// TODO Auto-generated method stub
		ApplicationEntityExample example = new ApplicationEntityExample();
		Criteria c = example.createCriteria().andShowPortalEqualTo(1);
		example.clear();
		example.or(c);
		return applicationEntityMapper.selectByExample(example);
	}

	@Override
	public List<ApplicationTypeEntity> findAppType() throws RCloudException {
		// TODO Auto-generated method stub
		ApplicationTypeEntityExample example = new ApplicationTypeEntityExample();
		example.or(example.createCriteria().andMarkDeleteEqualTo(0));//未删除
		
		return applicationTypeEntityMapperExt.selectByExample(example);
	}

	@Override
	public Pagination<ApplicationEntityBlobExt> getApplicationList(
			Map<String, Object> searchParams) throws RCloudException {
		// TODO Auto-generated method stub
		Pagination<ApplicationEntityBlobExt> pagination = new Pagination<ApplicationEntityBlobExt>();
		int count = applicationMapperExt.countApplicationbytype(searchParams);
		if(count > 0){
			List<ApplicationEntityBlobExt> list = applicationMapperExt.selectApplicationbytypeByPage(searchParams);
			pagination.setRows(list);
		}else{
			pagination.setRows(new ArrayList<ApplicationEntityBlobExt>());
		}
		pagination.setTotal(count);
		return pagination;
	}
}
