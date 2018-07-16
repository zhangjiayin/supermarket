package linkwee.serviceImpl.smsCenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import linkwee.config.SmsCenterConfig;
import linkwee.helper.CommonHelper;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.sms.req.SmsStatusReq;
import com.xiaoniu.sms.service.ISmsExtendService;
import com.xiaoniu.sms.util.SmsResult;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class ISmsExtendServiceImpl implements ISmsExtendService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private SmsCenterConfig smsCenterConfig;
	
	@Override
	public SmsResult<List<String>> getMsgDeliver(SmsStatusReq param,String partnerId) throws Exception {
		//技术平台内部使用
		return null;
	}

	@Override
	public SmsResult<String> getLeftMsg(String partnerId) throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("partnerId", partnerId);
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getGetLeftMsg(),new SmsResult<String>().getClass(),partnerId);
	}

}
