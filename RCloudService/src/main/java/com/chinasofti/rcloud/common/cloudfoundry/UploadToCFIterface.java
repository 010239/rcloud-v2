package com.chinasofti.rcloud.common.cloudfoundry;

import java.util.List;

import com.chinasofti.rcloud.common.cloudfoundry.domain.CFClientToken;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.cloudshop.vo.RaeAppVo;

public interface UploadToCFIterface {

	/**
	 * 
	 * @Title: getSpaceGuidBySpaceNameFromUAA
	 * @Description: 通过UAA中的spaceName获取space_guid
	 *               此方法是公共方法，只要有spaceName,就能获取space_guid
	 * @author sunlulu
	 * @param @param spaceName 在cf上的工作空间的名字
	 * @param @param clientToken
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public String getSpaceGuidBySpaceNameFromUAA(String spaceName,
			CFClientToken clientToken) throws Exception;

	/**
	 * 
	 * @Title: createApp
	 * @Description: 创建应用
	 * @author sunlulu
	 * @param @param raeAppVo 传入值: domainName 域名 meorySize 内存 hiskSize 硬盘
	 *        spaceName 工作空间() 传出值: AppGuid app在cf上的guid
	 * @param @param userEntity
	 * @param @param clientToken
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void createApp(RaeAppVo raeAppVo, UserEntity userEntity,
			CFClientToken clientToken) throws Exception;

	/**
	 * 
	 * @Title: getDomainId
	 * @Description: 获取domain_guid 公共方法
	 * @author sunlulu
	 * @param @param clientToken
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public String getDomainId(CFClientToken clientToken) throws Exception;

	/**
	 * 
	 * @Title: createRoutes
	 * @Description: 创建route
	 * @author sunlulu
	 * @param @param raeAppVo 传入值： domainguId 通过公共方法getDomainId获取 spaceGuid
	 *        通过spaceName获取 host 用户设置的域名 传出值： routeGuid
	 * @param @param clientToken
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void createRoutes(RaeAppVo raeAppVo, CFClientToken clientToken)
			throws Exception;

	/**
	 * 
	 * @Title: bingAppRoutes
	 * @Description: 绑定app 和 routes
	 * @author sunlulu
	 * @param @param raeAppVo
	 * @param @param clientToken
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void bingAppRoutes(RaeAppVo raeAppVo, CFClientToken clientToken)
			throws Exception;

	/**
	 * 
	 * @Title: uploadApp
	 * @Description: 上传App
	 * @author sunlulu
	 * @param @param raeAppVo
	 * @param @param clientToken
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void uploadApp(RaeAppVo raeAppVo, CFClientToken clientToken,
			String userId, String applicationId) throws Exception;

	/**
	 * 
	 * @Title: apps
	 * @Description: 提供统一的方法上传应用 1.创建app 2.创建route 3.绑定app和route 4.上传应用到app
	 * @author sunlulu
	 * @param @param raeAppVo
	 * @param @param userEntity
	 * @param @param clientToken
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void apps(RaeAppVo raeAppVo, UserEntity userEntity,
			CFClientToken clientToken, String applicationId, String developId)
			throws Exception;

	public int getRoutesNum(String domainName, CFClientToken clientToken)
			throws Exception;

	/**
	 * 
	 * @Title: startApp
	 * @Description: 启动应用
	 * @author sunlulu
	 * @param @param guid
	 * @param @param clientToken
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public String startApp(String guid, CFClientToken clientToken)
			throws Exception;

	/**
	 * 
	 * @Title: getSpaceEntitys
	 * @Description: 返回用户对应的工作空间(可能是一对一，也可能是一对多)
	 * @author sunlulu
	 * @param @param userEntity
	 * @param @return
	 * @return List<SpaceEntity>
	 * @throws
	 */
	public List<SpaceEntity> getSpaceEntitys(UserEntity userEntity);

	/**
	 * 
	 * @Title: stopAppsByUser 根据用户，停止用户下所有的应用
	 * @Description: 
	 * 1.根据用户的类型返回用户的spaceName
	 * 2.再根据spaceName,查询出对应的工作space_guid
	 * 3.通过space_guid筛选出应用的列表guid
	 * 4.根据guid,停止应用
	 * @author sunlulu
	 * @param @param userEntity
	 * @param @param clientToken
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void stopAppsByUser(UserEntity userEntity, CFClientToken clientToken)
			throws Exception;

}
