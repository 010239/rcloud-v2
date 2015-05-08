package com.chinasofti.rcloud.portal.memcacheDemo.domain;

import java.io.Serializable;

import com.google.code.ssm.api.CacheKeyMethod;



public class MemcacheDmeo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String spaceId;
	private String userId;
	
	
	public String getSpaceId() {
		return spaceId;
	}
	
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@CacheKeyMethod  
	public String cacheKey() {
		return spaceId.toString();   
		} 
}
