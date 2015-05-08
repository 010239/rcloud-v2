package com.chinasofti.rcloud.portal.memcacheDemo.dao;

import java.util.List;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.portal.memcacheDemo.domain.MemcacheDmeo;

public interface MemcacheDmeoMapperDemo extends BaseDao{

	MemcacheDmeo getById(String spaceId);

	void update(MemcacheDmeo memcacheDmeo);

	void deleteById(String spaceId);
	
	List<MemcacheDmeo> getMulti(String spaceId);
}
