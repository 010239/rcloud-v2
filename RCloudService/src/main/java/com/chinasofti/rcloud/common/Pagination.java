package com.chinasofti.rcloud.common;

import java.util.List;

/*
 * 往前台页面返回的分页对象。
 * 
 */
public class Pagination<T> {
	// 返回当前页结果
	private List<T> rows;
	// 总条数
	private int total;
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}
