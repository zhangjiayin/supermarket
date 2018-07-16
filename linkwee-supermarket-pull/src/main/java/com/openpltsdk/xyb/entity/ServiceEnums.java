package com.openpltsdk.xyb.entity;

/**
 * 
 * 项目名称：xyb-third4toobei-sdk
 * 类名称：ServiceEnums
 * 类描述：service枚举
 * @author zenggang
 * @date 创建时间：2016年11月14日 下午5:20:07
 * 修改人：zenggang
 * 修改时间：2016年11月14日 下午5:20:07
 * 修改备注：
 * @version V2.1.9
 * 
 */
public enum ServiceEnums
{
    createUser(1,"createUser"),
    queryBids(2,"queryBids"),
    queryUser(3,"queryUser"),
    queryInvests(4,"queryInvests"),
    queryRepays(5,"queryRepays"),
    bindUser(6,"bindUser"),
    login(7,"login"),
    queryBidInfo(8,"queryBidInfo"),
    querySyS(9,"querySyS"),;
    
    private String serviceName;
    private int code;
    
    
    ServiceEnums(int code,String serviceName){
        this.code = code;
        this.serviceName = serviceName;
    }
    
    public String getServiceName()
    {
        return serviceName;
    }
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
}
