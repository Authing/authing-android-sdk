package cn.authing.api.demo;

import android.app.Application;

import cn.authing.api.Authing;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Authing.init(getApplicationContext(), "60caaf41df670b771fd08937");
        Authing.setAuthProtocol(Authing.AuthProtocol.EOIDC);

    }
}
