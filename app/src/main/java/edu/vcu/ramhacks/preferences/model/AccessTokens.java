package edu.vcu.ramhacks.preferences.model;


public class AccessTokens {

    private String accessToken;
    private String refreshToken;
    private long userId;

    public String getAccessToken() {

        return accessToken;
    }

    public void setAccessToken(String accessToken) {

        this.accessToken = accessToken;
    }

    public String getRefreshToken() {

        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {

        this.refreshToken = refreshToken;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public String getAccessTokenHeader() {

        return "Bearer " + accessToken;
    }
}
