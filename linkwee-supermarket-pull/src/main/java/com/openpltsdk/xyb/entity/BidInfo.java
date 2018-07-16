package com.openpltsdk.xyb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BidInfo {
	private String id;                                  //标的ID
	private String url;									//标的URL
	private String title;								//标题
	private String desc;								//描述
	private String borrower;							//借款人
	private BigDecimal borrowAmount;					//借款金额
	private BigDecimal remainAmount;					//剩余金额
	private BigDecimal minInvestAmount;					//起投金额
	private String period;								//借款期限, 1d, 1m，如果为活期该字段为0
	private BigDecimal originalRate;						//原始年化利率，13.5表示13.5%
	private String rewardRate;						//奖励利率，13.5表示13.5%
	
	//0还款中 1已还清 2	逾期 3	投标中 4	流标
	//5	撤标 6	满标 7	放款 8	等待放款 9	等待开始 99	其他
	private int status;									//标的状态
	
	private String repayment;							//还款方式//

	//////0:普通标（固定期限，如10天，15天，1个月）
	/////1:转让标 2:	净值标 10:活期 101新手标（新手标也是一个普通的固定期限标，仅限新手投资）
	/////102 体验金标  1000	其他（如：秒还标）
	private int type;									//标的类型
	
	private String prop;								//标的性质,比如:车贷，房贷，信用贷、融资租赁、保理等等
	private Date createAt;								//标的创建时间
	private Date publishAt;								//标的起投时间，如果有倒计时，这个时间会晚于标的创建时间
	private Date closeAt;								//标的截止购买时间
	private Object fullAt;								//标的满标时间
	private String repayDate;							//预计还款日期
	private String[] tags;								//标签，数组，用以扩充标的属性。如：标的活动信息
	private BigDecimal buyIncreaseMoney;                //购买递增金额.最多两位小数,必须大于等于0,不能为空
	private BigDecimal buyMaxMoney;                     //产品单笔购买最大额度
	/**
     *产品已投资人数
     */
    //@NotNull(message="产品已投资人数不能为空")
    //@Min(value=0, message="产品已投资人数必须大于等于0")
    private Integer buyedTotalPeople;
    
    /**
     *募集开始时间|格式yyyy-mm-ddhh:mm:ss
     */ 
    private String collectBeginTime;
    
    /**
     *募集截止时间可以为nul格式yyyy-mm-ddhh:mm:ss
     */ 
    private String collectEndTime;
    
    /**
     *起息方式(1=购买当日|2=购买次日|3=募集完成当日|4=募集完成次日|5=指定日期)
     */
    //@NotNull(message="起息方式不能为空")
    //@Range(min=1,max=5,message="起息方式必需在1-5之间")
    private Integer interestWay;
    
    /**
     *是否需要募集开始及截止时间(1=不需要|2=需要)
     */
    //@NotNull(message="是否需要募集开始及截止时间不能为空")
    //@Range(min=1,max=2,message="是否需要募集开始及截止时间必需在1-2之间")
    private Integer isCollect;
    
    /**
     *是否拥有产品进度(0=有|1没有)
     */
    //@NotNull(message="是否拥有产品进度不能为空")
    //@Range(min=0,max=1,message="是否拥有产品进度必需在0-1之间")
    private Integer isHaveProgress;
    
    /**
     *'是否限额产品。1-限额、2-不限额'
     */
    //@NotNull(message="是否限额产品不能为空")
    //@Range(min=1,max=2,message="是否限额产品必需在1-2之间")
    private Integer isQuota = 2;
    
    /**
     *是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让|3=可赎回且可转让)
     */
    //@NotNull(message="是否可赎回不能为空")
    //@Range(min=0,max=3,message="是否可赎回可转让必需在0-3之间")
    private Integer isRedemption;
    
    /**
     *货币类型(1=rmb|2=港币|3=美元)
     */  
    private Integer moneyType = 1;
    
    /**
     *产品类型(1=P2P|2=信托 |3=资管|4=基金|401=公募基金|402=阳光私募|403=股权基金|5=保险|6=众筹|999=其他)
     */
    //@NotNull(message="产品类型不能为空")
    private Integer productType;
    
    /**
     *风控类型(1=抵押|2=担保|3=信贷)
     */
    private Integer riskControlType;
    
    /**
     *风险级别(1=一般|2=重要|3=紧急|4=非常紧急)
     */
    private Integer riskLevel = 1;
    
    /**
     *产品起息日格式yyyy-mm-dd
     */
    private String validBeginDate;
    
    /**
     *加息利率
     */
    private BigDecimal addRate;
    
    /**
     *可赎回天数
     */
    private Integer redemptionTime;
    
    /**
     *可转让天数
     */
    private Integer assignmentTime;
    
    /**
     *是否是新手标(1=新手标|2=非新手标)
     */
    private Integer ifRookie = 2;
    
	public BigDecimal getBuyIncreaseMoney()
    {
        return buyIncreaseMoney;
    }
    public void setBuyIncreaseMoney(BigDecimal buyIncreaseMoney)
    {
        this.buyIncreaseMoney = buyIncreaseMoney;
    }
    public BigDecimal getBuyMaxMoney()
    {
        return buyMaxMoney;
    }
    public void setBuyMaxMoney(BigDecimal buyMaxMoney)
    {
        this.buyMaxMoney = buyMaxMoney;
    }
    public Integer getBuyedTotalPeople()
    {
        return buyedTotalPeople;
    }
    public void setBuyedTotalPeople(Integer buyedTotalPeople)
    {
        this.buyedTotalPeople = buyedTotalPeople;
    }
    public String getCollectBeginTime()
    {
        return collectBeginTime;
    }
    public void setCollectBeginTime(String collectBeginTime)
    {
        this.collectBeginTime = collectBeginTime;
    }
    public String getCollectEndTime()
    {
        return collectEndTime;
    }
    public void setCollectEndTime(String collectEndTime)
    {
        this.collectEndTime = collectEndTime;
    }
    public Integer getInterestWay()
    {
        return interestWay;
    }
    public void setInterestWay(Integer interestWay)
    {
        this.interestWay = interestWay;
    }
    public Integer getIsCollect()
    {
        return isCollect;
    }
    public void setIsCollect(Integer isCollect)
    {
        this.isCollect = isCollect;
    }
    public Integer getIsHaveProgress()
    {
        return isHaveProgress;
    }
    public void setIsHaveProgress(Integer isHaveProgress)
    {
        this.isHaveProgress = isHaveProgress;
    }
    public Integer getIsQuota()
    {
        return isQuota;
    }
    public void setIsQuota(Integer isQuota)
    {
        this.isQuota = isQuota;
    }
    public Integer getIsRedemption()
    {
        return isRedemption;
    }
    public void setIsRedemption(Integer isRedemption)
    {
        this.isRedemption = isRedemption;
    }
    public Integer getMoneyType()
    {
        return moneyType;
    }
    public void setMoneyType(Integer moneyType)
    {
        this.moneyType = moneyType;
    }
    public Integer getProductType()
    {
        return productType;
    }
    public void setProductType(Integer productType)
    {
        this.productType = productType;
    }
    public Integer getRiskControlType()
    {
        return riskControlType;
    }
    public void setRiskControlType(Integer riskControlType)
    {
        this.riskControlType = riskControlType;
    }
    public Integer getRiskLevel()
    {
        return riskLevel;
    }
    public void setRiskLevel(Integer riskLevel)
    {
        this.riskLevel = riskLevel;
    }
    public String getValidBeginDate()
    {
        return validBeginDate;
    }
    public void setValidBeginDate(String validBeginDate)
    {
        this.validBeginDate = validBeginDate;
    }
    public BigDecimal getAddRate()
    {
        return addRate;
    }
    public void setAddRate(BigDecimal addRate)
    {
        this.addRate = addRate;
    }
    public Integer getRedemptionTime()
    {
        return redemptionTime;
    }
    public void setRedemptionTime(Integer redemptionTime)
    {
        this.redemptionTime = redemptionTime;
    }
    public Integer getAssignmentTime()
    {
        return assignmentTime;
    }
    public void setAssignmentTime(Integer assignmentTime)
    {
        this.assignmentTime = assignmentTime;
    }
    public Integer getIfRookie()
    {
        return ifRookie;
    }
    public void setIfRookie(Integer ifRookie)
    {
        this.ifRookie = ifRookie;
    }
    public String getProp() {
		return prop;
	}
	public void setProp(String prop) {
		this.prop = prop;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public BigDecimal getBorrowAmount() {
		return borrowAmount;
	}
	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}
	public BigDecimal getRemainAmount() {
		return remainAmount;
	}
	public void setRemainAmount(BigDecimal remainAmount) {
		this.remainAmount = remainAmount;
	}
	public BigDecimal getMinInvestAmount() {
		return minInvestAmount;
	}
	public void setMinInvestAmount(BigDecimal minInvestAmount) {
		this.minInvestAmount = minInvestAmount;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public BigDecimal getOriginalRate() {
		return originalRate;
	}
	public void setOriginalRate(BigDecimal originalRate) {
		this.originalRate = originalRate;
	}
	public String getRewardRate() {
		return rewardRate;
	}
	public void setRewardRate(String rewardRate) {
		this.rewardRate = rewardRate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRepayment() {
		return repayment;
	}
	public void setRepayment(String repayment) {
		this.repayment = repayment;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getPublishAt() {
		return publishAt;
	}
	public void setPublishAt(Date publishAt) {
		this.publishAt = publishAt;
	}
	public Date getCloseAt() {
		return closeAt;
	}
	public void setCloseAt(Date closeAt) {
		this.closeAt = closeAt;
	}
	public Object getFullAt() {
		return fullAt;
	}
	public void setFullAt(Object fullAt) {
		this.fullAt = fullAt;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
}
