package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.Advertisement;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;

/**
*
* @描述： Dao接口
*
* @创建人： Bob
*
* @创建时间：2015年10月17日 15:18:02
*
* Copyright (c) 深圳市小牛科技有限公司-版权所有
*/
public interface AdvertisementDao extends BasePageDao<Advertisement>{

   public PageList<Advertisement> query(PageRequest req);
   /**
    * 新版本广告列表
    * @param newsRequest
    * @param bounds
    * @return
    * @throws Exception
     */
   public List<Advertisement> findAdvList( Advertisement pageRequest , RowBounds bounds) throws Exception;
}
