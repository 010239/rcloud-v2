package com.chinasofti.rcloud.portal.message.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.common.PaginationDetail;
import com.chinasofti.rcloud.common.callback.DefaultSessionCallBack;
import com.chinasofti.rcloud.common.client.CFClientResource;
import com.chinasofti.rcloud.common.dao.RedisBaseDao;
import com.chinasofti.rcloud.portal.message.domain.MessageDomain;

@Component
public class MessageDao extends RedisBaseDao<MessageDomain> {
	private static final String DEPLOY_ENV = CFClientResource.DEPLOY_ENV;
	private static final String ENV_PLACE_HOLDER = "DeployEnv";

	private static final String MESSAGE_KEY_PARTTEN = "RCloud:DeployEnv:Message:Private:MessageGuid";
	private static final String MESSAGE_PLACE_HOLDER = "MessageGuid";

	private static final String MESSAGE_LIST_KEY_PARTTEN = "RCloud:DeployEnv:Message:Private:UserGuid:List";
	private static final String USERGUID_PLACE_HOLDER = "UserGuid";

	private static final String MESSAGE_UNREAD_LIST_KEY_PARTTEN = "RCloud:DeployEnv:Message:Private:UserGuid:UnreadList";

	// private static final String MESSAGE_UNREAD_COUNT_KEY_PARTTEN =
	// "RCloud:DeployEnv:Message:Private:UserGuid:UnreadMessageCount";

	public String getMessageKey() {
		String guid = CommonUtil.getId();
		return MESSAGE_KEY_PARTTEN.replaceAll(ENV_PLACE_HOLDER, DEPLOY_ENV)
				.replaceAll(MESSAGE_PLACE_HOLDER, guid);
	}

	public String getListKey(String userId) {
		return MESSAGE_LIST_KEY_PARTTEN
				.replaceAll(ENV_PLACE_HOLDER, DEPLOY_ENV).replaceAll(
						USERGUID_PLACE_HOLDER, userId);
	}

