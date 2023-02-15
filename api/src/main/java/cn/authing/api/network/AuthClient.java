package cn.authing.api.network;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

import cn.authing.api.network.client.AccountBindingClient;
import cn.authing.api.network.client.CodeClient;
import cn.authing.api.network.client.CommonClient;
import cn.authing.api.network.client.MFAClient;
import cn.authing.api.network.client.OIDCClient;
import cn.authing.api.network.client.SignInClient;
import cn.authing.api.network.client.SignUpClient;
import cn.authing.api.network.client.UserClient;
import cn.authing.api.params.AuthOptions;
import cn.authing.api.params.AuthProfile;
import cn.authing.api.params.DeleteAccountPayload;
import cn.authing.api.params.FactorProfile;
import cn.authing.api.params.Profile;
import cn.authing.api.params.ResetPasswordPayload;
import cn.authing.api.params.type.Action;
import cn.authing.api.params.type.Connection;
import cn.authing.api.params.type.DeleteAccountVerifyMethod;
import cn.authing.api.params.type.EmailType;
import cn.authing.api.params.type.FactorType;
import cn.authing.api.params.type.PasswordEncryptType;
import cn.authing.api.params.type.PasswordStrength;
import cn.authing.api.params.type.ResetPasswordVerifyMethod;
import cn.authing.api.params.type.ResourceType;
import cn.authing.api.params.type.SmsType;


public class AuthClient {

    /**
     * 注册 - 用户名+密码
     *
     * @param username 用户名。
     * @param password 用户密码，默认不加密。
     * @param profile  用户资料
     * @param options  可选参数
     *                 clientIp 客户端 IP
     *                 context
     *                 passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     * @param callback 回调
     */
    public void signUpByUserNamePassword(String username, String password, AuthProfile profile, AuthOptions options, @NotNull AuthCallback callback) {
        SignUpClient.getInstance().signUpByUserNamePassword(username, password, profile, options, callback);
    }

    /**
     * 注册 - 邮箱+密码
     *
     * @param email    邮箱，不区分大小写。
     * @param password 用户密码，默认不加密。
     * @param profile  用户资料
     * @param options  可选参数
     *                 clientIp 客户端 IP
     *                 context
     *                 passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     * @param callback 回调
     */
    public void signUpByEmailPassword(String email, String password, AuthProfile profile, AuthOptions options, @NotNull AuthCallback callback) {
        SignUpClient.getInstance().signUpByEmailPassword(email, password, profile, options, callback);
    }

    /**
     * 注册 - 邮箱+验证码
     *
     * @param email    邮箱，不区分大小写。
     * @param passCode 一次性临时验证码，你需要先调用发送短信或者发送邮件接口获取验证码。
     * @param profile  用户资料
     * @param options  可选参数
     * @param callback 回调
     */
    public void signUpByEmailPassCode(String email, String passCode, AuthProfile profile, AuthOptions options, @NotNull AuthCallback callback) {
        SignUpClient.getInstance().signUpByEmailPassCode(email, passCode, profile, options, callback);
    }

    /**
     * 注册 - 手机号+验证码
     *
     * @param phoneCountryCode 手机区号，中国大陆手机号可不填。Default: "+86"
     * @param phone            手机号
     * @param passCode         一次性临时验证码，你需要先调用发送短信或者发送邮件接口获取验证码。
     * @param profile          用户资料
     * @param options          可选参数
     * @param callback         回调
     */
    public void signUpByPhonePassCode(String phoneCountryCode, String phone, String passCode, AuthProfile profile, AuthOptions options, @NotNull AuthCallback callback) {
        SignUpClient.getInstance().signUpByPhonePassCode(phoneCountryCode, phone, passCode, profile, options, callback);
    }

    /**
     * 登录 - 账号+密码
     *
     * @param account  用户账号（用户名/手机号/邮箱）
     * @param password 用户密码，默认不加密。
     * @param options  可选参数
     *                 passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     *                 autoRegister 是否开启自动注册。如果设置为 true，当用户不存在的时候，会先自动为其创建一个账号。
     *                 scope        需要请求的权限，必须包含 openid。如果需要获取手机号和 email 需要包含 phone email；
     *                 如果需要 refresh_token 需要包含 offline_access。多个 scope 请用空格分隔。
     *                 id_token 解码后的内容中会包含这些 scope 对应的用户信息相关的字段。
     * @param callback 回调
     */
    public void signInByAccountPassword(String account, String password, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByAccountPassword(account, password, options, callback);
    }

