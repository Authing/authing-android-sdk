package cn.authing.api.params;

import org.json.JSONException;
import org.json.JSONObject;

public class ResetPasswordPayload {

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
