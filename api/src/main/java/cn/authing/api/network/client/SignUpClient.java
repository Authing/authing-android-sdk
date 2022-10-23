package cn.authing.api.network.client;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.Guardian;
import cn.authing.api.params.AuthOptions;
import cn.authing.api.params.AuthProfile;
import cn.authing.api.params.type.PasswordEncryptType;
import cn.authing.api.util.Util;


public class SignUpClient extends BaseClient {

    private SignUpClient() {
    }

    private static class RegisterSingletonHolder {
        private static final SignUpClient mInstance = new SignUpClient();
    }

    public static SignUpClient getInstance() {
        return RegisterSingletonHolder.mInstance;
    }


    public void signUpByUserNamePassword(String username, String password, AuthProfile profile, AuthOptions options, @NotNull AuthCallback callback) {
        PasswordEncryptType passwordEncryptType = (options != null ? options.getPasswordEncryptType() : null);
        getPublicKey(passwordEncryptType, (ok, data) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("connection", "PASSWORD");
                JSONObject passwordPayload = new JSONObject();
                passwordPayload.put("username", username);
                passwordPayload.put("password", encryptPassword(password, passwordEncryptType, data));
                body.put("passwordPayload", passwordPayload);
                if (profile != null) {
                    body.put("profile", profile.toJSON());
                }
                if (options == null) {
                    body.put("options", new AuthOptions().toJSON());
                } else {
                    body.put("options", options.toJSON());
                }
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            signUp(body, callback);
        });
    }

    public void signUpByEmailPassword(String email, String password, AuthProfile profile, AuthOptions options, @NotNull AuthCallback callback) {
        PasswordEncryptType passwordEncryptType = (options != null ? options.getPasswordEncryptType() : null);
        getPublicKey(passwordEncryptType, (ok, data) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("connection", "PASSWORD");
                JSONObject passwordPayload = new JSONObject();
                passwordPayload.put("email", email);
                passwordPayload.put("password", encryptPassword(password, passwordEncryptType, data));
                body.put("passwordPayload", passwordPayload);
                if (profile != null) {
                    body.put("profile", profile.toJSON());
                }
                if (options == null) {
                    body.put("options", new AuthOptions().toJSON());
                } else {
                    body.put("options", options.toJSON());
                }
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            signUp(body, callback);
        });
    }

    public void signUpByEmailPassCode(String email, String passCode, AuthProfile profile, AuthOptions options, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("connection", "PASSCODE");
            JSONObject passCodePayload = new JSONObject();
            passCodePayload.put("email", email);
            passCodePayload.put("passCode", passCode);
            body.put("passCodePayload", passCodePayload);
            if (profile != null) {
                body.put("profile", profile.toJSON());
            }
            if (options == null) {
                body.put("options", new AuthOptions().toJSON());
            } else {
                body.put("options", options.toJSON());
            }
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        signUp(body, callback);
    }

    public void signUpByPhonePassCode(String phoneCountryCode, String phone, String passCode, AuthProfile profile, AuthOptions options, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("connection", "PASSCODE");
            JSONObject passCodePayload = new JSONObject();
            if (!Util.isNull(phoneCountryCode)) {
                passCodePayload.put("phoneCountryCode", phoneCountryCode);
            }
            passCodePayload.put("phone", phone);
            passCodePayload.put("passCode", passCode);
            body.put("passCodePayload", passCodePayload);
            if (profile != null) {
                body.put("profile", profile.toJSON());
            }
            if (options == null) {
                body.put("options", new AuthOptions().toJSON());
            } else {
                body.put("options", options.toJSON());
            }
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        signUp(body, callback);
    }

    private void signUp(JSONObject body, @NotNull AuthCallback callback) {
        Guardian.post("/api/v3/signup", body, callback::call);
    }

}
