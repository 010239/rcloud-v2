package com.chinasofti.rcloud.portal.userconsole.service;

import com.chinasofti.rcloud.domain.UserEntity;

/**
 * @author zhangjiaxing
 *
 * 2014年7月2日
 */
public interface UserInfoService {
	
	/**
	 * 根据userId展示用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	UserEntity findByUserId(String userId) throws Exception;

	/**
	 * 修改密码
	 * @param userId
	 * @param password
	 * @throws Exception
	 */
	void updatePassword(String userId, String password) throws Exception;

	/**
	 * 修改邮箱
	 * @param userId
	 * @param userEmail
	 * @throws Exception
	 */
	void updateEmail(String userId, String userEmail) throws Exception;

	/**
	 * 修改手机号码
	 * @param userId
	 * @param mobilePhone
	 * @throws Exception
	 */
	void updatePhone(String userId, String mobilePhone) throws Exception;

    /**
     * 执行修改用户信息
     * @param userEntity
     * @return
     * @throws Exception
     */
    void updateUserInfo(UserEntity userEntity,String description) throws Exception;
    
    void updateUser(UserEntity userEntity) throws Exception;
    
    /**
     * 验证码是否过期
     * @param createTime
     * @param nowTime
     * @return
     * @throws Exception
     */
    boolean isValiTimeValid(long createTime, long nowTime) throws Exception;
    

    /**
     * 向账户充值
     * 
     * @param userEmail
     * @param rechargeMoney
     * @throws Exception
     */
    void updateAccount(String userName, double rechargeMoney) throws Exception;
}
