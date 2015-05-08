package com.chinasofti.rcloud.portal.message.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chinasofti.rcloud.common.CommonConstants;
import com.chinasofti.rcloud.common.JsonResult;
import com.chinasofti.rcloud.common.PaginationDetail;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.constants.ExceptionContants;
import com.chinasofti.rcloud.portal.message.dao.MessageDao;
import com.chinasofti.rcloud.portal.message.dao.StringBaseDao;
import com.chinasofti.rcloud.portal.message.domain.MessageDomain;
import com.chinasofti.rcloud.portal.message.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	private static Logger logger = Logger.getLogger(MessageServiceImpl.class);
	@Autowired
	MessageDao messageDao;

	@Autowired
	StringBaseDao stringBaseDao;

	@Override
	public void saveMessage(MessageDomain message) throws Exception {
		if (message == null) {
			throw new RCloudException(ExceptionContants.MESSAGE_NOT_EXIST);
		}

		messageDao.saveMessage(message);
	}

	@Override
	public MessageDomain readMessage(String messageKey, String userId)
			throws Exception {
		if (StringUtils.isEmpty(messageKey)) {
			return null;
		}

		MessageDomain message = messageDao.getByKey(messageKey);
		if (message == null) {
			throw new RCloudException(ExceptionContants.MESSAGE_NOT_EXIST);
		}

		if (!message.isStatus()) {
			message.setStatus(CommonConstants.MESSAGE_STATUS_READ);
			messageDao.readMessage(message, userId);

		}

		return message;
	}

	@Override
	public void deleteMessageList(List<String> messageKeyList, String userId)
			throws Exception {
		List<MessageDomain> list = new ArrayList<MessageDomain>();
		MessageDomain message = null;
		for (String str : messageKeyList) {
			message = messageDao.getByKey(str);
			if (message != null) {
				list.add(message);
			}
		}
		messageDao.deleteMessageList(list, userId, messageKeyList);

	}

	@Override
	public void getMessageByPage(
			PaginationDetail<MessageDomain> paginationDetail, String userId,
			boolean isAll) throws Exception {
		if (paginationDetail.getCurrentPage() <= 0
				|| paginationDetail.getPageSize() <= 0) {
			throw new RCloudException(ExceptionContants.MESSAGE_NOT_EXIST);
		}

		messageDao.getMessageDomainList(userId, paginationDetail, true);

	}

	@Override
	public void markRead(List<String> messageKeyList, String userId)
			throws Exception {
		List<MessageDomain> list = new ArrayList<MessageDomain>();
		MessageDomain message = null;
		for (String str : messageKeyList) {
			message = messageDao.getByKey(str);
			if (message != null) {
				list.add(message);
			}
		}
		messageDao.markRead(list, userId);
	}

	public JsonResult insertMessageApi(String jsonObj) throws Exception {
		JsonResult jsonResult = new JsonResult();
		try {
			JSONObject jsonObject = JSONObject.fromObject(jsonObj);
			MessageDomain bean = (MessageDomain) JSONObject.toBean(jsonObject,
					MessageDomain.class);
			this.saveMessage(bean);
			jsonResult.setStatus("200");
		} catch (Exception e) {
			logger.error("raeMeasurementData", e);
			jsonResult.setStatus(CommonConstants.STATUS_BUSINESS_EXCEPTION);
			jsonResult.setErrorMessage("未知异常");
			jsonResult.setErrorCode("999999");
			throw new RCloudException(e);
		}
		return jsonResult;
	}

	/**
	 * @Title: getUnreadMessageCount
	 * @Description: 获取未读消息
	 * @author shimeihua
	 * @param @param userId
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public String getUnreadMessageCount(String userId) throws Exception {
		// String key=messageDao.getUnreadMessageCountKey(userId);
		String unreadlistKey = messageDao.getUnreadListKey(userId);
		String count = stringBaseDao.length(unreadlistKey).toString();
		return count;

	}

}
