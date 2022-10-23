package cn.authing.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONObject;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import cn.authing.api.data.Config;
import cn.authing.api.data.LoginTokenResponse;
import cn.authing.api.data.Safe;
import cn.authing.api.network.Guardian;
import cn.authing.api.util.ALog;
import cn.authing.api.util.Util;


public class Authing {

    private final static String TAG = "Authing";
    private static Context sAppContext;
    private static String scheme = "https";
    private static String sHost = "authing.cn"; // for private deployment
    private static boolean isOnPremises;
    private static String sAppId;
    private static boolean isGettingConfig;
    private static Config publicConfig;
    private static final Queue<Config.ConfigCallback> listeners = new ConcurrentLinkedQueue<>();
    private static LoginTokenResponse token;
    private static AuthProtocol authProtocol = AuthProtocol.EInHouse;

    public static void init(final Context context, String appId) {
        sAppContext = context.getApplicationContext();
        sAppId = appId;
        requestPublicConfig();
    }

    public static Context getAppContext() {
        return sAppContext;
    }

    public static String getScheme() {
        return scheme;
    }

    public static void setScheme(String scheme) {
        Authing.scheme = scheme;
    }

    public static String getHost() {
        return sHost;
    }

    public static void setHost(String host) {
        Authing.sHost = host;
        isOnPremises = true;
    }

    public static void setAuthProtocol(AuthProtocol authProtocol) {
        Authing.authProtocol = authProtocol;
    }

    public static AuthProtocol getAuthProtocol() {
        return Authing.authProtocol;
    }

    public enum AuthProtocol {
        EInHouse,
        EOIDC
    }

    public static String getAppId() {
        return sAppId;
    }

    public static void getPublicConfig(Config.ConfigCallback callback) {
        // add listener first. otherwise callback might be fired in the other thread
        // and this listener is missed
        if (isGettingConfig) {
            listeners.add(callback);
        }

        if (publicConfig != null) {
            listeners.clear();
            callback.call(publicConfig);
        }
    }

    public static LoginTokenResponse getToken() {
        if (token == null) {
            token = Safe.loadToken();
        }
        return token;
    }


    public static void setToken(LoginTokenResponse tokenResponse) {
        token = tokenResponse;
    }

    public static void saveToken(LoginTokenResponse tokenResponse) {
        token = tokenResponse;
        Safe.saveToken(tokenResponse);
    }

    private static void requestPublicConfig() {
        isGettingConfig = true;
        publicConfig = null;
        _requestPublicConfig();
    }

    private static void _requestPublicConfig() {
        String host = sHost;
        if (!Util.isIp(sHost) && !isOnPremises) {
            host = "console." + sHost;
        }
        String url = scheme + "://" + host + "/api/v2/applications/" + sAppId + "/public-config";
        Guardian.request(null, url, "get", null, (response) -> {
            try {
                if (response.getStatusCode() == 200) {
                    JSONObject data = response.getData();
                    Config config = Config.parse(data);
                    config.setUserAgent(Util.getUserAgent(getAppContext()));
                    fireCallback(config);
                } else {
                    ALog.e(TAG, "Get public config failed for appId: " + sAppId + " Msg:" + response.getMessage());
                    fireCallback(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fireCallback(null);
            }
        });
    }

    private static void fireCallback(Config config) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            publicConfig = config;
            for (Config.ConfigCallback callback : listeners) {
                callback.call(config);
            }
            listeners.clear();
            isGettingConfig = false;
        });
    }

    public static boolean isGettingConfig() {
        return isGettingConfig;
    }
}