    /**
     * 登录 - 用户名+密码
     *
     * @param username 用户名
     * @param password 用户密码，默认不加密。
     * @param options  可选参数
     *                 passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     *                 autoRegister 是否开启自动注册。如果设置为 true，当用户不存在的时候，会先自动为其创建一个账号。
     *                 scope        需要请求的权限，必须包含 openid。如果需要获取手机号和 email 需要包含 phone email；
     *                 如果需要 refresh_token 需要包含 offline_access。多个 scope 请用空格分隔。
     *                 id_token 解码后的内容中会包含这些 scope 对应的用户信息相关的字段。
     * @param callback 回调
     */
    public void signInByUserNamePassword(String username, String password, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByUserNamePassword(username, password, options, callback);
    }

    /**
     * 登录 - 邮箱+密码
     *
     * @param email    邮箱
     * @param password 用户密码，默认不加密。
     * @param options  可选参数
     *                 passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     *                 autoRegister 是否开启自动注册。如果设置为 true，当用户不存在的时候，会先自动为其创建一个账号。
     *                 scope        需要请求的权限，必须包含 openid。如果需要获取手机号和 email 需要包含 phone email；
     *                 如果需要 refresh_token 需要包含 offline_access。多个 scope 请用空格分隔。
     *                 id_token 解码后的内容中会包含这些 scope 对应的用户信息相关的字段。
     * @param callback 回调
     */
    public void signInByEmailPassword(String email, String password, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByEmailPassword(email, password, options, callback);
    }

    /**
     * 登录 - 手机号+密码
     *
     * @param phone    手机号
     * @param password 用户密码，默认不加密。
     * @param options  可选参数
     *                 passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     *                 autoRegister 是否开启自动注册。如果设置为 true，当用户不存在的时候，会先自动为其创建一个账号。
     *                 scope        需要请求的权限，必须包含 openid。如果需要获取手机号和 email 需要包含 phone email；
     *                 如果需要 refresh_token 需要包含 offline_access。多个 scope 请用空格分隔。
     *                 id_token 解码后的内容中会包含这些 scope 对应的用户信息相关的字段。
     * @param callback 回调
     */
    public void signInByPhonePassword(String phone, String password, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByPhonePassword(phone, password, options, callback);
    }

    /**
     * 登录 - 手机号+验证码
     *
     * @param phoneCountryCode 手机区号，中国大陆手机号可不填。Default: "+86"
     * @param phone            手机号
     * @param passCode         一次性临时验证码，你需要先调用发送短信接口获取验证码。
     * @param options          可选参数
     *                         autoRegister 是否开启自动注册。如果设置为 true，当用户不存在的时候，会先自动为其创建一个账号。
     *                         scope        需要请求的权限，必须包含 openid。如果需要获取手机号和 email 需要包含 phone email；
     *                         如果需要 refresh_token 需要包含 offline_access。多个 scope 请用空格分隔。
     *                         id_token 解码后的内容中会包含这些 scope 对应的用户信息相关的字段。
     * @param callback         回调
     */
    public void signInByPhonePassCode(String phoneCountryCode, String phone, String passCode, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByPhonePassCode(phoneCountryCode, phone, passCode, options, callback);
    }

    /**
     * 登录 - 邮箱+验证码
     *
     * @param email    邮箱，不区分大小写。
     * @param passCode 一次性临时验证码，你需要先调用发送邮件接口获取验证码。
     * @param options  可选参数
     *                 autoRegister 是否开启自动注册。如果设置为 true，当用户不存在的时候，会先自动为其创建一个账号。
     *                 scope        需要请求的权限，必须包含 openid。如果需要获取手机号和 email 需要包含 phone email；
     *                 如果需要 refresh_token 需要包含 offline_access。多个 scope 请用空格分隔。
     *                 id_token 解码后的内容中会包含这些 scope 对应的用户信息相关的字段。
     * @param callback 回调
     */
    public void signInByEmailPassCode(String email, String passCode, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByEmailPassCode(email, passCode, options, callback);
    }

    /**
     * 登录 - AD账号+密码
     *
     * @param sAMAccountName Windows AD 用户目录中账号的 sAMAccountName
     * @param password       用户密码，默认不加密。
     * @param options        可选参数
     *                       passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     *                       autoRegister 是否开启自动注册。如果设置为 true，当用户不存在的时候，会先自动为其创建一个账号。
     *                       scope        需要请求的权限，必须包含 openid。如果需要获取手机号和 email 需要包含 phone email；
     *                       如果需要 refresh_token 需要包含 offline_access。多个 scope 请用空格分隔。
     *                       id_token 解码后的内容中会包含这些 scope 对应的用户信息相关的字段。
     * @param callback       回调
     */
    public void signInByAD(String sAMAccountName, String password, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByAD(sAMAccountName, password, options, callback);
    }

