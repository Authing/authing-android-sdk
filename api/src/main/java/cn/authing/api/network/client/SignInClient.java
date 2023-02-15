package cn.authing.api.network.client;


import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.Guardian;
import cn.authing.api.params.AuthOptions;
import cn.authing.api.params.type.Action;
import cn.authing.api.params.type.Connection;
import cn.authing.api.params.type.PasswordEncryptType;
import cn.authing.api.util.Const;


public class SignInClient extends BaseClient {

    private SignInClient() {
    }

    private static class LoginSingletonHolder {
        private static final SignInClient mInstance = new SignInClient();
    }

    public static SignInClient getInstance() {
        return LoginSingletonHolder.mInstance;
    }

    public void signInByAccountPassword(String account, String password, AuthOptions options, @NotNull AuthCallback callback) {
        signInByPassword("account", account, password, options, callback);
    }

    public void signInByUserNamePassword(String username, String password, AuthOptions options, @NotNull AuthCallback callback) {
        signInByPassword("username", username, password, options, callback);
    }

    public void signInByEmailPassword(String email, String password, AuthOptions options, @NotNull AuthCallback callback) {
        signInByPassword("email", email, password, options, callback);
    }

    public void signInByPhonePassword(String phone, String password, AuthOptions options, @NotNull AuthCallback callback) {
        signInByPassword("phone", phone, password, options, callback);
    }

