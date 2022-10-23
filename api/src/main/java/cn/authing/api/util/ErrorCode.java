package cn.authing.api.util;

public class ErrorCode {

    // api error code
    public final static int EC_MFA_REQUIRED = 1636;
    public final static int EC_FIRST_TIME_LOGIN = 1639;
    public final static int EC_CAPTCHA = 2000;
    public final static int EC_INCORRECT_VERIFY_CODE = 2001;
    public final static int EC_INCORRECT_CREDENTIAL = 2333;

    // sdk error code
    public final static int ERROR_CODE_10001 = 10001; // Network error
    public final static int ERROR_CODE_10002 = 10002; // Config not found
    public final static int ERROR_CODE_10003 = 10003; // Login failed
    public final static int ERROR_CODE_10004 = 10004; // JSON parse failed
    public final static int ERROR_CODE_10009 = 10009; // Upload avatar failed

}
