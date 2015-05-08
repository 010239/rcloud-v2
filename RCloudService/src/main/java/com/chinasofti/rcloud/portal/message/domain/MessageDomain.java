package com.chinasofti.rcloud.portal.message.domain;

import java.io.Serializable;
import java.util.Date;

public class MessageDomain implements Serializable {

	private static final long serialVersionUID = -5951805224924757431L;
	
	private String messageKey;
	private String sender;
	private boolean status = false; // 状态：false-未读，true-已读
	private String content;
	private Date sendTime;
	private String receiverId;

	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

}
