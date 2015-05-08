package com.chinasofti.rcloud.web.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.PageParameter;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.ApplicationTypeEntity;
import com.chinasofti.rcloud.portal.app.service.AppService;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityBlobExt;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.CommonUtil;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;

@Controller
public class AppStoreController extends BasicController {

	private Logger logger = Logger.getLogger(AppStoreController.class);
	
	@Autowired
	private AppService appService;
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping("page/application/findList")
	public String findApplicationList( HttpServletRequest request,Model model){
		String result = "storeManager/store";
		String userId = LoginUtil.getUserId();
		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
		searchParams.put("page", new PageParameter());
		searchParams.put("userId", userId);
		
		List<ApplicationTypeEntity> typeList = new ArrayList<ApplicationTypeEntity>();
		try {
			typeList = appService.findAppType();
			if(typeList!=null && typeList.size() > 0){
				searchParams.put("applicationType", typeList.get(0).getTypeId());
				model.addAttribute("defaultType", typeList.get(0).getTypeId());
			}
			
			model.addAttribute("typeList", typeList);
			
			Pagination<ApplicationEntityBlobExt> pagination = appService.getApplicationList(searchParams);
			model.addAttribute("pagination", pagination);
		} catch (RCloudException e) {
			logger.error("查询应用列表异常",e);
			result = this.handleBusinessExceptionByPage(e);
		}catch(Exception e){
			logger.error("查询应用列表异常",e);
			result = this.handleServerExceptionByPage(e);
		}		
		return result;
	}
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("rest/application/getApplicationListByType")
	public ResponseEntity<Pagination<ApplicationEntityBlobExt>> getApplicationListByType(
			 String applicationType,
			 int currentPage, 
			int pageSize,HttpServletRequest request){
		ResponseEntity<Pagination<ApplicationEntityBlobExt>> responseEntity = new ResponseEntity<Pagination<ApplicationEntityBlobExt>>();
		String userId = LoginUtil.getUserId();
		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
		PageParameter page = new PageParameter();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		searchParams.put("page", page);
		searchParams.put("applicationType", applicationType);		
		try {
			Pagination<ApplicationEntityBlobExt> pagination = appService.getApplicationList(searchParams);
			responseEntity.setEntity(pagination);
		} catch (RCloudException e) {
			logger.error("按应用类型查询应用列表异常",e);
			this.handleBusinessExceptionByJson(e, responseEntity);
		}catch(Exception e){
			logger.error("按应用类型查询应用列表异常",e);
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		return responseEntity;
	}
}