    /**
     * 登录 - LDAP账号+密码
     *
     * @param sAMAccountName LDAP AD 用户目录中账号的 sAMAccountName
     * @param password       用户密码，默认不加密。
     * @param options        可选参数
     *                       passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     *                       autoRegister 是否开启自动注册。如果设置为 true，当用户不存在的时候，会先自动为其创建一个账号。
     *                       scope        需要请求的权限，必须包含 openid。如果需要获取手机号和 email 需要包含 phone email；
     *                       如果需要 refresh_token 需要包含 offline_access。多个 scope 请用空格分隔。
     *                       id_token 解码后的内容中会包含这些 scope 对应的用户信息相关的字段。
     * @param callback       回调
     */
    public void signInByLDAP(String sAMAccountName, String password, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByLDAP(sAMAccountName, password, options, callback);
    }

    /**
     * 登录 - 一键登录
     *
     * @param token       网易易盾 token
     * @param accessToken 网易易盾运营商授权码
     * @param options     可选参数
     * @param callback    回调
     */
    public void signInByOneAuth(String token, String accessToken, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByOneAuth(token, accessToken, options, callback);
    }

    /**
     * 登录 - AP P拉起微信小程序授权登录
     *
     * @param code          wx.login 接口返回的用户 code
     * @param phoneInfoCode 小程序获取用户手机号返回的 code
     * @param options       可选参数
     * @param callback      回调
     */
    public void signInByWechatMiniProgram(String code, String phoneInfoCode, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByWechatMiniProgram(code, phoneInfoCode, options, callback);
    }

    /**
     * 登录 - 社会化身份源
     *
     * @param connection 移动端社会化登录类型：
     *                   wechat: 微信移动应用
     *                   alipay: 支付宝移动应用
     *                   wechatwork: 企业微信移动应用
     *                   wechatwork_agency: 企业微信移动应用（代开发模式）
     *                   lark_internal: 飞书移动端企业自建应用
     *                   lark_public: 飞书移动端应用商店应用
     *                   yidun: 网易易盾一键登录
     *                   google: Google 移动端社会化登录
     *                   facebook: Facebook 移动端社会化登录
     * @param code       授权码
     * @param options    可选参数
     * @param callback   回调
     */
    public void signInByMobile(Connection connection, String code, AuthOptions options, @NotNull AuthCallback callback) {
        SignInClient.getInstance().signInByMobile(connection, code, options, callback);
    }

    /**
     * 查询二维码状态
     *
     * @param qrcodeId 二维码唯一 ID，通过扫码获取
     * @param callback 回调
     *                 status：二维码状态。按照用户扫码顺序，共分为未扫码、已扫码等待用户确认、用户同意/取消授权、二维码过期以及未知错误六种状态。
     *                 Enum: "EXPIRED" "PENDING" "SCANNED" "AUTHORIZED" "CANCELLED" "ERROR"
     */
    public void checkQRCodeStatus(String qrcodeId, @NotNull AuthCallback callback) {
        SignInClient.getInstance().checkQRCodeStatus(qrcodeId, callback);
    }

    /**
     * 使用二维码的 ticket 换取用户的 access_token 和 id_token
     *
     * @param ticket        当二维码状态为已授权时返回
     * @param client_id     应用 ID。当应用的「换取 token 身份验证方式」配置为 client_secret_post 需要传。
     * @param client_secret 应用密钥。当应用的「换取 token 身份验证方式」配置为 client_secret_post 需要传。
     * @param callback      回调
     */
    public void exchangeTokenSetWithQRCode(String ticket, String client_id, String client_secret, @NotNull AuthCallback callback) {
        SignInClient.getInstance().exchangeTokenSetWithQRCode(ticket, client_id, client_secret, callback);
    }

    /**
     * 自建 APP 扫码登录：APP 端修改二维码状态
     *
     * @param qrcodeId 二维码唯一 ID
     * @param action   修改二维码状态的动作:
     *                 Example: action=CONFIRM
     *                 Enum: "SCAN" "CONFIRM" "CANCEL"
     *                 修改二维码状态的动作:
     *                 SCAN: 修改二维码状态为已扫码状态，当移动 APP 扫了码之后，应当立即执行此操作；
     *                 CONFIRM: 修改二维码状态为已授权，执行此操作前必须先执行 `SCAN 操作；
     *                 CANCEL: 修改二维码状态为已取消，执行此操作前必须先执行 `SCAN 操作；
     * @param callback 回调
     */
    public void changeQrCodeStatus(String qrcodeId, Action action, @NotNull AuthCallback callback) {
        SignInClient.getInstance().changeQrCodeStatus(qrcodeId, action, callback);
    }

