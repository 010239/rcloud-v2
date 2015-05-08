package com.chinasofti.rcloud.web.cloudshop.controller;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.PageParameter;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityExt;
import com.chinasofti.rcloud.portal.cloudshop.service.ApplicationService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.CommonUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;

@Controller
public class ApplicationController extends BasicController {

	@Autowired
	private ApplicationService applicationService;

	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("rest/application/{applicationId}")
	public ResponseEntity<ApplicationEntityExt> selectById(
			@PathVariable("applicationId") String appId) {
		ResponseEntity<ApplicationEntityExt> responseEntity = new ResponseEntity<ApplicationEntityExt>();
		try {
			ApplicationEntityExt applicationEntityExt = applicationService
					.selectApplicationById(appId);
			responseEntity.setEntity(applicationEntityExt);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}

		return responseEntity;
	}

	@RequestMapping("page")
	public String goPage() {
		System.out.println("sssssssss");
		return "aa";
	}

	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("rest/application/view/{applicationId}")
	public ResponseEntity<ApplicationEntity> selectBasicById(
			@PathVariable("applicationId") String appId) {
		ResponseEntity<ApplicationEntity> responseEntity = new ResponseEntity<ApplicationEntity>();
		try {
			ApplicationEntity applicationEntity = applicationService
					.selectAppById(appId);
			responseEntity.setEntity(applicationEntity);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}

		return responseEntity;
	}

	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("rest/application/pagination/{currentPage}/{pageSize}")
	public ResponseEntity<Pagination<ApplicationEntityExt>> searchApplicationByPage(
			HttpServletRequest request, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize")int pageSize) {
		ResponseEntity<Pagination<ApplicationEntityExt>> responseEntity = new ResponseEntity<Pagination<ApplicationEntityExt>>();
		try {
			Map<String, Object> searchParams = CommonUtil.getParameterMap(request);

			PageParameter page = new PageParameter();
			page.setCurrentPage(currentPage);
			page.setPageSize(pageSize);
			searchParams.put("page", page);

			Pagination<ApplicationEntityExt> pagination = applicationService
					.searchAppByPage(searchParams);

			responseEntity.setEntity(pagination);

		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}

		return responseEntity;

	}

}
