package com.linkwee.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.jackson.MoneySerializer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class HomePageInvestResponse extends BaseEntity {

    /**
     * 名称
     */
    private String userName;

    /**
     * 金额
     */
    @JsonSerialize(using=MoneySerializer.class)
    private BigDecimal amount;

    /**
     * 收益
     */
    @JsonSerialize(using=MoneySerializer.class)
    private BigDecimal profit;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    /**
     * 时间描述
     */
    private String timeDesc;

    public String getUserName() {
    	String userNameTemp = userName;
        String[] bai={
                "万俟","司马","上官","欧阳","夏侯",
                "诸葛","闻人","东方","赫连","皇甫",
                "尉迟","公羊","澹台","公冶","宗政",
                "濮阳","淳于","单于","太叔","申屠",
                "公孙","仲孙","轩辕","令狐","锺离",
                "宇文","长孙","慕容","鲜于","闾丘",
                "司徒","司空","丌官","司寇","南宫",
                "子车","颛孙","端木","巫马","公西",
                "漆雕","乐正","壤驷","公良","拓拔",
                "夹谷","宰父","谷梁","段干","百里",
                "东郭","南门","呼延","羊舌","微生",
                "梁丘","左丘","东门","西门"
        };
        String name1=userNameTemp.substring(0,2);
        boolean flag = false;
        for(int i=0;i<bai.length;i++){
            if(name1.equals(bai[i])){
                flag=true;
                break;
            }else{
                flag=false;
            }
        }
        if(flag){
        	userNameTemp = userNameTemp.substring(0,2);
        }else{
        	userNameTemp = userNameTemp.substring(0,1);
        }
        String sex;
        if (Integer.parseInt(idCard.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "女士";
        } else {
            sex = "先生";
        }
        return userNameTemp + sex;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTimeDesc() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createTime);
        int minute = calendar.get(Calendar.MINUTE);
        if(minute == 0){
            int second = calendar.get(Calendar.SECOND);
            return second+"秒前";
        }else {
            return minute+"分钟前";
        }
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }

}
