package com.chinasofti.rcloud.portal.notice.domain;

import java.io.Serializable;

public class NoticeDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3459430789472194482L;
	
	private String noticeKey;
	private String publishTime; //发送时间
	private String notice;
	private String showCut; //公告简述
	private String startDate; //发布时间起
	private String endDate;

	
	public String getNoticeKey() {
		return noticeKey;
	}
	public void setNoticeKey(String noticeKey) {
		this.noticeKey = noticeKey;
	}

	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getShowCut() {
		return showCut;
	}
	public void setShowCut(String showCut) {
		this.showCut = showCut;
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	

}
