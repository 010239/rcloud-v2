package com.chinasofti.rcloud.portal.cloudshop.dao;

import java.util.Map;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.ApplyEntity;

public interface ApplyMapperExt extends BaseDao {
	ApplyEntity getApplyListByAppId(Map<String, Object> searchParam);
}
