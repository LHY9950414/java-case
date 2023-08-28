package com.example.springbootdbsources.utils;

import cn.hutool.core.codec.Base64;
import com.example.springbootdbsources.config.common.Constants;
import com.example.springbootdbsources.config.exception.DSException;
import com.example.springbootdbsources.enums.DSHttpStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * Aes 加解密工具类
 */
@Slf4j
public class AesUtil {

    // 算法方式
    private static final String AES_ALGORITHM= "AES";
    // 工作模式
    private static final String AES_MODE= "AES/ECB/PKCS5Padding";
    // 自定义加盐值 明文为AES_HK
    private static final String AES_SALT= "AES_DS";

    /**
     * 随机生成指定位数的加盐值
     * @return String
     */
    public static String getSalt(int num){
        char[] chars = AES_SALT.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for(int i = 0; i < num; i++){
            stringBuilder.append(chars[random.nextInt(chars.length)]);
        }
        return stringBuilder.toString();
    }

    /**
     * 自定义加盐 生成18位AESKey
     * @return String
     */
    public static String generateAesKey(){
        return DigestUtils.md5DigestAsHex(getSalt(7).getBytes());
    }

    /**
     * 加密
     * @return 加密后的值
     */
    public static String encryptionAes(String text, String aesKey){
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey.getBytes(), AES_ALGORITHM);
            Cipher instance = Cipher.getInstance(AES_MODE);
            instance.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] bytes = instance.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.encode(bytes);
        } catch (Exception e) {
            log.error("AesUtil.encryptionAes: {}, {}", e, e.getMessage());
            throw new DSException(DSHttpStatusEnum.ERROR.getCode(), Constants.ExceptionMsg.ENCRYPTION_FAILED_MSG);
        }
    }

    /**
     * 解密
     * @return 解密后的值
     */
    public static String decryptAes(String encryptionText, String aesKey){
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey.getBytes(), AES_ALGORITHM);
            Cipher instance = Cipher.getInstance(AES_MODE);
            instance.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] doFinal = instance.doFinal(Base64.decode(encryptionText));
            return new String(doFinal, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AesUtil.decryptAes: {}, {}", e, e.getMessage());
            throw new DSException(DSHttpStatusEnum.ERROR.getCode(), Constants.ExceptionMsg.DECRYPTION_FAILED_MSG);
        }
    }


    public static void main(String[] args) {
        String aesKey = generateAesKey();
//        String aesKey = "26414e791765e05f6e26fde91f62b203";
        System.out.println("Aes密钥："+aesKey);
        String s = encryptionAes(AES_SALT, aesKey);
        System.out.println(s);
        String s1 = decryptAes(s, aesKey);
        System.out.println(s1);
    }
}
