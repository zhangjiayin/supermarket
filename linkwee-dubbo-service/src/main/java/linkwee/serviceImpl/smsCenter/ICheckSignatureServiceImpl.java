package linkwee.serviceImpl.smsCenter;

import java.util.TreeMap;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.sms.service.ICheckSignatureService;
import com.xiaoniu.sms.util.SmsResult;

@Service(version="1.0",group="p2p")
public class ICheckSignatureServiceImpl implements ICheckSignatureService {

	@Override
	public SmsResult<Object> checkSignature(TreeMap<String, String> paramTreeMap, String partnerId,String signature) {
		// TODO Auto-generated method stub
		// 技术平台内部使用
		return null;
	}

}
