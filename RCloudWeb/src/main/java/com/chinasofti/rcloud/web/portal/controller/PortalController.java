package com.chinasofti.rcloud.web.portal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.SystemParametersCacheService;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.ServiceEntity;
import com.chinasofti.rcloud.portal.app.service.AppService;
import com.chinasofti.rcloud.portal.cloudservice.service.CloudService;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.portal.message.service.MessageService;
import com.chinasofti.rcloud.portal.userconsole.service.UserInfoService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.CommonUtil;
import com.chinasofti.rcloud.web.common.FileUtil;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;
import com.chinasofti.rcloud.web.portal.controller.info.PortalInfo;
import com.chinasofti.rcloud.web.portal.controller.info.Service;
import com.chinasofti.rcloud.web.portal.controller.info.Slide;
import com.chinasofti.rcloud.web.portal.controller.info.Store;

@Controller
public class PortalController extends BasicController {
	private Logger logger = Logger.getLogger(PortalController.class);
	
	@Autowired
	private CloudService cloudService;
	@Autowired
	private AppService appService;
	
	@Autowired
	SystemParametersCacheService systemParameters;
	
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 首页载入时,查询云服务、应用、首页轮播图等信息返回
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping(value="rest/portal/index")
	public ResponseEntity<PortalInfo> index(){
		ResponseEntity<PortalInfo> responseEntity = new ResponseEntity<PortalInfo>();
		List<Service> service = new ArrayList<Service>();
		List<Store> store = new ArrayList<Store>();
		List<Slide> slide = new ArrayList<Slide>();
		PortalInfo protalInfo = new PortalInfo();
		
		try {
			List<ServiceEntity> serList = cloudService.findForPortal();
			if(serList != null && serList.size() > 0){
				Service serInfo ;
				for(ServiceEntity s : serList){
					serInfo = new Service();
					serInfo.setHref(s.getShowDetailUrl());
					serInfo.setName(s.getChname());
					serInfo.setPrice1(s.getShowPriceHour() == null ? 0:s.getShowPriceHour().doubleValue());
					serInfo.setPrice2(s.getShowPriceYear() == null ? 0:s.getShowPriceYear().doubleValue());
					serInfo.setUrl(s.getLogoPath());
					
					service.add(serInfo);
				}
			}
			
			List<ApplicationEntity> appList = appService.findForPortal();
			if(appList != null && appList.size() > 0){
				Store storeInfo ;
				int index = 0;
				for(ApplicationEntity app : appList){
					if(index < 4){
						storeInfo = new Store();
						storeInfo.setHref(app.getShowDetailUrl());
						storeInfo.setName(app.getChname());
						storeInfo.setOimg(app.getLogoPath());
						storeInfo.setNimg(app.getLogoPathHover());
						
						store.add(storeInfo);
					}
					index++;
				}
			}
			
			//首页轮播图查询(数据库暂未设计,暂时写死)
			Slide slideInfo;
			for(int i = 0;i < 5;i++){
				slideInfo = new Slide();
				slideInfo.setHref("#");
				slideInfo.setUrl("static/extends/images/upload/idx_banner.jpg");
				slide.add(slideInfo);
			}
			
			protalInfo.setSlide(slide);
			protalInfo.setService(service);
			protalInfo.setStore(store);			
			responseEntity.setEntity(protalInfo);
		}catch (RCloudException e) {
			logger.error("首页信息加载出错",e);
            this.handleBusinessExceptionByJson(e, responseEntity);
		}catch(Exception e){
			logger.error("首页信息加载出错",e);
            this.handleServerExceptionByJson(e, responseEntity);
		}
		
		return responseEntity;
	}
	

//	@RequestMapping("center/service")
//	public String index1(){
//		return "serviceManager/cloud_service";
//	}
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_APP_QUERY)
	@RequestMapping("page/center/store")
	public String index2(){
		return "storeManager/wind_shop";
	}
	
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping("page/center/servsupport")
	public String index6(Model model){
		String otrs_address = FileUtil.getValue("otrs_address");
		model.addAttribute("otrs_address",otrs_address);
		return "job/support";
	}
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping("page/center/otrs")
	public String index7(){
		
		return "job/otrs";
	}
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping("page/survey")
	public String toSurvey(){		
		return "survey/survey";
	}
	
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping("page/center")
	public String index3(Model model, HttpServletRequest request){
		String result = "individual_center";
		try{
			String userId=LoginUtil.getUserId();
			HashMap<String, String> userMap=cloudService.getUserInfo(userId);
			model.addAttribute("user", userMap);
			
			Map<String, Integer> permissionMap = new HashMap<String, Integer>();
			HttpSession session = request.getSession();
			// 判断session是否已过期
			UserEntityExt user = (UserEntityExt)session.getAttribute(CommonConstant.USER_INFO);
			String roleCode = user.getRoleCode();
			Map roleMap = systemParameters.getRoleToPermissionMap();
			Map perMap = (Map)roleMap.get(roleCode);
			if (perMap.containsKey(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)) {
				permissionMap.put(CommonConstant.ROLE_PERMISSION_ORG_ADMIN, 1);
			} else {
				permissionMap.put(CommonConstant.ROLE_PERMISSION_ORG_ADMIN, 0);
			}
			
			if (perMap.containsKey(CommonConstant.ROLE_PERMISSION_ORDER_ADMIN)) {
				permissionMap.put(CommonConstant.ROLE_PERMISSION_ORDER_ADMIN, 1);
			} else {
				permissionMap.put(CommonConstant.ROLE_PERMISSION_ORDER_ADMIN, 0);
			}
			
			if (perMap.containsKey(CommonConstant.ROLE_PERMISSION_BILL_ADMIN)) {
				permissionMap.put(CommonConstant.ROLE_PERMISSION_BILL_ADMIN, 1);
			} else {
				permissionMap.put(CommonConstant.ROLE_PERMISSION_BILL_ADMIN, 0);
			}
			
			model.addAttribute("permissionMap", permissionMap);
			String unreadMesCount=messageService.getUnreadMessageCount(userId);//未读消息
			if(unreadMesCount==null){
				unreadMesCount="0";
			}
			model.addAttribute("unreadMesCount", unreadMesCount);
			
		}catch(Exception e){
			result = this.handleServerExceptionByPage (e);
		}
		return result;
	}
	
	@RequestMapping("center/test")
	public String test() {
		return "storeManager/wind_shop_data";
	}
	
	@RequestMapping(value = "/portal/recharge", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> recharge(HttpServletRequest request) {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		try {
			Map<String, Object> map = CommonUtil.getParameterMap(request);
			String userName = (String) map.get("userName");
			responseEntity.setEntity(userName);
			double rechargeMoney = Double.parseDouble((String)map.get("money"));
			System.out.println(map.get("userName"));
			
			userInfoService.updateAccount(userName, rechargeMoney);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	@Autowired
	MessageService messageService;
}
