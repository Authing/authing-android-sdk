package cn.authing.api.params.type;

public enum SmsType {
    /**
     * 登录
     */
    CHANNEL_LOGIN,
    /**
     * 注册
     */
    CHANNEL_REGISTER,
    /**
     * 重置密码
     */
    CHANNEL_RESET_PASSWORD,
    /**
     * 绑定手机
     */
    CHANNEL_BIND_PHONE,
    /**
     * 解绑手机
     */
    CHANNEL_UNBIND_PHONE,
    /**
     * 绑定MFA
     */
    CHANNEL_BIND_MFA,
    /**
     * 校验MFA
     */
    CHANNEL_VERIFY_MFA,
    /**
     * 解绑MFA
     */
    CHANNEL_UNBIND_MFA,
    /**
     * 注册/登录时补全手机号信息
     */
    CHANNEL_COMPLETE_PHONE,
    /**
     * 进行用户实名认证
     */
    CHANNEL_IDENTITY_VERIFICATION,
    /**
     * 删除账号
     */
    CHANNEL_DELETE_ACCOUNT
}
