package cn.authing.api.demo.ut.v3;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.authing.api.Authing;
import cn.authing.api.data.AuthResponse;
import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.AuthClient;
import cn.authing.api.network.AuthRequest;
import cn.authing.api.params.AuthOptions;
import cn.authing.api.params.AuthProfile;
import cn.authing.api.params.DeleteAccountPayload;
import cn.authing.api.params.FactorProfile;
import cn.authing.api.params.Profile;
import cn.authing.api.params.type.Action;
import cn.authing.api.params.type.Connection;
import cn.authing.api.params.type.DeleteAccountVerifyMethod;
import cn.authing.api.params.type.EmailType;
import cn.authing.api.params.type.FactorType;
import cn.authing.api.params.type.Gender;
import cn.authing.api.params.type.PasswordEncryptType;
import cn.authing.api.params.type.ResourceType;
import cn.authing.api.params.type.SmsType;
import cn.authing.api.demo.ut.IHttpCallBack;
import cn.authing.api.demo.ut.TestCase;

public class HttpV3Util {

    public static void sync(TestCase testCase, IHttpCallBack callBack) {
        if (testCase == null) {
            return;
        }
        String testApiName = testCase.getTestApiName();
        if (TextUtils.isEmpty(testApiName)) {
            return;
        }
        String params = testCase.getParams();
        if ("registerByEmailPassword".equals(testApiName)) {
            registerByEmailPassword(params, callBack);
        } else if ("registerByPhoneCode".equals(testApiName)) {
            registerByPhoneCode(params, callBack);
        } else if ("registerByEmailCode".equals(testApiName)) {
            registerByEmailCode(params, callBack);
        } else if ("loginByAccount".equals(testApiName)) {
            loginByAccount(params, callBack);
        } else if ("loginByPhoneCode".equals(testApiName)) {
            loginByPhoneCode(params, callBack);
        } else if ("loginByEmailCode".equals(testApiName)) {
            loginByEmailCode(params, callBack);
        } else if ("loginByWechat".equals(testApiName)) {
            loginByWechat(params, callBack);
        } else if ("loginByWecom".equals(testApiName)) {
            loginByWecom(params, callBack);
        } else if ("loginByWecomAgency".equals(testApiName)) {
            loginByWecomAgency(params, callBack);
        } else if ("loginByAlipay".equals(testApiName)) {
            loginByAlipay(params, callBack);
        } else if ("loginByLark".equals(testApiName)) {
            loginByLark(params, callBack);
        } else if ("loginByOneAuth".equals(testApiName)) {
            loginByOneAuth(params, callBack);
        } else if ("loginByGoogle".equals(testApiName)) {
            loginByGoogle(params, callBack);
        } else if ("checkQRCodeStatus".equals(testApiName)) {
            checkQRCodeStatus(params, callBack);
        } else if ("changeQrCodeStatus".equals(testApiName)) {
            changeQrCodeStatus(params, callBack);
        } else if ("linkExtIdp".equals(testApiName)) {
            linkExtIdp(params, callBack);
        } else if ("generateLinkExtIdpUrl".equals(testApiName)) {
            generateLinkExtIdpUrl(params, callBack);
        } else if ("unLinkExtIdp".equals(testApiName)) {
            unLinkExtIdp(params, callBack);
        } else if ("getIdentities".equals(testApiName)) {
            getIdentities(params, callBack);
        } else if ("getExtIdps".equals(testApiName)) {
            getExtIdps(params, callBack);
        } else if ("sendSms".equals(testApiName)) {
            sendSms(params, callBack);
        } else if ("sendEmail".equals(testApiName)) {
            sendEmail(params, callBack);
        } else if ("getCurrentUser".equals(testApiName)) {
            getCurrentUser(callBack);
        } else if ("getSecurityInfo".equals(testApiName)) {
            getSecurityInfo(callBack);
        } else if ("getCustomUserData".equals(testApiName)) {
            getCustomUserData(callBack);
        } else if ("bindPhone".equals(testApiName)) {
            bindPhone(params, callBack);
        } else if ("unbindPhone".equals(testApiName)) {
            unBindPhone(callBack);
        } else if ("updatePhone".equals(testApiName)) {
            updatePhone(params, callBack);
        } else if ("bindEmail".equals(testApiName)) {
            bindEmail(params, callBack);
        } else if ("unbindEmail".equals(testApiName)) {
            unbindEmail(callBack);
        } else if ("updateEmail".equals(testApiName)) {
            updateEmail(params, callBack);
        } else if ("resetPasswordByPhoneCode".equals(testApiName)) {
            resetPasswordByPhoneCode(params, callBack);
        } else if ("resetPasswordByEmailCode".equals(testApiName)) {
            resetPasswordByEmailCode(params, callBack);
        } else if ("updatePassword".equals(testApiName)) {
            updatePassword(params, callBack);
        } else if ("updateProfile".equals(testApiName)) {
            updateProfile(params, callBack);
        } else if ("getLoggedHistory".equals(testApiName)) {
            getLoggedHistory(params, callBack);
        } else if ("getLoggedApplication".equals(testApiName)) {
            getLoggedApplication(params, callBack);
        } else if ("getTenantList".equals(testApiName)) {
            getTenantList(params, callBack);
        } else if ("getDepartmentList".equals(testApiName)) {
            getDepartmentList(params, callBack);
        } else if ("getRoleList".equals(testApiName)) {
            getRoleList(params, callBack);
        } else if ("getApplicationList".equals(testApiName)) {
            getApplicationList(callBack);
        } else if ("getAuthorizedResources".equals(testApiName)) {
            getAuthorizedResources(callBack);
        } else if ("getGroupList".equals(testApiName)) {
            getGroupList(callBack);
        } else if ("sendBindMFARequestByPhone".equals(testApiName)) {
            sendBindMFARequestByPhone(params, callBack);
        } else if ("sendBindMFARequestByEmail".equals(testApiName)) {
            sendBindMFARequestByEmail(params, callBack);
        } else if ("sendBindMFARequestByOtp".equals(testApiName)) {
            sendBindMFARequestByOtp(params, callBack);
        } else if ("sendBindMFARequestByFace".equals(testApiName)) {
            sendBindMFARequestByFace(params, callBack);
        } else if ("bindMFAByPhone".equals(testApiName)) {
            bindMFAByPhone(params, callBack);
        } else if ("bindMFAByEmail".equals(testApiName)) {
            bindMFAByEmail(params, callBack);
        } else if ("bindMFAByOtp".equals(testApiName)) {
            bindMFAByOtp(params, callBack);
        } else if ("bindMFAByFace".equals(testApiName)) {
            bindMFAByFace(params, callBack);
        } else if ("unBindMFA".equals(testApiName)) {
            unBindMFA(params, callBack);
        } else if ("getAllBindingMFA".equals(testApiName)) {
            getAllBindingMFA(params, callBack);
        } else if ("getBindingMFA".equals(testApiName)) {
            getBindingMFA(params, callBack);
        } else if ("getUnBindingMFA".equals(testApiName)) {
            getUnBindingMFA(params, callBack);
        } else if ("logout".equals(testApiName)) {
            logout(callBack);
        } else if ("getNewAccessTokenByRefreshToken".equals(testApiName)) {
            getNewAccessTokenByRefreshToken(callBack);
        } else if ("deleteAccount".equals(testApiName)) {
            deleteAccount(params, callBack);
        } else if ("checkAccount".equals(testApiName)) {
            checkAccount(params, callBack);
        } else if ("checkPassword".equals(testApiName)) {
            checkPassword(params, callBack);
        }
    }


