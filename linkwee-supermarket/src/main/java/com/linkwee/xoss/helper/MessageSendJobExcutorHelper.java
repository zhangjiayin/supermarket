package com.linkwee.xoss.helper;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.impl.MainExecutor;
import com.linkwee.web.service.task.TaskSendMessage;

//@Component
public class MessageSendJobExcutorHelper {

	
//@Resource
private SysConfigService sysConfigService;
	


	//@PostConstruct
	public void init() throws Exception {
		
		//MainExecutor.addAddSendMsgTask(sysConfigService);
		//TaskSendMessage task = new TaskSendMessage(sysConfigService);
		MainExecutor mainExecutor = new MainExecutor();
		mainExecutor.scheduledExecutionTime();
		//MainExecutor.execute(new MessagePrinterTask("Message" + i));  
	}

	
}
