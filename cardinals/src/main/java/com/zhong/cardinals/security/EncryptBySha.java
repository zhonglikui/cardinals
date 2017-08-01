package com.zhong.cardinals.security;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhong on 2017/1/22.
 * SHA加密
 */

public class EncryptBySha implements Encrypt {
    @Override
    public String encrypt(String plainText) {
        if (plainText == null) {
            return null;
        }
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            sha.update(plainText.getBytes());
            byte[] output = sha.digest();
            return Base64.encodeToString(output, Base64.URL_SAFE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String cipherText) {
        return cipherText;
    }
}