    private static void registerByEmailPassword(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
//        SignUpParams signupParams = new SignUpParams();
//        signupParams.setConnection("PASSWORD");
//        PasswordPayload passwordPayload = new PasswordPayload();
//        passwordPayload.setEmail(paramsArr[0]);
//        passwordPayload.setPassword(paramsArr.length > 1 ? paramsArr[1] : "");
//        signupParams.setPasswordPayload(passwordPayload);
//        authClient.register(signupParams, new AuthCallback() {
//            @Override
//            public void call(AuthResponse response) {
//                callBack.showV3Result("registerByEmailPassword", response);
//            }
//        });

        AuthProfile profile = new AuthProfile();
        profile.setNickname("fin");
        AuthOptions options = new AuthOptions();
        options.setPasswordEncryptType(PasswordEncryptType.rsa);
        authClient.signUpByEmailPassword(paramsArr[0],
                paramsArr.length > 1 ? paramsArr[1] : "",
                profile,
                options,
                (AuthCallback) response -> {
                    if (response.getStatusCode() == 200) {
                        JSONObject data = response.getData();
                    }

                    callBack.showV3Result("registerByEmailPassword", response);
                });
    }

    private static void registerByPhoneCode(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        AuthProfile profile = new AuthProfile();
        profile.setName("zjh");
        authClient.signUpByPhonePassCode(paramsArr[0],
                paramsArr.length > 1 ? paramsArr[1] : "",
                paramsArr.length > 2 ? paramsArr[2] : "",
                profile,
                null,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("registerByPhoneCode", response);
                    }
                });
    }

    private static void registerByEmailCode(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        AuthProfile profile = new AuthProfile();
        profile.setGender("M");
        authClient.signUpByEmailPassCode(paramsArr[0],
                paramsArr.length > 1 ? paramsArr[1] : "",
                profile,
                null,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("registerByEmailCode", response);
                    }
                });
    }

    private static void loginByAccount(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        AuthOptions options = new AuthOptions();
        options.setAutoRegister(true);
        options.setPasswordEncryptType(PasswordEncryptType.sm2);
        authClient.signInByAccountPassword(paramsArr[0],
                paramsArr.length > 1 ? paramsArr[1] : "",
                options,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("loginByAccount", response);
                    }
                });
    }

    private static void loginByPhoneCode(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.signInByPhonePassCode(paramsArr[0],
                paramsArr.length > 1 ? paramsArr[1] : "",
                paramsArr.length > 2 ? paramsArr[2] : "",
                null,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("loginByPhoneCode", response);
                    }
                });
    }

    private static void loginByEmailCode(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.signInByEmailPassCode(paramsArr[0],
                paramsArr.length > 1 ? paramsArr[1] : "",
                null,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("loginByEmailCode", response);
                    }
                });
    }

    private static void loginByWechat(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.signInByMobile(Connection.wechat, paramsArr.length > 0 ? paramsArr[0] : "", null, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("loginByWechat", response);
            }
        });
    }

    private static void loginByWecom(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.signInByMobile(Connection.wechatwork, paramsArr.length > 0 ? paramsArr[0] : "", null, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("loginByWecom", response);
            }
        });

    }

    private static void loginByWecomAgency(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.signInByMobile(Connection.wechatwork_agency, paramsArr.length > 0 ? paramsArr[0] : "", null, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("loginByByWeComAgency", response);
            }
        });
    }

    private static void loginByAlipay(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.signInByMobile(Connection.alipay, paramsArr.length > 0 ? paramsArr[0] : "", null, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("loginByAlipay", response);
            }
        });
    }

    private static void loginByLark(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.signInByMobile(Connection.lark_internal, paramsArr.length > 0 ? paramsArr[0] : "", null, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("loginByLarkInternal", response);
            }
        });
    }

    private static void loginByOneAuth(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.signInByOneAuth(paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "", null, new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("loginByLarkInternal", response);
                    }
                });
    }

    private static void loginByGoogle(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.signInByMobile(Connection.google, paramsArr.length > 0 ? paramsArr[0] : "", null, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("loginByGoogle", response);
            }
        });
    }

    private static void checkQRCodeStatus(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.checkQRCodeStatus("PdpZB8puy", new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("checkQRCodeStatus", response);
            }
        });
    }

    private static void changeQrCodeStatus(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        String action = paramsArr.length > 1 ? paramsArr[1] : "";
        authClient.changeQrCodeStatus("PdpZB8puy", Action.SCAN, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("changeQrCodeStatus", response);
            }
        });
    }

    private static void linkExtIdp(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.linkExtIdp(paramsArr.length > 0 ? paramsArr[0] : "", "", "", new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("linkExtIdp", response);
            }
        });
    }

    private static void generateLinkExtIdpUrl(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
//        authClient.generateLinkExtIdpUrl(paramsArr.length > 0 ? paramsArr[0] : "", new AuthCallback() {
//            @Override
//            public void call(AuthResponse response) {
//                callBack.showV3Result("generateLinkExtIdpUrl", response);
//            }
//        });
    }

    private static void unLinkExtIdp(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.unLinkExtIdp(paramsArr.length > 0 ? paramsArr[0] : "", new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("unLinkExtIdp", response);
            }
        });
    }

    private static void getIdentities(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.getIdentities(new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getIdentities", response);
            }
        });
    }

    private static void getExtIdps(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.getExtIdps(new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getExtIdps", response);
            }
        });
    }

    private static void sendSms(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.sendSms(paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "",
                SmsType.CHANNEL_LOGIN, new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("sendSms", response);
                    }
                });
    }

    private static void sendEmail(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.sendEmail(paramsArr.length > 0 ? paramsArr[0] : "",
                EmailType.CHANNEL_RESET_PASSWORD, new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("sendEmail", response);
                    }
                });
    }

    private static void getCurrentUser(IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getProfile(true, true, true, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getCurrentUser", response);
            }
        });
    }

    private static void getSecurityInfo(IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getSecurityLevel((AuthCallback) response -> callBack.showV3Result("getSecurityInfo", response));
    }

    private static void getCustomUserData(IHttpCallBack callBack) {

    }

    private static void getLoggedHistory(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getLoginHistory("",
                "",
                false,
                0,
                0,
                1,
                10,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("getLoggedHistory", response);
                    }
                });
    }

    private static void getLoggedApplication(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getLoggedInApps(new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getLoggedApplication", response);
            }
        });
    }

    private static void getTenantList(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getTenantList(new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getTenantList", response);
            }
        });
    }

    private static void getDepartmentList(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getDepartmentList(1, 10, false, "", "",
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("getDepartmentList", response);
                    }
                });
    }

    private static void getRoleList(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getRoleList("",
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("getRoleList", response);
                    }
                });
    }

    private static void getApplicationList(IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getAccessibleApps(new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getApplicationList", response);
            }
        });
    }

    private static void getAuthorizedResources(IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getAuthorizedResources("", ResourceType.DATA, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getAuthorizedResources", response);
            }
        });
    }

    private static void getGroupList(IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.getGroupList(new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getGroupList", response);
            }
        });
    }

    private static void bindPhone(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.bindPhone(paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "",
                paramsArr.length > 2 ? paramsArr[2] : "",
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("bindPhone", response);
                    }
                });
    }

    private static void unBindPhone(IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.unBindPhone("", new AuthCallback() {

            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("unBindPhone", response);
            }
        });
    }

    private static void updatePhone(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.updatePhoneRequest(paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "",
                paramsArr.length > 2 ? paramsArr[2] : "",
                paramsArr.length > 3 ? paramsArr[3] : "",
                paramsArr.length > 4 ? paramsArr[4] : "",
                paramsArr.length > 5 ? paramsArr[5] : "",
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        Log.e("zjh", "updatePhone response.getStatusCode() = " + response.getStatusCode() + " message = " + response.getMessage());
                        if (response.getStatusCode() == 200 && response.getData() != null) {
                            JSONObject data = response.getData();
                            if (data.has("updatePhoneToken")) {
                                try {
                                    String updatePhoneToken = (String) data.get("updatePhoneToken");
                                    Log.e("zjh", "updatePhoneToken = " + updatePhoneToken);
                                    authClient.updatePhone(updatePhoneToken, new AuthCallback() {
                                        @Override
                                        public void call(AuthResponse response) {
                                            callBack.showV3Result("updatePhone", response);
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            callBack.showV3Result("sendUpdatePhoneRequest", response);
                        }
                    }
                });
    }

    private static void bindEmail(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.bindEmail(paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "",
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("bindEmail", response);
                    }
                });
    }

    private static void unbindEmail(IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.unBindEmail("", new AuthCallback() {

            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("unBindPhone", response);
            }
        });
    }


    private static void updateEmail(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.updateEmailRequest(paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "",
                paramsArr.length > 2 ? paramsArr[2] : "",
                paramsArr.length > 3 ? paramsArr[3] : "",
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        Log.e("zjh", "updateEmail response.getStatusCode() = " + response.getStatusCode() + " message = " + response.getMessage());
                        if (response.getStatusCode() == 200 && response.getData() != null) {
                            JSONObject data = response.getData();
                            if (data.has("updateEmailToken")) {
                                try {
                                    String updateEmailToken = (String) data.get("updateEmailToken");
                                    Log.e("zjh", "updateEmailToken = " + updateEmailToken);
                                    authClient.updateEmail(updateEmailToken, new AuthCallback() {
                                        @Override
                                        public void call(AuthResponse response) {
                                            callBack.showV3Result("updateEmail", response);
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            callBack.showV3Result("sendUpdateEmailRequest", response);
                        }
                    }
                });
    }


    private static void resetPasswordByPhoneCode(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.resetPasswordByPhone(paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "",
                paramsArr.length > 2 ? paramsArr[2] : "",
                paramsArr.length > 3 ? paramsArr[3] : "",
                PasswordEncryptType.none,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("resetPasswordByPhoneCode", response);
                    }
                });

    }

    private static void resetPasswordByEmailCode(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.resetPasswordByEmail(paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "",
                paramsArr.length > 2 ? paramsArr[2] : "",
                PasswordEncryptType.none,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("resetPasswordByEmailCode", response);
                    }
                });

