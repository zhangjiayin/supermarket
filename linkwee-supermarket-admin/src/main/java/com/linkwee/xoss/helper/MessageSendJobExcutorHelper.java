package com.linkwee.xoss.helper;

import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.impl.MainExecutor;

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
