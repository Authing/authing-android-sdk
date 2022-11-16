package cn.authing.api.params.type;

public enum EmailType {
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
     * 验证邮箱地址
     */
    CHANNEL_VERIFY_EMAIL_LINK,
    /**
     * 修改邮箱
     */
    CHANNEL_UPDATE_EMAIL,
    /**
     * 绑定邮箱
     */
    CHANNEL_BIND_EMAIL,
    /**
     * 解绑邮箱
     */
    CHANNEL_UNBIND_EMAIL,
    /**
     * 验证 MFA
     */
    CHANNEL_VERIFY_MFA,
    /**
     * 自助解锁
     */
    CHANNEL_UNLOCK_ACCOUNT,
    /**
     * 注册/登录时补全邮箱信息
     */
    CHANNEL_COMPLETE_EMAIL,
    /**
     * 用于注销账号
     */
    CHANNEL_DELETE_ACCOUNT
}
