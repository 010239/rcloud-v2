package com.chinasofti.rcloud.common.factory.service;

import java.util.Map;


/**
 * 
 * 服务产品接口类
 * 
 * @author lidonghui
 *
 * 2014年7月11日
 */
public interface ServiceLocal {
	
	/**
     * 调整套餐
     * @param parameters 参数容器
     * 
     */
	public void adjustPlan(Map parameters);
	
	/**
     * 获取租户到当前时间点为止该服务所产生的费用
     * @param parameters 参数容器
     * @return Map 返回数据容器
     * 
     */
	public Map getCost(Map parameters);

}
