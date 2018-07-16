package linkwee.serviceImpl.smsCenter;

import java.util.HashMap;

import javax.annotation.Resource;

import linkwee.config.SmsCenterConfig;
import linkwee.helper.CommonHelper;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.sms.domain.MessageTemplateRlt;
import com.xiaoniu.sms.req.GetMessageTemplateReq;
import com.xiaoniu.sms.req.GetUnreadCountReq;
import com.xiaoniu.sms.req.UpdateSpreadMessageReq;
import com.xiaoniu.sms.service.IMessageExtService;
import com.xiaoniu.sms.util.SmsResult;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IMessageExtServiceImpl implements IMessageExtService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private SmsCenterConfig smsCenterConfig;

	@Override
	public SmsResult<Boolean> updateSpreadMessage(UpdateSpreadMessageReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getUpdateSpreadMessage(),new SmsResult<Boolean>().getClass(),req);
	}

	@Override
	public SmsResult<HashMap<String, Integer>> getUnreadCount(GetUnreadCountReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getGetUnreadCountEx(),new SmsResult<HashMap<String, Integer>>().getClass(),req);
	}

	@Override
	public SmsResult<MessageTemplateRlt> getMessageTemplate(GetMessageTemplateReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getGetMessageTemplate(),new SmsResult<MessageTemplateRlt>().getClass(),req);
	}

}
