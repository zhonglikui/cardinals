package com.zhong.cardinals.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhong on 2017/3/28.
 * MD5加解密
 */

public class EncryptByMD5 implements Encrypt {
    @Override
    public String encrypt(String plainText) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(plainText.getBytes());
            return Conversion.bytesToHexString(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return plainText;
    }

    @Override
    public String decrypt(String cipherText) {
        return cipherText;
    }
}
