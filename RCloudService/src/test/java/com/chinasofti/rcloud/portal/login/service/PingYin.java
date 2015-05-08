/**
 * @Title: PingYin.java
 * @Package: com.chinasofti.rcloud.portal.login.service
 * @Author: sunlulu
 * @Date: 2014年7月25日 上午11:15:44
 * @Version: V1.0
 */
package com.chinasofti.rcloud.portal.login.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chinasofti.rcloud.common.PinyinUtil;

/**
 * @ClassName: PingYin
 * @Description: TODO
 * @Author: sunlulu
 * @Date: 2014年7月25日 上午11:15:44
 *
 */
public class PingYin {

	/**
	 *@Description: TODO
	 *@throws java.lang.Exception
	 *@return void
	 *@throws 
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 *@Description: TODO
	 *@throws java.lang.Exception
	 *@return void
	 *@throws 
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String str = PinyinUtil.cn2Spell("中国人");
		System.out.println(str);
	}

}
