package com.chinasofti.rcloud.web.userorder.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.chinasofti.rcloud.portal.userorder.domain.ApplicationOrderEntityExt;
import com.chinasofti.rcloud.portal.userorder.domain.BusinessOrderEntityExt;
import com.chinasofti.rcloud.portal.userorder.service.ApplicationOrderService;
import com.chinasofti.rcloud.portal.userorder.service.BusinessOrderService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.CommonUtil;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;

/**
 * @author zhangjiaxing
 *
 * 2014年7月7日
 */
@Controller
public class OrderController extends BasicController{
	
    @Autowired
	private ApplicationOrderService applicationOrderService;
	
	@Autowired
	private BusinessOrderService businessOrderService;
	
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_ORDER_ADMIN)
	@RequestMapping("page/userOrder")
	public String getPage() {
		return "userConsole/userOrder";
	}
	
	/**
	 * 套餐选择列表
	 * @param serviceId
	 * @param orderId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("rest/getInclusivePriceList/{serviceId}")
	public String getInclusivePriceList(@PathVariable("serviceId") String serviceId,HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>>  list=new ArrayList<HashMap<String,String>>();
		try {
			//list=cloudServiceManagerService.getInclusivePriceList(serviceId);
			map.put("rows", list);
			map.put("total", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jacksonMapper.writeValueAsString(map); 
	}
	
	
	/**
	 * 根据条件查询用户云应用订单列表
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@ResponseBody
	@RequestMapping(value="rest/userorder/applicationOrder", method = RequestMethod.GET)
	public ResponseEntity<Pagination<ApplicationOrderEntityExt>> getApplicationOrderListByPage(
			HttpServletRequest request) throws ParseException {
		ResponseEntity<Pagination<ApplicationOrderEntityExt>> responseEntity = new ResponseEntity<Pagination<ApplicationOrderEntityExt>>();
		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
		String userId = LoginUtil.getUserId();
		String aStatus = request.getParameter("orderStatus");
		if ( aStatus!=null && !aStatus.equals("")) {	
			int orderStatus = Integer.parseInt(aStatus);
			searchParams.put("orderStatus", orderStatus);
		}
		String startT =request.getParameter("startTime");
		String endT= request.getParameter("endTime");
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtils.isEmpty(startT) ){
			searchParams.put("orderStartTime", sdt.parse(startT));
		}
		if(!StringUtils.isEmpty(endT)){
			searchParams.put("orderEndTime", sdt.parse(endT));
		}
		String orderType = request.getParameter("orderType");
		PageParameter page = new PageParameter();
		if (!StringUtils.isEmpty(searchParams.get("page")) && 
				!StringUtils.isEmpty(searchParams.get("rows"))) {
			page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
			page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
		}
		searchParams.put("page", page);
		searchParams.put("userId", userId);
		//如果是售出应用
		if( "1".equals(orderType)) {
			try {
				Pagination<ApplicationOrderEntityExt> pagination = applicationOrderService.
						getSaleApplicationOrderByPage(searchParams);
				responseEntity.setEntity(pagination);
			} catch (RCloudException re) {
				this.handleBusinessExceptionByJson(re, responseEntity);
			} catch (Exception e) {
				this.handleServerExceptionByJson(e, responseEntity);
			}
		}
		//如果是购买应用
		if ( "2".equals(orderType)) {
			try {
				Pagination<ApplicationOrderEntityExt> pagination = applicationOrderService.
						getBuyApplicationOrderByPage(searchParams);
				responseEntity.setEntity(pagination);
			} catch (RCloudException re) {
				this.handleBusinessExceptionByJson(re, responseEntity);
			} catch (Exception e) {
				this.handleServerExceptionByJson(e, responseEntity);
			}
		}
		return responseEntity;		
	}
	
	
	@RequestMappingName( value =CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@ResponseBody
	@RequestMapping(value="rest/userorder/getAllapplicationOrder", method = RequestMethod.GET)
	public ResponseEntity<Pagination<ApplicationOrderEntityExt>> getAllapplicationOrder(
			HttpServletRequest request) throws ParseException {
		ResponseEntity<Pagination<ApplicationOrderEntityExt>> responseEntity = new ResponseEntity<Pagination<ApplicationOrderEntityExt>>();
		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
		String userId = LoginUtil.getUserId();
		String aStatus = request.getParameter("orderStatus");
		if ( aStatus!=null && !aStatus.equals("")) {	
			int orderStatus = Integer.parseInt(aStatus);
			searchParams.put("orderStatus", orderStatus);
		}
		String startT =request.getParameter("startTime");
		String endT= request.getParameter("endTime");
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtils.isEmpty(startT)){
			searchParams.put("orderStartTime", sdt.parse(startT));
		}
		if(!StringUtils.isEmpty(endT)){
			searchParams.put("orderEndTime", sdt.parse(endT));
		}
		String orderType = request.getParameter("orderType");
		PageParameter page = new PageParameter();
		if (!StringUtils.isEmpty(searchParams.get("page")) && 
				!StringUtils.isEmpty(searchParams.get("rows"))) {
			page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
			page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
		}
		searchParams.put("page", page);
		searchParams.put("userId", userId);
		Pagination<ApplicationOrderEntityExt>	pagination=new Pagination<ApplicationOrderEntityExt>();
		Pagination<ApplicationOrderEntityExt>	sc=new Pagination<ApplicationOrderEntityExt>();
		Pagination<ApplicationOrderEntityExt>	gm=new Pagination<ApplicationOrderEntityExt>();
		//如果是售出应用
			try {
				 sc = applicationOrderService.
						getSaleApplicationOrderByPage(searchParams);
			} catch (RCloudException re) {
				this.handleBusinessExceptionByJson(re, responseEntity);
			} catch (Exception e) {
				this.handleServerExceptionByJson(e, responseEntity);
			}
		//如果是购买应用
			try {
				gm= applicationOrderService.
						getBuyApplicationOrderByPage(searchParams);
				
			} catch (RCloudException re) {
				this.handleBusinessExceptionByJson(re, responseEntity);
			} catch (Exception e) {
				this.handleServerExceptionByJson(e, responseEntity);
			}
			List<ApplicationOrderEntityExt> list=new ArrayList<ApplicationOrderEntityExt>();
			list=sc.getRows();
			list.addAll(gm.getRows());
			pagination.setRows(list);
			pagination.setTotal(sc.getTotal()+gm.getTotal());
			responseEntity.setEntity(gm);
		return responseEntity;		
	}
	
	
	
	
	

	/**
	 * 根据订单号和订单类型查询订单详情
	 * @param orderType
	 * @param orderNumber
	 * @param request
	 * @return
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_ORDER_ADMIN)
	@ResponseBody
	@RequestMapping("rest/userorder/applicationOrderInfo/{orderNumber}/{orderType}")
	public ResponseEntity<ApplicationOrderEntityExt> getApplicationOrderInfo(@PathVariable("orderType") String orderType,
			@PathVariable("orderNumber") String orderNumber,HttpServletRequest request) {
		ResponseEntity<ApplicationOrderEntityExt> responseEntity = new ResponseEntity<ApplicationOrderEntityExt>();
		String userId = LoginUtil.getUserId();	
		//售出应用
		if ( "1".equals(orderType)) {
			try{
				ApplicationOrderEntityExt applicationOrderEntityExt = applicationOrderService.
						getSaleApplicationOrderInfo(userId, orderNumber);
				responseEntity.setEntity(applicationOrderEntityExt);
			} catch (RCloudException re) {
				this.handleBusinessExceptionByJson(re, responseEntity);
			} catch (Exception e) {
				this.handleServerExceptionByJson(e, responseEntity);
			}
		}
		//购买应用
		if ( "2".equals(orderType)) {
			try{
				ApplicationOrderEntityExt applicationOrderEntityExt = applicationOrderService.
						getBuyApplicationOrderInfo(userId, orderNumber);
				responseEntity.setEntity(applicationOrderEntityExt);
			} catch (RCloudException re) {
				this.handleBusinessExceptionByJson(re, responseEntity);
			} catch (Exception e) {
				this.handleServerExceptionByJson(e, responseEntity);
			}
		}
		
		return responseEntity;
		
	}
	
//	/**
//	 * 根据条件查询用户云服务订单列表
//	 * @param currentPage
//	 * @param pageSize
//	 * @param request
//	 * @return
//	 * @throws ParseException 
//	 */
//	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_USER_ADMIN)
//	@ResponseBody
//	@RequestMapping(value="rest/userorder/serviceOrder", method = RequestMethod.GET)
//	public ResponseEntity<Pagination<ServiceOrderEntityExt>> getServiceOrderList(
//			HttpServletRequest request) throws ParseException {
//		
//		ResponseEntity<Pagination<ServiceOrderEntityExt>> responseEntity = new ResponseEntity<Pagination<ServiceOrderEntityExt>>();
//		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
//		String userId = LoginUtil.getUserId();
//		String oStatus = request.getParameter("status");
//		if ( oStatus!=null && ! oStatus.equals("")) {	
//			int status = Integer.parseInt(oStatus);
//			searchParams.put("status", status);
//		}
//		String payPattern =request.getParameter("payPattern");
//		if ( payPattern != null && ! payPattern.equals("")){
//			int pay = Integer.parseInt(payPattern);
//			searchParams.put("payPattern", pay);
//		}
//		String startT =request.getParameter("startTime");
//		String endT= request.getParameter("endTime");
//		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
//		if(!StringUtils.isEmpty(startT) && !StringUtils.isEmpty(endT)){
//			searchParams.put("orderStartTime", sdt.parse(startT));
//			searchParams.put("orderEndTime", sdt.parse(endT));
//		}
//		
//		PageParameter page = new PageParameter();
//		if (!StringUtils.isEmpty(searchParams.get("page")) && 
//				!StringUtils.isEmpty(searchParams.get("rows"))) {
//			page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
//			page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
//		}
//		searchParams.put("page", page);
//		searchParams.put("userId", userId);
//		try {
//			Pagination<ServiceOrderEntityExt> pagination = serviceOrderService.
//					findServiceOrderByPage(searchParams);
//			responseEntity.setEntity(pagination);
//		} catch (RCloudException re) {
//			this.handleBusinessExceptionByJson(re, responseEntity);
//		} catch (Exception e) {
//			this.handleServerExceptionByJson(e, responseEntity);
//		}		
//		return responseEntity;		
//	}
	
	
//	/**
//	 * 根据订单号查询云服务订单详情
//	 * @param orderNumber
//	 * @return
//	 */
//	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_ORDER_ADMIN)
//	@ResponseBody
//	@RequestMapping("rest/userorder/serviceOrderInfo/{orderNumber}")
//	public ResponseEntity<ServiceOrderEntityExt> getServiceOrderInfo(
//			@PathVariable("orderNumber") String orderNumber) {
//		ResponseEntity<ServiceOrderEntityExt> responseEntity = new ResponseEntity<ServiceOrderEntityExt>();
//		try {
//			ServiceOrderEntityExt serviceOrderEntityExt = serviceOrderService.findServiceOrderInfo(orderNumber);
//			responseEntity.setEntity(serviceOrderEntityExt);
//		} catch (RCloudException re) {
//			this.handleBusinessExceptionByJson(re, responseEntity);
//		} catch (Exception e) {
//			this.handleServerExceptionByJson(e, responseEntity);
//		}
//
//		return responseEntity;
//	}
	
	/**
	 * 根据条件查询用户云服务订单列表
	 * （新版）
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@ResponseBody
	@RequestMapping(value="rest/userorder/businessOrder", method = RequestMethod.GET)
	public ResponseEntity<Pagination<BusinessOrderEntityExt>> getBusinessOrder(
			HttpServletRequest request) throws ParseException {
		
		ResponseEntity<Pagination<BusinessOrderEntityExt>> responseEntity = new ResponseEntity<Pagination<BusinessOrderEntityExt>>();
		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
		String userId = LoginUtil.getUserId();
		String oStatus = request.getParameter("status");
		if ( oStatus!=null && ! oStatus.equals("")) {	
			searchParams.put("oStatus", oStatus);
		}
		String type =request.getParameter("type");
		if ( type != null && ! type.equals("")){
			int orderType = Integer.parseInt(type);
			searchParams.put("orderType", orderType);
		}
		String startT =request.getParameter("startTime");
		String endT= request.getParameter("endTime");
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtils.isEmpty(startT) ){
			searchParams.put("orderStartTime", sdt.parse(startT));
		}
		if(!StringUtils.isEmpty(endT)){
			searchParams.put("orderEndTime", sdt.parse(endT));
		}
		
		PageParameter page = new PageParameter();
		if (!StringUtils.isEmpty(searchParams.get("page")) && 
				!StringUtils.isEmpty(searchParams.get("rows"))) {
			page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
			page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
		}
		searchParams.put("page", page);
		searchParams.put("userId", userId);
		try {
			Pagination<BusinessOrderEntityExt> pagination = businessOrderService.
					searchOrderByPage(searchParams);
			responseEntity.setEntity(pagination);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		return responseEntity;		
	}
	
	
	/**
	 * 云服务订单详情(新)
	 * @param orderNumber
	 * @return
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_ORDER_ADMIN)
	@ResponseBody
	@RequestMapping("rest/userorder/businessOrderInfo/{businessOrderId}")
	public ResponseEntity<BusinessOrderEntityExt> getBusinessOrderInfo(
			@PathVariable("businessOrderId") String businessOrderId) {
		ResponseEntity<BusinessOrderEntityExt> responseEntity = new ResponseEntity<BusinessOrderEntityExt>();
		try {
			BusinessOrderEntityExt businessOrderEntityExt = businessOrderService.getOrderInfo(businessOrderId);
			responseEntity.setEntity(businessOrderEntityExt);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}

		return responseEntity;
	}
}