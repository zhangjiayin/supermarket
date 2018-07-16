package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.request.CfpCommonRequest;

/**
 * Created by lenli on 2016/6/7.
 *
 * @Author Libin
 * @Date 2016/6/7
 */
public interface LogService {
    /**
     * 分页查询帐户操作日志表
     * @param cfpCommonRequest
     * @return
     */
    public DataTableReturn queryAccountOpLogList(CfpCommonRequest cfpCommonRequest);
}
