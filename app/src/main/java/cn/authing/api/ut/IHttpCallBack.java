package cn.authing.api.ut;

import cn.authing.api.data.AuthResponse;

public interface IHttpCallBack {

    void showV3Result(String apiName, AuthResponse response);
}
