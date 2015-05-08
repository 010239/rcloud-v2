package com.chinasofti.rcloud.common;

import com.chinasofti.notemail.util.MailSenderInfo;
import com.chinasofti.notemail.util.SendMail;

public class EmailUtil {

	public static void sendEmail(String toEmail, String subject, String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(toEmail); // 收件人
		mailInfo.setSubject(subject); //邮件标题
		mailInfo.setContent(content);
		SendMail.sendHtmlMail(mailInfo);//发送文本内容邮件
	}
	
}
