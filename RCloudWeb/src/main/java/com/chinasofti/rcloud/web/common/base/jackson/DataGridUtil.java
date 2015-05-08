package com.chinasofti.rcloud.web.common.base.jackson;


public class DataGridUtil {
	
	/**
	 * 生成带分页的表格数据格式
	 * @Description: 
	 * @author 王慧智
	 * @date 2014-5-4
	 * @param total
	 * @param array
	 * @return
	 */
	public static String formatDataGridPage(int total,String array) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"total\":"+total+",\"rows\":");
		sb.append(array);
		sb.append("}");
		return sb.toString();
	}
}
