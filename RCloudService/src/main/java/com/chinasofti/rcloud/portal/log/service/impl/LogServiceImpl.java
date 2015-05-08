package com.chinasofti.rcloud.portal.log.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.dao.AuditLogEntityMapper;
import com.chinasofti.rcloud.domain.AuditLogEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.log.service.LogService;

/**
 * @author zhangjiaxing
 *
 * 2014年10月27日
 */
@Service
public class LogServiceImpl implements LogService{

	@Autowired
	private AuditLogEntityMapper auditMapper;
	
	@Override
	public void insertLog(UserEntity user,
			String operation, String description) throws Exception {
		AuditLogEntity auditLog = new AuditLogEntity();
		String auditLogId = CommonUtil.getId();

		int userType = 1 ;  //开发者
		Date createDate = new Date();
		
		auditLog.setAuditLogId(auditLogId);
		auditLog.setUserId(user.getUserId());
		auditLog.setCreateDate(createDate);
		auditLog.setOperation(operation);
		auditLog.setUserType(userType);
		auditLog.setDescription(description);
		auditLog.setRoleCode(user.getRoleCode());
		
		auditMapper.insertSelective(auditLog);
		
	}

}
