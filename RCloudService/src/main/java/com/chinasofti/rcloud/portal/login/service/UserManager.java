package com.chinasofti.rcloud.portal.login.service;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.UserEntity;

/**
 * @author DongZhukai
 * @date 14-7-1.
 */
public interface UserManager {
  /**
   * 保存数据
   *
   * 用户注册流程：
   * 1. 获取admin的token
   * 2. 使用admin权限，建立新用户默认组织，CsiUtils.getOrgName(email)获取组织名
   * 参考：http://docs.cloudfoundry.com/docs/reference/cc-api.html#organizations
   * 3. 使用admin权限，在刚建立的组织里建立新用户默认空间，CsiUtils.getSpaceName(email)获取空间名
   * 参考：http://docs.cloudfoundry.com/docs/reference/cc-api.html#spaces
   * 4. 使用admin权限，create新用户，务必specify刚才建立好的空间和组织名在request里
   * 参考：http://docs.cloudfoundry.com/docs/reference/cc-api.html#user
   * 请在过程中注意error handling。如果任何一个步骤失败了，请通知用户原因和做必要的roll back
   * 5. 成功后可使用新建立好的用户名和密码申请用户级别的token来做任何用户需要的操作。
   */
  public boolean register(UserEntity user) throws RCloudException;

  public void login(UserEntity user)throws RCloudException;
}
