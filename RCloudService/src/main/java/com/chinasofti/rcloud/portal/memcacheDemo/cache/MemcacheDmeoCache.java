package com.chinasofti.rcloud.portal.memcacheDemo.cache;

import java.util.List;

import com.chinasofti.rcloud.portal.memcacheDemo.domain.MemcacheDmeo;
import com.google.code.ssm.api.ParameterValueKeyProvider;

public interface MemcacheDmeoCache {

	public MemcacheDmeo getById(String spaceId);

	public void update(MemcacheDmeo memcacheDmeo);

	public void deleteById(String spaceId);

	public List<MemcacheDmeo> getMulti(String spaceId);

	public void updateMulti(MemcacheDmeo memcacheDmeo);

	public void deleteMulti(String spaceId);

	public MemcacheDmeo getAssign(@ParameterValueKeyProvider String spaceId);

	public void updateAssign(MemcacheDmeo memcacheDmeo);

	public void deleteAssign(@ParameterValueKeyProvider String spaceId);

}
