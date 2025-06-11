package com.test.myhomequote.api.dto.resonse;

public class InfoResponse {

    private Long userId;
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