    /**
     * 绑定外部身份源
     *
     * @param ext_idp_conn_identifier 外部身份源连接唯一标志
     *                                Example: ext_idp_conn_identifier=my-wechat
     * @param callback                回调
     */
    /**
     * 绑定外部身份源
     *
     * @param ext_idp_conn_identifier 外部身份源连接唯一标志
     *                                *                                Example: ext_idp_conn_identifier=my-wechat
     * @param appId                   Authing 应用 ID
     * @param idToken                 用户的 id_token
     * @param callback                回调
     */
    public void linkExtIdp(String ext_idp_conn_identifier, String appId, String idToken, @NotNull AuthCallback callback) {
        AccountBindingClient.getInstance().linkExtIdp(ext_idp_conn_identifier, appId, idToken, callback);
    }

    /**
     * 解绑外部身份源
     *
     * @param extIdpId 外部身份源 ID
     * @param callback 回调
     */
    public void unLinkExtIdp(String extIdpId, @NotNull AuthCallback callback) {
        AccountBindingClient.getInstance().unLinkExtIdp(extIdpId, callback);
    }

    /**
     * 获取绑定的外部身份源
     *
     * @param callback 回调
     */
    public void getIdentities(@NotNull AuthCallback callback) {
        AccountBindingClient.getInstance().getIdentities(callback);
    }

    /**
     * 获取应用开启的外部身份源列表
     *
     * @param callback 回调
     */
    public void getExtIdps(@NotNull AuthCallback callback) {
        AccountBindingClient.getInstance().getExtIdps(callback);
    }


    /**
     * 发送短信验证码
     *
     * @param phoneCountryCode 手机区号，中国大陆手机号可不填。Authing 短信服务暂不内置支持国际手机号，你需要在 Authing 控制台配置对应的国际短信服务。
     * @param phoneNumber      手机号，不带区号。如果是国外手机号，请在 phoneCountryCode 参数中指定区号。
     * @param channel          短信通道，指定发送此短信的目的，如 CHANNEL_LOGIN 用于登录、CHANNEL_REGISTER 用于注册。
     *                         Enum: "CHANNEL_LOGIN" "CHANNEL_REGISTER" "CHANNEL_RESET_PASSWORD" "CHANNEL_BIND_PHONE" "CHANNEL_UNBIND_PHONE" "CHANNEL_BIND_MFA" "CHANNEL_VERIFY_MFA" "CHANNEL_UNBIND_MFA" "CHANNEL_COMPLETE_PHONE" "CHANNEL_IDENTITY_VERIFICATION" "CHANNEL_DELETE_ACCOUNT"
     * @param callback         回调
     */
    public void sendSms(String phoneCountryCode, String phoneNumber, SmsType channel, @NotNull AuthCallback callback) {
        CodeClient.getInstance().sendSms(phoneCountryCode, phoneNumber, channel, callback);
    }

    /**
     * 发送邮件验证码
     *
     * @param email    邮箱，不区分大小写
     * @param channel  短信通道，指定发送此短信的目的，如 CHANNEL_LOGIN 用于登录、CHANNEL_REGISTER 用于注册。
     *                 "CHANNEL_LOGIN" "CHANNEL_REGISTER" "CHANNEL_RESET_PASSWORD" "CHANNEL_VERIFY_EMAIL_LINK" "CHANNEL_BIND_EMAIL" "CHANNEL_UNBIND_EMAIL" "CHANNEL_VERIFY_MFA" "CHANNEL_UNLOCK_ACCOUNT" "CHANNEL_COMPLETE_EMAIL"
     * @param callback 回调
     */
    public void sendEmail(String email, EmailType channel, @NotNull AuthCallback callback) {
        CodeClient.getInstance().sendEmail(email, channel, callback);
    }

    /**
     * 发起绑定 MFA 认证要素请求
     *
     * @param factorType MFA 认证要素类型，目前共支持短信、邮箱验证码、OTP、人脸四种类型的认证要素。
     *                   Enum: "OTP" "SMS" "EMAIL" "FACE"
     * @param profile    MFA 认证要素详细信息
     * @param callback   回调
     */
    public void sendEnrollFactorRequest(@NotNull FactorType factorType, @NotNull FactorProfile profile, @NotNull AuthCallback callback) {
        MFAClient.getInstance().sendEnrollFactorRequest(factorType, profile, callback);
    }

