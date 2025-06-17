package com.test.myhomequote.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class InfoRequest {

    @JsonProperty("user_id")
    @NotNull
    @Positive
    private Long userId;

    @JsonProperty("level_id")
    @NotNull
    @Positive
    private Long levelId;

    private Long result;

    public Long getUserId() {
        return userId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public Long getResult() {
        return result;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
