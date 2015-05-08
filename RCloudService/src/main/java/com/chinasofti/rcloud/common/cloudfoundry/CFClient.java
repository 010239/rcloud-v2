package com.chinasofti.rcloud.common.cloudfoundry;

import java.util.List;

import com.chinasofti.rcloud.common.cloudfoundry.domain.CFClientToken;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CFBean;
import com.chinasofti.rcloud.common.cloudfoundry.domain.OAuthAccessToken;
import com.chinasofti.rcloud.common.cloudfoundry.domain.QuotaBean;
import com.chinasofti.rcloud.common.cloudfoundry.domain.QuotaBean;
import com.chinasofti.rcloud.domain.OrganizationEntity;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.UserEntity;

/**
 * 
 * cf平台接口，一期需要实现的接口
 * @author lidonghui
 *
 * 2014年7月15日
 */
public interface CFClient {
	
	/**
	 * 注册机构管理员
	 * @param user(用户信息)
	 * @return  空
	 * 
	 */
	public void createOrgAdmin(UserEntity user) throws Exception;
	
	/**
	 * 注册机构开发者
	 * @param user(用户信息)
	 * @param entity(空间信息)
	 * @param clientToken(访问令牌信息)
	 * @return  空
	 * 
	 */
	public void createOrgDeveloper(UserEntity user,CFClientToken clientToken) throws Exception;
	
	/**
	 * 注册独立开发者
	 * @param user(用户信息)
	 * @return  空
	 * 
	 */
	public void createDeveloper(UserEntity user) throws Exception;
	
	/**
	 * 注册组织机构
	 * @param org(组织结构信息)
	 * @return  空
	 * 
	 */
	public void createOrg(OrganizationEntity org) throws Exception;
	
	/**
	 * 注册工作空间
	 * @param space(工作空间信息)
	 * @return  空
	 * 
	 */
	public void createSpace(SpaceEntity space,CFClientToken clientToken)throws Exception;
	
	/**
	 * 绑定用户到工作空间
	 * @param users(用户信息)
	 * @param space(工作空间信息)
	 * @return  空
	 * 
	 */
	public void bindUserToSpace(UserEntity user,SpaceEntity space,CFClientToken clientToken)throws Exception;
	
	/**
	 * 从工作空间解除绑定用户
	 * @param users(用户信息)
	 * @param space(工作空间信息)
	 * @return  空
	 * 
	 */
	public void unBindUserToSpace(UserEntity user,SpaceEntity space,CFClientToken clientToken) throws Exception;
	
	/**
	 * 用户登录
	 * @param user(用户信息)
	 * @return  空
	 * 
	 */
	public CFClientToken login(UserEntity user) throws Exception;
	
	/**
	 * 调整套餐-针对云引擎服务
	 * @param org(组织结构信息)
	 * @param plan(要调整的套餐计划)
	 * @return  空
	 * 
	 */
	public void adjustPlan(OrganizationEntity org, String plan,CFClientToken clientToken) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param userEntity
	 * @throws Exception
	 */
	public boolean updateUser(UserEntity userEntity,CFClientToken clientToken) throws Exception;
	
	/**
	 * 修改密码
	 * @param userEntity
	 * @return
	 * @throws Exception
	 */
	public boolean changeUserPassword(UserEntity userEntity,String oldPassword) throws Exception; 
	
	
	/**
	 * 获取套餐列表-云引擎
	 * @param clientToken
	 * @return
	 * @throws Exception
	 */
	public List<QuotaBean> getQuotaList(CFClientToken clientToken)throws Exception;
	
	/**
	 * 获取用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void getUser(UserEntity user)throws Exception;
	
	/**
	 * 授权到空间管理者
	 * @param user
	 * @param spaceEntity
	 * @throws Exception 
	 */
	public void authorizeToSpaceManager(UserEntity user,SpaceEntity spaceEntity,CFClientToken clientToken) throws Exception;
	
	/**
	 * 授权用户到空间开发者
	 * @param user
	 * @param spaceEntity
	 * @throws Exception
	 */
	public void authorizeToSpaceDeveloper(UserEntity user,SpaceEntity spaceEntity, CFClientToken clientToken) throws Exception;
	
	
	
	

}
