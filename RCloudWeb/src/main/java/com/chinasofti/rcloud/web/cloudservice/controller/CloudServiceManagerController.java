package com.chinasofti.rcloud.web.cloudservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.PageParameter;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.dao.AccessKeyRecordEntityMapper;
import com.chinasofti.rcloud.domain.BusinessOrderEntity;
import com.chinasofti.rcloud.domain.ProductsEntity;
import com.chinasofti.rcloud.portal.cloudservice.service.CloudService;
import com.chinasofti.rcloud.portal.cloudservice.service.CloudServiceManagerService;
import com.chinasofti.rcloud.portal.cloudservice.service.RcloudService;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.portal.userconsole.domain.AccessKeyRecordEntityExt;
import com.chinasofti.rcloud.portal.userconsole.service.UseServiceCredentialService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;

@Controller
public class CloudServiceManagerController extends BasicController {
	
	
	@Autowired
	private CloudServiceManagerService cloudServiceManagerService;
	
	@Autowired
	private RcloudService rcloudService;
	
	@Autowired
	private UseServiceCredentialService useServiceCredentialService;
	
	@Autowired
	private AccessKeyRecordEntityMapper accessKeyRecordEntityMapper;
	
	@Autowired
	private CloudService cloudService;
	
	
	/**
	 * 服务管理列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
	@ResponseBody
	@RequestMapping("rest/cloudservice/getAllCloudService")
	public ResponseEntity<Pagination<HashMap<String, String>>> getAllCloudService1(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>>  list=new ArrayList<HashMap<String,String>>();
		ResponseEntity<Pagination<HashMap<String, String>>> responseEntity = new ResponseEntity<Pagination<HashMap<String, String>>>();
		try {
			Pagination<HashMap<String, String>> pagination = new Pagination<HashMap<String, String>>();
			String userId=LoginUtil.getUserId();
//			userId="1";
			list=cloudServiceManagerService.getAllCloudService(userId);
			pagination.setRows(list);
			map.put("rows", list);
			map.put("total", 1);
			responseEntity.setEntity(pagination);
			
		}  catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		return responseEntity; 
	}
	
	
	/**
	 * 跳转服务管理列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_QUERY )
	@RequestMapping("page/cloudservice/")
	public String getAllCloudService(HttpServletRequest request, Model model) {
		model.addAttribute("role", LoginUtil.getUser().getRoleCode());
		model.addAttribute("userId", LoginUtil.getUserId());
		String showList = request.getParameter("showList");
		if (!StringUtils.isEmpty(showList)) {
			model.addAttribute("show_list", showList);
		}
		
		return "serviceManager/serviceManager";
	}
	
	
	
	
	/**
	 * 服务订单详情
	 * @param orderId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
	@RequestMapping("page/cloudservice/getServiceOrderInfo/{orderId}")
	public String getServiceOrderInfo(@PathVariable("orderId") String orderId,HttpServletRequest request, Model model) {
		//ResponseEntity<Pagination<HashMap<String, String>>> responseEntity = new ResponseEntity<Pagination<HashMap<String, String>>>();
		try {
			HashMap<String, String> orderInfo=cloudServiceManagerService.getServiceOrderInfo(orderId);
			model.addAttribute("order", orderInfo);
		}catch (Exception e) {
			return this.handleServerExceptionByPage(e);
		}	
		return "serviceManager/orderInfo";
	}
	
	
	/**
	 * 服务凭证列表
	 * @param orderId
	 * @param serviceId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_QUERY )
	@ResponseBody
	@RequestMapping(value="rest/cloudservice/credential/list", method=RequestMethod.GET)
	public ResponseEntity<Pagination<AccessKeyRecordEntityExt>> getServiceCredentialList(HttpServletRequest request) {
		ResponseEntity<Pagination<AccessKeyRecordEntityExt>> responseEntity = new ResponseEntity<Pagination<AccessKeyRecordEntityExt>>();
		try {
			String userId=LoginUtil.getUserId();
			List<String> keyCreatorSet = new ArrayList<String>();
			keyCreatorSet.add(userId);
			if (!StringUtils.isEmpty(LoginUtil.getUser().getTenant())) {
				keyCreatorSet.add(LoginUtil.getUser().getTenant());
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userIdList", keyCreatorSet);
			
			PageParameter page = new PageParameter();
			Map<String, Object> searchParams = com.chinasofti.rcloud.web.common.CommonUtil
					.getParameterMap(request);
			if (!StringUtils.isEmpty(searchParams.get("page")) && 
					!StringUtils.isEmpty(searchParams.get("rows"))) {
				page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
				page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
			}
			paramMap.put("page", page);
			
			Pagination<AccessKeyRecordEntityExt> pagination = cloudServiceManagerService.getServiceCredentialList(paramMap);
			
			responseEntity.setEntity(pagination);
			
		}   catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}	
		
		return responseEntity; 
	}
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
	@RequestMapping("page/cloudservice/getServiceCredentialListForPage/{orderId}/{serviceId}")
	public String getServiceCredentialListForPage(@PathVariable("orderId") String orderId,@PathVariable("serviceId") String serviceId,HttpServletRequest request, Model model) {
		
		model.addAttribute("orderId", orderId);
		model.addAttribute("serviceId", serviceId);
		
		return "serviceManager/serviceCredentialList";
	}
	
	
	/**
	 * 服务凭证详情
	 * @param keyId
	 * @param keySecret
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_QUERY )
	@RequestMapping("page/cloudservice/getServiceCredentialInfo/{keyId}/{keySecret}")
	public String getServiceCredentialInfo(@PathVariable("keyId") String keyId,@PathVariable("keySecret") String keySecret,HttpServletRequest request, Model model) {
		//ResponseEntity<Pagination<HashMap<String, String>>> responseEntity = new ResponseEntity<Pagination<HashMap<String, String>>>();
		try {
			model.addAttribute("keyId", keyId);
			model.addAttribute("keySecret", keySecret);
		}   catch (Exception e) {
			return this.handleServerExceptionByPage(e);
		}	
		return "serviceManager/serviceCredentialInfo";
	}
	
	
	/**
	 * 注销服务凭证
	 * @param keyId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
	@RequestMapping("rest/cloudservice/CancelServiceCredentia/{keyId}")
	@ResponseBody
	public ResponseEntity<Boolean> CancelServiceCredentia(@PathVariable("keyId") String keyId,HttpServletRequest request) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			UserEntityExt  user=LoginUtil.getUser();
			useServiceCredentialService.cancelServiceCredential(keyId, user);
		}   catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}	
		return responseEntity;
	}
	
	/**
	 * 服务凭证激活
	 * @param keyId
	 * @param orderId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
	@RequestMapping("page/cloudservice/activateServiceCredential/{keyId}/{orderId}/{serviceId}")
	public String ActivateServiceCredential(@PathVariable("keyId") String keyId,@PathVariable("orderId") String orderId,@PathVariable("serviceId") String serviceId,HttpServletRequest request, Model model) {
		//ResponseEntity<Pagination<HashMap<String, String>>> responseEntity = new ResponseEntity<Pagination<HashMap<String, String>>>();
		try {
			useServiceCredentialService.activateServiceCredential(keyId, orderId);
		}   catch (RCloudException re) {
			return this.handleBusinessExceptionByPage(re);
		} catch (Exception e) {
			return this.handleServerExceptionByPage(e);
		}
		return "redirect:/page/cloudservice/getServiceCredentialListForPage/"+orderId+"/"+serviceId;
	}
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
	@RequestMapping("page/cloudservice/getInclusivePriceListForPage/{serviceId}/{orderId}")
	public String getInclusivePriceListForPage(@PathVariable("serviceId") String serviceId,@PathVariable("orderId") String orderId,HttpServletRequest request, Model model) {
		model.addAttribute("serviceId", serviceId);
		model.addAttribute("orderId", orderId);
		return "serviceManager/inclusivePriceList";
	}
	
	/**
	 * 服务凭证生成
	 * @param serviceId
	 * @param orderId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
	@RequestMapping("rest/cloudservice/createServiceCredential")
	@ResponseBody
	public ResponseEntity<Boolean> createServiceCredential(HttpServletRequest request, Model model) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			UserEntityExt  user=LoginUtil.getUser();
			useServiceCredentialService.insertServiceCredential(user);
		}   catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		
		return responseEntity;
	}
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
	@RequestMapping("page/cloudservicePlatform/cloudServiceBuyForPage/{serviceId}")
	public String cloudServiceBuyForPage(@PathVariable("serviceId") String serviceId,HttpServletRequest request, Model model) {
		ResponseEntity<Pagination<HashMap<String, String>>> responseEntity = new ResponseEntity<Pagination<HashMap<String, String>>>();
		String userId=LoginUtil.getUserId();
		List<BusinessOrderEntity> listOrder= rcloudService.getCloudServiceOrder(userId,serviceId);
		if(listOrder.size()>=1){
			return "redirect:/page/cloudservicePlatform/";
		}else{
			model.addAttribute("serviceId", serviceId);
			ProductsEntity list = new ProductsEntity();
			try {
				list = rcloudService.getserviceList(serviceId);
			}   catch (RCloudException re) {
				return this.handleBusinessExceptionByPage(re);
			} catch (Exception e) {
				return this.handleServerExceptionByPage(e);
			}
			model.addAttribute("clusivePricelist", list);
			return "serviceManager/rcloudServiceBuy";
		}	
	}
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_QUERY )
	@ResponseBody
	@RequestMapping("rest/cloudservicePlatform/cloudServiceBuyCheck/{serviceId}")
	public ResponseEntity<String> cloudServiceBuyCheck(@PathVariable("serviceId") String serviceId,HttpServletRequest request, Model model) {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		try{
			String userId=LoginUtil.getUserId();
			if(userId==null){
				responseEntity.setEntity("false");
			}else{
				List<BusinessOrderEntity> listOrder= rcloudService.getCloudServiceOrder(userId,serviceId);
				if(listOrder.size()>=1){
					responseEntity.setEntity("true");
				}else{
					responseEntity.setEntity("false");
				}
			}
		}catch(Exception e){
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	
	/**
	 * 查询serviceName和manageurl 合集的api接口
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "api/getManageUrlList", method = RequestMethod.GET)
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	public ResponseEntity<List<HashMap<String, String>>> getManageUrlList(
			HttpServletResponse response) throws IOException {
		ResponseEntity<List<HashMap<String, String>>> responseEntity = new ResponseEntity<List<HashMap<String, String>>>();
			try{
				
				responseEntity.setEntity(cloudService.getManageUrlList());
			} catch (RCloudException re) {
				this.handleBusinessExceptionByJson(re, responseEntity);
			} catch (Exception e) {
				this.handleServerExceptionByJson(e, responseEntity);
			}
				return responseEntity;
		
	}
	
	/**
	 * 根据serviceName查询manageurl的api接口
	 * @param serviceName
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "api/getManageUrl/{serviceName}", method = RequestMethod.GET)
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	public ResponseEntity<HashMap<String, String>> getManageUrl(@PathVariable("serviceName") String serviceName,
			HttpServletResponse response) throws IOException {
		ResponseEntity<HashMap<String, String>> responseEntity = new ResponseEntity<HashMap<String, String>>();
		try{
			
			responseEntity.setEntity(cloudService.getManageUrl(serviceName));
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
			return responseEntity;
	}
	
	
}
