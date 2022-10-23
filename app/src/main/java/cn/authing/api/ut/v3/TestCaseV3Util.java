package cn.authing.api.ut.v3;

import cn.authing.api.ut.TestCase;

public class TestCaseV3Util {

    public static TestCase createRegisterByEmailCase(int type, boolean isOidc) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("注册");
        testCase.setMethod("post");
        testCase.setOidc(isOidc);
        testCase.setCaseName("邮箱密码");
        testCase.setTestApiName("registerByEmailPassword");
        testCase.setApiName("registerByEmailPassword");
        testCase.setUrl("/api/v3/signup");
        if (type == 0) {
            testCase.setCaseSubName("传邮箱号+密码");
            testCase.setParams("389000577@qq.com,123456");
        } else if (type == 1) {
            testCase.setCaseSubName("只传邮箱号");
            testCase.setParams("389000577@qq.com,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传密码");
            testCase.setParams(",123456");
        }
        return testCase;
    }

    public static TestCase createRegisterByPhoneCodeCase(int type, boolean isOidc) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("注册");
        testCase.setMethod("post");
        testCase.setOidc(isOidc);
        testCase.setCaseName("短信验证码");
        testCase.setTestApiName("registerByPhoneCode");
        testCase.setApiName("registerByPhoneCode");
        testCase.setUrl("/api/v3/signup");
        if (type == 0) {
            testCase.setCaseSubName("传手机号码+验证码");
            testCase.setParams(",19129910165,4967");
        } else if (type == 1) {
            testCase.setCaseSubName("只传电话号码");
            testCase.setParams("+86,19129910165,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",,123456");
        }
        return testCase;
    }

    public static TestCase createRegisterByEmailCodeCase(int type, boolean isOidc) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("注册");
        testCase.setMethod("post");
        testCase.setOidc(isOidc);
        testCase.setCaseName("邮箱验证码");
        testCase.setTestApiName("registerByEmailCode");
        testCase.setApiName("registerByEmailCode");
        testCase.setUrl("/api/v3/signup");
        if (type == 0) {
            testCase.setCaseSubName("传邮箱号+验证码");
            testCase.setParams("389000577@qq.com,1310");
        } else if (type == 1) {
            testCase.setCaseSubName("只传邮箱号");
            testCase.setParams("89000577@qq.com,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",123456");
        }
        return testCase;
    }

    public static TestCase createLoginByAccountCase(int type, boolean isOidc) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("登录");
        testCase.setMethod("post");
        testCase.setOidc(isOidc);
        testCase.setCaseName("账号密码");
        testCase.setTestApiName("loginByAccount");
        testCase.setApiName("loginByAccount");
        testCase.setUrl("/api/v3/signin");
        if (type == 0) {
            testCase.setCaseSubName("传邮箱号+密码");
            testCase.setParams("389000577@qq.com,123456");
        } else if (type == 1) {
            testCase.setCaseSubName("传手机号+密码");
            testCase.setParams("19129910165,123456");
        } else if (type == 2) {
            testCase.setCaseSubName("只传邮箱号");
            testCase.setParams("389000577@qq.com,");
        } else if (type == 3) {
            testCase.setCaseSubName("只传手机号");
            testCase.setParams("19129910165,");
        } else if (type == 4) {
            testCase.setCaseSubName("只传密码");
            testCase.setParams(",123456");
        }
        return testCase;
    }

    public static TestCase createLoginByPhoneCodeCase(int type, boolean isOidc) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("登录");
        testCase.setMethod("post");
        testCase.setOidc(isOidc);
        testCase.setCaseName("短信验证码");
        testCase.setTestApiName("loginByPhoneCode");
        testCase.setApiName("loginByPhoneCode");
        testCase.setUrl("/api/v3/signin");
        if (type == 0) {
            testCase.setCaseSubName("传手机号+验证码");
            testCase.setParams(",18820251010,9127");
        } else if (type == 1) {
            testCase.setCaseSubName("只传手机号");
            testCase.setParams(",19129910165,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",,123456");
        }
        return testCase;
    }

    public static TestCase createLoginByEmailCodeCase(int type, boolean isOidc) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("登录");
        testCase.setMethod("post");
        testCase.setOidc(isOidc);
        testCase.setCaseName("邮箱验证码");
        testCase.setTestApiName("loginByEmailCode");
        testCase.setApiName("loginByEmailCode");
        testCase.setUrl("//api/v3/signin");
        if (type == 0) {
            testCase.setCaseSubName("传邮箱号+验证码");
            testCase.setParams("389000577@qq.com,2574");
        } else if (type == 1) {
            testCase.setCaseSubName("只传邮箱号");
            testCase.setParams("389000577@qq.com,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",123456");
        }
        return testCase;
    }

    public static TestCase createSocialLoginCase(int type, boolean isOidc) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("社会化登录");
        testCase.setMethod("post");
        testCase.setOidc(isOidc);
        if (type == 0) {
            testCase.setCaseName("微信");
            testCase.setTestApiName("loginByWechat");
            testCase.setApiName("loginByWechat");
            testCase.setUrl("/api/v3/signin-by-mobile");
            testCase.setCaseSubName("");
            testCase.setParams("19129910165");
        } else if (type == 1) {
            testCase.setCaseName("企业微信");
            testCase.setTestApiName("loginByWecom");
            testCase.setApiName("loginByWecom");
            testCase.setUrl("/api/v3/signin-by-mobile");
            testCase.setCaseSubName("");
            testCase.setParams("19129910165");
        } else if (type == 2) {
            testCase.setCaseName("企业微信-代理模式");
            testCase.setTestApiName("loginByWecomAgency");
            testCase.setApiName("loginByWecomAgency");
            testCase.setUrl("/api/v3/signin-by-mobile");
            testCase.setCaseSubName("");
            testCase.setParams("19129910165");
        } else if (type == 3) {
            testCase.setCaseName("支付宝");
            testCase.setTestApiName("loginByAlipay");
            testCase.setApiName("loginByAlipay");
            testCase.setUrl("/api/v3/signin-by-mobile");
            testCase.setCaseSubName("");
            testCase.setParams("19129910165");
        } else if (type == 4) {
            testCase.setCaseName("飞书");
            testCase.setTestApiName("loginByLark");
            testCase.setApiName("loginByLark");
            testCase.setUrl("//api/v3/signin-by-mobile");
            testCase.setCaseSubName("");
            testCase.setParams("19129910165");
        } else if (type == 5) {
            testCase.setCaseName("易盾一键登录");
            testCase.setTestApiName("loginByOneAuth");
            testCase.setApiName("loginByOneAuth");
            testCase.setUrl("/api/v3/signin-by-mobile");
            testCase.setCaseSubName("");
            testCase.setParams("19129910165,19129910165");
        } else if (type == 6) {
            testCase.setCaseName("google");
            testCase.setTestApiName("loginByGoogle");
            testCase.setApiName("loginByGoogle");
            testCase.setUrl("/api/v3/signin-by-mobile");
            testCase.setCaseSubName("");
            testCase.setParams("19129910165");
        }
        return testCase;
    }

    public static TestCase createQrLoginCase(int type, boolean isOidc) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("扫码登录");
        testCase.setOidc(isOidc);
        if (type == 0) {
            testCase.setCaseName("查询二维码状态");
            testCase.setMethod("get");
            testCase.setTestApiName("checkQRCodeStatus");
            testCase.setApiName("checkQRCodeStatus");
            testCase.setUrl("/api/v3/check-qrcode-status");
            testCase.setCaseSubName("");
            testCase.setParams("");
        }  else if (type == 1) {
            testCase.setCaseName("自建 APP 扫码登录：APP 端修改二维码状态");
            testCase.setMethod("post");
            testCase.setTestApiName("changeQrCodeStatus");
            testCase.setApiName("changeQrCodeStatus");
            testCase.setUrl("/api/v3/change-qrcode-status");
            testCase.setCaseSubName("");
            testCase.setParams(",SCAN");
        } else if (type == 2) {
            testCase.setCaseName("自建 APP 扫码登录：APP 端修改二维码状态");
            testCase.setMethod("post");
            testCase.setTestApiName("changeQrCodeStatus");
            testCase.setApiName("changeQrCodeStatus");
            testCase.setUrl("/api/v3/change-qrcode-status");
            testCase.setCaseSubName("");
            testCase.setParams(",CONFIRM");
        } else if (type == 3) {
            testCase.setCaseName("自建 APP 扫码登录：APP 端修改二维码状态");
            testCase.setMethod("post");
            testCase.setTestApiName("changeQrCodeStatus");
            testCase.setApiName("changeQrCodeStatus");
            testCase.setUrl("/api/v3/change-qrcode-status");
            testCase.setCaseSubName("");
            testCase.setParams(",CANCEL");
        }
        return testCase;
    }


    public static TestCase createAccountBindingCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("账号绑定");
        if (type == 0) {
            testCase.setMethod("get");
            testCase.setCaseName("绑定外部身份源");
            testCase.setTestApiName("linkExtIdp");
            testCase.setApiName("linkExtIdp");
            testCase.setUrl("/api/v3/link-extidp");
            testCase.setCaseSubName("");
            testCase.setParams("13724352141");
        } else if (type == 1) {
            testCase.setMethod("get");
            testCase.setCaseName("生成绑定外部身份源的链接");
            testCase.setTestApiName("generateLinkExtIdpUrl");
            testCase.setApiName("generateLinkExtIdpUrl");
            testCase.setUrl("/v3/generate-link-extidp-url");
            testCase.setCaseSubName("");
            testCase.setParams("13724352141");
        } else if (type == 2) {
            testCase.setMethod("post");
            testCase.setCaseName("解绑外部身份源");
            testCase.setTestApiName("unLinkExtIdp");
            testCase.setApiName("unLinkExtIdp");
            testCase.setUrl("/api/v3/unlink-extidp");
            testCase.setCaseSubName("");
            testCase.setParams("13724352141");
        } else if (type == 3) {
            testCase.setMethod("get");
            testCase.setCaseName("获取绑定的外部身份源");
            testCase.setTestApiName("getIdentities");
            testCase.setApiName("getIdentities");
            testCase.setUrl("/api/v3/get-identities");
            testCase.setCaseSubName("");
            testCase.setParams("");
        } else if (type == 4) {
            testCase.setMethod("get");
            testCase.setCaseName("获取应用开启的外部身份源列表");
            testCase.setTestApiName("getExtIdps");
            testCase.setApiName("getExtIdps");
            testCase.setUrl("/api/v3/get-extidps");
            testCase.setCaseSubName("");
            testCase.setParams("");
        }
        return testCase;
    }

    public static TestCase createSendSmsCodeCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("发送验证码");
        testCase.setMethod("post");
        testCase.setCaseName("短信");
        testCase.setTestApiName("sendSms");
        testCase.setApiName("sendSms");
        testCase.setUrl("/api/v3/send-sms");
        if (type == 0) {
            testCase.setCaseSubName("传手机号 13724352141");
            testCase.setParams(",13724352141");
        } else if (type == 1) {
            testCase.setCaseSubName("传区号+手机号 19129910165");
            testCase.setParams("+86,19129910165");
        } else if (type == 2) {
            testCase.setCaseSubName("传错误区号+手机号");
            testCase.setParams("+1,19129910165");
        } else if (type == 3) {
            testCase.setCaseSubName("传空手机号");
            testCase.setParams(",");
        }
        return testCase;
    }

    public static TestCase createSendEmailCodeCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("发送验证码");
        testCase.setMethod("post");
        testCase.setCaseName("邮箱");
        testCase.setTestApiName("sendEmail");
        testCase.setApiName("sendEmail");
        testCase.setUrl("/api/v3/send-email");
        if (type == 0) {
            testCase.setCaseSubName("传邮箱 389000577@qq.com");
            testCase.setParams("389000577@qq.com");
        } else if (type == 1) {
            testCase.setCaseSubName("传邮箱 zhongjiahui_work@163.com");
            testCase.setParams("zhongjiahui_work@163.com");
        }  else if (type == 2) {
            testCase.setCaseSubName("传空邮箱");
            testCase.setParams("");
        }
        return testCase;
    }

    public static TestCase createGetUerInfoCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取用户信息");
        testCase.setTestApiName("getCurrentUser");
        testCase.setApiName("getUserInfo");
        testCase.setUrl("api/v3/get-profile");
        return testCase;
    }

    public static TestCase createGetSecurityInfoCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取密码强度和账号安全等级评分");
        testCase.setTestApiName("getSecurityInfo");
        testCase.setApiName("getSecurityInfo");
        testCase.setUrl("/api/v3/get-security-info");
        return testCase;
    }

    public static TestCase createGetCustomUserDataCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取用户扩展信息");
        testCase.setApiName("getCustomUserData");
        testCase.setUrl("/api/v2/udvs/get");
        return testCase;
    }

    public static TestCase createGetLoggedHistoryCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取登录日志");
        testCase.setTestApiName("getLoggedHistory");
        testCase.setApiName("getLoggedHistory");
        testCase.setUrl("/api/v3/get-login-history");
        return testCase;
    }

    public static TestCase createGetLoggedApplicationCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取登录应用");
        testCase.setTestApiName("getLoggedApplication");
        testCase.setApiName("getLoggedApplication");
        testCase.setUrl("/api/v3/get-logged-in-apps");
        return testCase;
    }

    public static TestCase createGetTenantListCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取租户列表");
        testCase.setTestApiName("getLoggedHistory");
        testCase.setApiName("getLoggedHistory");
        testCase.setUrl("/api/v3/get-tenant-list");
        return testCase;
    }

    public static TestCase createGetDepartmentListCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取部门列表");
        testCase.setTestApiName("getDepartmentList");
        testCase.setApiName("getDepartmentList");
        testCase.setUrl("/api/v3/get-department-list");
        return testCase;
    }

    public static TestCase createGetListRolesCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取用户角色信息");
        testCase.setTestApiName("getRoleList");
        testCase.setApiName("getRoleList");
        testCase.setUrl("/api/v3/get-role-list");
        return testCase;
    }

    public static TestCase createGetListApplicationsCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取用户能够访问的应用信息");
        testCase.setTestApiName("getApplicationList");
        testCase.setApiName("getApplicationList");
        testCase.setUrl("/api/v3/get-accessible-apps");
        return testCase;
    }

    public static TestCase createGetListAuthorizedResourcesCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取用户授权资源信息");
        testCase.setTestApiName("getAuthorizedResources");
        testCase.setApiName("getAuthorizedResources");
        testCase.setUrl("/api/v3/get-authorized-resources");
        return testCase;
    }

    public static TestCase createGetListOrgsCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("获取用户信息");
        testCase.setMethod("get");
        testCase.setCaseName("获取用户所在组织机构信息");
        testCase.setTestApiName("getGroupList");
        testCase.setApiName("getGroupList");
        testCase.setUrl("/api/v3/get-group-list");
        return testCase;
    }

    public static TestCase createBindPhoneCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setMethod("post");
        testCase.setCaseName("绑定手机");
        testCase.setTestApiName("bindPhone");
        testCase.setApiName("bindPhone");
        testCase.setUrl("/api/v3/bind-phone");
        if (type == 0) {
            testCase.setCaseSubName("传手机号+验证码");
            testCase.setParams(",18820251010,7370");
        } else if (type == 1) {
            testCase.setCaseSubName("只传手机号");
            testCase.setParams(",19129910165,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",,1234");
        } else if (type == 3) {
            testCase.setCaseSubName("传区号+手机号+验证码");
            testCase.setParams("+86,18820251010,7370");
        }
        return testCase;
    }

    public static TestCase createUnbindPhoneCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setMethod("post");
        testCase.setCaseName("解除手机绑定");
        testCase.setTestApiName("unbindPhone");
        testCase.setApiName("unbindPhone");
        testCase.setUrl("/api/v3/unbind-phone");
        return testCase;
    }

    public static TestCase createUpdatePhoneCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setMethod("post");
        testCase.setCaseName("修改手机");
        testCase.setTestApiName("updatePhone");
        testCase.setApiName("updatePhone");
        testCase.setUrl("/api/v3/update-phone");
        if (type == 0) {
            testCase.setCaseSubName("传手机号+验证码");
            testCase.setParams("+86,19129910165,8352,,,");
        }
        return testCase;
    }

    public static TestCase createBindEmailCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setMethod("post");
        testCase.setCaseName("绑定邮箱");
        testCase.setTestApiName("bindEmail");
        testCase.setApiName("bindEmail");
        testCase.setUrl("/api/v3/bind-email");
        if (type == 0) {
            testCase.setCaseSubName("传邮箱+验证码");
            testCase.setParams("zhongjiahui_work@163.com,1796");
        } else if (type == 1) {
            testCase.setCaseSubName("只传邮箱");
            testCase.setParams("389000577@qq.com,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",1234");
        } else if (type == 3) {
            testCase.setCaseSubName("传空");
            testCase.setParams(",");
        }
        return testCase;
    }

    public static TestCase createUnbindEmailCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setMethod("post");
        testCase.setCaseName("解除邮箱绑定");
        testCase.setTestApiName("unbindEmail");
        testCase.setApiName("unbindEmail");
        testCase.setUrl("/api/v3/unbind-email");
        return testCase;
    }

    public static TestCase createUpdateEmailCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setMethod("post");
        testCase.setCaseName("修改邮箱");
        testCase.setTestApiName("updateEmail");
        testCase.setApiName("updateEmail");
        testCase.setUrl("/api/v3/update-email");
        if (type == 0) {
            testCase.setCaseSubName("传邮箱+验证码");
            testCase.setParams("zhongjiahui_work@163.com,9404,,");
        }
        return testCase;
    }

    public static TestCase createResetPasswordByPhoneCodeCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setMethod("post");
        testCase.setCaseName("手机号重置密码");
        testCase.setTestApiName("resetPasswordByPhoneCode");
        testCase.setApiName("resetPasswordByPhoneCode");
        testCase.setUrl("/api/v3/verify-reset-password-request  api/v3/reset-password");
        if (type == 0) {
            testCase.setCaseSubName("传手机号+验证码+密码");
            testCase.setParams("+86,19129910165,6546,123456");
        } else if (type == 1) {
            testCase.setCaseSubName("只传手机号+验证码");
            testCase.setParams(",19129910165,1234,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传手机号");
            testCase.setParams(",19129910165,,");
        } else if (type == 3) {
            testCase.setCaseSubName("只传验证码+密码");
            testCase.setParams(",,1234,123456");
        } else if (type == 4) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",,1234,");
        }  else if (type == 5) {
            testCase.setCaseSubName("只传密码");
            testCase.setParams(",,,123456");
        } else if (type == 6) {
            testCase.setCaseSubName("传区号+手机号+验证码+密码");
            testCase.setParams("+86,19129910165,1234,123456");
        }
        return testCase;
    }

    public static TestCase createResetPasswordByEmailCodeCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setMethod("post");
        testCase.setCaseName("邮箱重置密码");
        testCase.setTestApiName("resetPasswordByEmailCode");
        testCase.setApiName("resetPasswordByEmailCode");
        testCase.setUrl("/api/v3/verify-reset-password-request  api/v3/reset-password");
        if (type == 0) {
            testCase.setCaseSubName("传邮箱+验证码");
            testCase.setParams("389000577@qq.com,1091,123456");
        } else if (type == 1) {
            testCase.setCaseSubName("只传邮箱");
            testCase.setParams("389000577@qq.com,,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",,1234");
        } else if (type == 3) {
            testCase.setCaseSubName("传空");
            testCase.setParams(",,,");
        }
        return testCase;
    }

    public static TestCase createUpdatePassword() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setCaseName("修改密码");
        testCase.setCaseSubName(getUserName());
        testCase.setTestApiName("updatePassword");
        testCase.setApiName("updatePassword");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/update-password");
        testCase.setParams("12345678,123456");
        return testCase;
    }

    public static TestCase createUpdateProfileCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("更新用户信息");
        testCase.setMethod("post");
        testCase.setCaseName("修改用户资料");
        testCase.setTestApiName("updateProfile");
        testCase.setApiName("updateUserInfo");
        testCase.setUrl("/api/v3/update-profile");
        if (type == 0) {
            testCase.setCaseSubName("用户名...");
            testCase.setParams("");
        }
        return testCase;
    }

    public static TestCase createMfaCheckCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("检测手机号或邮箱是否已被绑定");
        testCase.setApiName("mfaCheck");
        testCase.setMethod("post");
        testCase.setUrl("/api/v2/applications/mfa/check");
        if (type == 0) {
            testCase.setCaseSubName("手机号");
            testCase.setParams("19129910165,");
        } else if (type == 1) {
            testCase.setCaseSubName("邮箱");
            testCase.setParams(",389000577@qq.com");
        } else if (type == 2) {
            testCase.setCaseSubName("传空");
            testCase.setParams("");
        }
        return testCase;
    }

    public static TestCase createMfaVerifyByPhoneCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("检验二次验证 MFA 短信验证码");
        testCase.setApiName("mfaVerifyByPhone");
        testCase.setMethod("post");
        testCase.setUrl("/api/v2/applications/mfa/sms/verify");
        if (type == 0) {
            testCase.setCaseSubName("传手机号+验证码");
            testCase.setParams("19129910165,1234");
        } else if (type == 1) {
            testCase.setCaseSubName("只传手机号");
            testCase.setParams("19129910165,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",1234");
        } else if (type == 3) {
            testCase.setCaseSubName("传区号+手机号+验证码");
            testCase.setParams("+86,19129910165,1234");
        }
        return testCase;
    }

    public static TestCase createMfaVerifyByEmailCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("检验二次验证 MFA 邮箱验证码");
        testCase.setApiName("mfaVerifyByEmail");
        testCase.setMethod("post");
        testCase.setUrl("/api/v2/applications/mfa/email/verify");
        if (type == 0) {
            testCase.setCaseSubName("传邮箱号+验证码");
            testCase.setParams("389000577@qq.com,123456");
        } else if (type == 1) {
            testCase.setCaseSubName("只传邮箱号");
            testCase.setParams("389000577@qq.com,");
        } else if (type == 2) {
            testCase.setCaseSubName("只传验证码");
            testCase.setParams(",123456");
        }
        return testCase;
    }

    public static TestCase createMfaVerifyByOTPCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("检验二次验证 MFA 口令");
        testCase.setApiName("mfaVerifyByOTP");
        testCase.setMethod("post");
        testCase.setUrl("/api/v2/mfa/totp/verify");
        testCase.setCaseSubName("");
        testCase.setParams("123456");
        return testCase;
    }

    public static TestCase createMfaVerifyByRecoveryCodeCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("检验二次验证 MFA 恢复代码");
        testCase.setApiName("mfaVerifyByRecoveryCode");
        testCase.setMethod("post");
        testCase.setUrl("/api/v2/mfa/totp/recovery");
        testCase.setCaseSubName("");
        testCase.setParams("123456");
        return testCase;
    }

    public static TestCase createSendBindMFARequestByPhoneCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("发起绑定 MFA 认证要素请求 - 手机号");
        testCase.setTestApiName("sendBindMFARequestByPhone");
        testCase.setApiName("sendBindMFARequestByPhone");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/send-enroll-factor-request");
        testCase.setCaseSubName("");
        testCase.setParams("+86,19129910165");
        return testCase;
    }

    public static TestCase createSendBindMFARequestByEmailCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("发起绑定 MFA 认证要素请求 - 邮箱");
        testCase.setTestApiName("sendBindMFARequestByEmail");
        testCase.setApiName("sendBindMFARequestByEmail");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/send-enroll-factor-request");
        testCase.setCaseSubName("");
        testCase.setParams("389000577@qq.com");
        return testCase;
    }

    public static TestCase createSendBindMFARequestByOtpCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("发起绑定 MFA 认证要素请求 - otp");
        testCase.setTestApiName("sendBindMFARequestByOtp");
        testCase.setApiName("sendBindMFARequestByOtp");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/send-enroll-factor-request");
        testCase.setCaseSubName("");
        testCase.setParams("");
        return testCase;
    }

    public static TestCase createSendBindMFARequestByFaceCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("发起绑定 MFA 认证要素请求 - face");
        testCase.setTestApiName("sendBindMFARequestByFace");
        testCase.setApiName("sendBindMFARequestByFace");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/send-enroll-factor-request");
        testCase.setCaseSubName("");
        testCase.setParams("");
        return testCase;
    }


    public static TestCase createBindMFAByPhoneCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("绑定 MFA 认证要素 - 手机号");
        testCase.setTestApiName("bindMFAByPhone");
        testCase.setApiName("bindMFAByPhone");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/enroll-factor");
        testCase.setCaseSubName("");
        testCase.setParams("+86,19129910165");
        return testCase;
    }

    public static TestCase createBindMFAByEmailCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("绑定 MFA 认证要素 - email");
        testCase.setTestApiName("bindMFAByEmail");
        testCase.setApiName("bindMFAByEmail");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/enroll-factor");
        testCase.setCaseSubName("");
        testCase.setParams("+86,19129910165");
        return testCase;
    }

    public static TestCase createBindMFAByOtpCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("绑定 MFA 认证要素 - otp");
        testCase.setTestApiName("bindMFAByOtp");
        testCase.setApiName("bindMFAByOtp");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/enroll-factor");
        testCase.setCaseSubName("");
        testCase.setParams("VrLKpE3k7gwdp4FQSPZPyqNTaySiJj2w0zPrgcjfOxz/UjtksfvDxxzoSVvZuj6nO2YGLr45TRnnWnqiJN3XXrGTS2pq4tXHWi+6qSBbeoT3Hlh8iwwiLGY3OYwG8zme8heCguQvzR3hqkAnKD5QxhwOsBZasAIUac+twSPN3Pc=\",\"otpData\":{\"qrCodeUri\":\"otpauth://totp/GuardPool:389000577%40qq.com?secret=HMNUMPBZMY6GGZBZ&period=30&digits=6&algorithm=SHA1&issuer=GuardPool\",\"qrCodeDataUrl\":\"data:image/png;base64,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,19129910165");
        return testCase;
    }

    public static TestCase createBindMFAByFaceCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("绑定 MFA 认证要素 - face");
        testCase.setTestApiName("bindMFAByFace");
        testCase.setApiName("bindMFAByFace");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/enroll-factor");
        testCase.setCaseSubName("");
        testCase.setParams("RxEy+rrbPN4KMEzuXF6WrUHRuvMib9DaerCjgFlRoLaWEPfeYkqtkhXIIIWLVSgfk24C2XfboinIC9TwJ3+kSBngyUGxd03zAC5z+SvABNuSUbBAeH0CMJ+1ZNOdTf7XO+4jyROn3ysz4z7qEevdJ5+ImZ1k6DpmEi3YwxRx0Y0=");
        return testCase;
    }

    public static TestCase createUnBindMFACase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("解绑 MFA 认证要素");
        testCase.setTestApiName("unBindMFA");
        testCase.setApiName("unBindMFA");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/reset-factor");
        if (type == 0){
            testCase.setCaseSubName("手机号");
            testCase.setParams("634931036f1551730edd2bb3|phone");
        } else if (type == 1){
            testCase.setCaseSubName("邮箱");
            testCase.setParams("634931036f1551730edd2bb3|email");
        } else if (type == 2){
            testCase.setCaseSubName("otp");
            testCase.setParams("634931036f1551730edd2bb3|otp");
        } else if (type == 3){
            testCase.setCaseSubName("face");
            testCase.setParams("634931036f1551730edd2bb3|face");
        }

        return testCase;
    }

    public static TestCase createGetAllBindingMFACase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("获取绑定的所有 MFA 认证要素");
        testCase.setTestApiName("getAllBindingMFA");
        testCase.setApiName("getAllBindingMFA");
        testCase.setMethod("get");
        testCase.setUrl("/api/v3/list-enrolled-factors");
        testCase.setCaseSubName("");
        testCase.setParams("");
        return testCase;
    }

    public static TestCase createGetBindingMFACase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("获取绑定的某个 MFA 认证要素");
        testCase.setTestApiName("getBindingMFA");
        testCase.setApiName("getBindingMFA");
        testCase.setMethod("get");
        testCase.setUrl("/api/v3/get-factor");
        testCase.setCaseSubName("");
        testCase.setParams("634931036f1551730edd2bb3|phone");
        return testCase;
    }

    public static TestCase createGetUnBindingMFACase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("MFA");
        testCase.setCaseName("获取可绑定的 MFA 认证要素");
        testCase.setTestApiName("getUnBindingMFA");
        testCase.setApiName("getUnBindingMFA");
        testCase.setMethod("get");
        testCase.setUrl("/api/v3/list-factors-to-enroll");
        testCase.setCaseSubName("");
        testCase.setParams("");
        return testCase;
    }

    public static TestCase createLogoutCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("账号密码");
        testCase.setCaseName("退出登录");
        testCase.setCaseSubName(getUserName());
        testCase.setTestApiName("logout");
        testCase.setApiName("logout");
        testCase.setMethod("post");
        testCase.setUrl("/oidc/token/revocation");
        return testCase;
    }

    public static TestCase createGetNewAccessTokenByRefreshTokenCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("Token");
        testCase.setCaseName("通过RefreshToken刷新AccessToken");
        testCase.setCaseSubName("");
        testCase.setTestApiName("getNewAccessTokenByRefreshToken");
        testCase.setApiName("getNewAccessTokenByRefreshToken");
        testCase.setMethod("post");
        testCase.setUrl("/oidc/token");
        return testCase;
    }

    public static TestCase createDeleteAccountCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("账号密码");
        testCase.setCaseName("注销账号");
        testCase.setCaseSubName(getUserName());
        testCase.setTestApiName("deleteAccount");
        testCase.setApiName("deleteAccount");
        testCase.setMethod("post");
        testCase.setUrl("/api/v3/delete-account");
        if (type == 0) {
            testCase.setCaseSubName("手机号");
            testCase.setParams(",19129910165,8256");
        } else if (type == 1) {
            testCase.setCaseSubName("邮箱");
            testCase.setParams("zhongjiahui_work@163.com,7964");
        } else if (type == 2) {
            testCase.setCaseSubName("密码");
            testCase.setParams("123456");
        }
        return testCase;
    }

    public static TestCase createCheckAccountCase(int type) {
        TestCase testCase = new TestCase();
        testCase.setModuleName("账号密码");
        testCase.setCaseName("检测用户是否存在");
        testCase.setCaseSubName(getUserName());
        testCase.setApiName("checkAccount");
        testCase.setMethod("get");
        testCase.setUrl("/api/v2/users/is-user-exists?");
        if (type == 0) {
            testCase.setCaseSubName("手机号");
            testCase.setParams("phone,19129910165");
        } else if (type == 1) {
            testCase.setCaseSubName("邮箱");
            testCase.setParams("email,389000577@qq.com");
        } else if (type == 2) {
            testCase.setCaseSubName("用户名");
            testCase.setParams("username,fin");
        }
        return testCase;
    }

    public static TestCase createCheckPasswordCase() {
        TestCase testCase = new TestCase();
        testCase.setModuleName("账号密码");
        testCase.setCaseName("检查密码是否合法");
        testCase.setApiName("checkPassword");
        testCase.setMethod("get");
        testCase.setUrl("/api/v2/users/password/check?");
        testCase.setParams("123456");
        return testCase;
    }

//    public static TestCase createGetSecurityLevelCase() {
//        TestCase testCase = new TestCase();
//        testCase.setModuleName("账号密码");
//        testCase.setCaseName("获取用户的安全等级评分");
//        testCase.setApiName("getSecurityLevel");
//        testCase.setMethod("get");
//        testCase.setUrl("/api/v2/users/me/security-level");
//        return testCase;
//    }

    public static String getUserName(){
        return "";
    }

}
