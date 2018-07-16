package com.linkwee.api.request.mc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.validation.constraints.NotNull;

public class CustomerSignPushRequest {
    /**
     * 是否开启推送签到消息：0-不开启 1-开启
     */
    @NotNull(message = "是否开启推送签到消息状态不能为空")
    private Integer signPushStatus;

    public Integer getSignPushStatus() {
        return signPushStatus;
    }

    public void setSignPushStatus(Integer signPushStatus) {
        this.signPushStatus = signPushStatus;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
    }
}
