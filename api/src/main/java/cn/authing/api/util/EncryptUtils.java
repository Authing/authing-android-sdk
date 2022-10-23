package cn.authing.api.util;

import android.util.Base64;

import org.bouncycastle.crypto.engines.SM2Engine;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

public class EncryptUtils {

    /**
     * rsa 公钥加密
     *
     * @param publicKey 公钥
     * @param text      预加密文本
     * @return 加密后文本
     */
    public static String rsaEncryptPassword(String publicKey, String text) {
        if (Util.isNull(publicKey) || Util.isNull(text)) {
            return text;
        }
        try {
            byte[] keyBytes = Base64.decode(publicKey, Base64.NO_WRAP);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey key = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherMsg = cipher.doFinal(text.getBytes());
            return new String(Base64.encode(cipherMsg, Base64.NO_WRAP));
        } catch (Exception e) {
            return "{\"2020\":\"" + e + "\"}";
        }
    }


    /**
     * sm2 公钥加密
     *
     * @param publicKey 公钥
     * @param text      预加密文本
     * @return 加密后文本
     */
    public static String sm2Encrypt(String publicKey, String text) {
        if (Util.isNull(publicKey) || Util.isNull(text)) {
            return text;
        }
        // 通过密钥解密
        SM2 sm2 = SmUtil.sm2(null, publicKey);
        sm2.setMode(SM2Engine.Mode.C1C2C3);
        //1 先把明文转成base64
        text = Base64.encodeToString(text.getBytes(), android.util.Base64.NO_WRAP);
        //2 把base64的文本用公钥加密后在转成密文为16进制，否则前端解密前需要先转换格式
        return sm2.encryptHex(text, Charset.forName("utf-8"), KeyType.PublicKey);

    }

    /**
     * sm2 私钥解密
     *
     * @param privateKey 私钥
     * @param text       加密文本
     * @return 解密后文本
     */
    public static String sm2Decrypt(String privateKey, String text) {
        if (Util.isNull(privateKey) || Util.isNull(text)) {
            return text;
        }
        //创建sm2 对象
        SM2 sm2 = SmUtil.sm2(privateKey, null);
        sm2.setMode(SM2Engine.Mode.C1C2C3);
        // 私钥解密
        String decrypt = sm2.decryptStr(text, KeyType.PrivateKey);
        byte[] decode = Base64.decode(decrypt, android.util.Base64.NO_WRAP);
        return new String(decode);
    }

}
