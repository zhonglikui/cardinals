package com.zhong.cardinals.security;


import android.text.TextUtils;
import android.util.Base64;

/**
 * Created by zhong on 2016/11/16.
 * Base64加解密算法
 */

public class EncryptByBase64 implements Encrypt {
    private int intFlags = Base64.DEFAULT;

    public EncryptByBase64() {
    }

    public EncryptByBase64(int type) {
        this.intFlags = type;
    }


    @Override
    public String encrypt(String plainText) {
        if (TextUtils.isEmpty(plainText)) {
            return null;
        } else {
            return Base64.encodeToString(plainText.getBytes(), intFlags);
        }
    }

    @Override
    public String decrypt(String cipherText) {
        if (TextUtils.isEmpty(cipherText)) {
            return null;
        } else {
            return new String(Base64.decode(cipherText.getBytes(), intFlags));
        }

    }
}
