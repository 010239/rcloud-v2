package com.chinasofti.rcloud.common.factory.cache;



/**
 * 
 * 缓存产品接口类
 * 
 * @author lidonghui
 *
 * 2014年7月11日
 */
public interface DataCache {

	/**
     * 向缓存中放入数据
     * @param key 存放数据的key
     * @param value 需要在缓存中存放的数据
     * 
     */
	public void putData(String key,Object value);
	
	
	/**
     * 取出放在缓存中的数据
     * @param key 存放数据的key
     * @param value 需要在缓存中存放的数据
     * 
     */
	public Object getData(String key);
	
	/**
     * 清除放在缓存中的数据
     * @param key 存放数据的key
     * 
     */
	public Object clearData(String key);
	
	/**
     * 清除所有缓存中的数据
     * 
     */
	public void clearAll();
}
