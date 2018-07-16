package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.fee.FeeDetail;

/**
* Title: FeeDetailDao
* Description: 佣金明细Dao
* Company: Copyright (c) 深圳市前海领会科技有限公司-版权所有
* @author jinbo.fu
* @date 2016年5月25日下午5:37:04
 */
public interface FeeDetailDao extends BasePageDao<FeeDetail> {
	public List<FeeDetail> queryFeeDetail(FeeDetail feeDetailEntity,RowBounds rowBounds);
}
