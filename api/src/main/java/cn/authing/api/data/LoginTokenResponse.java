package cn.authing.api.data;

import org.json.JSONObject;

public class LoginTokenResponse {

    private String accessToken;
    private String idToken;
    private String refreshToken;
    private String tokenType;
    private int expireIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Integer expireIn) {
        this.expireIn = expireIn;
    }

    public void parseTokens(JSONObject obj) {
        try {
            if (obj.has("access_token")) {
                String s = obj.getString("access_token");
                setAccessToken(s);
            }
            if (obj.has("id_token")) {
                String s = obj.getString("id_token");
                setIdToken(s);
            }
            if (obj.has("refresh_token")) {
                String s = obj.getString("refresh_token");
                setRefreshToken(s);
            }
            if (obj.has("token_type")) {
                String s = obj.getString("token_type");
                setTokenType(s);
            }
            if (obj.has("expire_in")) {
                int s = obj.getInt("expire_in");
                setExpireIn(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
