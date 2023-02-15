package cn.authing.api.params.type;

public enum Connection {
    /**
     * 微信移动应用
     */
    wechat,
    /**
     * 支付宝移动应用
     */
    alipay,
    /**
     * 企业微信移动应用
     */
    wechatwork,
    /**
     * 企业微信移动应用（代开发模式）
     */
    wechatwork_agency,
    /**
     * 飞书移动端企业自建应用
     */
    lark_internal,
    /**
     * 飞书移动端应用商店应用
     */
    lark_public,
    /**
     * 网易易盾一键登录
     */
    yidun,
    /**
     * 微信小程序使用 code 登录
     */
    wechat_mini_program_code,
    /**
     * 微信小程序使用手机号登录
     */
    wechat_mini_program_phone,
    /**
     * 微信小程序使用 code 和手机号登录
     */
    wechat_mini_program_code_and_phone,
    /**
     * Google 移动端社会化登录
     */
    google,
    /**
     * Facebook 移动端社会化登录
     */
    facebook,
}
