package cn.authing.api.params;

import org.json.JSONException;
import org.json.JSONObject;

import cn.authing.api.params.type.PasswordEncryptType;

public class DeleteAccountPayload {

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 手机号码区号
     */
    private String phoneCountryCode;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String passCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     */
    private PasswordEncryptType passwordEncryptType;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PasswordEncryptType getPasswordEncryptType() {
        return passwordEncryptType;
    }

    public void setPasswordEncryptType(PasswordEncryptType passwordEncryptType) {
        this.passwordEncryptType = passwordEncryptType;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            if (getPhoneNumber() != null) {
                jsonObject.put("phoneNumber", getPhoneNumber());
            }
            if (getPhoneCountryCode() != null) {
                jsonObject.put("phoneCountryCode", getPhoneCountryCode());
            }
            if (getEmail() != null) {
                jsonObject.put("email", getEmail());
            }
            if (getPassCode() != null) {
                jsonObject.put("passCode", getPassCode());
            }
            if (getPassword() != null) {
                jsonObject.put("password", getPassword());
            }
            if (getPasswordEncryptType() != null) {
                jsonObject.put("passwordEncryptType", getPasswordEncryptType().toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
