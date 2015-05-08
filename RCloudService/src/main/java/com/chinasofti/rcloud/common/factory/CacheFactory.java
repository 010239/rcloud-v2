package com.chinasofti.rcloud.common.factory;

import com.chinasofti.rcloud.common.SpringContextUtil;
import com.chinasofti.rcloud.common.constants.BusinessConstants;
import com.chinasofti.rcloud.common.factory.cache.DataCache;
import com.chinasofti.rcloud.common.factory.cache.LocalCacheImpl;


/**
 * 
 * 缓存实现工厂类，目前包括三类实现，本地缓存、memcache缓存、阿里云缓存服务
 * 
 * @author lidonghui
 *
 * 2014年7月11日
 */
public class CacheFactory {
	
	/**
     * 获取真实存在的缓存实现对象
     * @param cacheImpl 缓存实现，local-本地实现，memcached-缓存服务器实现，aliyun-阿里云缓存服务实现
     * @return DataCache 数据缓存接口
     * 
     */	
	public static DataCache buildCache(String cacheImpl){
		
		if(BusinessConstants.CACHE_IMPL_ALIYUN.equalsIgnoreCase(cacheImpl)){
			return null;
		}else if(BusinessConstants.CACHE_IMPL_MEMCACHE.equalsIgnoreCase(cacheImpl)){
			return null;
		}else{
			return (DataCache)SpringContextUtil.getClassInstance(LocalCacheImpl.class);
		}
	}

}
