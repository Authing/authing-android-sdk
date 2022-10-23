package cn.authing.api.data;

import org.json.JSONObject;

public class AuthResponse {
    private int statusCode;
    private int apiCode;
    private String message;
    private String requestId;
    private JSONObject data;

    // work-around. should move this to data
    private String recoveryCode;

    public AuthResponse() {
    }

    public AuthResponse(int statusCode, String message, JSONObject obj) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = obj;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getApiCode() {
        return apiCode;
    }

    public void setApiCode(int apiCode) {
        this.apiCode = apiCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getRecoveryCode() {
        return recoveryCode;
    }

    public void setRecoveryCode(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }
}
