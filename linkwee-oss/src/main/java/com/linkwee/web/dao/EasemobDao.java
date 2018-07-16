package com.linkwee.web.dao;

import java.util.HashMap;
import java.util.List;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.Easemob;

 /**
 * 
 * @描述：环信 DAO接口
 * 
 * @创建人： ZhongLing
 * 
 * @创建时间：2015年12月17日 09:37:08
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface EasemobDao extends BasePageDao<Easemob>{
	
	/**
	 * 根据用户id修改
	 * @Auther ZhongLing
	 * @date 2015年12月17日 下午8:03:16
	 * @param object
	 * @return
	 */
	public void updateByCustomerId(Easemob object);
	
	/**
	 * 根据customerId查环信
	 * @Auther ZhongLing
	 * @date 2015年12月18日 下午2:39:09
	 * @param customerId
	 * @return
	 */
	public Easemob queryByCustomerId(String customerId);

	/**
	 * 更新环信管理员token
	 * @Auther ZhongLing
	 * @date 2015年12月18日 下午4:29:58
	 * @param object
	 */
	public void updateEaseToken(String token);
	
	/**
	 * 查环信管理员token
	 * @Auther ZhongLing
	 * @date 2015年12月18日 下午5:15:12
	 * @return
	 */
	public HashMap<String,Object> queryEaseToken();

	/**
	 * 查没有环信数据的用户id（30个）
	 * @Auther ZhongLing
	 * @date 2015年12月21日 下午9:02:17
	 * @return
	 */
	public List<HashMap<String ,String>> query40CustomerIdList();
	
	/**
	 * 批量修改为成功状态
	 * @Auther ZhongLing
	 * @Date 2015年12月23日 上午10:21:50
	 * @param customerId
	 * @return
	 */
	public Integer updateStatusBatch(List<String> customerId);

	/**
	 * 查没有环信数据或注册失败的用户id
	 * @Auther ZhongLing
	 * @Date 2015年12月23日 下午5:04:55
	 * @return
	 */
	public List<HashMap<String ,String>> queryNotRegUserIds();
	
	/**
	 *  查为设置昵称用户
	 *  未设置昵称，已注册环信，已实名认证
	 * @Auther ZhongLing
	 * @Date 2015年12月24日 下午5:32:36
	 * @return
	 */
	public List<HashMap<String ,String>> queryNotModifyNick();
	
	
}
