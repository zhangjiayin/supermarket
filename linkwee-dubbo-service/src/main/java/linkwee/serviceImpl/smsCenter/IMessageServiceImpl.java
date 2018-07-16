package linkwee.serviceImpl.smsCenter;

import java.util.List;

import javax.annotation.Resource;

import linkwee.config.SmsCenterConfig;
import linkwee.helper.CommonHelper;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.sms.domain.SpreadMessageRlt;
import com.xiaoniu.sms.domain.SystemMessageRlt;
import com.xiaoniu.sms.req.DeleteSystemMessageReq;
import com.xiaoniu.sms.req.GetSpreadMessageReq;
import com.xiaoniu.sms.req.GetSystemMessageReq;
import com.xiaoniu.sms.req.GetUnreadCountReq;
import com.xiaoniu.sms.req.SpreadMessageListReq;
import com.xiaoniu.sms.req.SystemMessageListReq;
import com.xiaoniu.sms.req.UpdateSystemMessageReq;
import com.xiaoniu.sms.service.IMessageService;
import com.xiaoniu.sms.util.DataGridResult;
import com.xiaoniu.sms.util.SmsResult;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IMessageServiceImpl implements IMessageService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private SmsCenterConfig smsCenterConfig;

	@Override
	public SmsResult<Object> updateSystemMessage(UpdateSystemMessageReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getUpdateSystemMessage(),new SmsResult<Object>().getClass(),req);
	}

	@Override
	public SmsResult<Object> deleteSystemMessage(DeleteSystemMessageReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getDeleteSystemMessage(),new SmsResult<Object>().getClass(),req);
	}

	@Override
	public DataGridResult<List<SystemMessageRlt>> getSystemMessageList(SystemMessageListReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getGetSystemMessageList(),new DataGridResult<List<SystemMessageRlt>>().getClass(),req);
	}

	@Override
	public SmsResult<SystemMessageRlt> getSystemMessageById(GetSystemMessageReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getGetSystemMessageById(),new SmsResult<SystemMessageRlt>().getClass(),req);
	}

	@Override
	public SmsResult<Integer> getUnreadCount(GetUnreadCountReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getGetUnreadCount(),new SmsResult<Integer>().getClass(),req);
	}

	@Override
	public DataGridResult<List<SpreadMessageRlt>> getSpreadMessageList(SpreadMessageListReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getGetSpreadMessageList(),new DataGridResult<List<SpreadMessageRlt>>().getClass(),req);
	}

	@Override
	public SmsResult<SpreadMessageRlt> getSpreadMessageById(GetSpreadMessageReq req) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getGetSpreadMessageById(),new SmsResult<SpreadMessageRlt>().getClass(),req);
	}

}
