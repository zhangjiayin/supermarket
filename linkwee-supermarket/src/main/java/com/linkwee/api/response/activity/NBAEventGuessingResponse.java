package com.linkwee.api.response.activity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.jackson.MoneySerializer;

import java.math.BigDecimal;

public class NBAEventGuessingResponse extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6407190003504183115L;

	/**
     * 奖池金额
     */
	@JsonSerialize(using=MoneySerializer.class)
    private BigDecimal jackpot;

    /**
     * 剩余可投票数
     */
    private Integer leftVotes;

    /**
     * A方已投票数
     */
    private Integer hadVotedNumA;

    /**
     * B方已投票数
     */
    private Integer hadVotedNumB;

    /**
     * 胜出方
     */
    private String winningParty;

    /**
     * 奖励
     */
    @JsonSerialize(using=MoneySerializer.class)
    private BigDecimal prize;

    /**
     *A方得分
     */
    private Integer scoreA;

    /**
     *B方得分
     */
    private Integer scoreB;

    /**
     *A方支持票数
     */
    private Integer supportVotesA;

    /**
     *B方支持票数
     */
    private Integer supportVotesB;

    /**
     *比赛方A
     */
    private String competitionPartyA;

    /**
     *比赛方B
     */
    private String competitionPartyB;

    /**
     * 竞猜状态 1:可投票  2:竞猜活动尚未开放投票 3:不在竞猜活动投票时间内 4:竞猜结束
     */
    private Integer guessingStatus;
    
    /**
     * 竞猜ID
     */
    private Integer guessId;

    public BigDecimal getJackpot() {
        return jackpot;
    }

    public void setJackpot(BigDecimal jackpot) {
        this.jackpot = jackpot;
    }

    public Integer getLeftVotes() {
        return leftVotes;
    }

    public void setLeftVotes(Integer leftVotes) {
        this.leftVotes = leftVotes;
    }

    public Integer getHadVotedNumA() {
        return hadVotedNumA;
    }

    public void setHadVotedNumA(Integer hadVotedNumA) {
        this.hadVotedNumA = hadVotedNumA;
    }

    public Integer getHadVotedNumB() {
        return hadVotedNumB;
    }

    public void setHadVotedNumB(Integer hadVotedNumB) {
        this.hadVotedNumB = hadVotedNumB;
    }

    public String getWinningParty() {
        return winningParty;
    }

    public void setWinningParty(String winningParty) {
        this.winningParty = winningParty;
    }

    public Integer getScoreA() {
        return scoreA;
    }

    public void setScoreA(Integer scoreA) {
        this.scoreA = scoreA;
    }

    public Integer getScoreB() {
        return scoreB;
    }

    public void setScoreB(Integer scoreB) {
        this.scoreB = scoreB;
    }

    public Integer getSupportVotesA() {
        return supportVotesA;
    }

    public void setSupportVotesA(Integer supportVotesA) {
        this.supportVotesA = supportVotesA;
    }

    public Integer getSupportVotesB() {
        return supportVotesB;
    }

    public void setSupportVotesB(Integer supportVotesB) {
        this.supportVotesB = supportVotesB;
    }

    public String getCompetitionPartyA() {
        return competitionPartyA;
    }

    public void setCompetitionPartyA(String competitionPartyA) {
        this.competitionPartyA = competitionPartyA;
    }

    public String getCompetitionPartyB() {
        return competitionPartyB;
    }

    public void setCompetitionPartyB(String competitionPartyB) {
        this.competitionPartyB = competitionPartyB;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public Integer getGuessingStatus() {
        return guessingStatus;
    }

    public void setGuessingStatus(Integer guessingStatus) {
        this.guessingStatus = guessingStatus;
    }

	public Integer getGuessId() {
		return guessId;
	}

	public void setGuessId(Integer guessId) {
		this.guessId = guessId;
	}
    
}
