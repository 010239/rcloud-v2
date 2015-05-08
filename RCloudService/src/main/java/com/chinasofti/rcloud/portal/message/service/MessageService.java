package com.chinasofti.rcloud.portal.message.service;

import java.util.List;

import com.chinasofti.rcloud.common.JsonResult;
import com.chinasofti.rcloud.common.PaginationDetail;
import com.chinasofti.rcloud.portal.message.domain.MessageDomain;

public interface MessageService {
	/**
	 * 保存Message。
	 * 
	 * @param message
	 */
	void saveMessage(MessageDomain message) throws Exception;

	/**
	 * 读Message
	 * 
	 * @param messageKey
	 */
	MessageDomain readMessage(String messageKey, String userId)
			throws Exception;

	/**
	 * 标记为已读。
	 * 
	 * @param messageKeyList
	 */
	void markRead(List<String> messageKeyList, String userId) throws Exception;

	/**
	 * 分页获取Message, 当前页和每页多少条
	 * 
	 * @param paginationDetail
	 * @return
	 */
	void getMessageByPage(PaginationDetail<MessageDomain> paginationDetail,
			String userId, boolean isAll) throws Exception;

	/**
	 * 根据Message的Key，删除Message
	 * 
	 * @param messageKeyList
	 */
	void deleteMessageList(List<String> messageKeyList, String userId)
			throws Exception;

	/**
	 * @Title: insertMessageApi
	 * @Description: 对外开放 保存消息
	 * @author shimeihua
	 * @param @param jsonObj
	 * @param @return
	 * @param @throws Exception
	 * @return JsonResult
	 * @throws
	 */
	public JsonResult insertMessageApi(String jsonObj) throws Exception;

	/**
	 * @Title: getUnreadMessageCount
	 * @Description: 未读消息
	 * @author shimeihua
	 * @param @param userId
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public String getUnreadMessageCount(String userId) throws Exception;

}
