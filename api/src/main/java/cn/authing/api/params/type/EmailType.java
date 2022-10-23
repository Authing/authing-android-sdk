package cn.authing.api.params.type;

public enum EmailType {
    CHANNEL_LOGIN, //登录
    CHANNEL_REGISTER, //注册
    CHANNEL_RESET_PASSWORD, //重置密码
    CHANNEL_VERIFY_EMAIL_LINK, //验证邮箱地址
    CHANNEL_UPDATE_EMAIL, //修改邮箱
    CHANNEL_BIND_EMAIL, //绑定邮箱
    CHANNEL_UNBIND_EMAIL, //解绑邮箱
    CHANNEL_VERIFY_MFA, //验证 MFA
    CHANNEL_UNLOCK_ACCOUNT, //自助解锁
    CHANNEL_COMPLETE_EMAIL, //注册/登录时补全邮箱信息
    CHANNEL_DELETE_ACCOUNT //用于注销账号
}