    /**
     * 绑定 MFA 认证要素
     *
     * @param factorType      MFA 认证要素类型，目前共支持短信、邮箱验证码、OTP、人脸四种类型的认证要素。
     *                        Enum: "OTP" "SMS" "EMAIL" "FACE"
     * @param enrollmentToken 「发起绑定 MFA 认证要素请求」接口返回的 enrollmentToken，此 token 有效时间为一分钟。
     * @param passCode        绑定短信、邮箱验证码、OTP 类型的认证要素时，需要传此参数。值为短信/邮箱/OTP 验证码。
     * @param callback        回调
     */
    public void enrollFactor(@NotNull FactorType factorType, String enrollmentToken, String passCode, @NotNull AuthCallback callback) {
        MFAClient.getInstance().enrollFactor(factorType, enrollmentToken, passCode, callback);
    }

    /**
     * 解绑 MFA 认证要素
     * 当前不支持通过此接口解绑短信、邮箱验证码类型的认证要素。如果需要，请调用「解绑邮箱」和「解绑手机号」接口。
     *
     * @param factorId MFA 认证要素 ID
     * @param callback 回调
     */
    public void resetFactor(String factorId, @NotNull AuthCallback callback) {
        MFAClient.getInstance().resetFactor(factorId, callback);
    }

    /**
     * 获取绑定的所有 MFA 认证要素
     *
     * @param callback 回调
     */
    public void listEnrolledFactors(@NotNull AuthCallback callback) {
        MFAClient.getInstance().listEnrolledFactors(callback);
    }

    /**
     * 获取绑定的某个 MFA 认证要素
     *
     * @param factorId MFA Factor ID
     * @param callback 回调
     */
    public void getFactor(String factorId, @NotNull AuthCallback callback) {
        MFAClient.getInstance().getFactor(factorId, callback);
    }

    /**
     * 获取可绑定的 MFA 认证要素
     *
     * @param callback 回调
     */
    public void listFactorsToEnroll(@NotNull AuthCallback callback) {
        MFAClient.getInstance().listFactorsToEnroll(callback);
    }

    /**
     * 获取用户资料
     *
     * @param withCustomData    是否获取自定义数据
     * @param withIdentities    是否获取 identities
     * @param withDepartmentIds 是否获取部门 ID 列表
     * @param callback          回调
     */
    public void getProfile(boolean withCustomData, boolean withIdentities, boolean withDepartmentIds, @NotNull AuthCallback callback) {
        UserClient.getInstance().getProfile(withCustomData, withIdentities, withDepartmentIds, callback);
    }

    /**
     * 修改用户资料
     *
     * @param profile  用户资料，包含用户的自定义数据，不包含邮箱、手机号、密码，如果需要修改邮箱、修改手机号、修改密码，请使用对应的单独接口。
     * @param callback 回调
     */
    public void updateProfile(@NotNull Profile profile, @NotNull AuthCallback callback) {
        UserClient.getInstance().updateProfile(profile, callback);
    }

    /**
     * 更新头像
     *
     * @param in       图片流
     * @param callback 回调
     */
    public void uploadAvatar(InputStream in, @NotNull AuthCallback callback) {
        UserClient.getInstance().uploadAvatar(in, callback);
    }

    /**
     * 计算密码安全等级
     *
     * @param password 密码
     * @return PasswordStrength
     */
    public PasswordStrength computePasswordSecurityLevel(String password) {
        return UserClient.getInstance().computePasswordSecurityLevel(password);
    }

    /**
     * 获取密码强度和账号安全等级评分
     *
     * @param callback 回调
     */
    public void getSecurityLevel(@NotNull AuthCallback callback) {
        UserClient.getInstance().getSecurityLevel(callback);
    }

    /**
     * 绑定手机号
     *
     * @param phoneCountryCode 手机区号，中国大陆手机号可不填。
     * @param phoneNumber      手机号，不带区号。如果是国外手机号，请在 phoneCountryCode 参数中指定区号。
     * @param passCode         短信验证码，注意一个短信验证码指南使用一次，且有过期时间。
     * @param callback         回调
     */
    public void bindPhone(String phoneCountryCode, String phoneNumber, String passCode, @NotNull AuthCallback callback) {
        UserClient.getInstance().bindPhone(phoneCountryCode, phoneNumber, passCode, callback);
    }

    /**
     * 解绑手机号
     *
     * @param passCode 短信验证码，注意一个短信验证码指南使用一次，且有过期时间。
     * @param callback 回调
     */
    public void unBindPhone(String passCode, @NotNull AuthCallback callback) {
        UserClient.getInstance().unBindPhone(passCode, callback);
    }

