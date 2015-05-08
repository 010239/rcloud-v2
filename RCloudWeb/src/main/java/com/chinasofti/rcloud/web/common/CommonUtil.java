package com.chinasofti.rcloud.web.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class CommonUtil {

	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> requestMap = request.getParameterMap();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		for (String key : requestMap.keySet()) {
			String[] values = (String[])requestMap.get(key);
			if (values != null && values.length != 0) {
				if (values.length == 1) {
					parameterMap.put(key, values[0]);
				} else {
					parameterMap.put(key, values);
				}
			}
		}
		
		return parameterMap;
	}
}
