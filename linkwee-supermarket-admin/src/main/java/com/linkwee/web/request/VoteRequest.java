package com.linkwee.web.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class VoteRequest {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 支持方
     */
    @NotNull(message = "支持方不能为空")
    private String supportVote;

    /**
     * 票数
     */
    @NotNull(message = "票数不能为空")
    @Min(1)
    private Integer voteNumber;

    /**
     * 竞猜ID
     */
    @NotNull(message = "竞猜ID不能为空")
    @Min(1)
    private Integer guessId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSupportVote() {
        return supportVote;
    }

    public void setSupportVote(String supportVote) {
        this.supportVote = supportVote;
    }

    public Integer getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(Integer voteNumber) {
        this.voteNumber = voteNumber;
    }

    public Integer getGuessId() {
        return guessId;
    }

    public void setGuessId(Integer guessId) {
        this.guessId = guessId;
    }
}