//        AuthClient authClient = new AuthClient();
//        ResetPasswordPayload resetPasswordPayload = new ResetPasswordPayload();
//        resetPasswordPayload.setPhoneCountryCode("+86");
//        resetPasswordPayload.setPhoneNumber("+86");
//        resetPasswordPayload.setPassCode("+86");
//        authClient.resetPasswordRequest(ResetPasswordVerifyMethod.PHONE_PASSCODE, resetPasswordPayload, new AuthCallback() {
//            @Override
//            public void call(AuthResponse response) {
//
//            }
//        });


//        AuthClient authClient = new AuthClient();
//        ResetPasswordPayload resetPasswordPayload = new ResetPasswordPayload();
//        resetPasswordPayload.setEmail("+86");
//        resetPasswordPayload.setPassCode("+86");
//        authClient.resetPasswordRequest(ResetPasswordVerifyMethod.EMAIL_PASSCODE, resetPasswordPayload, new AuthCallback() {
//            @Override
//            public void call(AuthResponse response) {
//
//            }
//        });
    }

    private static void updatePassword(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        authClient.updatePassword(paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "",
                PasswordEncryptType.none,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("updatePassword", response);
                    }
                });
    }

    private static void updateProfile(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        Profile profile = new Profile();
        profile.setName("张三");
        profile.setNickname("张三");
        profile.setPhoto("https://files.authing.co/authing-console/default-user-avatar.png");
        profile.setExternalId("10010");
        profile.setBirthdate("2022-06-03");
        profile.setCountry("CN");
        profile.setProvince("BJ");
        profile.setCity("BJ");
        profile.setRegion("CN");
        profile.setAddress("北京朝阳");
        profile.setStreetAddress("北京朝阳区 xxx 街道");
        profile.setPostalCode("438100");
        profile.setGender(Gender.M);
        profile.setUsername("bob");
        JSONObject customData = new JSONObject();
        try {
            customData.put("height", "170");
            customData.put("org", "11111111111");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        profile.setCustomData(customData);
        authClient.updateProfile(profile, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("updateProfile", response);
            }
        });

    }

    private static void mfaCheck(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");

    }

    private static void mfaVerifyByPhone(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        if (paramsArr.length == 1 || paramsArr.length == 2) {

            return;
        }


    }

    private static void mfaVerifyByEmail(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");

    }

    private static void mfaVerifyByOTP(String params, IHttpCallBack callBack) {

    }

    private static void mfaVerifyByRecoveryCode(String params, IHttpCallBack callBack) {

    }

    private static void bindMFAByPhone(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.enrollFactor(FactorType.SMS, paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "", new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("bindMFAByPhone", response);
                    }
                });
    }

    private static void bindMFAByEmail(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.enrollFactor(FactorType.EMAIL, paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "", new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("bindMFAByEmail", response);
                    }
                });
    }

    private static void bindMFAByOtp(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.enrollFactor(FactorType.OTP, paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "", new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("bindMFAByOtp", response);
                    }
                });
    }

    private static void bindMFAByFace(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.enrollFactor(FactorType.FACE, paramsArr.length > 0 ? paramsArr[0] : "",
                paramsArr.length > 1 ? paramsArr[1] : "", new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("bindMFAByFace", response);
                    }
                });
    }

    private static void unBindMFA(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.resetFactor(paramsArr.length > 0 ? paramsArr[0] : "", new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("unBindMFA", response);
            }
        });
    }

    private static void getAllBindingMFA(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.listEnrolledFactors(new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getAllBindingMFA", response);
            }
        });
    }

    private static void getBindingMFA(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.getFactor(paramsArr.length > 0 ? paramsArr[0] : "", new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getBindingMFA", response);
            }
        });
    }

    private static void getUnBindingMFA(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.listFactorsToEnroll(new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getUnBindingMFA", response);
            }
        });
    }

    private static void sendBindMFARequestByOtp(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.sendEnrollFactorRequest(FactorType.OTP, new FactorProfile(), new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("sendBindMFARequestByOtp", response);
            }
        });
    }

    private static void sendBindMFARequestByFace(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        authClient.sendEnrollFactorRequest(FactorType.FACE, new FactorProfile(), new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("sendBindMFARequestByFace", response);
            }
        });
    }

    private static void sendBindMFARequestByEmail(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        FactorProfile factorProfile = new FactorProfile();
        factorProfile.setEmail(paramsArr[0]);
        authClient.sendEnrollFactorRequest(FactorType.EMAIL, factorProfile,
                new AuthCallback() {
                    @Override
                    public void call(AuthResponse response) {
                        callBack.showV3Result("sendBindMFARequestByEmail", response);
                    }
                });
    }

    private static void sendBindMFARequestByPhone(String params, IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String[] paramsArr = params.split(",");
        FactorProfile factorProfile = new FactorProfile();
        factorProfile.setPhoneCountryCode(paramsArr[0]);
        factorProfile.setPhoneNumber(paramsArr[1]);
        authClient.sendEnrollFactorRequest(FactorType.SMS, factorProfile, new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("sendBindMFARequestByPhone", response);
            }
        });
    }

    private static void logout(IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        authClient.logout(new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("logout", response);
            }
        });
    }

    private static void getNewAccessTokenByRefreshToken(IHttpCallBack callBack) {
        AuthClient authClient = new AuthClient();
        String refreshToken = "";
        if (Authing.getToken()!= null && Authing.getToken().getRefreshToken() != null){
            refreshToken = Authing.getToken().getRefreshToken();
        }
        authClient.getNewAccessTokenByRefreshToken(refreshToken, new AuthRequest(), new AuthCallback() {
            @Override
            public void call(AuthResponse response) {
                callBack.showV3Result("getNewAccessTokenByRefreshToken", response);
            }
        });
    }

    private static void deleteAccount(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");
        AuthClient authClient = new AuthClient();
        if (paramsArr.length == 1) {
            DeleteAccountPayload deleteAccountPayload = new DeleteAccountPayload();
            deleteAccountPayload.setPassword(paramsArr[0]);
            deleteAccountPayload.setPasswordEncryptType(PasswordEncryptType.none);
            authClient.deleteAccountRequest(DeleteAccountVerifyMethod.PASSWORD, deleteAccountPayload, new AuthCallback() {
                @Override
                public void call(AuthResponse response) {
                    deleteAccount("deleteAccountByPasswordRequest", response, authClient, callBack);
                }
            });
        }

        if (paramsArr.length == 2) {
            DeleteAccountPayload deleteAccountPayload = new DeleteAccountPayload();
            deleteAccountPayload.setEmail(paramsArr[0]);
            deleteAccountPayload.setPassCode(paramsArr[1]);
            authClient.deleteAccountRequest(DeleteAccountVerifyMethod.EMAIL_PASSCODE, deleteAccountPayload, new AuthCallback() {
                @Override
                public void call(AuthResponse response) {
                    deleteAccount("deleteAccountByEmailRequest", response, authClient, callBack);
                }
            });
        }

        if (paramsArr.length == 3) {
            DeleteAccountPayload deleteAccountPayload = new DeleteAccountPayload();
            deleteAccountPayload.setPhoneCountryCode(paramsArr[0]);
            deleteAccountPayload.setPhoneNumber(paramsArr[1]);
            deleteAccountPayload.setPassCode(paramsArr[2]);
            authClient.deleteAccountRequest(DeleteAccountVerifyMethod.PHONE_PASSCODE, deleteAccountPayload, new AuthCallback() {
                @Override
                public void call(AuthResponse response) {
                    deleteAccount("deleteAccountByPhoneRequest", response, authClient, callBack);
                }
            });
        }
    }

    private static void deleteAccount(String aipName, AuthResponse response, AuthClient authClient, IHttpCallBack callBack) {
        Log.e("zjh", "deleteAccount response.getStatusCode() = " + response.getStatusCode() + " message = " + response.getMessage());
        if (response.getStatusCode() == 200 && response.getData() != null) {
            JSONObject data = response.getData();
            if (data.has("deleteAccountToken")) {
                try {
                    String deleteAccountToken = (String) data.get("deleteAccountToken");
                    Log.e("zjh", "deleteAccountToken = " + deleteAccountToken);
                    authClient.deleteAccount(deleteAccountToken, new AuthCallback() {
                        @Override
                        public void call(AuthResponse response) {
                            callBack.showV3Result("deleteAccount", response);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            callBack.showV3Result(aipName, response);
        }
    }

    private static void checkAccount(String params, IHttpCallBack callBack) {
        String[] paramsArr = params.split(",");

    }

    private static void checkPassword(String params, IHttpCallBack callBack) {
    }


}
