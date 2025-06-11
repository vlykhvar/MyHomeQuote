package com.test.myhomequote.service.dto;

public class InfoBO {

    private Long userId;
    private Long levelId;
    private Long result;

    public InfoBO() {
    }

    public InfoBO(Long userId, Long levelId, Long result) {
        this.userId = userId;
        this.levelId = levelId;
        this.result = result;
    }

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