	/**
	 * @Title: getUnreadListKey
	 * @Description: 未读消息的key
	 * @author shimeihua
	 * @param @param userId
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getUnreadListKey(String userId) {
		return MESSAGE_UNREAD_LIST_KEY_PARTTEN.replaceAll(ENV_PLACE_HOLDER,
				DEPLOY_ENV).replaceAll(USERGUID_PLACE_HOLDER, userId);
	}

	// smh 2014-11-06 暂时无用
	// public String getUnreadMessageCountKey(String userId) {
	// return MESSAGE_UNREAD_COUNT_KEY_PARTTEN.replaceAll(ENV_PLACE_HOLDER,
	// DEPLOY_ENV)
	// .replaceAll(USERGUID_PLACE_HOLDER, userId);
	// }

	public void saveMessage(final MessageDomain message) {
		// final String countKey =
		// getUnreadMessageCountKey(message.getReceiverId());

		@SuppressWarnings("unchecked")
		SessionCallback<Object> sessionCallback = new DefaultSessionCallBack() {

			@SuppressWarnings("rawtypes")
			@Override
			public void excuteOps(RedisOperations operations) {
				String key = getMessageKey();
				message.setMessageKey(key);
				operations.opsForValue().set(key, message);

				String listKey = getListKey(message.getReceiverId());
				operations.opsForList().leftPush(listKey, key);
				// 添加保存未读消息集合
				if (!message.isStatus()) {// 状态：false-未读，true-已读
					String unreadlistKey = getUnreadListKey(message
							.getReceiverId());
					operations.opsForList().leftPush(unreadlistKey, key);
				}
				// operations.opsForValue().increment(countKey, 1);
			}

		};

		this.redisTemplate.execute(sessionCallback);
	}

	public void readMessage(final MessageDomain message, final String userId) {
		// final String countKey =
		// getUnreadMessageCountKey(message.getReceiverId());

		@SuppressWarnings("unchecked")
		SessionCallback<Object> sessionCallback = new DefaultSessionCallBack() {
			@SuppressWarnings("rawtypes")
			@Override
			public void excuteOps(RedisOperations operations) {
				operations.opsForValue().set(message.getMessageKey(), message);

				// smh 如果此消息是未读的消息，那么移除集合
				// operations.opsForValue().increment(countKey, -1);
				// if (!message.isStatus()) {//状态：false-未读，true-已读
				String unreadlistKey = getUnreadListKey(userId);
				operations.opsForList().remove(unreadlistKey, 1,
						message.getMessageKey());
				// }
			}
		};

		this.redisTemplate.execute(sessionCallback);
	}

	public void deleteMessageList(final List<MessageDomain> messages,
			final String userId, final List<String> messageKeys) {
		// final String countKey = getUnreadMessageCountKey(userId);

		@SuppressWarnings("unchecked")
		SessionCallback<Object> sessionCallback = new DefaultSessionCallBack() {
			@SuppressWarnings({ "rawtypes", "unused" })
			@Override
			public void excuteOps(RedisOperations operations) {
				int count = 0;
				String listKey = getListKey(userId);
				String unreadlistKey = getUnreadListKey(userId);
				for (MessageDomain message : messages) {
					// smh 如果此消息是未读的消息，那么移除集合
					if (!message.isStatus()) {// 状态：false-未读，true-已读
						operations.opsForList().remove(unreadlistKey, 1,
								message.getMessageKey());
					}

					// 在list中删除MessageKey
					operations.opsForList().remove(listKey, 1,
							message.getMessageKey());
				}

				operations.delete(messageKeys);

				// operations.opsForValue().increment(countKey, -count);
			}
		};

		this.redisTemplate.execute(sessionCallback);
	}

	public void markRead(final List<MessageDomain> messageKeys,
			final String userId) {
		// final String countKey = getUnreadMessageCountKey(userId);

		@SuppressWarnings("unchecked")
		SessionCallback<Object> sessionCallback = new DefaultSessionCallBack() {
			@SuppressWarnings("rawtypes")
			@Override
			public void excuteOps(RedisOperations operations) {
				String unreadlistKey = getUnreadListKey(userId);
				for (MessageDomain message : messageKeys) {
					if (!message.isStatus()) {
						// count ++;
						message.setStatus(true);
						operations.opsForValue().set(message.getMessageKey(),
								message);
						// smh 如果是未读消息标记为已读，移除未读消息集合
						operations.opsForList().remove(unreadlistKey, 1,
								message.getMessageKey());
					}
				}

				// operations.opsForValue().increment(countKey, -count);
			}
		};

		this.redisTemplate.execute(sessionCallback);
	}

	// public String getUnreadCount(final String userId) {
	// RedisCallback<String> redisCallback = new RedisCallback<String>() {
	// public String doInRedis(RedisConnection connection)
	// throws DataAccessException {
	// RedisSerializer<String> redisSerializer =
	// redisTemplate.getStringSerializer();
	// String countKey = getUnreadMessageCountKey(userId);
	// byte[] keyAfterSerialize = redisSerializer.serialize(countKey);
	//
	// if (connection.exists(keyAfterSerialize)) {
	// byte[] value = connection.get(keyAfterSerialize);
	// return redisSerializer.deserialize(value);
	// } else {
	// return "0";
	// }
	// }
	//
	// };
	//
	// return redisTemplate.execute(redisCallback);
	//
	// }

	/**
	 * 分页获取消息
	 * 
	 * @param userId
	 * @param paginationDetail
	 * @param isAll
	 *            true则返回全部，false则返回未读。
	 */
	public void getMessageDomainList(final String userId,
			final PaginationDetail<MessageDomain> paginationDetail,
			final boolean isAll) {
		SessionCallback sessionCallback = new SessionCallback() {

			@Override
			public Object execute(RedisOperations operations)
					throws DataAccessException {
				String listKey = null;
				if (isAll) {
					listKey = getListKey(userId);
				} else {
					listKey = getUnreadListKey(userId);
				}

				BoundListOperations oper1 = operations.boundListOps(listKey);
				List<Object> keyList = oper1.range(paginationDetail.getStart(),
						paginationDetail.getEnd());
				long total = oper1.size();
				paginationDetail.setTotal(total);

				List<MessageDomain> userList = new ArrayList<MessageDomain>();
				for (Object key : keyList) {
					BoundValueOperations<String, Object> oper2 = operations
							.boundValueOps((String) key);
					MessageDomain user = (MessageDomain) oper2.get();
					userList.add(user);
				}

				paginationDetail.setRows(userList);
				return null;
			}

		};

		redisTemplate.execute(sessionCallback);
	}
}
