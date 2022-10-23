package cn.authing.api.network;

import java.io.Serializable;

import cn.authing.api.data.AuthResponse;

public interface AuthCallback extends Serializable {

    void call(AuthResponse response);
}