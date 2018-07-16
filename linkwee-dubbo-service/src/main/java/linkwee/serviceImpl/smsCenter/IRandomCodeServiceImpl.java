package linkwee.serviceImpl.smsCenter;

import javax.annotation.Resource;

import linkwee.config.SmsCenterConfig;
import linkwee.helper.CommonHelper;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.sms.req.CheckCodeReq;
import com.xiaoniu.sms.req.GenerateCodeReq;
import com.xiaoniu.sms.service.IRandomCodeService;
import com.xiaoniu.sms.util.SmsResult;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IRandomCodeServiceImpl implements IRandomCodeService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private SmsCenterConfig smsCenterConfig;

	@Override
	public SmsResult<String> generateCode(GenerateCodeReq genCodeDto) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getGenerateCode(),new SmsResult<String>().getClass(),genCodeDto);
	}

	@Override
	public SmsResult<Object> checkCode(CheckCodeReq checkCodeDto) {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getCheckCode(),new SmsResult<Object>().getClass(),checkCodeDto);
	}

}
