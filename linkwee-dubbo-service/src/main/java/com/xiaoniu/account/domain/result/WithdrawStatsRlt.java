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

/**
 * 提现统计返回结果.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/12 9:44
 */
public class WithdrawStatsRlt implements Serializable {
	/** 提现中笔数*/
	private Long totalItems;
	/** 提现总金额 */
	private Long totalAmount;
	/** 成功笔数 */
	private Long succItems;
	/** 成功金额 */
	private Long succAmount;
	/** 失败笔数 */
	private Long failItems;
	/** 失败总金额 */
	private Long faliAmount;
	/** 处理中总数 */
	private Long processingItems;
	/** 处理中总金额 */
	private Long processingAmount;
	/** 待审总数 */
	private Long watiAuditItems;
	/** 待审总金额 */
	private Long watiAuditAmount;
	/** 大额提现笔数（单笔大于10W，金额资源文件可配置） */
	private Long largeItems;
	/** 大额提现总额 */
	private Long largeAmount;
	/** 提现成本，1元一笔，金额可在资源文件配置 */
	private Long withdrawCost;
	/** 收取客户提现手续费总额 */
	private Long withdrawFee;
	/** 实际提现成本 = 提现成本 - 收取客户提现手续费总额 */
	private Long withdrawActualCost;

	public WithdrawStatsRlt() {
	}

	/**
	 * 初始化
	 * @return
	 */
	public static WithdrawStatsRlt init(){
		WithdrawStatsRlt rlt = new WithdrawStatsRlt();
		rlt.setTotalItems(0);
		rlt.setTotalAmount(0l);
		rlt.setSuccItems(0l);
		rlt.setSuccAmount(0l);
		rlt.setFailItems(0l);
		rlt.setFaliAmount(0l);
		rlt.setProcessingItems(0l);
		rlt.setProcessingAmount(0l);
		rlt.setWatiAuditItems(0l);
		rlt.setWatiAuditAmount(0l);
		rlt.setLargeItems(0l);
		rlt.setLargeAmount(0l);
		rlt.setWithdrawCost(0l);
		rlt.setWithdrawFee(0l);
		rlt.setWithdrawActualCost(0l);
		return rlt;
	}

	/**
	 * 合并结果
	 * @param originalStatsRlt
	 * @param waitMergeStatsRlt
	 * @return
	 */
	public static WithdrawStatsRlt merge(WithdrawStatsRlt originalStatsRlt, WithdrawStatsRlt waitMergeStatsRlt){
		WithdrawStatsRlt rlt = new WithdrawStatsRlt();
		rlt.setTotalItems(originalStatsRlt.getTotalItems() + waitMergeStatsRlt.getTotalItems());
		rlt.setTotalAmount(originalStatsRlt.getTotalAmount() + waitMergeStatsRlt.getTotalAmount());
		rlt.setSuccItems(originalStatsRlt.getSuccItems() + waitMergeStatsRlt.getSuccItems());
		rlt.setSuccAmount(originalStatsRlt.getSuccAmount() + waitMergeStatsRlt.getSuccAmount());
		rlt.setFailItems(originalStatsRlt.getFailItems() + waitMergeStatsRlt.getFailItems());
		rlt.setFaliAmount(originalStatsRlt.getFaliAmount() + waitMergeStatsRlt.getFaliAmount());
		rlt.setProcessingItems(originalStatsRlt.getProcessingItems() + waitMergeStatsRlt.getProcessingItems());
		rlt.setProcessingAmount(originalStatsRlt.getProcessingAmount() + waitMergeStatsRlt.getProcessingAmount());
		rlt.setWatiAuditItems(originalStatsRlt.getWatiAuditItems() + waitMergeStatsRlt.getWatiAuditItems());
		rlt.setWatiAuditAmount(originalStatsRlt.getWatiAuditAmount() + waitMergeStatsRlt.getWatiAuditAmount());
		rlt.setLargeItems(originalStatsRlt.getLargeItems() + waitMergeStatsRlt.getLargeItems());
		rlt.setLargeAmount(originalStatsRlt.getLargeAmount() + waitMergeStatsRlt.getLargeAmount());
		rlt.setWithdrawCost(originalStatsRlt.getWithdrawCost() + waitMergeStatsRlt.getWithdrawCost());
		rlt.setWithdrawFee(originalStatsRlt.getWithdrawFee() + waitMergeStatsRlt.getWithdrawFee());
		rlt.setWithdrawActualCost(originalStatsRlt.getWithdrawActualCost() + waitMergeStatsRlt.getWithdrawActualCost());
		return rlt;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getSuccItems() {
		return succItems;
	}

	public void setSuccItems(Long succItems) {
		this.succItems = succItems;
	}

	public Long getSuccAmount() {
		return succAmount;
	}

	public void setSuccAmount(Long succAmount) {
		this.succAmount = succAmount;
	}

	public Long getFailItems() {
		return failItems;
	}

	public void setFailItems(Long failItems) {
		this.failItems = failItems;
	}

	public Long getFaliAmount() {
		return faliAmount;
	}

	public void setFaliAmount(Long faliAmount) {
		this.faliAmount = faliAmount;
	}

	public Long getProcessingItems() {
		return processingItems;
	}

	public void setProcessingItems(Long processingItems) {
		this.processingItems = processingItems;
	}

	public Long getProcessingAmount() {
		return processingAmount;
	}

	public void setProcessingAmount(Long processingAmount) {
		this.processingAmount = processingAmount;
	}

	public Long getWatiAuditItems() {
		return watiAuditItems;
	}

	public void setWatiAuditItems(Long watiAuditItems) {
		this.watiAuditItems = watiAuditItems;
	}

	public Long getWatiAuditAmount() {
		return watiAuditAmount;
	}

	public void setWatiAuditAmount(Long watiAuditAmount) {
		this.watiAuditAmount = watiAuditAmount;
	}

	public Long getLargeItems() {
		return largeItems;
	}

	public void setLargeItems(Long largeItems) {
		this.largeItems = largeItems;
	}

	public Long getLargeAmount() {
		return largeAmount;
	}

	public void setLargeAmount(Long largeAmount) {
		this.largeAmount = largeAmount;
	}

	public Long getWithdrawCost() {
		return withdrawCost;
	}

	public void setWithdrawCost(Long withdrawCost) {
		this.withdrawCost = withdrawCost;
	}

	public Long getWithdrawFee() {
		return withdrawFee;
	}

	public void setWithdrawFee(Long withdrawFee) {
		this.withdrawFee = withdrawFee;
	}

	public Long getWithdrawActualCost() {
		return withdrawActualCost;
	}

	public void setWithdrawActualCost(Long withdrawActualCost) {
		this.withdrawActualCost = withdrawActualCost;
	}
}
