package cn.authing.api.network.client;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.Guardian;
import cn.authing.api.params.type.EmailType;
import cn.authing.api.params.type.SmsType;
import cn.authing.api.util.Util;

public class CodeClient extends BaseClient {

    private CodeClient() {
    }

    private static class CodeSingletonHolder {
        private static final CodeClient mInstance = new CodeClient();
    }

    public static CodeClient getInstance() {
        return CodeSingletonHolder.mInstance;
    }

    public void sendSms(String phoneCountryCode, String phoneNumber, SmsType type, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            if (!Util.isNull(phoneCountryCode)) {
                body.put("phoneCountryCode", phoneCountryCode);
            }
            body.put("phoneNumber", phoneNumber);
            body.put("channel", type.toString());
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/send-sms", body, callback::call);
    }


    public void sendEmail(String email, EmailType type, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("email", email);
            body.put("channel", type.toString());
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/send-email", body, callback::call);
    }

}
