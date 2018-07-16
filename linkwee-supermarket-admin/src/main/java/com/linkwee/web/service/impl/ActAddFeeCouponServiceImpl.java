package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActAddFeeCoupon;
import com.linkwee.web.dao.ActAddFeeCouponMapper;
import com.linkwee.web.request.act.AddFeeCouponInfoRequest;
import com.linkwee.web.response.act.AddFeeCouponResponse;
import com.linkwee.web.response.act.RedpacketListResponse;
import com.linkwee.web.service.ActAddFeeCouponService;
import com.linkwee.web.service.impl.ActAddFeeCouponServiceImpl;
import com.linkwee.xoss.rbac.PermissionSign;


 /**
 * 
 * @描述：ActAddFeeCouponService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月20日 17:11:30
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actAddFeeCouponService")
public class ActAddFeeCouponServiceImpl extends GenericServiceImpl<ActAddFeeCoupon, Long> implements ActAddFeeCouponService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActAddFeeCouponServiceImpl.class);
	
	@Resource
	private ActAddFeeCouponMapper actAddFeeCouponMapper;
	
	@Override
    public GenericDao<ActAddFeeCoupon, Long> getDao() {
        return actAddFeeCouponMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActAddFeeCoupon -- 排序和模糊查询 ");
		Page<ActAddFeeCoupon> page = new Page<ActAddFeeCoupon>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActAddFeeCoupon> list = this.actAddFeeCouponMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn getAddFeeCouponList(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		Page<AddFeeCouponResponse> page = new Page<AddFeeCouponResponse>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AddFeeCouponResponse> list = actAddFeeCouponMapper.getAddFeeCouponList(page);
		
		Subject currentUser = SecurityUtils.getSubject();
		String addFeeEditPermission = "0";
		if(currentUser.isPermitted(PermissionSign.ADDFEECOUPON_EDIT)) {
			addFeeEditPermission = "1";
		}
		for(AddFeeCouponResponse temp : list){			
			temp.setAddFeeEditPermission(addFeeEditPermission);
		}	
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void insertAddFeeCoupon(AddFeeCouponInfoRequest addFeeCouponInfo) {
		ActAddFeeCoupon addFeeCoupon = new ActAddFeeCoupon();
		BeanUtils.copyProperties(addFeeCouponInfo, addFeeCoupon);
		addFeeCoupon.setCouponId(StringUtils.getUUID());
		addFeeCoupon.setCreateTime(new Date());
		addFeeCoupon.setUpdateTime(new Date());
		insert(addFeeCoupon);
	}

	@Override
	public void updateAddFeeCoupon(AddFeeCouponInfoRequest addFeeCouponInfo) {
		ActAddFeeCoupon temp = new ActAddFeeCoupon();
		temp.setCouponId(addFeeCouponInfo.getCouponId());
		temp = selectOne(temp);
		ActAddFeeCoupon addFeeCoupon = new ActAddFeeCoupon();
		BeanUtils.copyProperties(addFeeCouponInfo, addFeeCoupon);
		addFeeCoupon.setUpdateTime(new Date());
		addFeeCoupon.setId(temp.getId());
		update(addFeeCoupon);
	}

}
