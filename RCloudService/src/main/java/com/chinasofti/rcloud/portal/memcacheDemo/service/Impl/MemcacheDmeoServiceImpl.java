package com.chinasofti.rcloud.portal.memcacheDemo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.portal.memcacheDemo.cache.MemcacheDmeoCache;
import com.chinasofti.rcloud.portal.memcacheDemo.dao.MemcacheDmeoMapperDemo;
import com.chinasofti.rcloud.portal.memcacheDemo.domain.MemcacheDmeo;
import com.chinasofti.rcloud.portal.memcacheDemo.service.MemcacheDmeoService;


@Service("memcacheDmeoService")
public class MemcacheDmeoServiceImpl implements MemcacheDmeoService{
	
	@Autowired
	private MemcacheDmeoCache memcacheDmeoCache;
	@Autowired 
	private MemcacheDmeoMapperDemo memcacheDmeoMapper;
	
	@Override
	public MemcacheDmeo getById(String spaceId){
		
		MemcacheDmeo memcacheDmeo=memcacheDmeoCache.getById(spaceId);
		return memcacheDmeo;
	}
	
	@Override   
	public void update(MemcacheDmeo memcacheDmeo) {
		memcacheDmeoCache.update(memcacheDmeo);   
	} 
		
	@Override
	public void deleteById(String spaceId) {
		memcacheDmeoCache.deleteById(spaceId);   
	}

	@Override
	public List<MemcacheDmeo> getMulti(String spaceId) {
		return memcacheDmeoCache.getMulti(spaceId);
	}

	@Override
	public void updateMulti(MemcacheDmeo memcacheDmeo) {
		memcacheDmeoCache.update(memcacheDmeo);
		
	}

	@Override
	public void deleteMulti(String spaceId) {
		memcacheDmeoCache.deleteMulti(spaceId);
		
	}

	@Override
	public MemcacheDmeo getAssign(String spaceId) {
		return memcacheDmeoCache.getAssign(spaceId);
	}

	@Override
	public void updateAssign(MemcacheDmeo memcacheDmeo) {
		memcacheDmeoCache.updateAssign(memcacheDmeo);
		
	}

	@Override
	public void deleteAssign(String spaceId) {
		memcacheDmeoCache.deleteAssign(spaceId);
		
	} 
	
	
	
}