    /**
     * 绑定邮箱
     *
     * @param email    邮箱，不区分大小写。
     * @param passCode 邮箱验证码，一个邮箱验证码只能使用一次，且有一定有效时间。
     * @param callback 回调
     */
    public void bindEmail(String email, String passCode, @NotNull AuthCallback callback) {
        UserClient.getInstance().bindEmail(email, passCode, callback);
    }

    /**
     * 解绑邮箱
     *
     * @param passCode 短信验证码，注意一个短信验证码指南使用一次，且有过期时间。
     * @param callback 回调
     */
    public void unBindEmail(String passCode, @NotNull AuthCallback callback) {
        UserClient.getInstance().unBindEmail(passCode, callback);
    }

    /**
     * 发起修改手机号的验证请求
     *
     * @param newPhoneCountryCode 手机区号，中国大陆手机号可不填。Authing 短信服务暂不内置支持国际手机号，你需要在 Authing 控制台配置对应的国际短信服务
     * @param newPhoneNumber      新手机号码，不带区号。如果是国外手机号，请在 newPhoneCountryCode 参数中指定区号。
     * @param newPhonePassCode    验证码
     * @param oldPhoneCountryCode 手机区号
     * @param oldPhoneNumber      旧手机号码，如果用户池开启了修改手机号需要验证之前的手机号，此参数必填。
     * @param oldPhonePassCode    旧手机号的验证码，如果用户池开启了修改手机号需要验证之前的手机号，此参数必填
     * @param callback            回调
     */
    public void updatePhoneRequest(String newPhoneCountryCode, String newPhoneNumber, String newPhonePassCode,
                                   String oldPhoneCountryCode, String oldPhoneNumber, String oldPhonePassCode,
                                   @NotNull AuthCallback callback) {
        UserClient.getInstance().updatePhoneRequest(newPhoneCountryCode, newPhoneNumber, newPhonePassCode,
                oldPhoneCountryCode, oldPhoneNumber, oldPhonePassCode, callback);
    }

    /**
     * 修改手机号
     *
     * @param updatePhoneToken 用于临时修改手机号的 token，可从发起修改手机号的验证请求接口获取。
     * @param callback         回调
     */
    public void updatePhone(String updatePhoneToken, @NotNull AuthCallback callback) {
        UserClient.getInstance().updatePhone(updatePhoneToken, callback);
    }

    /**
     * 发起修改邮箱的验证请求
     *
     * @param newEmail         新邮箱，此参数必填。
     * @param newEmailPassCode 新邮箱验证码，此参数必填。
     * @param oldEmail         旧邮箱，如果用户池开启了修改邮箱需要验证之前的邮箱，此参数必填。
     * @param oldEmailPassCode 旧邮箱验证码，如果用户池开启了修改邮箱需要验证之前的邮箱，此参数必填。
     * @param callback         回调
     */
    public void updateEmailRequest(String newEmail, String newEmailPassCode,
                                   String oldEmail, String oldEmailPassCode,
                                   @NotNull AuthCallback callback) {
        UserClient.getInstance().updateEmailRequest(newEmail, newEmailPassCode, oldEmail, oldEmailPassCode, callback);
    }

    /**
     * 修改邮箱
     *
     * @param updateEmailToken 用于临时修改邮箱的 token，可从发起修改邮箱的验证请求接口获取。
     * @param callback         回调
     */
    public void updateEmail(String updateEmailToken, @NotNull AuthCallback callback) {
        UserClient.getInstance().updateEmail(updateEmailToken, callback);
    }

    /**
     * 修改密码
     *
     * @param newPassword         新密码
     * @param oldPassword         原始密码，如果用户当前设置了密码，此参数必填。
     * @param passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     *                            Default: "none"
     *                            Enum: "sm2" "rsa" "none"
     * @param callback            回调
     */
    public void updatePassword(String newPassword, String oldPassword, PasswordEncryptType passwordEncryptType, @NotNull AuthCallback callback) {
        UserClient.getInstance().updatePassword(newPassword, oldPassword, passwordEncryptType, callback);
    }

    /**
     * 通过手机号验证码重置密码
     *
     * @param phoneCountryCode    手机区号，中国大陆手机号可不填。Authing 短信服务暂不内置支持国际手机号，你需要在 Authing 控制台配置对应的国际短信服务。
     * @param phoneNumber         此账号绑定的手机号，不带区号。如果是国外手机号，请在 phoneCountryCode 参数中指定区号。
     * @param passCode            短信验证码，一个短信验证码只能使用一次，有效时间为一分钟。你需要通过发送短信接口获取。
     * @param password            密码
     * @param passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     * @param callback            回调
     */
    public void resetPasswordByPhone(String phoneCountryCode, String phoneNumber, String passCode, String password, PasswordEncryptType passwordEncryptType, @NotNull AuthCallback callback) {
        UserClient.getInstance().resetPasswordByPhone(phoneCountryCode, phoneNumber, passCode, password, passwordEncryptType, callback);
    }

