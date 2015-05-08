package com.chinasofti.rcloud.common.factory;

import com.chinasofti.rcloud.common.factory.service.ServiceLocal;


/**
 * 
 * 平台服务产品实现工厂，象调整套餐、获取计量信息这类功能均涉及到具体的产品操作，几个产品要区分对待。
 * 
 * @author lidonghui
 *
 * 2014年7月11日
 */
public class ServiceFactory {
	
	/**
     * 获取真实存在的服务实现对象
     * @param serviceImpl 服务实现，待定
     * @return ServiceLocal 服务实现接口
     * 
     */	
	public static ServiceLocal buildCache(String serviceImpl){
		return null;
	}
}
