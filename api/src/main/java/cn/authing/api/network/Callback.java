package cn.authing.api.network;

public interface Callback<T> {
    void call(boolean ok, T data);
}
