package com.chinasofti.rcloud.portal.userconsole.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.dao.UserAccountEntityMapper;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.UserAccountEntity;
import com.chinasofti.rcloud.domain.UserAccountEntityExample;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.domain.UserEntityExample;
import com.chinasofti.rcloud.portal.log.service.LogService;
import com.chinasofti.rcloud.portal.userconsole.dao.UserAccountMapperExt;
import com.chinasofti.rcloud.portal.userconsole.dao.UserEntityMapperExt;
import com.chinasofti.rcloud.portal.userconsole.service.UserInfoService;

/**
 * @author zhangjiaxing
 *
 * 2014年7月2日
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{
	
	private static final long VALI_TIMEOUT = 120;

	@Autowired
	private UserEntityMapper userEntityMapper;
	
	@Autowired
	private UserEntityMapperExt userEntityMapperExt;
	
	@Autowired
	private UserAccountEntityMapper userAccountEntityMapper;
	
	@Autowired
	private UserAccountMapperExt userAccountMapperExt;
	
	@Autowired
	private LogService logService;
	
	private Logger logger = Logger.getLogger(UserInfoServiceImpl.class);
	
	@Override
	public UserEntity findByUserId(String userId) throws Exception {
		UserEntity userEntity = userEntityMapper.selectByPrimaryKey(userId);
		return userEntity;
	}


	@Override
	public void updatePassword(String userId, String password) throws Exception {		
		int record = userEntityMapperExt.updatePassword(userId, password);
		if (record ==1) {
			logger.info("密码修改成功:用户ID"+userId);
		}
	}
	
	@Override
	public void updateEmail(String userId, String userEmail) throws Exception {
		int record = userEntityMapperExt.updateEmail(userId, userEmail);
		if (record ==1) {
			logger.info("邮箱修改成功:用户ID"+userId);
		}
	}
	
	@Override
	public void updatePhone(String userId, String mobilePhone) throws Exception {
		int record = userEntityMapperExt.updateMobilePhone(userId, mobilePhone);
		if (record ==1) {
			logger.info("手机修改成功:用户ID"+userId);
		}
	}
	
	@Override
	public void updateUserInfo(UserEntity userEntity,String description) throws Exception {
		int record = userEntityMapper.updateByPrimaryKeySelective(userEntity);
		if (record ==1 ) {
			logger.info("用户信息修改成功:用户ID"+userEntity.getUserId());
			logService.insertLog(userEntity, "修改安全信息", description);
		}
	}


	@Override
	public boolean isValiTimeValid(long createTime, long nowTime) throws Exception {
		String timeout = "120";
		long time;
		if( timeout != null && !"".equals(timeout.trim())) {
			time= Long.parseLong(timeout);
		}else{
			time = VALI_TIMEOUT;
		}
		long second = nowTime - createTime ;
	    if (second > time ){
	    	logger.error("验证码过期，请重新获取验证码");
	    	return true;
	    }
		return false;		
	}


	@Override
	public void updateAccount(String userName, double rechargeMoney)
			throws Exception {
		UserEntityExample example = new UserEntityExample();
		example.createCriteria().andUserNameEqualTo(userName);
		
		List<UserEntity> userEntityList = userEntityMapper.selectByExample(example);
		if (userEntityList.isEmpty()) {
			throw new RCloudException("该用户不存在", "00000001");
		}
		UserEntity userEntity = userEntityList.get(0);
		String userId = userEntity.getUserId();
		UserAccountEntityExample accountEntityExample = new UserAccountEntityExample();
		accountEntityExample.createCriteria().andUserIdEqualTo(userId);
		UserAccountEntity accountEntity = userAccountEntityMapper.selectByExample(accountEntityExample).get(0);
		
		double amount = 0;
		if (accountEntity.getMoneyAmount() == null) {
			amount = rechargeMoney;
		} else {
			amount = rechargeMoney + accountEntity.getMoneyAmount().doubleValue();
		} 
		
		accountEntity.setMoneyAmount(new BigDecimal(amount));
		
		userAccountEntityMapper.updateByPrimaryKey(accountEntity);
	}


	@Override
	public void updateUser(UserEntity userEntity) throws Exception {
		userEntityMapper.updateByPrimaryKeySelective(userEntity);
		
	}

}



