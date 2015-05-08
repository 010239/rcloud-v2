package com.chinasofti.rcloud.web.cloudservice.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chinasofti.rcloud.common.CommonConstants;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.ServiceEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.cloudservice.service.RcloudService;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;

/**
 * @author zhangjiaxing
 *
 * 2014年9月24日
 */
@Controller
public class RcloudServiceManagerController extends BasicController{
	
	
	@Autowired
	private RcloudService rcloudService;
	
//	/**
//	 * 根据serviceId跳转到相关产品购买页面
//	 * @param serviceId
//	 * @param request
//	 * @param model
//	 * @return
//	 */
//	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
//	@RequestMapping("page/cloudservicePlatform/rcloudServiceBuyPage/{serviceId}")
//	public String cloudServiceBuy(@PathVariable("serviceId") String serviceId,
//			HttpServletRequest request, Model model) {
//		
//		model.addAttribute("serviceId", serviceId);
//		try {
//			ProductsEntity list = rcloudService.getserviceList(serviceId);
//			model.addAttribute("clusivePricelist", list);
//		} catch (RCloudException re) {
//			return handleBusinessExceptionByPage(re);
//		} catch (Exception e) {
//			return this.handleServerExceptionByPage(e);
//		}
//		
//		return "serviceManager/rcloudServiceBuy";
//	}
//	
	
	/**
	 * 根据serviceId跳转到相关产品购买页面
	 * @param serviceId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_ADMIN )
	@RequestMapping(value = "page/cloudservicePlatform/rcloudServiceBuy", method = RequestMethod.POST)
	public String cloudServiceBuySave(@RequestParam(value = "pkgId", defaultValue = "") String pkgId,
			@RequestParam(value = "prodId", defaultValue = "") String prodId,
			@RequestParam(value = "prodName", defaultValue = "") String prodName,
			@RequestParam(value = "serviceId", defaultValue = "") String serviceId,
			HttpServletRequest request, Model model) {
		
		String userId = LoginUtil.getUserId();
		try {
			UserEntity user  = new UserEntity();
			user.setUserId(userId);
			user.setRoleCode(LoginUtil.getUser().getRoleCode());
			rcloudService.insertBusinessOrder(userId, prodId, pkgId,user,prodName,serviceId);
		} catch (RCloudException re) {
			return handleBusinessExceptionByPage(re);
		} catch (Exception e) {
			return this.handleServerExceptionByPage(e);
		}
		
		return "redirect:/page/cloudservicePlatform/";
	}
	
	
	/**
	 * 云服务控制台
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_SERVICE_QUERY )
	@RequestMapping("page/cloudservicePlatform/")
	public String getCloudservicePlatform(HttpServletRequest request, Model model) {
	
		Map<String ,List> map = new HashMap<String, List>();
		UserEntityExt user=LoginUtil.getUser();
		HttpSession  session =LoginUtil.getSession();
		String userId=user.getUserId();
		String roleCode=user.getRoleCode();
		
		if(roleCode.equals(CommonConstants.ORG_DEVLOPER)){
			userId=(String) session.getAttribute("tenant");
			model.addAttribute("xs", 0);
		}else{
			model.addAttribute("xs", 1);
		}
		
		try {
			map = rcloudService.getServicePlatform(userId);
			List<ServiceEntity> listOpen = map.get("open");
			List<ServiceEntity> listClose = map.get("close");
			model.addAttribute("listOpen", listOpen);
			model.addAttribute("listClose", listClose);
		}   catch (Exception e) {
			return this.handleServerExceptionByPage(e);
		}	
		
		return "serviceManager/cloudServicePlatform";
	}
	
}
