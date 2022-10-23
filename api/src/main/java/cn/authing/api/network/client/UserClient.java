package cn.authing.api.network.client;

import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

import cn.authing.api.Authing;
import cn.authing.api.data.AuthResponse;
import cn.authing.api.data.Safe;
import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.CookieManager;
import cn.authing.api.network.Guardian;
import cn.authing.api.network.Uploader;
import cn.authing.api.params.DeleteAccountPayload;
import cn.authing.api.params.Profile;
import cn.authing.api.params.ResetPasswordPayload;
import cn.authing.api.params.type.DeleteAccountVerifyMethod;
import cn.authing.api.params.type.PasswordEncryptType;
import cn.authing.api.params.type.PasswordStrength;
import cn.authing.api.params.type.ResetPasswordVerifyMethod;
import cn.authing.api.params.type.ResourceType;
import cn.authing.api.util.ErrorCode;
import cn.authing.api.util.Util;
import cn.authing.api.util.Validator;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class UserClient extends BaseClient {

    private UserClient() {
    }

    private static class UserInfoSingletonHolder {
        private static final UserClient mInstance = new UserClient();
    }

    public static UserClient getInstance() {
        return UserInfoSingletonHolder.mInstance;
    }

    public void getProfile(boolean withCustomData, boolean withIdentities, boolean withDepartmentIds, @NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-profile?withCustomData=" + withCustomData + "&withIdentities=" + withIdentities + "&withDepartmentIds=" + withDepartmentIds, callback::call);
    }

    public void updateProfile(@NotNull Profile profile, @NotNull AuthCallback callback) {
        Guardian.post("/api/v3/update-profile", profile.toJSON(), callback::call);
    }

    public void uploadAvatar(InputStream in, @NotNull AuthCallback callback) {
        Uploader.uploadImage(in, (ok, uploadedUrl) -> {
            if (ok && !Util.isNull(uploadedUrl)) {
                Profile profile = new Profile();
                profile.setPhoto(uploadedUrl);
                updateProfile(profile, callback);
            } else {
                callback.call(new AuthResponse(ErrorCode.ERROR_CODE_10009, "Upload avatar failed", null));
            }
        });
    }

    public PasswordStrength computePasswordSecurityLevel(String password) {
        if (password.length() < 6) {
            return PasswordStrength.EWeak;
        }

        boolean hasEnglish = Validator.hasEnglish(password);
        boolean hasNumber = Validator.hasNumber(password);
        boolean hasSpecialChar = Validator.hasSpecialCharacter(password);
        if (hasEnglish && hasNumber && hasSpecialChar) {
            return PasswordStrength.EStrong;
        } else if ((hasEnglish && hasNumber) ||
                (hasEnglish && hasSpecialChar) ||
                (hasNumber && hasSpecialChar)) {
            return PasswordStrength.EMedium;
        } else {
            return PasswordStrength.EWeak;
        }
    }

    public void getSecurityLevel(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-security-info", callback::call);
    }

    public void bindPhone(String phoneCountryCode, String phoneNumber, String passCode, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("phoneCountryCode", phoneCountryCode);
            body.put("phoneNumber", phoneNumber);
            body.put("passCode", passCode);
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/bind-phone", body, callback::call);
    }

    public void unBindPhone(@NotNull AuthCallback callback) {
        try {
            JSONObject body = new JSONObject();
            Guardian.post("/api/v3/unbind-phone", body, callback::call);
        } catch (Exception e) {
            error(e, callback);
        }
    }

    public void bindEmail(String email, String passCode, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("email", email);
            body.put("passCode", passCode);
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/bind-email", body, callback::call);
    }

    public void unBindEmail(@NotNull AuthCallback callback) {
        try {
            JSONObject body = new JSONObject();
            Guardian.post("/api/v3/unbind-email", body, callback::call);
        } catch (Exception e) {
            error(e, callback);
        }
    }

    public void updatePhoneRequest(String newPhoneCountryCode, String newPhoneNumber, String newPhonePassCode,
                                   String oldPhoneCountryCode, String oldPhoneNumber, String oldPhonePassCode,
                                   @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("verifyMethod", "PHONE_PASSCODE");
            JSONObject phonePassCodePayload = new JSONObject();
            if (!TextUtils.isEmpty(newPhoneCountryCode)) {
                phonePassCodePayload.put("newPhoneCountryCode", newPhoneCountryCode);
            }
            if (!TextUtils.isEmpty(newPhoneNumber)) {
                phonePassCodePayload.put("newPhoneNumber", newPhoneNumber);
            }
            if (!TextUtils.isEmpty(newPhonePassCode)) {
                phonePassCodePayload.put("newPhonePassCode", newPhonePassCode);
            }
            if (!TextUtils.isEmpty(oldPhoneCountryCode)) {
                phonePassCodePayload.put("oldPhoneCountryCode", oldPhoneCountryCode);
            }
            if (!TextUtils.isEmpty(oldPhoneNumber)) {
                phonePassCodePayload.put("oldPhoneNumber", oldPhoneNumber);
            }
            if (!TextUtils.isEmpty(oldPhonePassCode)) {
                phonePassCodePayload.put("oldPhonePassCode", oldPhonePassCode);
            }
            body.put("phonePassCodePayload", phonePassCodePayload);
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/verify-update-phone-request", body, callback::call);
    }

    public void updatePhone(String updatePhoneToken, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("updatePhoneToken", updatePhoneToken);
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/update-phone", body, callback::call);
    }

    public void updateEmailRequest(String newEmail, String newEmailPassCode,
                                   String oldEmail, String oldEmailPassCode,
                                   @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("verifyMethod", "EMAIL_PASSCODE");
            JSONObject emailPasscodePayload = new JSONObject();
            if (!TextUtils.isEmpty(newEmail)) {
                emailPasscodePayload.put("newEmail", newEmail);
            }
            if (!TextUtils.isEmpty(newEmailPassCode)) {
                emailPasscodePayload.put("newEmailPassCode", newEmailPassCode);
            }
            if (!TextUtils.isEmpty(oldEmail)) {
                emailPasscodePayload.put("oldEmail", oldEmail);
            }
            if (!TextUtils.isEmpty(oldEmailPassCode)) {
                emailPasscodePayload.put("oldEmailPassCode", oldEmailPassCode);
            }
            body.put("emailPasscodePayload", emailPasscodePayload);
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/verify-update-email-request", body, callback::call);
    }

    public void updateEmail(String updateEmailToken, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("updateEmailToken", updateEmailToken);
        } catch (Exception e) {
            error(e, callback);
        }
        Guardian.post("/api/v3/update-email", body, callback::call);
    }

    public void updatePassword(String newPassword, String oldPassword, PasswordEncryptType passwordEncryptType, @NotNull AuthCallback callback) {
        getPublicKey(passwordEncryptType, (ok, data) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("newPassword", encryptPassword(newPassword, passwordEncryptType, data));
                body.put("oldPassword", encryptPassword(oldPassword, passwordEncryptType, data));
                body.put("passwordEncryptType", passwordEncryptType.toString());
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            Guardian.post("/api/v3/update-password", body, callback::call);
        });
    }

    public void resetPasswordByPhone(String phoneCountryCode, String phoneNumber, String passCode, String password, PasswordEncryptType passwordEncryptType, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("verifyMethod", "PHONE_PASSCODE");
            JSONObject phonePassCodePayload = new JSONObject();
            phonePassCodePayload.put("phoneCountryCode", phoneCountryCode);
            phonePassCodePayload.put("phoneNumber", phoneNumber);
            phonePassCodePayload.put("passCode", passCode);
            body.put("phonePassCodePayload", phonePassCodePayload);
        } catch (Exception e) {
            error(e, callback);
            return;
        }

        Guardian.post("/api/v3/verify-reset-password-request", body, response -> {
            if (response.getStatusCode() == 200 && response.getData() != null) {
                JSONObject data = response.getData();
                if (data.has("passwordResetToken")) {
                    try {
                        String passwordResetToken = (String) data.get("passwordResetToken");
                        resetPassword(passwordResetToken, password, passwordEncryptType, callback);
                    } catch (JSONException e) {
                        error(e, callback);
                    }
                }
            } else {
                callback.call(response);
            }
        });
    }

    public void resetPasswordByEmail(String email, String passCode, String password, PasswordEncryptType passwordEncryptType, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("verifyMethod", "EMAIL_PASSCODE");
            JSONObject emailPassCodePayload = new JSONObject();
            emailPassCodePayload.put("email", email);
            emailPassCodePayload.put("passCode", passCode);
            body.put("emailPassCodePayload", emailPassCodePayload);
        } catch (Exception e) {
            error(e, callback);
        }

        Guardian.post("/api/v3/verify-reset-password-request", body, response -> {
            if (response.getStatusCode() == 200 && response.getData() != null) {
                JSONObject data = response.getData();
                if (data.has("passwordResetToken")) {
                    try {
                        String passwordResetToken = (String) data.get("passwordResetToken");
                        resetPassword(passwordResetToken, password, passwordEncryptType, callback);
                    } catch (JSONException e) {
                        error(e, callback);
                    }
                }
            } else {
                callback.call(response);
            }
        });
    }

    public void resetPasswordRequest(@NotNull ResetPasswordVerifyMethod verifyMethod, @NotNull ResetPasswordPayload resetPasswordPayload, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("verifyMethod", verifyMethod.toString());
            if (verifyMethod == ResetPasswordVerifyMethod.PHONE_PASSCODE) {
                body.put("phonePassCodePayload", resetPasswordPayload.toJSON());
            } else if (verifyMethod == ResetPasswordVerifyMethod.EMAIL_PASSCODE) {
                body.put("emailPassCodePayload", resetPasswordPayload.toJSON());
            }
        } catch (Exception e) {
            error(e, callback);
            return;
        }
        Guardian.post("/api/v3/verify-reset-password-request", body, callback::call);
    }

    public void resetPassword(String passwordResetToken, String password, PasswordEncryptType passwordEncryptType, @NotNull AuthCallback callback) {
        getPublicKey(passwordEncryptType, (ok, data) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("passwordResetToken", passwordResetToken);
                body.put("password", encryptPassword(password, passwordEncryptType, data));
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            Guardian.post("/api/v3/reset-password", body, callback::call);
        });
    }

    public void deleteAccountRequest(@NotNull DeleteAccountVerifyMethod verifyMethod, @NotNull DeleteAccountPayload deleteAccountPayload, @NotNull AuthCallback callback) {
        getPublicKey(deleteAccountPayload.getPasswordEncryptType(), (ok, data) -> {
            JSONObject body = new JSONObject();
            try {
                body.put("verifyMethod", verifyMethod.toString());
                if (verifyMethod == DeleteAccountVerifyMethod.PHONE_PASSCODE) {
                    body.put("phonePassCodePayload", deleteAccountPayload.toJSON());
                } else if (verifyMethod == DeleteAccountVerifyMethod.EMAIL_PASSCODE) {
                    body.put("emailPassCodePayload", deleteAccountPayload.toJSON());
                } else if (verifyMethod == DeleteAccountVerifyMethod.PASSWORD) {
                    body.put("passwordPayload", deleteAccountPayload.toJSON());
                }
            } catch (Exception e) {
                error(e, callback);
                return;
            }
            Guardian.post("/api/v3/verify-delete-account-request", body, callback::call);
        });
    }

    public void deleteAccount(String deleteAccountToken, @NotNull AuthCallback callback) {
        try {
            JSONObject body = new JSONObject();
            body.put("deleteAccountToken", deleteAccountToken);
            Guardian.post("/api/v3/delete-account", body, response -> {
                clearToken(response);
                callback.call(response);
            });
        } catch (Exception e) {
            error(e, callback);
        }
    }

    public void logout(@NotNull AuthCallback callback) {
//        UserInfo userInfo = Authing.getCurrentUser();
//        String token = "";
//        if (userInfo != null){
//            token = userInfo.getAccessToken();
//            if (token == null){
//                token = userInfo.getIdToken();
//            }
//        }
//        Guardian.get("oidc/session/end?id_token_hint="+idToken, response -> {
//            clearToken(response);
//            callback.call(response);
//        });

        getPublicConfig(callback, (ok, config) -> {
            String token = getAccessToken();
            if (token == null) {
                token = getIdToken();
            }

            try {
                RequestBody formBody = new FormBody.Builder()
                        .add("client_id", Authing.getAppId())
                        .add("token", token)
                        .build();
                String url = Authing.getScheme() + "://" + Util.getHost(config) + "/oidc/token/revocation";
                Guardian.authRequest(url, "post", formBody, response -> {
                    clearToken(response);
                    callback.call(response);
                });
            } catch (Exception e) {
                error(e, callback);
            }
        });
    }

    public void getLoginHistory(String appId, String clientIp, boolean success, long start, long end, int page, int limit, @NotNull AuthCallback callback) {
        StringBuilder params = new StringBuilder();
        if (!Util.isNull(appId)) {
            params.append("appId=");
            params.append(appId);
            params.append("&");
        }
        if (!Util.isNull(clientIp)) {
            params.append("clientIp=");
            params.append(clientIp);
            params.append("&");
        }
        if (start != 0) {
            params.append("start=");
            params.append(start);
            params.append("&");
        }
        if (end != 0) {
            params.append("end=");
            params.append(end);
            params.append("&");
        }
        if (page != 0) {
            params.append("page=");
            params.append(page);
            params.append("&");
        }
        if (limit != 0) {
            params.append("limit=");
            params.append(limit);
            params.append("&");
        }
        params.append("success=");
        params.append(success);
        Guardian.get("/api/v3/get-my-login-history?" + params, callback::call);
    }

    public void getLoggedInApps(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-my-logged-in-apps", callback::call);
    }

    public void getAccessibleApps(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-my-accessible-apps", callback::call);
    }

    public void getTenantList(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-my-tenant-list", callback::call);
    }

    public void getRoleList(String namespace, @NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-my-role-list?namespace=" + namespace, callback::call);
    }

    public void getGroupList(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-my-group-list", callback::call);
    }

    public void getDepartmentList(int page, int limit, boolean withCustomData, String sortBy, String orderBy, @NotNull AuthCallback callback) {
        StringBuilder params = new StringBuilder();
        if (page != 0) {
            params.append("page=");
            params.append(page);
            params.append("&");
        }
        if (limit != 0) {
            params.append("limit=");
            params.append(limit);
            params.append("&");
        }
        if (!Util.isNull(sortBy)) {
            params.append("sortBy=");
            params.append(sortBy);
            params.append("&");
        }
        if (!Util.isNull(orderBy)) {
            params.append("orderBy=");
            params.append(sortBy);
            params.append("&");
        }
        params.append("withCustomData=");
        params.append(withCustomData);
        Guardian.get("/api/v3/get-my-department-list?" + params, callback::call);
    }

    public void getAuthorizedResources(String namespace, ResourceType resourceType, @NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-my-authorized-resources?namespace=" + namespace + "&resourceType=" + resourceType.toString(), callback::call);
    }

    private void clearToken(AuthResponse response) {
        if (response.getStatusCode() == 200) {
            Safe.clearToken();
            Authing.setToken(null);
            CookieManager.removeAllCookies();
        }
    }
}
