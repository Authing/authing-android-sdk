package cn.authing.api.demo.ut;

import cn.authing.api.data.AuthResponse;

public interface IHttpCallBack {

    void showV3Result(String apiName, AuthResponse response);
}
