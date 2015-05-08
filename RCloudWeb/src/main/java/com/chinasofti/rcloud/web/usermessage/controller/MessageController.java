package com.chinasofti.rcloud.web.usermessage.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.JsonResult;
import com.chinasofti.rcloud.common.PaginationDetail;
import com.chinasofti.rcloud.portal.message.domain.MessageDomain;
import com.chinasofti.rcloud.portal.message.service.MessageService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.JsonUtil;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;
/**
* @ClassName: MessageController
* @Description: 消息中心
* @author shimeihua
* @date 2014年10月21日 下午4:57:23
*
 */
@Controller
public class MessageController extends BasicController{
	private static Logger logger = Logger.getLogger(MessageController.class);
	/**
	* @Title: getApplicationBillList 
	* @Description: 展示我的消息
	* @author shimeihua
	* @param @param request
	* @param @return
	* @param @throws ParseException
	* @return ResponseEntity<PaginationDetail<MessageDomain>>  
	* @throws
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@ResponseBody
	@RequestMapping(value="/rest/usermessage/mymessage/{currentPage}", method=RequestMethod.GET)
	public ResponseEntity<PaginationDetail<MessageDomain>> getApplicationBillList(@PathVariable("currentPage") Integer currentPage,HttpServletRequest request) throws ParseException {
		
		ResponseEntity<PaginationDetail<MessageDomain>> responseEntity = new ResponseEntity<PaginationDetail<MessageDomain>>();
		String userId = LoginUtil.getUserId();
		try {
			PaginationDetail<MessageDomain> pagination =new PaginationDetail<MessageDomain>() ;
			pagination.setCurrentPage(currentPage);
			pagination.setPageSize(10);
			String readFlag=request.getParameter("readFlag");
			if("allmessage".equals(readFlag)){
				messageService.getMessageByPage(pagination, userId, true);
			}else{
				messageService.getMessageByPage(pagination, userId, false);
			}
			
			responseEntity.setEntity(pagination);
		}  catch (Exception e) {
			logger.error("getApplicationBillList", e);
			this.handleServerExceptionByJson(e, responseEntity);
		}		
		return responseEntity;		
	}
	/**
	* @Title: messagedelete 
	* @Description: 删除我的消息
	* @author shimeihua
	* @param @param messagekeys
	* @param @return
	* @return String  
	* @throws
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping(value="/rest/usermessage/messagedelete/{messagekeys}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageDomain> messagedelete(@PathVariable("messagekeys") String messagekeys) {
		ResponseEntity<MessageDomain> responseEntity = new ResponseEntity<MessageDomain>();
		try {
			String userId = LoginUtil.getUserId();
			String ids[]=messagekeys.split(",");
			List<String> messageKeyList=new ArrayList<String>();
			for(int i=0;i<ids.length;i++){
				messageKeyList.add(ids[i]);
				
			}
			messageService.deleteMessageList(messageKeyList, userId);
		}  catch (Exception e) {
			logger.error("messagedelete", e);
			handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	/**
	* @Title: messagemarkread 
	* @Description: 标记为已读
	* @author shimeihua
	* @param @param messagekeys
	* @param @return
	* @return String  
	* @throws
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping(value="/rest/usermessage/messagemarkread/{messagekeys}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageDomain> messagemarkread(@PathVariable("messagekeys") String messagekeys) {
		ResponseEntity<MessageDomain> responseEntity = new ResponseEntity<MessageDomain>();
		String userId = LoginUtil.getUserId();
		try {
			String ids[]=messagekeys.split(",");
			List<String> messageKeyList=new ArrayList<String>();
			for(int i=0;i<ids.length;i++){
				messageKeyList.add(ids[i]);
				
			}
			messageService.markRead(messageKeyList,userId);
		}  catch (Exception e) {
			logger.error("messagemarkread", e);
			handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	/**
	* @Title: messageread 
	* @Description: 查看我的消息
	* @author shimeihua
	* @param @param messagekey
	* @param @return
	* @return ResponseEntity<MessageDomain>  
	* @throws
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping(value="/rest/usermessage/messageread/{messagekey}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageDomain> messageread(@PathVariable("messagekey") String messagekey) {
		ResponseEntity<MessageDomain> responseEntity = new ResponseEntity<MessageDomain>();
		try {
			String userId = LoginUtil.getUserId();
			MessageDomain messageDomain=messageService.readMessage(messagekey,userId);
			responseEntity.setEntity(messageDomain);
		}   catch (Exception e) {
			logger.error("messageread", e);
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	/**
	* @Title: insertmymessage 
	* @Description: 对外提供API 保存消息
	* @author shimeihua
	* @param @param jsonObj
	* @param @param response
	* @return void  
	* @throws
	 */
	@RequestMapping(value ="/api/usermessage/insertmymessage",method = RequestMethod.POST)
	@ResponseBody 
	public void insertmymessage(@RequestBody String jsonObj,HttpServletResponse response){
			JsonResult jsonResult=new JsonResult();
		logger.info(jsonObj);
		try {
			if(jsonObj==null){
				jsonResult.setStatus(CommonConstant.STATUS_BUSINESS_EXCEPTION);
				jsonResult.setErrorMessage("传值异常");
				jsonResult.setErrorCode("999999");
				JsonUtil.print(JsonUtil.getJsonFromObject(jsonResult), response);
				return;
			}
			
			jsonResult=messageService.insertMessageApi(jsonObj);
		}  catch (Exception e) {
			logger.error("insertmymessage", e.fillInStackTrace());
		}
		JsonUtil.print(JsonUtil.getJsonFromObject(jsonResult), response);
	}
	/**
	* @Title: getunreadmessagecount 
	* @Description: 获取未读消息
	* @author shimeihua
	* @param @return
	* @return ResponseEntity<String>  
	* @throws
	 */
	@RequestMappingName(value =CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping(value="/rest/usermessage/getunreadmessagecount", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getunreadmessagecount() {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		String userId = LoginUtil.getUserId();
		try {
			String unreadCount="0";
			String unreount=messageService.getUnreadMessageCount(userId);
			if(unreount!=null){
				unreadCount=unreount;
			}
			responseEntity.setEntity(unreadCount);
		}  catch (Exception e) {
			logger.error("getunreadmessagecount", e);
			handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	@Autowired
	MessageService messageService;

}
