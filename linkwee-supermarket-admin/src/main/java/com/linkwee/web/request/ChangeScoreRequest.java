package com.linkwee.web.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ChangeScoreRequest {

    @NotNull
    @Min(1)
    private Long guessId;

    private String nextStartTime;

    @NotNull
    @Min(0)
    private Integer scoreA;

    @NotNull
    @Min(0)
    private Integer scoreB;

    public Long getGuessId() {
        return guessId;
    }

    public void setGuessId(Long guessId) {
        this.guessId = guessId;
    }

    public String getNextStartTime() {
        return nextStartTime;
    }

    public void setNextStartTime(String nextStartTime) {
        this.nextStartTime = nextStartTime;
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
}
