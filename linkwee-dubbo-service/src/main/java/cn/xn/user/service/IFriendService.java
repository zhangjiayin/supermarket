/*
 * 文件名: IFriendService.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package cn.xn.user.service;

import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.FriendInfoRlt;
import cn.xn.user.domain.GetFriendReq;
import cn.xn.user.domain.GetSecretReq;
import cn.xn.user.domain.Page;
import cn.xn.user.domain.SearchFriendReq;
import cn.xn.user.domain.UpdateFriendReq;
import cn.xn.user.domain.UpdateSecretReq;

/**
 *
 * @类描述：好友信息服务
 *
 * @创建人：luohao
 *
 * @创建时间：2016年8月3日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public interface IFriendService {
    
    /**
     * 同步通讯录（更新好友）
     * @param req
     * @return
     */
    public CommonRlt<Boolean> updateFriend(UpdateFriendReq req);
    
    
    /**
     * 获取好友列表
     * @param req
     * @return
     */
    public CommonRlt<Page<FriendInfoRlt>> getFriendList(GetFriendReq req);
    
    
    /**
     * 获取所有好友列表
     * @param req
     * @return
     */
    public CommonRlt<Page<FriendInfoRlt>> getAllFriend(GetFriendReq req);
    
    
    /**
     * 获取个人匿名设置
     * @param req
     * @return
     */
    public CommonRlt<String> getSecret(GetSecretReq req);
    
    
    /**
     * 更新匿名设置
     * @param req
     * @return
     */
    public CommonRlt<Boolean> updateSecret(UpdateSecretReq req);
    
    
    /**
     * 搜索好友信息
     * @param req
     * @return
     */
    public CommonRlt<Page<FriendInfoRlt>> searchFriend(SearchFriendReq req);
    
}
