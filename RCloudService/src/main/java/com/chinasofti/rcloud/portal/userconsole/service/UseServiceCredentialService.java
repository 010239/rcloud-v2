package com.chinasofti.rcloud.portal.userconsole.service;

import java.util.Map;

import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;


public interface UseServiceCredentialService {
	
	
    /**
     * 生成服务凭证
     * @param entity
     * @throws Exception
     */
	void insertServiceCredential(UserEntityExt user) throws Exception;
	/**
     * 激活服务凭证
     * @param id
     * @return
     * @throws Exception
     */
    boolean activateServiceCredential(String id,String orderId) throws Exception;
	
	/**
     * 注销服务凭证
     * @param id
     * @return
     * @throws Exception
     */
    boolean cancelServiceCredential(String id, UserEntityExt user) throws Exception;

    /**
     * 
     * @param jsonStr
     * @param accessKeyId
     * @param desStr
     * @return
     * @throws Exception
     */
	Map verifyServiceCredential(String jsonStr,String accessKeyId,String desStr,String serviceName) throws Exception;

	
	boolean getServiceOrderStatus(String tenantId, String serviceName) throws Exception;
}
