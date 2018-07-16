package com.linkwee.web.service.impl;

import com.linkwee.core.constant.Constants;
import com.linkwee.web.service.BankCardTCService;
import com.linkwee.web.service.SystemConfigService;
import com.xiaoniu.account.domain.BankListReq;
import com.xiaoniu.account.domain.SearchCardInfoReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.service.IPrepare2PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by lenli on 2016/5/25.
 *
 * @Author Libin
 * @Date 2016/5/25
 */
@Service(value ="bankCardTCService" )
public class BankCardTCServiceImpl implements BankCardTCService {

    private static Logger logger = LoggerFactory.getLogger(BankCardTCServiceImpl.class);

    @Resource
    private IPrepare2PayService p2pPrepare2PayService;

    @Resource
    private SystemConfigService systemConfigService;

    @Override
    public Object queryBankList() throws Exception {
        BankListReq bankListReq = new BankListReq();
        bankListReq.setCharset("UTF-8");
        bankListReq.setPartnerId(systemConfigService.getValuesByKey(Constants.account_partnerId));
        bankListReq.setSign(com.xiaoniu.account.utils.SignUtils.addSign(bankListReq,bankListReq.getCharset(),systemConfigService.getValuesByKey(Constants.TRANS_MD5_SIGN_KEY)));
        CommonRlt<Object> commonRlt =  p2pPrepare2PayService.queryBankList(bankListReq);
        return commonRlt.getData();
    }

    /**
     * 根据客户ID查银行卡信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Object findBankCardInfoByUserId(String userId) throws Exception {

        String provider = "kuai_qian";
        String charset = "UTF-8";
        SearchCardInfoReq searchCardInfoReq = new SearchCardInfoReq();
        searchCardInfoReq.setPartnerId(systemConfigService.getValuesByKey(Constants.account_partnerId));
        searchCardInfoReq.setUserId(userId);
        searchCardInfoReq.setUserPayAccount(null);
        searchCardInfoReq.setProvider(provider);
        searchCardInfoReq.setCharset(charset);
        CommonRlt<Map<String, Object>> result = p2pPrepare2PayService.searchCardInfo(searchCardInfoReq);
        return null;
    }
}
