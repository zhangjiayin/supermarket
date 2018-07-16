package com.openpltsdk.xyb.entity;

public class Index
{
    /**
     * name:索引名字
     * @author zenggang
     * @date 创建时间：2017年1月10日 下午3:26:18
     * @version V1.3.1
     */
    private String name;
    /**
     * vals:索引查询
     * @author zenggang
     * @date 创建时间：2017年1月10日 下午3:26:00
     * @version V1.3.1
     */
    private String[] vals;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String[] getVals()
    {
        return vals;
    }

    public void setVals(String[] vals)
    {
        this.vals = vals;
    }
}