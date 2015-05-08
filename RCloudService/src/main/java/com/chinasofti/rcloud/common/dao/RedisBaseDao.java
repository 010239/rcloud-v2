package com.chinasofti.rcloud.common.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public abstract class RedisBaseDao<T extends Serializable> {
	
	@Autowired
	protected RedisTemplate<String, T> redisTemplate;
	
	private static final long DEFAULT_DELETE_COUNT = 1;

	/**
	 * 保存或修改一个对象。
	 * 
	 * @param key
	 * @param value
	 */
	public void saveOrUpdate(final String key, final T value) {
		RedisCallback<Object> redisCallback = new RedisCallback<Object>() {

			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> keySerializer = redisTemplate.getStringSerializer();
				@SuppressWarnings("unchecked")
				RedisSerializer<T> valueSerializer = (RedisSerializer<T>) redisTemplate.getValueSerializer();
				connection.set(keySerializer.serialize(key),
						valueSerializer.serialize(value));
				return null;
			}
			
		};
		
		
		redisTemplate.execute(redisCallback);
	}
	
	/**
	 * 根据key获取值。
	 * 
	 * @param key
	 * @return
	 */
	public T getByKey(final String key) {
		RedisCallback<T> redisCallback = new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
				byte[] keyAfterSerialize = redisSerializer.serialize(key);
		
				if (connection.exists(keyAfterSerialize)) {
					byte[] value = connection.get(keyAfterSerialize);
					@SuppressWarnings("unchecked")
					RedisSerializer<T> valueSerializer = (RedisSerializer<T>) redisTemplate.getValueSerializer();
					return valueSerializer.deserialize(value);
				}
				return null;
			}
			
		};
		
		return redisTemplate.execute(redisCallback);
	}
	
	/**
	 * 根据key删除。
	 * 
	 * @param key
	 */
	public void deleteByKey(final String key) {
		RedisCallback<Object> redisCallback = new RedisCallback<Object>() {

			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> keySerializer = redisTemplate.getStringSerializer();
				connection.del(keySerializer.serialize(key));
				return null;
			}
			
		};
		
		redisTemplate.execute(redisCallback);
	}
	
	/**
	 * 根据匹配模式，列出key
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<String> listKeys(final String pattern) {
		RedisCallback<Set<String>> redisCallback = new RedisCallback<Set<String>>() {

			public Set<String> doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> keySerializer = redisTemplate.getStringSerializer();
				Set<byte[]> keySet = connection.keys(keySerializer.serialize(pattern));
				Set<String> keyStrSet = new HashSet<String>();
				for (byte[] key : keySet) {
					keyStrSet.add(keySerializer.deserialize(key));
				}
				return keyStrSet;
			}
			
		};
		
		return redisTemplate.execute(redisCallback);
	}
	
	public Long push(String key, T value) {  
        return redisTemplate.opsForList().leftPush(key, value);  
    }
	
	public T pop(String key) {  
        return redisTemplate.opsForList().leftPop(key); 
	}
	
	/**
	 * 获取列表中元素的长度。
	 * 
	 * @param key
	 * @return
	 */
	public Long length(String key) {  
        return redisTemplate.opsForList().size(key);  
    }
	
	/** 
     * 范围检索 
     *  
     * @param key 
     * @param start 
     * @param end 
     * @return 
     */  
    public List<T> range(String key, long start, long end) {  
        return redisTemplate.opsForList().range(key, start, end);  
    }
    
    /**
     * 从list中删除
     * 
     * @param key
     * @param i
     * @param value
     */
    public void remove(String key, long i, String value) {  
    	redisTemplate.opsForList().remove(key, i, value);  
    }  
    
    /**
     * 从list中删除
     * 
     * @param key
     * @param value
     */
    public void remove (String key, String value) {
    	remove(key, DEFAULT_DELETE_COUNT, value);
    }
    
}
