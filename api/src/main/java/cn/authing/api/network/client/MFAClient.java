package cn.authing.api.network.client;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import cn.authing.api.network.AuthCallback;
import cn.authing.api.network.Guardian;
import cn.authing.api.params.FactorProfile;
import cn.authing.api.params.type.FactorType;

public class MFAClient extends BaseClient {

    private MFAClient() {
    }

    private static class MFASingletonHolder {
        private static final MFAClient mInstance = new MFAClient();
    }

    public static MFAClient getInstance() {
        return MFAClient.MFASingletonHolder.mInstance;
    }

    public void sendEnrollFactorRequest(@NotNull FactorType factorType, @NotNull FactorProfile profile, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("factorType", factorType.toString());
            body.put("profile", profile.toJSON());
        } catch (Exception e) {
            error(e, callback);
        }

        Guardian.post("/api/v3/send-enroll-factor-request", body, callback::call);
    }

    public void enrollFactor(@NotNull FactorType factorType, String enrollmentToken, String passCode, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("factorType", factorType.toString());
            body.put("enrollmentToken", enrollmentToken);
            JSONObject enrollmentData = new JSONObject();
            if (passCode != null) {
                enrollmentData.put("passCode", passCode);
            }
            body.put("enrollmentData", enrollmentData);
        } catch (Exception e) {
            error(e, callback);
            return;
        }

        Guardian.post("/api/v3/enroll-factor", body, callback::call);
    }

    public void resetFactor(String factorId, @NotNull AuthCallback callback) {
        JSONObject body = new JSONObject();
        try {
            body.put("factorId", factorId);
        } catch (Exception e) {
            error(e, callback);
            return;
        }

        Guardian.post("/api/v3/reset-factor", body, callback::call);
    }

    public void listEnrolledFactors(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/list-enrolled-factors", callback::call);
    }

    public void getFactor(String factorId, @NotNull AuthCallback callback) {
        Guardian.get("/api/v3/get-factor?factorId=" + factorId, callback::call);
    }

    public void listFactorsToEnroll(@NotNull AuthCallback callback) {
        Guardian.get("/api/v3/list-factors-to-enroll", callback::call);
    }

}
