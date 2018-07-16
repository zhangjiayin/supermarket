package com.linkwee.web.response.act;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class AddfeeTicketListResponse extends BaseEntity {

    /**
     * 来源
     */
    private String source;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 加拥比率
     */
    private BigDecimal rate;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 加拥券ID
     */
    private String ticketId;
    /**
     * 加拥券编辑权限
     */
    private String addfeeTicketEditPermission;

    public String getAddfeeTicketEditPermission() {
        return addfeeTicketEditPermission;
    }

    public void setAddfeeTicketEditPermission(String addfeeTicketEditPermission) {
        this.addfeeTicketEditPermission = addfeeTicketEditPermission;
    }

    /**
     * 加拥券发放权限
     */

    private String addfeeTicketSendPermission;
    /**
     * 加拥券名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getAddfeeTicketSendPermission() {
        return addfeeTicketSendPermission;
    }

    public void setAddfeeTicketSendPermission(String addfeeTicketSendPermission) {
        this.addfeeTicketSendPermission = addfeeTicketSendPermission;
    }
}
