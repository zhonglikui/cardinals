package com.zhong.cardinals.security;


import android.text.TextUtils;
import android.util.Base64;

/**
 * Created by zhong on 2016/11/16.
 * Base64加解密算法
 */

public class EncryptByBase64 implements Encrypt {


    @Override
    public String encrypt(String plainText) {
        if (TextUtils.isEmpty(plainText)) {
            return null;
        } else {
            return Base64.encodeToString(plainText.getBytes(), Base64.URL_SAFE);
        }
    }

    @Override
    public String decrypt(String cipherText) {
        if (TextUtils.isEmpty(cipherText)) {
            return null;
        } else {
            return new String(Base64.decode(cipherText.getBytes(), Base64.URL_SAFE));
        }

    }
}
