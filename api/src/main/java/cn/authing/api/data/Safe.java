package cn.authing.api.data;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import cn.authing.api.Authing;
import cn.authing.api.util.Util;


public class Safe {

    private static final String SP_NAME = "SP_AUTHING_GUARD";

    private static final String SP_KEY_TOKEN = "SP_TOKEN";
    private static final String SP_KEY_REFRESH_TOKEN = "SP_REFRESH_TOKEN";

    public static void saveToken(LoginTokenResponse tokenResponse) {
        if (Authing.getAppContext() != null) {
            SharedPreferences sp = Authing.getAppContext().getSharedPreferences(SP_NAME, MODE_PRIVATE);
            sp.edit().putString(SP_KEY_TOKEN, tokenResponse.getIdToken()).apply();
            sp.edit().putString(SP_KEY_REFRESH_TOKEN, tokenResponse.getRefreshToken()).apply();
        }
    }

    public static LoginTokenResponse loadToken() {
        if (Authing.getAppContext() != null) {
            SharedPreferences sp = Authing.getAppContext().getSharedPreferences(SP_NAME, 0);
            String token = sp.getString(SP_KEY_TOKEN, "");
            if (Util.isNull(token)) {
                return null;
            } else {
                LoginTokenResponse tokenResponse = new LoginTokenResponse();
                tokenResponse.setIdToken(token);
                String refreshToken = sp.getString(SP_KEY_REFRESH_TOKEN, "");
                tokenResponse.setRefreshToken(refreshToken);
                return tokenResponse;
            }
        } else {
            return null;
        }
    }

    public static void clearToken() {
        if (Authing.getAppContext() != null) {
            SharedPreferences sp = Authing.getAppContext().getSharedPreferences(SP_NAME, MODE_PRIVATE);
            sp.edit().remove(SP_KEY_TOKEN).apply();
            sp.edit().remove(SP_KEY_REFRESH_TOKEN).apply();
        }
    }
}
