package com.chinasofti.rcloud.web.common.base.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.node.ArrayNode;
import org.springframework.util.StringUtils;

import com.chinasofti.rcloud.web.common.base.PageBean;
import com.chinasofti.rcloud.web.common.base.ResultBean;

/**
 * 
 * @Description:
 * @author 王慧智
 * @date 2014-4-1
 */
public class JacksonMap extends ObjectMapper {
	
	public JacksonMap() {
		disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		setDateFormat(formatter);
	}
	
	public static JacksonMap instance = new JacksonMap();
	
	public synchronized static JacksonMap getInstance(){
		if(instance==null){
			
			instance = new JacksonMap();
		}
		return instance;
	}

	private static final long serialVersionUID = 1L;
	
	@Override
	public String writeValueAsString(Object value){
		try {
			return super.writeValueAsString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T readValue(String content, Class<T> valueType)
			throws IOException, JsonParseException, JsonMappingException {
		if(content==null || "[]".equals(content) || "".equals(content)){
			return null;
		}
		return super.readValue(content, valueType);
	}
	
	@Override
	public JsonNode readTree(String content) throws IOException,
			JsonProcessingException {
		if(content==null || "[]".equals(content) || "".equals(content)){
			return null;
		}
		return super.readTree(content);
	}
	
	/**
	 * 对ResultBean结果集里的Object类型转换
	 * @Description: 
	 * @author 王慧智
	 * @date 2014-5-26
	 * @param content
	 * @param valueType
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	public ResultBean readValueByBean(String content,Class<?> valueType)
			throws IOException, JsonParseException, JsonMappingException {
		if(content==null || "[]".equals(content)){
			return null;
		}
		ResultBean resultBean = new ResultBean();
		resultBean = readValue(content, ResultBean.class);
		if(!StringUtils.isEmpty(resultBean.getResult())){
			resultBean.setResult(readValue(resultBean.getResult().toString(),valueType));
		}
		return resultBean;
	}
	
	/**
	 * 处理List数组里包含实体类的JSON解析
	 * @Description: 
	 * @author 王慧智
	 * @date 2014年7月4日
	 * @param content
	 * @param valueType
	 * @return 
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	public <T> Object readValueByListBean(String content,Class<T> valueType)
			throws IOException, JsonParseException, JsonMappingException {
		ArrayNode arr = (ArrayNode) readTree(content);
		if(arr!=null){
			ArrayList<Object> list = new ArrayList<Object>(arr.size());
			for (int i = 0, max = arr.size(); i < max; i++) {
				list.add(readValue(arr.get(i).toString(), valueType));
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 对PageBean结果集里的Object类型转换
	 * @Description: 
	 * @author 王立伟
	 * @param <T>
	 * @date 2014-7-4
	 * @param content
	 * @param typeReference
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	public <T> PageBean<T> readValueByPageBean(String content, String result, Class<T> valueType)
			throws IOException, JsonParseException, JsonMappingException {
		if(content==null || "[]".equals(content)){
			return null;
		}
		PageBean<T> resultBean = new PageBean<T>();
		resultBean = readValue(content, PageBean.class);
		ArrayNode arr = (ArrayNode) readTree(result);
		if(arr!=null){
			ArrayList<T> list = new ArrayList<T>(arr.size());
			for (int i = 0, max = arr.size(); i < max; i++) {
				list.add(readValue(arr.get(i).toString(), valueType));
			}
			resultBean.setResult(list);
		}
		return resultBean;
	}
}
