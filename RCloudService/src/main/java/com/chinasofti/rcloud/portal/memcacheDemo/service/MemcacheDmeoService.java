package com.chinasofti.rcloud.portal.memcacheDemo.service;

import java.util.List;

import com.chinasofti.rcloud.portal.memcacheDemo.domain.MemcacheDmeo;
import com.google.code.ssm.api.ParameterValueKeyProvider;


public interface MemcacheDmeoService {

	MemcacheDmeo getById(String spaceId);

	void deleteById(String spaceId);

	void update(MemcacheDmeo memcacheDmeo);
	
	
	public List<MemcacheDmeo> getMulti(String spaceId) ;
	public void updateMulti(MemcacheDmeo memcacheDmeo) ;
	public void deleteMulti( String spaceId) ;
	
	
	
	public MemcacheDmeo getAssign(@ParameterValueKeyProvider String spaceId) ;
	public void updateAssign( MemcacheDmeo memcacheDmeo) ;
	public void deleteAssign(@ParameterValueKeyProvider String spaceId) ;

    
}
