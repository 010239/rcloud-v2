package com.chinasofti.rcloud.common.callback;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

public abstract class DefaultSessionCallBack implements SessionCallback<Object> {
	@SuppressWarnings("unchecked")
	@Override
	public <T, V> Object execute(RedisOperations<T, V> operations)
			throws DataAccessException {
		operations.multi();
		excuteOps((RedisOperations<String, Object>) operations);
		operations.exec();
		return null;
	}
	
	abstract public void excuteOps(RedisOperations<String, Object> operations);
}
