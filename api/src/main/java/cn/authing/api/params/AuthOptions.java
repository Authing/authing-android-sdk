package cn.authing.api.params;

import org.json.JSONException;
import org.json.JSONObject;

import cn.authing.api.params.type.PasswordEncryptType;

/**
 * 可选参数
 */
public class AuthOptions {

    /**
     * 客户端 IP
     */
    private String clientIp;

    /**
     * 额外请求上下文
     */
    private String context;

    /**
     * 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     * Default: "none"
     * Enum: "sm2" "rsa" "none"
     */
    private PasswordEncryptType passwordEncryptType;

    /**
     * 需要请求的权限，必须包含 openid。如果需要获取手机号和 email 需要包含 phone email；如果需要 refresh_token 需要包含 offline_access。
     * 多个 scope 请用空格分隔。id_token 解码后的内容中会包含这些 scope 对应的用户信息相关的字段。
     */
    private String scope;

    /**
     * 租户 ID
     */
    private String tenantId;

    /**
     * 设置额外的用户自定义数据，你需要先在 Authing 控制台配置自定义数据。
     */
    private JSONObject customData;

    /**
     * 是否开启自动注册。如果设置为 true，当用户不存在的时候，会先自动为其创建一个账号。
     */
    private boolean autoRegister;

    /**
     * Captcha 图形验证码，不区分大小写。当安全策略设置为验证码且触发登录失败次数限制时，下次登录需要填写图形验证码。
     */
    private String captchaCode;

    /**
     * 用于注册时补全用户信息的短信验证码
     */
    private String phonePassCodeForInformationCompletion;

    /**
     * 用于注册时补全用户信息的邮箱验证码
     */
    private String emailPassCodeForInformationCompletion;


    public AuthOptions() {
        scope = "openid profile email phone username address offline_access roles extended_fields";
        passwordEncryptType = PasswordEncryptType.none;
        autoRegister = false;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public PasswordEncryptType getPasswordEncryptType() {
        return passwordEncryptType;
    }

    public void setPasswordEncryptType(PasswordEncryptType passwordEncryptType) {
        this.passwordEncryptType = passwordEncryptType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public JSONObject getCustomData() {
        return customData;
    }

    public void setCustomData(JSONObject customData) {
        this.customData = customData;
    }

    public boolean isAutoRegister() {
        return autoRegister;
    }

    public void setAutoRegister(boolean autoRegister) {
        this.autoRegister = autoRegister;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getPhonePassCodeForInformationCompletion() {
        return phonePassCodeForInformationCompletion;
    }

    public void setPhonePassCodeForInformationCompletion(String phonePassCodeForInformationCompletion) {
        this.phonePassCodeForInformationCompletion = phonePassCodeForInformationCompletion;
    }

    public String getEmailPassCodeForInformationCompletion() {
        return emailPassCodeForInformationCompletion;
    }

    public void setEmailPassCodeForInformationCompletion(String emailPassCodeForInformationCompletion) {
        this.emailPassCodeForInformationCompletion = emailPassCodeForInformationCompletion;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            if (getClientIp() != null) {
                jsonObject.put("clientIp", getClientIp());
            }
            if (getContext() != null) {
                jsonObject.put("context", getContext());
            }
            if (getPasswordEncryptType() != null) {
                jsonObject.put("passwordEncryptType", getPasswordEncryptType().toString());
            }
            if (getScope() != null) {
                jsonObject.put("scope", getScope());
            }
            if (getTenantId() != null) {
                jsonObject.put("tenantId", getTenantId());
            }
            if (getCustomData() != null) {
                jsonObject.put("customData", getCustomData());
            }
            if (getCaptchaCode() != null) {
                jsonObject.put("captchaCode", getCaptchaCode());
            }
            if (getPhonePassCodeForInformationCompletion() != null) {
                jsonObject.put("phonePassCodeForInformationCompletion", getPhonePassCodeForInformationCompletion());
            }
            if (getEmailPassCodeForInformationCompletion() != null) {
                jsonObject.put("emailPassCodeForInformationCompletion", getEmailPassCodeForInformationCompletion());
            }
            jsonObject.put("autoRegister", isAutoRegister());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
