package com.linkwee.web.service;

/**
 * Created by lenli on 2016/5/25.
 *操作技术平台与银行卡相关信息
 * @Author Libin
 * @Date 2016/5/25
 */
public interface BankCardTCService {


    /**
     * 从技术平台获取银行列表
     * @return
     * @throws Exception
     */
    public Object queryBankList() throws Exception;

    /**
     * 根据客户ID查银行卡信息
     * @param userId
     * @return
     * @throws Exception
     */
    public Object findBankCardInfoByUserId(String userId) throws Exception;

}
