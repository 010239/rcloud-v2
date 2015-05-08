package com.chinasofti.rcloud.portal.login.service;

import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CFClientToken;
import com.chinasofti.rcloud.domain.OrganizationEntity;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.SpaceToUserEntityKey;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;

/**
 * @author DongZhukai
 * @date 14-7-1.
 */
public interface UserService {
	/**
	 * 保存数据
	 *
	 * 用户注册流程： 1. 获取admin的token 2.
	 * 使用admin权限，建立新用户默认组织，CsiUtils.getOrgName(email)获取组织名
	 * 参考：http://docs.cloudfoundry.com/docs/reference/cc-api.html#organizations
	 * 3. 使用admin权限，在刚建立的组织里建立新用户默认空间，CsiUtils.getSpaceName(email)获取空间名
	 * 参考：http://docs.cloudfoundry.com/docs/reference/cc-api.html#spaces 4.
	 * 使用admin权限，create新用户，务必specify刚才建立好的空间和组织名在request里
	 * 参考：http://docs.cloudfoundry.com/docs/reference/cc-api.html#user
	 * 请在过程中注意error handling。如果任何一个步骤失败了，请通知用户原因和做必要的roll back 5.
	 * 成功后可使用新建立好的用户名和密码申请用户级别的token来做任何用户需要的操作。
	 */
	public boolean register(UserEntity user) throws RCloudException;

	public UserEntityExt login(String username, String passwd, String code,
			String oldcode, Long createTime) throws RCloudException;

	public UserEntity hasUserEntity(String username) throws RCloudException;

	public UserEntity selectByOrgIdAndRole(String orgId, String roleId)
			throws RCloudException;

	/**
	 * 
	 * @Description: 根据组织ID和角色类型返回，机构开发者
	 * @param map
	 *            中包含（orgId,rolecode）
	 * @param String
	 *            orgId 组织ID
	 * @param String
	 *            rolecode 角色类型 40002：表示结构开发者
	 * @return
	 * @throws RCloudException
	 * @return List<UserEntity>
	 * @throws
	 */
	public List<Map<String, String>> selectByMap(Map<String, Object> map)
			throws RCloudException;

	public List<SpaceToUserEntityKey> selectBySpaceIdNotUserId(
			Map<String, Object> map) throws RCloudException;

	public int selectNumsBySpaceIdNotUserId(Map<String, Object> map)
			throws RCloudException;

	/**
	 * 
	 * @Description: 保存独立开发者
	 * @param userEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean saveUserEntity(UserEntity userEntity) throws RCloudException;

	/**
	 * 
	 * @Description: 保存组织管理员和组织信息
	 * @param userEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean saveUserEntityORG(UserEntity userEntity,
			OrganizationEntity organizationEntity) throws RCloudException;

	/**
	 * 
	 * @Description: 注册结构开发者
	 * @param userEntity
	 *            机构开发者收集到的信息
	 * @param loginuser
	 *            机构管理员信息
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean saveUserEntityDevleper(UserEntity userEntity,
			UserEntityExt loginuser) throws RCloudException;

	/**
	 * 
	 * @Description: 绑定机构开发者
	 * @param userEntity
	 * @param listDev
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean bingEntityDevleper(UserEntityExt userEntity,
			List<UserEntity> listDev, String spaceId) throws RCloudException;

	/**
	 * 
	 * @Description: 保存工作空间
	 * @param userEntity
	 * @param loginuser
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean saveSpace(UserEntityExt userEntity, SpaceEntity spaceEntity)
			throws RCloudException;

	/**
	 * 
	 * @Description: 更新组织管理员和组织信息
	 * @param userEntity
	 * @param organizationEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean updateOrganizationEntity(
			OrganizationEntity organizationEntity) throws RCloudException;

	/**
	 * 
	 * @Description: 跟新独立开发者
	 * @param userEntity
	 * @param organizationEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean updateUserEntity(UserEntity userEntity)
			throws RCloudException;

	/**
	 * 
	 * @Description: 更新用户的密码
	 * @param userEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean modifyUserPwd(UserEntity userEntity) throws RCloudException;

	/**
	 * 
	 * @Description: 管理员调用rest向cf平台注册用户和组织
	 * @param userEntity
	 * @param organizationEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean registEntity(UserEntity userEntity,
			OrganizationEntity organizationEntity) throws RCloudException;

	/**
	 * 
	 * @Description: 管理员调用rest向cf平台注册用户
	 * @param userEntity
	 * @param organizationEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean registEntity(UserEntity userEntity) throws RCloudException;

	/**
	 * 
	 * @Description: 管理员调用rest向cf平台注册组织
	 * @return
	 * @return boolean
	 * @throws
	 */
	public boolean registEntity(OrganizationEntity organizationEntity)
			throws RCloudException;

	/**
	 * 
	 * @Description: 注册机构开发者
	 * @param userEntity
	 * @param clientToken
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	public boolean registEntityDevloper(UserEntity userEntity,
			CFClientToken clientToken) throws RCloudException;

	/**
	 * 
	 * @Description: 返回随机的6位数
	 * @param eamil
	 * @return
	 * @throws RCloudException
	 * @return String
	 * @throws
	 */
	public String sendEmail(String eamil) throws RCloudException;

	/**
	 * @Description: 如果邮箱已注册，抛异常
	 * @param email
	 * @return
	 * @throws RCloudException
	 * @return UserEntity
	 * @throws
	 */
	public void hasUserEntityEmail(String email) throws RCloudException;

	/**
	 * @Description: 根据guid查询用户信息
	 * @param guid
	 * @return
	 * @throws RCloudException
	 * @return UserEntity
	 * @throws
	 */
	public UserEntity getUserByGuid(String guid) throws RCloudException;

	/**
	 * @Description: 根据userid查询用户信息
	 * @param userid
	 * @return
	 * @throws RCloudException
	 * @return UserEntity
	 * @throws
	 */
	public UserEntity getUserByUserid(String userid) throws RCloudException;

	/**
	 * @param userName
	 * @throws RCloudException
	 */
	public void hasUserName(String userName) throws RCloudException;
	
	
	/** 
	 * 判断用户是否已登录
	* @Title: isLogin 
	* @author zhangjiaxing
	* @param @return
	* @param @throws RCloudException
	* @return String  
	* @throws 
	*/ 
	
	public String isLogin(UserEntityExt user) throws RCloudException;
	
	
	/** 
	 * 判断原密码是否正确
	* @Title: isPasswordRight 
	* @author zhangjiaxing
	* @param @param password
	* @param @param userId
	* @param @return
	* @param @throws RCloudException
	* @return String  
	* @throws 
	*/ 
	
	public String isPasswordRight(String password,String userId) throws RCloudException;
}
