package com.chinasofti.rcloud.common.factory.cache;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;


@Component
public class LocalCacheImpl implements DataCache {
	
	private Map<String,Object> cacheMap = new HashMap<String,Object>();
	
	private Object waitObject = new Object();

	@Override
	public void putData(String key, Object value) {
		synchronized(waitObject){
			this.cacheMap.put(key, value);
			this.waitObject.notifyAll();
		}

	}

	@Override
	public Object getData(String key) {
		return this.cacheMap.get(key);
	}

	@Override
	public Object clearData(String key) {
		Object value=null;
		synchronized(waitObject){
			value = this.cacheMap.remove(key);
			this.waitObject.notifyAll();
		}
		return value;
	}

	@Override
	public void clearAll() {
		synchronized(waitObject){
			this.cacheMap.clear();
			this.waitObject.notifyAll();
		}
	}

}
