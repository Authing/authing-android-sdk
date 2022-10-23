package cn.authing.api.network.client;

import android.text.TextUtils;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import cn.authing.api.Authing;
import cn.authing.api.data.AuthResponse;
import cn.authing.api.data.Config;
import cn.authing.api.data.LoginTokenResponse;
import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.Callback;
import cn.authing.api.network.Guardian;
import cn.authing.api.params.type.PasswordEncryptType;
import cn.authing.api.util.EncryptUtils;
import cn.authing.api.util.ErrorCode;

public class BaseClient {

    private static final String TAG = BaseClient.class.getSimpleName();

    public BaseClient() {

    }

    protected void error(Exception e, @NotNull AuthCallback callback) {
        e.printStackTrace();
        callback.call(new AuthResponse(ErrorCode.ERROR_CODE_10004, "JSON parse failed", null));
    }

    protected void getPublicConfig(@NotNull AuthCallback authCallback, Callback<Config> callback) {
        Authing.getPublicConfig(config -> {
            if (config == null) {
                getPublicConfigError(authCallback);
                return;
            }
            callback.call(true, config);
        });
    }

    protected void getPublicConfigError(@NotNull AuthCallback callback) {
        callback.call(new AuthResponse(ErrorCode.ERROR_CODE_10002, "Config not found", null));
    }

    protected String encryptPassword(String password, PasswordEncryptType passwordEncryptType, String publicKey) {
        if (passwordEncryptType == null || publicKey == null || TextUtils.isEmpty(publicKey)) {
            return password;
        }

        if (PasswordEncryptType.rsa == passwordEncryptType) {
            return EncryptUtils.rsaEncryptPassword(publicKey, password);
        } else if (PasswordEncryptType.sm2 == passwordEncryptType) {
            return EncryptUtils.sm2Encrypt(publicKey, password);
        }

        return password;
    }

    protected void getPublicKey(PasswordEncryptType passwordEncryptType, Callback<String> callback) {
        if (passwordEncryptType == null || passwordEncryptType == PasswordEncryptType.none) {
            callback.call(false, null);
            return;
        }
        Guardian.get("/api/v3/system", response -> {
            JSONObject data = response.getData();
            if (response.getStatusCode() == 200 && data != null) {
                if (PasswordEncryptType.rsa == passwordEncryptType) {
                    if (data.has("rsa")) {
                        try {
                            JSONObject rsa = (JSONObject) data.get("rsa");
                            if (rsa.has("publicKey")) {
                                String publicKey = (String) rsa.get("publicKey");
                                if (publicKey.contains("-----BEGIN PUBLIC KEY-----\n")
                                        && publicKey.contains("\n-----END PUBLIC KEY-----\n")) {
                                    publicKey = publicKey.substring("-----BEGIN PUBLIC KEY-----".length(),
                                            publicKey.length() - "-----END PUBLIC KEY-----".length() - 1);
                                }
                                callback.call(true, publicKey);
                            } else {
                                callback.call(false, null);
                                Log.e(TAG, "get public key failed");
                            }
                        } catch (JSONException e) {
                            Log.e(TAG, "get public key failed");
                            e.printStackTrace();
                            callback.call(false, null);
                        }
                    } else {
                        Log.e(TAG, "get public key failed");
                        callback.call(false, null);
                    }
                } else if (PasswordEncryptType.sm2 == passwordEncryptType) {
                    if (data.has("sm2")) {
                        try {
                            JSONObject sm2 = (JSONObject) data.get("sm2");
                            if (sm2.has("publicKey")) {
                                callback.call(true, (String) sm2.get("publicKey"));
                            } else {
                                callback.call(false, null);
                                Log.e(TAG, "get public key failed");
                            }
                        } catch (JSONException e) {
                            Log.e(TAG, "get public key failed");
                            e.printStackTrace();
                            callback.call(false, null);
                        }
                    } else {
                        Log.e(TAG, "get public key failed");
                        callback.call(false, null);
                    }
                }
            } else {
                Log.e(TAG, "get public key failed");
                callback.call(false, null);
            }
        });
    }

    protected void saveTokens(AuthResponse response) {
        if (response != null && response.getStatusCode() == 200) {
            LoginTokenResponse loginTokenResponse = new LoginTokenResponse();
            loginTokenResponse.parseTokens(response.getData());
            Authing.saveToken(loginTokenResponse);
        }
    }

    protected String getIdToken() {
        return Authing.getToken() != null ? Authing.getToken().getIdToken() : "";
    }

    protected String getAccessToken() {
        return Authing.getToken() != null ? Authing.getToken().getAccessToken() : "";
    }

    protected String getRefreshToken() {
        return Authing.getToken() != null ? Authing.getToken().getRefreshToken() : "";
    }
}
