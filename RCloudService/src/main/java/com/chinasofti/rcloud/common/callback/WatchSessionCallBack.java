package com.chinasofti.rcloud.common.callback;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

public abstract class WatchSessionCallBack<T> implements SessionCallback<Object> {

	private static final int DEFAULT_CHECK_TIME = 5;
	
	private int checkTime;
	private T key;
	private List<T> keyList;
	public WatchSessionCallBack(T key) {
		checkTime = DEFAULT_CHECK_TIME;
		this.key = key;
	}
	
	public WatchSessionCallBack(List<T> keyList) {
		checkTime = DEFAULT_CHECK_TIME;
		this.keyList = keyList;
	}
	
	public WatchSessionCallBack(int checkTime, T key) {
		this.checkTime = checkTime;
		this.key = key;
	}
	
	public WatchSessionCallBack(int checkTime, List<T> keyList) {
		this.checkTime = checkTime;
		this.keyList = keyList;
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T, V> Object execute(RedisOperations<T, V> operations)
			throws DataAccessException {
		List<Object> execResult = null;
		int i = 0;
		while (execResult == null && i < checkTime) {
			if (key == null) {
				operations.watch((List<T>) keyList);
			} else {
				operations.watch((T) key);
			}
			
			operations.multi();
			excuteOps((RedisOperations<String, Object>) operations);
			execResult = operations.exec();
		}
		return null;
	}
	
	abstract public void excuteOps(RedisOperations<String, Object> operations);
	
	public int getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public List<T> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<T> keyList) {
		this.keyList = keyList;
	}
}
