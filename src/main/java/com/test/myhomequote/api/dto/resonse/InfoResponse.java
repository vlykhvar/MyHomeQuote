package com.test.myhomequote.api.dto.resonse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("level_id")
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
