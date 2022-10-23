package cn.authing.api.params.type;

public enum PasswordEncryptType {
    /**
     * 不对密码进行加密，使用明文进行传输。
     */
    none,
    /**
     * 使用 RSA256 算法对密码进行加密，需要使用 Authing 服务的 RSA 公钥进行加密，请阅读介绍部分了解如何获取 Authing 服务的 RSA256 公钥。
     */
    rsa,
    /**
     * 使用 国密 SM2 算法 对密码进行加密，需要使用 Authing 服务的 SM2 公钥进行加密，请阅读介绍部分了解如何获取 Authing 服务的 SM2 公钥
     */
    sm2
}
