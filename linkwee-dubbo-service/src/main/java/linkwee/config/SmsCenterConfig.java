package linkwee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsCenterConfig {
	
	@Value("${ms.updateSpreadMessage}")
	private String updateSpreadMessage;
	
	@Value("${ms.getUnreadCountEx}")
	private String getUnreadCountEx;
	
	@Value("${ms.getMessageTemplate}")
	private String getMessageTemplate;
	
	@Value("${ms.updateSystemMessage}")
	private String updateSystemMessage;
	
	@Value("${ms.deleteSystemMessage}")
	private String deleteSystemMessage;
	
	@Value("${ms.getSystemMessageList}")
	private String getSystemMessageList;
	
	@Value("${ms.getSystemMessageById}")
	private String getSystemMessageById;
	
	@Value("${ms.getUnreadCount}")
	private String getUnreadCount;
	
	@Value("${ms.getSpreadMessageList}")
	private String getSpreadMessageList;
	
	@Value("${ms.getSpreadMessageById}")
	private String getSpreadMessageById;
	
	@Value("${ms.pushMessage}")
	private String pushMessage;
	
	@Value("${ms.pushSysMessage}")
	private String pushSysMessage;
	
	@Value("${ms.generateCode}")
	private String generateCode;
	
	@Value("${ms.checkCode}")
	private String checkCode;
	
	@Value("${ms.generateImageCode}")
	private String generateImageCode;
	
	@Value("${ms.checkImageCode}")
	private String checkImageCode;
	
	@Value("${ms.getLeftMsg}")
	private String getLeftMsg;
	
	@Value("${ms.sendMsg}")
	private String sendMsg;
	
	@Value("${ms.getSendStatus}")
	private String getSendStatus;
	
	public String getUpdateSpreadMessage() {
		return updateSpreadMessage;
	}
	public void setUpdateSpreadMessage(String updateSpreadMessage) {
		this.updateSpreadMessage = updateSpreadMessage;
	}
	public String getGetUnreadCountEx() {
		return getUnreadCountEx;
	}
	public void setGetUnreadCountEx(String getUnreadCountEx) {
		this.getUnreadCountEx = getUnreadCountEx;
	}
	public String getGetMessageTemplate() {
		return getMessageTemplate;
	}
	public void setGetMessageTemplate(String getMessageTemplate) {
		this.getMessageTemplate = getMessageTemplate;
	}
	public String getUpdateSystemMessage() {
		return updateSystemMessage;
	}
	public void setUpdateSystemMessage(String updateSystemMessage) {
		this.updateSystemMessage = updateSystemMessage;
	}
	public String getDeleteSystemMessage() {
		return deleteSystemMessage;
	}
	public void setDeleteSystemMessage(String deleteSystemMessage) {
		this.deleteSystemMessage = deleteSystemMessage;
	}
	public String getGetSystemMessageList() {
		return getSystemMessageList;
	}
	public void setGetSystemMessageList(String getSystemMessageList) {
		this.getSystemMessageList = getSystemMessageList;
	}
	public String getGetSystemMessageById() {
		return getSystemMessageById;
	}
	public void setGetSystemMessageById(String getSystemMessageById) {
		this.getSystemMessageById = getSystemMessageById;
	}
	public String getGetUnreadCount() {
		return getUnreadCount;
	}
	public void setGetUnreadCount(String getUnreadCount) {
		this.getUnreadCount = getUnreadCount;
	}
	public String getGetSpreadMessageList() {
		return getSpreadMessageList;
	}
	public void setGetSpreadMessageList(String getSpreadMessageList) {
		this.getSpreadMessageList = getSpreadMessageList;
	}
	public String getGetSpreadMessageById() {
		return getSpreadMessageById;
	}
	public void setGetSpreadMessageById(String getSpreadMessageById) {
		this.getSpreadMessageById = getSpreadMessageById;
	}
	public String getPushMessage() {
		return pushMessage;
	}
	public void setPushMessage(String pushMessage) {
		this.pushMessage = pushMessage;
	}
	public String getPushSysMessage() {
		return pushSysMessage;
	}
	public void setPushSysMessage(String pushSysMessage) {
		this.pushSysMessage = pushSysMessage;
	}
	public String getGenerateCode() {
		return generateCode;
	}
	public void setGenerateCode(String generateCode) {
		this.generateCode = generateCode;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getGenerateImageCode() {
		return generateImageCode;
	}
	public void setGenerateImageCode(String generateImageCode) {
		this.generateImageCode = generateImageCode;
	}
	public String getCheckImageCode() {
		return checkImageCode;
	}
	public void setCheckImageCode(String checkImageCode) {
		this.checkImageCode = checkImageCode;
	}
	public String getGetLeftMsg() {
		return getLeftMsg;
	}
	public void setGetLeftMsg(String getLeftMsg) {
		this.getLeftMsg = getLeftMsg;
	}
	public String getSendMsg() {
		return sendMsg;
	}
	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}
	public String getGetSendStatus() {
		return getSendStatus;
	}
	public void setGetSendStatus(String getSendStatus) {
		this.getSendStatus = getSendStatus;
	}
}
