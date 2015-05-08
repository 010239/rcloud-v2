package com.chinasofti.rcloud.common;

import java.util.List;

public class PaginationDetail<T> {
	// 当前页数
	private int currentPage;
	// 每页多少条
	private int pageSize;
	// 计数开始
	private long start;
	// 计数结束
	private long end;
	// 总条数
	private long total;
	// 总页数
	private long totalPage;
	// 分页结果
	private List<T> rows;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getStart() {
		return (currentPage - 1) * pageSize;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return currentPage * pageSize - 1;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getTotalPage() {
		return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
