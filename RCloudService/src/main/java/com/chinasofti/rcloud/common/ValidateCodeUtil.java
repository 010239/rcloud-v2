package com.chinasofti.rcloud.common;

import java.util.Random;

/**
 * @author zhangjiaxing
 *
 * 2014年7月3日
 */
public class ValidateCodeUtil {
	
	/**
	 * 生成邮箱和手机验证码
	 * @param length
	 * @return
	 */
	public static String getRandString(int length) {
	        String charList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	        String rev = "";
	        java.util.Random f = new Random();
	        for(int i=0;i<length;i++)
	        {
	           rev += charList.charAt(Math.abs(f.nextInt())%charList.length());
	        }
	        
	        return rev;
	    }

	/**
	 * 生成验证码创建日期
	 * @return
	 */
	public static long getCreateTime(){		
		long createTime = System.currentTimeMillis()/1000;
		return createTime;
	}
}