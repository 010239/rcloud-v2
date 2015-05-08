package com.chinasofti.rcloud.portal.memcacheDemo.cache.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.portal.memcacheDemo.cache.MemcacheDmeoCache;
import com.chinasofti.rcloud.portal.memcacheDemo.dao.MemcacheDmeoMapperDemo;
import com.chinasofti.rcloud.portal.memcacheDemo.domain.MemcacheDmeo;
import com.google.code.ssm.api.InvalidateAssignCache;
import com.google.code.ssm.api.InvalidateMultiCache;
import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughAssignCache;
import com.google.code.ssm.api.ReadThroughMultiCache;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateAssignCache;
import com.google.code.ssm.api.UpdateMultiCache;
import com.google.code.ssm.api.UpdateSingleCache;

@Service("memcacheDmeoCache")
public class MemcacheDmeoCacheImpl implements MemcacheDmeoCache {

	@Autowired
	private MemcacheDmeoMapperDemo memcacheDmeoMapper;

	// *****SingleCache类 操作单个POJO的Cache数据*********

	// 取一个MemcacheDmeo对象。 缓存的key组成=demo+id，缓存时间=500秒
	@ReadThroughSingleCache(namespace = "demo", expiration = 500)
	public MemcacheDmeo getById(@ParameterValueKeyProvider String spaceId) {
		return memcacheDmeoMapper.getById(spaceId);
	}

	// 因数据修改时，更新名字空间为demo的缓存
	@UpdateSingleCache(namespace = "demo", expiration = 500)
	public void update(
			@ParameterDataUpdateContent @ParameterValueKeyProvider MemcacheDmeo memcacheDmeo) {
		memcacheDmeoMapper.update(memcacheDmeo);
	}

	// 删除数据时，删除缓存中的对象
	@InvalidateSingleCache(namespace = "demo")
	public void deleteById(@ParameterValueKeyProvider String spaceId) {
		memcacheDmeoMapper.deleteById(spaceId);
	}

	// ***************MultiCache类 操作List型的Cache数据**********

	// 取List对象。 缓存的key组成=demomulti+id，缓存时间=500秒
	@ReadThroughMultiCache(namespace = "demomulti", expiration = 500)
	public List<MemcacheDmeo> getMulti(@ParameterValueKeyProvider String spaceId) {
		return memcacheDmeoMapper.getMulti(spaceId);
	}

	// 因数据修改时，更新名字空间为demomulti的缓存
	@UpdateMultiCache(namespace = "demomulti", expiration = 500)
	public void updateMulti(
			@ParameterDataUpdateContent @ParameterValueKeyProvider MemcacheDmeo memcacheDmeo) {
		memcacheDmeoMapper.update(memcacheDmeo);
	}

	// 删除数据时，删除缓存中的对象
	@InvalidateMultiCache(namespace = "demomulti")
	public void deleteMulti(@ParameterValueKeyProvider String spaceId) {
		memcacheDmeoMapper.deleteById(spaceId);
	}

	// ***************AssignCache类 指定key操作Cache数据***********

	// 读取指定key缓存
	@ReadThroughAssignCache(assignedKey = "demoAssignKey", namespace = "demoAssign", expiration = 3000)
	public MemcacheDmeo getAssign(String spaceId) {
		return memcacheDmeoMapper.getById(spaceId);
	}

	// 指定key更新缓存
	@UpdateAssignCache(assignedKey = "demoAssignKey", namespace = "demoAssign", expiration = 3000)
	public void updateAssign(
			@ParameterDataUpdateContent MemcacheDmeo memcacheDmeo) {
		memcacheDmeoMapper.update(memcacheDmeo);
	}

	// 删除数据时，删除缓存中的对象
	@InvalidateAssignCache(assignedKey = "demoAssignKey", namespace = "demoAssign")
	public void deleteAssign(String spaceId) {
		memcacheDmeoMapper.deleteById(spaceId);
	}

}
