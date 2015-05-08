package com.chinasofti.rcloud.web.userbill.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.PageParameter;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.ApplicationBillEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.log.service.LogService;
import com.chinasofti.rcloud.portal.userbill.domain.ApplicationBillEntityExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossBillInfoEntityExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossDebtsEntityExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossServiceBillEntityExt;
import com.chinasofti.rcloud.portal.userbill.service.ApplicationBillService;
import com.chinasofti.rcloud.portal.userbill.service.BossServiceBillService;
import com.chinasofti.rcloud.portal.userbill.service.DebtsRecordService;
import com.chinasofti.rcloud.portal.userbill.vo.DebtsRecordVo;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.CommonUtil;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;


/**
 * @author zhangjiaxing
 *
 * 2014年7月10日
 */

@Controller
public class BillController extends BasicController{
	private static Logger logger = Logger.getLogger(BillController.class);
	@Autowired
	private ApplicationBillService applicationBillService;
	
	@Autowired
	private DebtsRecordService debtsRecordService;
	
	@Autowired
	private BossServiceBillService bossServiceBillService;

	
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_BILL_ADMIN)
	@RequestMapping("page/userBill")
	public String getPage() {
		
		return "userConsole/userBill";
	}
	/**
	 * 根据条件查询云应用账单
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@ResponseBody
	@RequestMapping(value="rest/userbill/applicationBill", method=RequestMethod.GET)
	public ResponseEntity<Pagination<ApplicationBillEntity>> getApplicationBillList(HttpServletRequest request) throws ParseException {
		
		ResponseEntity<Pagination<ApplicationBillEntity>> responseEntity = new ResponseEntity<Pagination<ApplicationBillEntity>>();
		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
		String userId = LoginUtil.getUserId();
		String bType = request.getParameter("billType");
		if ( bType!=null && !bType.equals("")) {	
			int billType = Integer.parseInt(bType);
			searchParams.put("billType", billType);
		}
		PageParameter page = new PageParameter();
		if (!StringUtils.isEmpty(searchParams.get("page")) && 
				!StringUtils.isEmpty(searchParams.get("rows"))) {
			page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
			page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
		}
		String startT =request.getParameter("startTime");
		String endT= request.getParameter("endTime");
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtils.isEmpty(startT) ){
			searchParams.put("billStartTime", sdt.parse(startT));
		}
		if( !StringUtils.isEmpty(endT)){
			searchParams.put("billEndTime", sdt.parse(endT));
		}
		searchParams.put("page", page);
		searchParams.put("userId", userId);
		try {
			Pagination<ApplicationBillEntity> pagination = applicationBillService.getApplicationBillListByPage(searchParams);
			responseEntity.setEntity(pagination);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		return responseEntity;		
	}
	
	
	/**
	 * 查询应用账单详情
	 * @param billId
	 * @return
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_BILL_ADMIN)
	@ResponseBody
	@RequestMapping("rest/userbill/applicationBillInfo/{billId}")
	public ResponseEntity<ApplicationBillEntityExt> getApplicationBillInfo(
			@PathVariable("billId") String billId) {
		ResponseEntity<ApplicationBillEntityExt> responseEntity = new ResponseEntity<ApplicationBillEntityExt>();
		try {
			ApplicationBillEntityExt applicationBillEntityExt = applicationBillService.getApplicationBillInfo(
					billId,applicationBillService.getOrderIdList(billId) );
			responseEntity.setEntity(applicationBillEntityExt);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}

		return responseEntity;
	}
	
	
//	/**
//	 * 根据条件查询云服务账单
//	 * @param billType
//	 * @param startTime
//	 * @param endTime
//	 * @param request
//	 * @return
//	 * @throws ParseException 
//	 */
//	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_USER_ADMIN)
//	@ResponseBody
//	@RequestMapping(value="rest/userbill/serviceBill", method=RequestMethod.GET)
//	public ResponseEntity<Pagination<ServiceBillEntity>> getServiceBillList(HttpServletRequest request) throws ParseException {
//		
//		ResponseEntity<Pagination<ServiceBillEntity>> responseEntity = new ResponseEntity<Pagination<ServiceBillEntity>>();
//		String userId = LoginUtil.getUserId();
//		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
//		PageParameter page = new PageParameter();
//		if (!StringUtils.isEmpty(searchParams.get("page")) && 
//				!StringUtils.isEmpty(searchParams.get("rows"))) {
//			page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
//			page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
//		}
//		String bType = request.getParameter("billType");
//		if ( bType!=null && !bType.equals("")) {	
//			int billType = Integer.parseInt(bType);
//			searchParams.put("billType", billType);
//		}
//		String startT =request.getParameter("startTime");
//		String endT= request.getParameter("endTime");
//		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
//		if(!StringUtils.isEmpty(startT) && !StringUtils.isEmpty(endT)){
//			searchParams.put("billStartTime", sdt.parse(startT));
//			searchParams.put("billEndTime", sdt.parse(endT));
//		}
//		searchParams.put("page", page);
//		searchParams.put("userId", userId);
//		try {
//			Pagination<ServiceBillEntity> pagination = serviceBillService.getServiceBillListByPage(searchParams);
//			responseEntity.setEntity(pagination);
//		} catch (RCloudException re) {
//			this.handleBusinessExceptionByJson(re, responseEntity);
//		} catch (Exception e) {
//			this.handleServerExceptionByJson(e, responseEntity);
//		}		
//		return responseEntity;		
//	}
//	
//	/**
//	 * 服务账单详情查询
//	 * @param billId
//	 * @return
//	 */
//	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_BILL_ADMIN)
//	@ResponseBody
//	@RequestMapping("rest/userbill/serviceBillInfo/{billId}")
//	public ResponseEntity<ServiceBillEntityExt> getserviceBillInfo(
//			@PathVariable("billId") String billId) {
//		ResponseEntity<ServiceBillEntityExt> responseEntity = new ResponseEntity<ServiceBillEntityExt>();
//		try {
//			ServiceBillEntityExt serviceBillEntityExt = serviceBillService.getServiceBillInfo(
//					billId, serviceBillService.getOrderIdList(billId));
//			responseEntity.setEntity(serviceBillEntityExt);
//		} catch (RCloudException re) {
//			this.handleBusinessExceptionByJson(re, responseEntity);
//		} catch (Exception e) {
//			this.handleServerExceptionByJson(e, responseEntity);
//		}
//
//		return responseEntity;
//	}
//	
	
	/**
	 * 查询应用欠费账单
	 * @param request
	 * @return
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_BILL_ADMIN)
	@ResponseBody
	@RequestMapping(value="rest/userbill/appdebtsRecord", method=RequestMethod.GET)
	public ResponseEntity<Pagination<ApplicationBillEntityExt>> getAppDebtsRecordList(HttpServletRequest request) {
		
		ResponseEntity<Pagination<ApplicationBillEntityExt>> responseEntity = new ResponseEntity<Pagination<ApplicationBillEntityExt>>();
		String userId = LoginUtil.getUserId();
		try {
			Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
			PageParameter page = new PageParameter();
			if (!StringUtils.isEmpty(searchParams.get("page")) && 
					!StringUtils.isEmpty(searchParams.get("rows"))) {
				page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
				page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
			}
			searchParams.put("page", page);
			searchParams.put("userId", userId);
			Pagination<ApplicationBillEntityExt> pagination = applicationBillService.getAppDebtsListByPage(searchParams);
			responseEntity.setEntity(pagination);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		return responseEntity;		
	}
	
	/**
	 * 查询服务欠费账单
	 * @param request
	 * @return
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_BILL_ADMIN)
	@ResponseBody
	@RequestMapping(value="rest/userbill/serdebtsRecord", method=RequestMethod.GET)
	public ResponseEntity<Pagination<BossDebtsEntityExt>> getSerDebtsRecordList(HttpServletRequest request) {
		
		ResponseEntity<Pagination<BossDebtsEntityExt>> responseEntity = new ResponseEntity<Pagination<BossDebtsEntityExt>>();
		String userId = LoginUtil.getUserId();
		try {
			Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
			PageParameter page = new PageParameter();
			if (!StringUtils.isEmpty(searchParams.get("page")) && 
					!StringUtils.isEmpty(searchParams.get("rows"))) {
				page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
				page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
			}
			searchParams.put("page", page);
			searchParams.put("userId", userId);
			Pagination<BossDebtsEntityExt> pagination = debtsRecordService.getDebtsByPage(searchParams);
			responseEntity.setEntity(pagination);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		return responseEntity;		
	}
	
	/**
	 * 查询欠费账单详情
	 * @param billId
	 * @return
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_BILL_ADMIN)
	@ResponseBody
	@RequestMapping("rest/userbill/debtsRecordInfo/{serviceBillId}")
	public ResponseEntity<BossDebtsEntityExt> getDebtsRecordInfo(
			@PathVariable("serviceBillId") String serviceBillId) {
		ResponseEntity<BossDebtsEntityExt> responseEntity = new ResponseEntity<BossDebtsEntityExt>();
		try {
		
				BossDebtsEntityExt debtsRecordEntityExt = debtsRecordService.getSerDebtsInfo(serviceBillId);
				responseEntity.setEntity(debtsRecordEntityExt);
			
//			if ( "2".equals(ty)) {
//				DebtsRecordEntityExt debtsRecordEntityExt = debtsRecordService.getAppDebtsRecordInfo(billId);
//				responseEntity.setEntity(debtsRecordEntityExt);
//			}
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}

		return responseEntity;
	}
	
	
	/**
	 * 根据条件查询云服务账单 (新)
	 * @param billType
	 * @param startTime
	 * @param endTime
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@ResponseBody
	@RequestMapping(value="rest/userbill/serviceBill", method=RequestMethod.GET)
	public ResponseEntity<Pagination<BossServiceBillEntityExt>> getServiceBillList(HttpServletRequest request) throws ParseException {
		
		ResponseEntity<Pagination<BossServiceBillEntityExt>> responseEntity = new ResponseEntity<Pagination<BossServiceBillEntityExt>>();
		String userId = LoginUtil.getUserId();
		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
		PageParameter page = new PageParameter();
		if (!StringUtils.isEmpty(searchParams.get("page")) && 
				!StringUtils.isEmpty(searchParams.get("rows"))) {
			page.setCurrentPage(Integer.parseInt((String) searchParams.get("page")));
			page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
		}
		String startT =request.getParameter("startTime");
		String endT= request.getParameter("endTime");
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtils.isEmpty(startT) ){
			searchParams.put("billStartTime", sdt.parse(startT));
		}
		if( !StringUtils.isEmpty(endT)){
			searchParams.put("billEndTime", sdt.parse(endT));
		}
		searchParams.put("page", page);
		searchParams.put("userId", userId);
		try {
			Pagination<BossServiceBillEntityExt> pagination = bossServiceBillService.searchBillByPage(searchParams);
			responseEntity.setEntity(pagination);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		return responseEntity;		
	}
	
	/**
	 * 服务账单详情查询(新)
	 * @param billId
	 * @return
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_BILL_ADMIN)
	@ResponseBody
	@RequestMapping("rest/userbill/serviceBillInfo/{chargeMonth}")
	public ResponseEntity<List<BossBillInfoEntityExt>> getserviceBillInfo(
			@PathVariable("chargeMonth") String chargeMonth,HttpServletRequest request) {
		ResponseEntity<List<BossBillInfoEntityExt>> responseEntity = new ResponseEntity<List<BossBillInfoEntityExt>>();
		try {
			String userId = LoginUtil.getUserId();
			Map<String, Object> searchParams = CommonUtil.getParameterMap(request);
			searchParams.put("userId", userId);
			searchParams.put("chargeMonth", chargeMonth);
			List<BossBillInfoEntityExt> billInfo = bossServiceBillService.getBillInfo(searchParams);
			responseEntity.setEntity(billInfo);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}

		return responseEntity;
	}
	//-----------------------------------续费-------------------
	/**
	* @Title: billrenewpage 
	* @Description: to续费页面
	* @param @param serviceBillId
	* @param @return
	* @return ResponseEntity<BossDebtsEntityExt>  
	* @throws
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_BILL_ADMIN)
	@RequestMapping("/rest/userbill/billrenewpage/{serviceBillId}")
	@ResponseBody
	public ResponseEntity<BossDebtsEntityExt> billrenewpage(@PathVariable("serviceBillId") String serviceBillId) {
		ResponseEntity<BossDebtsEntityExt> responseEntity = new ResponseEntity<BossDebtsEntityExt>();
		try {
			BossDebtsEntityExt debtsRecordEntityExt = bossServiceBillService.getDebtsInfoByServiceBillId(serviceBillId);
			responseEntity.setEntity(debtsRecordEntityExt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("billrenewpage", e);
		}
		
		return responseEntity;
	}
	/**
	* @Title: rechargeMoney 
	* @Description:续费
	* @author shimeihua
	* @param @param debtsRecordVo
	* @param @return
	* @return ResponseEntity<DebtsRecordVo>  
	* @throws
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_BILL_ADMIN)
	@RequestMapping(value = "/rest/userbill/renewMoney", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<DebtsRecordVo> rechargeMoney(@ModelAttribute DebtsRecordVo debtsRecordVo) {
		ResponseEntity<DebtsRecordVo> responseEntity = new ResponseEntity<DebtsRecordVo>();
		try {
			bossServiceBillService.updateArrearsBillingRenewals(debtsRecordVo);
			UserEntity user=(UserEntity)LoginUtil.getUser();//同一个地址
			String description="为账单("+debtsRecordVo.getServiceBillId()+")续费"+debtsRecordVo.getDebtsCosts()+"元";//为账单(service_bill_id)续费XX元
			logService.insertLog(user, "账单续费", description);
		}  catch (RCloudException re) {
			logger.error("renewMoney", re);
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			logger.error("renewMoney", e);
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	@Autowired
	private LogService logService;  
	
}
