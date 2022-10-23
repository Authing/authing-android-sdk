package cn.authing.api.network.client;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import cn.authing.api.Authing;
import cn.authing.api.data.AuthResponse;
import cn.authing.api.data.Config;
import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.AuthRequest;
import cn.authing.api.network.Callback;
import cn.authing.api.network.Guardian;
import cn.authing.api.util.PKCE;
import cn.authing.api.util.Util;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OIDCClient extends BaseClient {

    private OIDCClient() {
    }

    private static class OIDCSingletonHolder {
        private static final OIDCClient mInstance = new OIDCClient();
    }

    public static OIDCClient getInstance() {
        return OIDCClient.OIDCSingletonHolder.mInstance;
    }

    public void buildAuthorizeUrl(@NotNull AuthRequest authRequest, @NotNull Callback<String> callback) {
        Authing.getPublicConfig(config -> {
            if (config == null) {
                return;
            }
            callback.call(true, buildAuthorizeUrl(config, authRequest));
        });
    }

    private String buildAuthorizeUrl(Config config, @NotNull AuthRequest authRequest) {
        String secret = authRequest.getClientSecret();
        return Authing.getScheme() + "://" + Util.getHost(config) + "/oidc/auth?_authing_lang="
                + Util.getLangHeader()
                + "&app_id=" + Authing.getAppId()
                + "&client_id=" + Authing.getAppId()
                + "&nonce=" + authRequest.getNonce()
                + "&redirect_uri=" + authRequest.getRedirectURL()
                + "&response_type=" + authRequest.getResponse_type()
                + "&scope=" + authRequest.getScope()
                + "&prompt=consent"
                + "&state=" + authRequest.getState()
                + (secret == null ? "&code_challenge=" + authRequest.getCodeChallenge() + "&code_challenge_method=" + PKCE.getCodeChallengeMethod() : "");
    }

    public void getAccessTokenByCode(String code, @NotNull AuthRequest authRequest, @NotNull AuthCallback callback) {
        getPublicConfig(callback, (ok, config) -> {
            try {
                String url = Authing.getScheme() + "://" + Util.getHost(config) + "/oidc/token";
                String secret = authRequest.getClientSecret();
                RequestBody formBody = new FormBody.Builder()
                        .add("client_id", Authing.getAppId())
                        .add("grant_type", "authorization_code")
                        .add("code", code)
                        .add("scope", authRequest.getScope())
                        .add("prompt", "consent")
                        .add(secret == null ? "code_verifier" : "client_secret", secret == null ? authRequest.getCodeVerifier() : secret)
                        .add("redirect_uri", authRequest.getRedirectURL())
                        .build();
                Guardian.authRequest(url, "post", formBody, (response) -> {
                    saveTokens(response);
                    callback.call(response);
                });
            } catch (Exception e) {
                error(e, callback);
            }
        });
    }

    public void getUserInfoByAccessToken(String accessToken, @NotNull AuthCallback callback) {
        getPublicConfig(callback, (ok, config) -> {
            try {
                String url = Authing.getScheme() + "://" + Util.getHost(config) + "/oidc/me";
                Request.Builder builder = new Request.Builder();
                builder.url(url);
                builder.addHeader("Authorization", "Bearer " + accessToken);
                Request request = builder.build();
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                Call call = client.newCall(request);
                okhttp3.Response response;
                response = call.execute();
                String s = new String(Objects.requireNonNull(response.body()).bytes(), StandardCharsets.UTF_8);
                AuthResponse resp = new AuthResponse();
                if (response.code() == 200) {
                    JSONObject json;
                    try {
                        json = new JSONObject(s);
                        resp.setStatusCode(200);
                        resp.setData(json);
                        saveTokens(resp);
                        callback.call(resp);
                    } catch (JSONException e) {
                        error(e, callback);
                    }
                } else {
                    resp.setStatusCode(response.code());
                    resp.setMessage(s);
                    callback.call(resp);
                }
            } catch (Exception e) {
                error(e, callback);
            }
        });
    }

    public void getNewAccessTokenByRefreshToken(String refreshToken, @NotNull AuthRequest authRequest, @NotNull AuthCallback callback) {
        getPublicConfig(callback, (ok, config) -> {
            try {
                String url = Authing.getScheme() + "://" + Util.getHost(config) + "/oidc/token";
                String secret = authRequest.getClientSecret();
                RequestBody formBody = new FormBody.Builder()
                        .add("client_id", Authing.getAppId())
                        .add("grant_type", "refresh_token")
                        .add("refresh_token", refreshToken)
                        .add(secret == null ? "code_verifier" : "client_secret", secret == null ? authRequest.getCodeVerifier() : secret)
                        .build();
                Guardian.authRequest(url, "post", formBody, (response) -> {
                    saveTokens(response);
                    callback.call(response);
                });
            } catch (Exception e) {
                error(e, callback);
            }
        });
    }

}
