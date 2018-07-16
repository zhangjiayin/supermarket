package com.linkwee.web.dao;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActSignRecord;

/**
*
* @描述： Dao接口
*
* @创建人： Mignet
*
* @创建时间：2017年11月13日 16:59:18
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface ActSignRecordMapper extends GenericDao<ActSignRecord,Long>{

    /**
     *
     * @param signRecord
     * @return
     */
    ActSignRecord todaySign(ActSignRecord signRecord);

}
