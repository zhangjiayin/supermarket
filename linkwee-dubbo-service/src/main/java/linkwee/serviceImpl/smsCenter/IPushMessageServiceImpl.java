package linkwee.serviceImpl.smsCenter;

import javax.annotation.Resource;

import linkwee.config.SmsCenterConfig;
import linkwee.helper.CommonHelper;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.sms.req.PushAppMessageReq;
import com.xiaoniu.sms.req.PushMessageReq;
import com.xiaoniu.sms.req.PushSysMessageReq;
import com.xiaoniu.sms.service.IPushMessageService;
import com.xiaoniu.sms.util.SmsResult;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IPushMessageServiceImpl implements IPushMessageService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private SmsCenterConfig smsCenterConfig;

	@Override
	public SmsResult<Object> pushMessage(PushMessageReq pushMessageDto) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getPushMessage(),new SmsResult<Object>().getClass(),pushMessageDto);
	}

	@Override
	public SmsResult<Boolean> pushAppMessage(PushAppMessageReq req) {
		//暂时无人调用
		return null;
	}

	@Override
	public SmsResult<Boolean> pushSysMessage(PushSysMessageReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getPushSysMessage(),new SmsResult<Boolean>().getClass(),req);
	}

}
