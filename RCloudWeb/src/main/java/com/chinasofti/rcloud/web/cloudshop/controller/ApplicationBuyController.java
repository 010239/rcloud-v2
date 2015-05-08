package com.chinasofti.rcloud.web.cloudshop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.cloudshop.service.ApplicationService;
import com.chinasofti.rcloud.portal.cloudshop.vo.ApplicationMonthVo;
import com.chinasofti.rcloud.portal.userorder.domain.ApplicationOrderEntityExt;
import com.chinasofti.rcloud.portal.userorder.service.ApplicationOrderService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.CommonUtil;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;

/**
 * @author zhangjiaxing
 *
 * 2014年7月15日
 */
@Controller
public class ApplicationBuyController extends BasicController {
	
	
	@Autowired
	private ApplicationOrderService applicationOrderService;
	
	@Autowired
	private ApplicationService applicationService;
	
	/**
	 * 查询用户云应用订单列表
	 * @param request
	 * @return
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_QUERY)
	@ResponseBody
	@RequestMapping("rest/cloudshop/applicationBuyOrder")
	public ResponseEntity<Pagination<ApplicationOrderEntityExt>> getApplicationOrderList(
			HttpServletRequest request) {
		ResponseEntity<Pagination<ApplicationOrderEntityExt>> responseEntity = new 
				ResponseEntity<Pagination<ApplicationOrderEntityExt>>();
		String userId = LoginUtil.getUserId();
		
		PageParameter page = new PageParameter();
		
		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
		if (!StringUtils.isEmpty(searchParams.get("page")) && 
				!StringUtils.isEmpty(searchParams.get("rows"))) {
			page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
			page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
		}
		searchParams.put("page", page);
		searchParams.put("userId", userId);
		try {
			Pagination<ApplicationOrderEntityExt> pagination = applicationOrderService.
						getBuyApplicationOrderByPage(searchParams);
			responseEntity.setEntity(pagination);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}	
		return responseEntity;		
	}
	
	
	/**
	 * 根据订单号和订单类型查询订单详情
	 * @param orderType
	 * @param orderNumber
	 * @param request
	 * @return
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_QUERY)
	@ResponseBody
	@RequestMapping("rest/cloudshop/applicationBuyOrderInfo/{orderNumber}")
	public ResponseEntity<ApplicationOrderEntityExt> getApplicationOrderInfo(@PathVariable("orderNumber") 
						String orderNumber, HttpServletRequest request) {
		ResponseEntity<ApplicationOrderEntityExt> responseEntity = new ResponseEntity<ApplicationOrderEntityExt>();
		String userId = LoginUtil.getUserId();
		try{
			ApplicationOrderEntityExt applicationOrderEntityExt = applicationOrderService.
						getBuyApplicationOrderInfo(userId, orderNumber);
			responseEntity.setEntity(applicationOrderEntityExt);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
		
	}
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_ADMIN)
	@RequestMapping("page/pruchase/{appId}")
	public String goBuyPage(@PathVariable("appId")String appId, Model model) {
		try {
			ApplicationEntity applicationEntity = applicationService.selectAppById(appId);
			model.addAttribute("application", applicationEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "storeManager/purchase";
	} 
	//2014-11-20 废弃
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_ADMIN)
	@RequestMapping(value = "page/application/buy", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> goPayPage(HttpServletRequest request) {
		Map<String, Object> requestMap = CommonUtil.getParameterMap(request);
		
		ResponseEntity<Integer> responseEntity = new ResponseEntity<Integer>();
		
		try {
			int payPartten = applicationOrderService.insertApplicationOrderEntity(requestMap, LoginUtil.getUserId());
			responseEntity.setEntity(payPartten);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		
		return responseEntity;
	}
	//smh 试用应用
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_ADMIN)
	@RequestMapping(value = "page/application/buyapp", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ApplicationMonthVo> goPayPageapp(HttpServletRequest request) {
		Map<String, Object> requestMap = CommonUtil.getParameterMap(request);
		
		ResponseEntity<ApplicationMonthVo> responseEntity = new ResponseEntity<ApplicationMonthVo>();
		
		try {
			//int payPartten = applicationOrderService.insertApplicationOrderEntity(requestMap, LoginUtil.getUserId());
			UserEntity user=LoginUtil.getUser();
			ApplicationMonthVo vo = applicationOrderService.insertApplicationOrderEntityapp(requestMap, user);
			if(vo.isSaveSession()){
				HttpSession session=request.getSession();
				session.setAttribute("preorder", vo.getSaveSessAppOrder());
			}
			responseEntity.setEntity(vo);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		
		return responseEntity;
	}

}
