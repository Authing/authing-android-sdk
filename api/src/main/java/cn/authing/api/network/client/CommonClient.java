package cn.authing.api.network.client;

import org.jetbrains.annotations.NotNull;

import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.Guardian;

public class CommonClient extends BaseClient {

    private CommonClient() {
    }

    private static class CommonSingletonHolder {
        private static final CommonClient mInstance = new CommonClient();
    }

    public static CommonClient getInstance() {
        return CommonSingletonHolder.mInstance;
    }

    public void system(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/system", callback::call);
    }

    public void getCountryList(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-country-list", callback::call);
    }

}
