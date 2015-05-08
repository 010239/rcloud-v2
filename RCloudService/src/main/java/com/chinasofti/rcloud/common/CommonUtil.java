package com.chinasofti.rcloud.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

	private static UUIDHexGenerator uuid = new UUIDHexGenerator();
	/**
     * 取得不重复的32位字符串
     * @return
     */
    public static String getId() {
        return (String) uuid.generate();
    }
    
    /**
	 * MD5加密
	 * @param source
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5Encrypt(String source) throws NoSuchAlgorithmException {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		if (source != null) {
			byte[] input = source.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");

			mdInst.update(input);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		return null;
	}
	/**
	 * 
	 *@Description: 生成随机的6位数字
	 *@return
	 *@return String
	 *@throws
	 */
	public static String getRamdomSix(){
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < 6; i++)
		{
			String rand = String.valueOf(random.nextInt(10));
			sRand = sRand + rand;
		}
		return sRand;
	}
	
	public static Date GetSysDate(int month) {
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String StrDate = sdf.format(new Date());
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sFmt = new SimpleDateFormat(format);
		cal.setTime(sFmt.parse((StrDate), new ParsePosition(0)));
		if (month != 0) {
		    cal.add(cal.MONTH, month);
		}
		return cal.getTime();
    }
	
	public static String dateToString(Date date) {
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return dateStr ;
	}
	
	public static String dateToString(Date date,String style) {
		String dateStr = new SimpleDateFormat(style).format(date);
		return dateStr ;
	}
	
	/**
	 * 方法用于日期条件查询，获取后一天凌点的时间
	 * @param nowDay
	 * @return
	 */
	public static String getAfterDay(String nowDay) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String afterDay = null;
		try {
			Date now = sdf.parse(nowDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
	      
	        cal.add(Calendar.DAY_OF_MONTH, 1);
	        afterDay  = sdf.format(cal.getTime());
		} catch (ParseException e) {		
			e.printStackTrace();
		}
		
		return afterDay;
		
	}
}
