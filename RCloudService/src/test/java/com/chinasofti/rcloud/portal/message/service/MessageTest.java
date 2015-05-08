package com.chinasofti.rcloud.portal.message.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chinasofti.rcloud.portal.message.domain.MessageDomain;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-service.xml"})
public class MessageTest {
	//保存消息
	@Test
	public void testSaveMessage(){
		try {
			for (int i = 1; i < 30; i ++) {
				MessageDomain m1 = new MessageDomain();
				m1.setSender("系统管理员");
				//String str="社区点歌插播：在炫舞游戏内的休闲社区，可以尊享免费插播特权，并且不限次，处处彰显您尊贵的音乐特权身份社区点歌插播：在炫舞游戏内的休闲社区，可以尊享免费插播特权，并且不限次，处处彰显您尊贵的音乐特权身份社区点歌插播：在炫舞游戏内的休闲社区，可以尊享免费插播特权，并且不限次，处处彰显您尊贵的音乐特权身份社区点歌插播：在炫舞游戏内的休闲社区，可以尊享免费插播特权，并且不限次，处处彰显您尊贵的音乐特权身份";
				String str="社区点歌插播：在炫舞游戏内的休闲社区";
				m1.setContent(i+"~~~~"+str);
				m1.setReceiverId("ff80818147fcf76f0147fcf7f6610001");//接收者 ff80818147c3e2950147c3e295780000
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date=dateformat.format(new Date());
				m1.setSendTime( dateformat.parse(date));
				m1.setStatus(false);//状态：false-未读，true-已读
				messageService.saveMessage(m1);
			}
			System.out.println("执行完毕");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Autowired
	MessageService messageService;
}