    /**
     * 通过邮箱验证码重置密码
     *
     * @param email               此账号绑定的邮箱，不区分大小写。
     * @param passCode            邮箱验证码，一个短信验证码只能使用一次，默认有效时间为5分钟。你需要通过发送邮件接口获取。
     * @param password            密码
     * @param passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     * @param callback            回调
     */
    public void resetPasswordByEmail(String email, String passCode, String password, PasswordEncryptType passwordEncryptType, @NotNull AuthCallback callback) {
        UserClient.getInstance().resetPasswordByEmail(email, passCode, password, passwordEncryptType, callback);
    }

    /**
     * 发起忘记密码请求
     *
     * @param verifyMethod         忘记密码请求使用的验证手段
     *                             Enum: "EMAIL_PASSCODE" "PHONE_PASSCODE"
     * @param resetPasswordPayload 使用验证码验证的数据
     * @param callback             回调
     */
    public void resetPasswordRequest(@NotNull ResetPasswordVerifyMethod verifyMethod, @NotNull ResetPasswordPayload resetPasswordPayload, @NotNull AuthCallback callback) {
        UserClient.getInstance().resetPasswordRequest(verifyMethod, resetPasswordPayload, callback);
    }


    /**
     * 重置密码
     * 调用此方法前需要调用 resetPasswordByPhoneRequest 或 resetPasswordByEmailRequest 拿到 token
     *
     * @param passwordResetToken  重置密码的 token，此参数需要通过发起忘记密码请求接口获取
     * @param password            密码
     * @param passwordEncryptType 密码加密类型，支持 sm2 和 rsa。默认可以不加密。
     * @param callback            回调
     */
    public void resetPassword(String passwordResetToken, String password, PasswordEncryptType passwordEncryptType, @NotNull AuthCallback callback) {
        UserClient.getInstance().resetPassword(passwordResetToken, password, passwordEncryptType, callback);
    }

    /**
     * 发起注销账号请求
     *
     * @param verifyMethod         注销账号的验证手段
     *                             Enum: "PHONE_PASSCODE" "EMAIL_PASSCODE" "PASSWORD"
     * @param deleteAccountPayload 数据
     * @param callback             回调
     */
    public void deleteAccountRequest(@NotNull DeleteAccountVerifyMethod verifyMethod, @NotNull DeleteAccountPayload deleteAccountPayload, @NotNull AuthCallback callback) {
        UserClient.getInstance().deleteAccountRequest(verifyMethod, deleteAccountPayload, callback);
    }

    /**
     * 注销账户
     * 调用此方法前需要先调用 deleteAccountByEmailRequest/deleteAccountByEmailRequest/deleteAccountByPasswordRequest 拿到 token
     *
     * @param deleteAccountToken 注销账户的 token ，此参数需要通过发起注销账号请求接口获取。
     * @param callback           回调
     */
    public void deleteAccount(String deleteAccountToken, @NotNull AuthCallback callback) {
        UserClient.getInstance().deleteAccount(deleteAccountToken, callback);
    }

    /**
     * 登出
     *
     * @param callback 回调
     */
    public void logout(@NotNull AuthCallback callback) {
        UserClient.getInstance().logout(callback);
    }

    /**
     * 获取登录日志
     *
     * @param appId    应用 ID，可根据应用 ID 筛选。默认不传获取所有应用的登录历史。
     *                 Example: appId=5f6265c67fxxxxae64ec516e
     * @param clientIp 客户端 IP，可根据登录时的客户端 IP 进行筛选。默认不传获取所有登录 IP 的登录历史。
     *                 Example: clientIp=127.0.0.1
     * @param success  是否登录成功，可根据是否登录成功进行筛选。默认不传获取的记录中既包含成功也包含失败的的登录历史。
     *                 Example: success=true
     * @param start    开始时间，为单位为毫秒的时间戳
     *                 Example: start=1660828100000
     * @param end      结束时间，为单位为毫秒的时间戳
     *                 Example: end=1660828100000
     * @param page     当前页数，从 1 开始
     *                 Default: 1
     *                 Example: page=1
     * @param limit    每页数目，最大不能超过 50，默认为 10
     *                 Default: 10
     *                 Example: limit=10
     * @param callback 回调
     */
    public void getLoginHistory(String appId, String clientIp, boolean success, long start, long end, int page, int limit, @NotNull AuthCallback callback) {
        UserClient.getInstance().getLoginHistory(appId, clientIp, success, start, end, page, limit, callback);
    }

