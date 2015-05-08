package com.chinasofti.rcloud.portal.login.domain;

import com.chinasofti.rcloud.common.cloudfoundry.domain.CFClientToken;
import com.chinasofti.rcloud.domain.UserEntity;
/**
 * @author sunlulu
 * @date 14-7-9.
 */

public class UserEntityExt extends UserEntity {
	private CFClientToken cFClientToken;

	public CFClientToken getcFClientToken() {
		return cFClientToken;
	}

	public void setcFClientToken(CFClientToken cFClientToken) {
		this.cFClientToken = cFClientToken;
	}

}
