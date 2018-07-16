package com.linkwee.web.response;

import java.util.Date;

/**
 * Created by Mignet on 2016/6/2.
 *理财师-团队列表详情
 * @Author Libin
 * @Date 2016/6/2
 */
public class CfpTeamListResp {

    /**
     * 理财师ID
     */
    private String number;

    private String mobile;
    private String name;
    private String parentId;
    private String createTime;
    private String level;
    /**
     * 理财师总的销售额
     */
    private Double totalAmount;
    /**
     * 理财师创造的收益
     */
    private Double feeAmount;
    private int totalNums;
	/**
	 * 理财师职级
	 */
	private String jobGrade;
	
	/**
	 * 团队关系
	 */
	private String teamRela;
	
	/**
	 * 本月个人销售年化
	 */
	private String monthAmount;
	
	/**
	 * 直接下级人数
	 */
	private String directNums;
	
	/**
	 * 绑定时间
	 */
	private Date bindTime;
	
	/**
	 * 津贴、奖励(查询条件 )
	 */
	private String rewardAllowance;
	
	/**
	 * 产品期限
	 */
	private String productTerm;
	
	/**
	 * 产品所属机构
	 */
	private String orgName;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 销售金额
	 */
	private Double productAmount;
	
	/**
	 * 销售时间
	 */
	private Date saleTime;
	
	/**
	 * 奖励/津贴类型
	 */
	private String feeType;
	
    public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getRewardAllowance() {
		return rewardAllowance;
	}

	public void setRewardAllowance(String rewardAllowance) {
		this.rewardAllowance = rewardAllowance;
	}

	public String getProductTerm() {
		return productTerm;
	}

	public void setProductTerm(String productTerm) {
		this.productTerm = productTerm;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public String getTeamRela() {
		return teamRela;
	}

	public void setTeamRela(String teamRela) {
		this.teamRela = teamRela;
	}

	public String getMonthAmount() {
		return monthAmount;
	}

	public void setMonthAmount(String monthAmount) {
		this.monthAmount = monthAmount;
	}

	public String getDirectNums() {
		return directNums;
	}

	public void setDirectNums(String directNums) {
		this.directNums = directNums;
	}

	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

	public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(int totalNums) {
        this.totalNums = totalNums;
    }
}
