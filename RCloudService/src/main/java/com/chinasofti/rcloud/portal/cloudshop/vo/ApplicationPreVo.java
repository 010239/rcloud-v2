package com.chinasofti.rcloud.portal.cloudshop.vo;

import java.util.List;

import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.SpaceEntity;
/**
* @ClassName: ApplicationPreVo
* @Description: 预付费( 部署连接  指向部署页面)
* @author shimeihua
* @date 2014年11月28日 上午11:18:24
*
 */
public class ApplicationPreVo {
	private ApplicationEntity applicationEntity;
	private boolean isOpenRaePreFlag;//预付费   后部署  是否开通的标志
	private String orderNum;//订单号
	private List<SpaceEntity>  lists;
	
	
	public List<SpaceEntity> getLists() {
		return lists;
	}
	public void setLists(List<SpaceEntity> lists) {
		this.lists = lists;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public ApplicationEntity getApplicationEntity() {
		return applicationEntity;
	}
	public void setApplicationEntity(ApplicationEntity applicationEntity) {
		this.applicationEntity = applicationEntity;
	}
	public boolean isOpenRaePreFlag() {
		return isOpenRaePreFlag;
	}
	public void setOpenRaePreFlag(boolean isOpenRaePreFlag) {
		this.isOpenRaePreFlag = isOpenRaePreFlag;
	}
	

}
