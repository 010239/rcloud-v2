package com.chinasofti.rcloud.web.common;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * @ClassName: JsonUtil
 * @Description: TODO
 * @author shimeihua
 * @date 2014-9-12 上午11:14:09
 * 
 */
public class JsonUtil {

	private static final Log logger = LogFactory.getLog(JsonUtil.class);

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String valueToString(Object value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (Exception e) {
		}
		return null;
	}

	public static <T> Object jsonToObject(String jacksonValue, Class<?> T)
			throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(jacksonValue, T);
	}

	public static <T> T readValue(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("readValue", e);
		}
		return null;
	}

	public static <T> Object readValue(String json, TypeReference<T> ref) {
		try {
			return objectMapper.readValue(json, ref);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("readValue", e);
		}
		return null;
	}

	

	public static String getJsonFromObject(Object t) {
		try {
			if (logger.isDebugEnabled()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				objectMapper.setDateFormat(dateFormat);
				String ret = objectMapper.writeValueAsString(t);
				logger.debug(ret);
				return ret;
			} else {
				return objectMapper.writeValueAsString(t);
			}
		} catch (Exception e) {
			logger.error("getJsonFromObject", e);
			//e.printStackTrace();
		}
		return null;
	}
	public static void print(String str , HttpServletResponse response){
		OutputStream out = null;
		  try {  
	             response.setContentType("text/html;charset=utf-8");  
	             out = response.getOutputStream();  
	             out.write(str.getBytes("UTF-8"));  
	         } catch (IOException e) {  
	        	 logger.error(e.getMessage(),e);
	         }finally{  
	             if (null != out) {  
	                 try {  
	                	out.flush();
	                    out.close();  
	                 } catch (IOException e) {  
	                       logger.error(e.getMessage(),e);
	                 }  
	             }  
	         } 
	}

}