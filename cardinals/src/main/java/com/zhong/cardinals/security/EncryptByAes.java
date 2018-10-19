package com.zhong.cardinals.security;


import android.text.TextUtils;
import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by zhong on 2016/11/16.
 * 必要时可以参考http://stackoverflow.com/questions/13433529/android-4-2-broke-my-encrypt-decrypt-code-and-the-provided-solutions-dont-work
 * 使用更安全的PBE算法
 * 阿里聚安全：https://zhuanlan.zhihu.com/p/24255780
 * Aes加解密算法
 */

public class EncryptByAes implements Encrypt {
    private SecretKey key;

    public EncryptByAes(int keyLength) {

        try {
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(keyLength);
            //产生秘钥
            SecretKey secretKey = keyGenerator.generateKey();
            //获取秘钥
            byte[] keyBytes = secretKey.getEncoded();
            // Logger.e("AES Key=" + Base64.encodeToString(keyBytes, Base64.URL_SAFE));
            //还原秘钥
            key = new SecretKeySpec(keyBytes, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String encrypt(String plainText) {
        if (TextUtils.isEmpty(plainText)) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodBytes = cipher.doFinal(plainText.getBytes());
            String encodeResult = Base64.encodeToString(encodBytes, Base64.URL_SAFE);
            // Logger.e("AES Encod=" + encodeResult);
            return encodeResult;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String decrypt(String cipherText) {
        if (TextUtils.isEmpty(cipherText)) {
            return null;
        }


        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decode = Base64.decode(cipherText.getBytes(), Base64.URL_SAFE);
            byte[] decodeBytes = cipher.doFinal(decode);
            return decodeBytes.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }


        return null;
    }


}
