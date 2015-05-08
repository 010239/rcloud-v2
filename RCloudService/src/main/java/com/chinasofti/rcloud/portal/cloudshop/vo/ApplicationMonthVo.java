package com.chinasofti.rcloud.portal.cloudshop.vo;

import java.util.List;

import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.SpaceEntity;
/**
* @ClassName: ApplicationMonthVo
* @Description: 一次性付费
* @author shimeihua
* @date 2014年11月28日 上午11:18:00
*
 */
public class ApplicationMonthVo {
	private ApplicationEntity applicationEntity;
	private boolean isSaveSession;//预付费   是否暂存session信息
	private SaveSessAppOrder saveSessAppOrder;//暂存到session信息
	private String maintenanceCosts;//服务费用
	private boolean openIsRae;//是否开通rae服务
	private int payPattern;
	private List<SpaceEntity>  lists;
	
	public List<SpaceEntity> getLists() {
		return lists;
	}
	public void setLists(List<SpaceEntity> lists) {
		this.lists = lists;
	}
	private String orderNum;//订单
	
	
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public int getPayPattern() {
		return payPattern;
	}
	public void setPayPattern(int payPattern) {
		this.payPattern = payPattern;
	}
	public boolean isOpenIsRae() {
		return openIsRae;
	}
	public void setOpenIsRae(boolean openIsRae) {
		this.openIsRae = openIsRae;
	}
	public String getMaintenanceCosts() {
		return maintenanceCosts;
	}
	public void setMaintenanceCosts(String maintenanceCosts) {
		this.maintenanceCosts = maintenanceCosts;
	}
	public SaveSessAppOrder getSaveSessAppOrder() {
		return saveSessAppOrder;
	}
	public void setSaveSessAppOrder(SaveSessAppOrder saveSessAppOrder) {
		this.saveSessAppOrder = saveSessAppOrder;
	}
	public boolean isSaveSession() {
		return isSaveSession;
	}
	public void setSaveSession(boolean isSaveSession) {
		this.isSaveSession = isSaveSession;
	}
	public ApplicationEntity getApplicationEntity() {
		return applicationEntity;
	}
	public void setApplicationEntity(ApplicationEntity applicationEntity) {
		this.applicationEntity = applicationEntity;
	}
	

}
