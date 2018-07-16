package linkwee.serviceImpl.smsCenter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import linkwee.config.SmsCenterConfig;
import linkwee.helper.CommonHelper;
import linkwee.utils.CommonUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.sms.domain.SmsRecordRlt;
import com.xiaoniu.sms.req.GetSendStatusReq;
import com.xiaoniu.sms.req.SendMsgReq;
import com.xiaoniu.sms.service.ISmsService;
import com.xiaoniu.sms.util.DataGridResult;
import com.xiaoniu.sms.util.SmsResult;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class ISmsServiceImpl implements ISmsService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private SmsCenterConfig smsCenterConfig;

	@Override
	public SmsResult<Object> sendMsg(SendMsgReq param) throws Exception {
		return commonHelper.smsCenterHttpRequest(smsCenterConfig.getSendMsg(),new SmsResult<Object>().getClass(),param);
	}

	@Override
	public DataGridResult<List<SmsRecordRlt>> getSendStatus(GetSendStatusReq req) {
		DataGridResult<List<SmsRecordRlt>> commonRltLoginRlt = new DataGridResult<List<SmsRecordRlt>>();
		commonRltLoginRlt = commonHelper.smsCenterHttpRequest(smsCenterConfig.getGetSendStatus(),new DataGridResult<List<SmsRecordRlt>>().getClass(),req);
		List<SmsRecordRlt> dataList = new ArrayList<SmsRecordRlt>();
		dataList = JSONObject.parseArray(JSONObject.toJSONString(commonRltLoginRlt.getData()),SmsRecordRlt.class);
		commonRltLoginRlt.setData(dataList);
		CommonUtils.checkSmsCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

}
