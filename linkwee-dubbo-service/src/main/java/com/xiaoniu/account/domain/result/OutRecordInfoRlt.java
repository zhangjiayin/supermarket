/*************************************************************************
 *                  XIAONIU66 CONFIDENTIAL AND PROPRIETARY                                                      
 *
 *                COPYRIGHT (C) XIAONIU66 CORPORATION 2016                                                      
 *    ALL RIGHTS RESERVED BY XIAONIU66 CORPORATION. THIS PROGRAM    
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY   
 * XIAONIU66 CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN       
 * PERMISSION OF XIAONIU66 CORPORATION. USE OF COPYRIGHT NOTICE   
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.                       
 *                  XIAONIU66 CONFIDENTIAL AND PROPRIETARY        
 *************************************************************************/

package com.xiaoniu.account.domain.result;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 提现结果对象.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/10/25 11:55
 */
public class OutRecordInfoRlt implements Serializable {

    private static final long serialVersionUID = -4456973652363100824L;

    /** 提现流水号 */
    private Long outRecordNo;

    /** 业务ID */
    private String partnerId;

    /** 用户ID */
    private String userId;

    /** 用户名称 */
    private String userName;

    /** 用户身份证号码 */
    private String identityCard;

    /** 名称 */
    private String bisName;

    /** 提现时间 */
    private Date bisTime;

    /** 审核时间 */
    private Date confirmTime;

    /** 通知时间 */
    private Date noticeTime;

    /** 交易总金额，值等于 提现金额+手续费 */
    private Long totalAmount = 0l;

    /** 提值金额 */
    private Long amount = 0l;

    /** 手续费 */
    private Long fee = 0l;

    /** 提值状态 */
    private String status;

    /** 银行编号 */
    private String bankCode;

    /** 银行名称 */
    private String bankName;

    /** 城市 */
    private String city;

    /** 开户行 */
    private String kaiHuHang;

    /** 支付流水号 */
    private String payNo;

    /** 备注 */
    private String remark;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public Long getOutRecordNo() {
        return outRecordNo;
    }

    public void setOutRecordNo(Long outRecordNo) {
        this.outRecordNo = outRecordNo;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getBisName() {
        return bisName;
    }

    public void setBisName(String bisName) {
        this.bisName = bisName;
    }

    public Date getBisTime() {
        return bisTime;
    }

    public void setBisTime(Date bisTime) {
        this.bisTime = bisTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKaiHuHang() {
        return kaiHuHang;
    }

    public void setKaiHuHang(String kaiHuHang) {
        this.kaiHuHang = kaiHuHang;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