    /**
     * 获取登录应用
     *
     * @param callback 回调
     */
    public void getLoggedInApps(@NotNull AuthCallback callback) {
        UserClient.getInstance().getLoggedInApps(callback);
    }

    /**
     * 获取具备访问权限的应用
     *
     * @param callback 回调
     */
    public void getAccessibleApps(@NotNull AuthCallback callback) {
        UserClient.getInstance().getAccessibleApps(callback);
    }

    /**
     * 获取租户列表
     *
     * @param callback 回调
     */
    public void getTenantList(@NotNull AuthCallback callback) {
        UserClient.getInstance().getTenantList(callback);
    }

    /**
     * 获取角色列表
     *
     * @param namespace 所属权限分组的 code
     *                  Example: namespace=default
     * @param callback  回调
     */
    public void getRoleList(String namespace, @NotNull AuthCallback callback) {
        UserClient.getInstance().getRoleList(namespace, callback);
    }

    /**
     * 获取分组列表
     *
     * @param callback 回调
     */
    public void getGroupList(@NotNull AuthCallback callback) {
        UserClient.getInstance().getGroupList(callback);
    }

    /**
     * 获取用户的部门列表
     *
     * @param page           当前页数，从 1 开始
     *                       Default: 1
     *                       Example: page=1
     * @param limit          每页数目，最大不能超过 50，默认为 10
     *                       Default: 10
     *                       Example: limit=10
     * @param withCustomData 是否获取部门的自定义数据
     *                       Default: false
     *                       Example: withCustomData=true
     * @param sortBy         排序依据，如 部门创建时间、加入部门时间、部门名称、部门标志符
     *                       Default: "JoinDepartmentAt"
     *                       Enum: "DepartmentCreatedAt" "JoinDepartmentAt" "DepartmentName" "DepartmemtCode"
     *                       Example: sortBy=JoinDepartmentAt
     * @param orderBy        增序或降序
     *                       Default: "Desc"
     *                       Enum: "Asc" "Desc"
     *                       Example: orderBy=Desc
     * @param callback       回调
     */
    public void getDepartmentList(int page, int limit, boolean withCustomData, String sortBy, String orderBy, @NotNull AuthCallback callback) {
        UserClient.getInstance().getDepartmentList(page, limit, withCustomData, sortBy, orderBy, callback);
    }

    /**
     * 获取用户被授权的资源列表
     *
     * @param namespace    所属权限分组的 code
     *                     Example: namespace=default
     * @param resourceType 资源类型，如 数据、API、菜单、按钮
     *                     Enum: "DATA" "API" "MENU" "BUTTON" "UI"
     * @param callback     回调
     */
    public void getAuthorizedResources(String namespace, ResourceType resourceType, @NotNull AuthCallback callback) {
        UserClient.getInstance().getAuthorizedResources(namespace, resourceType, callback);
    }

    /**
     * 获取服务器公开信息
     * 可端点可获取服务器的公开信息，如 RSA256 公钥、SM2 公钥、Authing 服务版本号等。
     *
     * @param callback 回调
     */
    public void system(@NotNull AuthCallback callback) {
        CommonClient.getInstance().system(callback);
    }

    /**
     * 获取国家列表
     * 动态获取国家列表，可以用于前端登录页面国家选择和国际短信输入框选择，以减少前端静态资源体积。
     *
     * @param callback 回调
     */
    public void getCountryList(@NotNull AuthCallback callback) {
        CommonClient.getInstance().getCountryList(callback);
    }

    public void buildAuthorizeUrl(AuthRequest authRequest, @NotNull Callback<String> callback) {
        OIDCClient.getInstance().buildAuthorizeUrl(authRequest, callback);
    }

    public void getAccessTokenByCode(String code, AuthRequest authRequest, @NotNull AuthCallback callback) {
        OIDCClient.getInstance().getAccessTokenByCode(code, authRequest, callback);
    }

    public void getUserInfoByAccessToken(String accessToken, @NotNull AuthCallback callback) {
        OIDCClient.getInstance().getUserInfoByAccessToken(accessToken, callback);
    }

    public void getNewAccessTokenByRefreshToken(String refreshToken, AuthRequest authRequest, @NotNull AuthCallback callback) {
        OIDCClient.getInstance().getNewAccessTokenByRefreshToken(refreshToken, authRequest, callback);
    }

}