    private void signInByPassword(String key, String value, String password, AuthOptions options, @NotNull AuthCallback callback) {
        PasswordEncryptType passwordEncryptType = (options != null ? options.getPasswordEncryptType() : null);
        getPublicKey(passwordEncryptType, (ok, data) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("connection", "PASSWORD");
                JSONObject passwordPayload = new JSONObject();
                passwordPayload.put(key, value);
                passwordPayload.put("password", encryptPassword(password, passwordEncryptType, data));
                body.put("passwordPayload", passwordPayload);
                if (options == null) {
                    body.put("options", new AuthOptions().toJSON());
                } else {
                    body.put("options", options.toJSON());
                }
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            signIn(body, callback);
        });
    }

    public void signInByPhonePassCode(String phoneCountryCode, String phone, String passCode, AuthOptions options, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("connection", "PASSCODE");
            JSONObject passCodePayload = new JSONObject();
            passCodePayload.put("phoneCountryCode", phoneCountryCode);
            passCodePayload.put("phone", phone);
            passCodePayload.put("passCode", passCode);
            body.put("passCodePayload", passCodePayload);
            if (options == null) {
                body.put("options", new AuthOptions().toJSON());
            } else {
                body.put("options", options.toJSON());
            }
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        signIn(body, callback);
    }

    public void signInByEmailPassCode(String email, String passCode, AuthOptions options, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("connection", "PASSCODE");
            JSONObject passCodePayload = new JSONObject();
            passCodePayload.put("email", email);
            passCodePayload.put("passCode", passCode);
            body.put("passCodePayload", passCodePayload);
            if (options == null) {
                body.put("options", new AuthOptions().toJSON());
            } else {
                body.put("options", options.toJSON());
            }
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        signIn(body, callback);
    }

    public void signInByAD(String sAMAccountName, String password, AuthOptions options, @NotNull AuthCallback callback) {
        PasswordEncryptType passwordEncryptType = (options != null ? options.getPasswordEncryptType() : null);
        getPublicKey(passwordEncryptType, (ok, data) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("connection", "AD");
                JSONObject adPayload = new JSONObject();
                adPayload.put("sAMAccountName", sAMAccountName);
                adPayload.put("password", encryptPassword(password, passwordEncryptType, data));
                body.put("adPayload", adPayload);
                if (options == null) {
                    body.put("options", new AuthOptions().toJSON());
                } else {
                    body.put("options", options.toJSON());
                }
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            signIn(body, callback);
        });
    }

    public void signInByLDAP(String sAMAccountName, String password, AuthOptions options, @NotNull AuthCallback callback) {
        PasswordEncryptType passwordEncryptType = (options != null ? options.getPasswordEncryptType() : null);
        getPublicKey(passwordEncryptType, (ok, data) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("connection", "LDAP");
                JSONObject ldapPayload = new JSONObject();
                ldapPayload.put("sAMAccountName", sAMAccountName);
                ldapPayload.put("password", encryptPassword(password, passwordEncryptType, data));
                body.put("ldapPayload", ldapPayload);
                if (options == null) {
                    body.put("options", new AuthOptions().toJSON());
                } else {
                    body.put("options", options.toJSON());
                }
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            signIn(body, callback);
        });
    }

    private void signIn(JSONObject body, @NotNull AuthCallback callback) {
        Guardian.post("/api/v3/signin", body, response -> {
            saveTokens(response);
            callback.call(response);
        });
    }

    public void signInByOneAuth(String token, String accessToken, AuthOptions options, @NotNull AuthCallback callback) {
        getPublicConfig(callback, (ok, config) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("connection", Connection.yidun.toString());
                body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_YI_DUN));
                JSONObject yidunPayload = new JSONObject();
                yidunPayload.put("token", token);
                yidunPayload.put("accessToken", accessToken);
                body.put("yidunPayload", yidunPayload);
                if (options == null) {
                    body.put("options", new AuthOptions().toJSON());
                } else {
                    body.put("options", options.toJSON());
                }
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            singInBySocial(body, callback);
        });
    }

    public void signInByWechatMiniProgram(String code, String phoneInfoCode, AuthOptions options, @NotNull AuthCallback callback) {
        getPublicConfig(callback, (ok, config) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("connection", Connection.wechat_mini_program_code_and_phone.toString());
                body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_WECHAT_MINI_PROGRAM));
                JSONObject wechatMiniProgramCodeAndPhonePayload = new JSONObject();
                JSONObject wxLoginInfo = new JSONObject();
                wxLoginInfo.put("encryptedData", "");
                wxLoginInfo.put("iv", "");
                wxLoginInfo.put("code", code);
                wechatMiniProgramCodeAndPhonePayload.put("wxLoginInfo", wxLoginInfo);
                JSONObject wxPhoneInfo = new JSONObject();
                wxPhoneInfo.put("code", phoneInfoCode);
                wechatMiniProgramCodeAndPhonePayload.put("wxPhoneInfo", wxPhoneInfo);
                body.put("wechatMiniProgramCodeAndPhonePayload", wechatMiniProgramCodeAndPhonePayload);
                if (options == null) {
                    body.put("options", new AuthOptions().toJSON());
                } else {
                    body.put("options", options.toJSON());
                }
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            singInBySocial(body, callback);
        });
    }

    public void signInByMobile(Connection connection, String code, AuthOptions options, @NotNull AuthCallback callback) {
        getPublicConfig(callback, (ok, config) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("connection", connection.toString());
                if (connection == Connection.wechat) {
                    body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_WECHAT));
                    JSONObject wechatPayload = new JSONObject();
                    wechatPayload.put("code", code);
                    body.put("wechatPayload", wechatPayload);
                } else if (connection == Connection.alipay) {
                    body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_ALIPAY));
                    JSONObject alipayPayload = new JSONObject();
                    alipayPayload.put("code", code);
                    body.put("alipayPayload", alipayPayload);
                } else if (connection == Connection.wechatwork) {
                    body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_WECHAT_COM));
                    JSONObject wechatworkPayload = new JSONObject();
                    wechatworkPayload.put("code", code);
                    body.put("wechatworkPayload", wechatworkPayload);
                } else if (connection == Connection.wechatwork_agency) {
                    body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_WECHAT_COM_AGENCY));
                    JSONObject wechatworkAgencyPayload = new JSONObject();
                    wechatworkAgencyPayload.put("code", code);
                    body.put("wechatworkAgencyPayload", wechatworkAgencyPayload);
                } else if (connection == Connection.lark_internal) {
                    body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_LARK_INTERNAL));
                    JSONObject larkInternalPayload = new JSONObject();
                    larkInternalPayload.put("code", code);
                    body.put("larkInternalPayload", larkInternalPayload);
                } else if (connection == Connection.lark_public) {
                    body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_LARK_PUBLIC));
                    JSONObject larkPublicPayload = new JSONObject();
                    larkPublicPayload.put("code", code);
                    body.put("larkPublicPayload", larkPublicPayload);
                } else if (connection == Connection.google) {
                    body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_GOOGLE));
                    JSONObject googlePayload = new JSONObject();
                    googlePayload.put("code", code);
                    body.put("googlePayload", googlePayload);
                } else if (connection == Connection.facebook) {
                    body.put("extIdpConnidentifier", config.getSocialIdentifier(Const.EC_TYPE_FACE_BOOK));
                    JSONObject facebookPayload = new JSONObject();
                    facebookPayload.put("access_token", code);
                    body.put("facebookPayload", facebookPayload);
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
            singInBySocial(body, callback);
        });
    }

    private void singInBySocial(JSONObject body, @NotNull AuthCallback callback) {
        Guardian.post("/api/v3/signin-by-mobile", body, response -> {
            saveTokens(response);
            callback.call(response);
        });
    }

    public void checkQRCodeStatus(String qrcodeId, @NotNull AuthCallback callback) {
        Guardian.get("/api/v3/check-qrcode-status?qrcodeId=" + qrcodeId, callback::call);
    }

    public void exchangeTokenSetWithQRCode(String ticket, String client_id, String client_secret, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("ticket", ticket);
            body.put("client_id", client_id);
            body.put("client_secret", client_secret);
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/exchange-tokenset-with-qrcode-ticket", body, callback::call);
    }

    public void changeQrCodeStatus(String qrcodeId, Action action, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("qrcodeId", qrcodeId);
            body.put("action", action.toString());
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/change-qrcode-status", body, callback::call);
    }

}
