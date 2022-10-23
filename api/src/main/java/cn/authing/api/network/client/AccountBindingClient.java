package cn.authing.api.network.client;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.Guardian;

public class AccountBindingClient extends BaseClient {

    private AccountBindingClient() {
    }

    private static class AccountBindingSingletonHolder {
        private static final AccountBindingClient mInstance = new AccountBindingClient();
    }

    public static AccountBindingClient getInstance() {
        return AccountBindingClient.AccountBindingSingletonHolder.mInstance;
    }

    public void linkExtIdp(String ext_idp_conn_identifier, String appId, String idToken, @NotNull AuthCallback callback) {
        Guardian.get("/api/v3/link-extidp?ext_idp_conn_identifier=" + ext_idp_conn_identifier + "&app_id=" + appId + "&id_token=" + idToken, callback::call);
    }

    public void unLinkExtIdp(String extIdpId, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("extIdpId", extIdpId);
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/unlink-extidp", body, callback::call);
    }

    public void getIdentities(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-identities", callback::call);
    }

    public void getExtIdps(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-extidps", callback::call);
    }

}
