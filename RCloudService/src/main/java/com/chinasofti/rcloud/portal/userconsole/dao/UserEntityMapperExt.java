package com.chinasofti.rcloud.portal.userconsole.dao;

import org.apache.ibatis.annotations.Param;

import com.chinasofti.rcloud.common.dao.BaseDao;

/**
 * @author zhangjiaxing
 *
 * 2014年7月2日
 */
public interface UserEntityMapperExt extends BaseDao{
	
	int updatePassword(@Param("userId")String userId,@Param("password")String password);
	
	int updateEmail(@Param("userId")String userId,@Param("userEmail")String userEmail);
	
	int updateMobilePhone(@Param("userId")String userId,@Param("mobilePhone")String mobilePhone);
}
