package com.chinasofti.rcloud.web.userconsole.controller;


import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.constants.BusinessConstants;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.portal.userconsole.service.UseServiceCredentialService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;


@Controller
public class UseServiceCredentialController extends BasicController{

	@Autowired
	private UseServiceCredentialService useServiceCredentialService;
	
	private Logger logger = Logger.getLogger(UseServiceCredentialController.class);
	
	/**
	 * 生成服务凭证
     * @param serviceId
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_QUERY)
	@ResponseBody
	@RequestMapping("rest/useServiceCredential/insertServiceCredential")
	public ResponseEntity<Boolean> insertServiceCredential() {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			UserEntityExt user = LoginUtil.getUser();
			useServiceCredentialService.insertServiceCredential(user);
		} catch (Exception e) {
			logger.error("服务凭证生成异常",e);
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	
	/**
	 * 激活服务凭证
     * @param id
     * @param orderId
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_QUERY)
	@ResponseBody
	@RequestMapping("rest/useServiceCredential/activateServiceCredential/{id}/{orderId}")
	public ResponseEntity<Boolean> activateServiceCredential(@PathVariable("id") String id,@PathVariable("orderId") String orderId) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			useServiceCredentialService.activateServiceCredential(id,orderId);
		} catch (Exception e) {
			logger.error("服务凭证激活异常",e);
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	
	/**
	 * 激活服务凭证
     * @param id
     * @param orderId
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_QUERY)
	@ResponseBody
	@RequestMapping("rest/useServiceCredential/cancelServiceCredential/{id}")
	public ResponseEntity<Boolean> cancelServiceCredential(@PathVariable("id") String id) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			UserEntityExt user = LoginUtil.getUser();
			useServiceCredentialService.cancelServiceCredential(id, user);
		} catch (Exception e) {
			logger.error("服务凭证注销异常",e);
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	
	
	/**
	 * 激活服务凭证
     * @param id
     * @param orderId
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("api/useServiceCredential/verifyServiceCredential/{jsonStr}/{accessKeyId}/{desStr}/{serviceName}")
	public ResponseEntity<Map> verifyServiceCredential(@PathVariable("jsonStr") String jsonStr,@PathVariable("accessKeyId") String accessKeyId,@PathVariable("desStr") String desStr,@PathVariable("serviceName") String serviceName) {
		ResponseEntity<Map> responseEntity = new ResponseEntity<Map>();
		Map result = null;
		try {
			result=useServiceCredentialService.verifyServiceCredential(jsonStr, accessKeyId, desStr,serviceName);
			responseEntity.setEntity(result);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		return responseEntity;
	}
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("api/useServiceCredential/checkServiceStatus/{tenantId}/{serviceName}")
	public ResponseEntity<Boolean> checkServiceOfUser(@PathVariable("tenantId") String tenantId, 
			@PathVariable("serviceName") String serviceName) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			boolean serviceStatus = useServiceCredentialService.getServiceOrderStatus(tenantId, serviceName);
			responseEntity.setEntity(serviceStatus);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		
		return responseEntity;
	}
	
}
