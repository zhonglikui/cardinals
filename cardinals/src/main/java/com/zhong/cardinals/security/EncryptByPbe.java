package com.zhong.cardinals.security;

import android.text.TextUtils;
import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Created by Zhong on 2017/1/28.
 * PBE加解密
 */

public class EncryptByPbe implements Encrypt {


    /**
     * JAVA6支持以下任意一种算法
     * PBEWITHMD5ANDDES
     * PBEWITHMD5ANDTRIPLEDES
     * PBEWITHSHAANDDESEDE
     * PBEWITHSHA1ANDRC2_40
     * PBKDF2WITHHMACSHA1
     *
     * 定义使用的算法为:PBEWITHMD5andDES算法
     */
    public static final String ALGORITHM = "PBEWithMD5AndDES";

    /**
     * 定义迭代次数为256次
     */
    private static final int ITERATIONCOUNT = 256;

    private static Key key;
    private static byte[] salt;

    /**
     * 根据PBE密码生成一把密钥
     *
     * @param password 生成密钥时所使用的密码
     * @return Key PBE算法密钥
     */
    public EncryptByPbe(String password) {
        if (!TextUtils.isEmpty(password)) {
            try {
                key = getPBEKey(password);
                salt = getSalt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 获取加密算法中使用的盐值,解密中使用的盐值必须与加密中使用的相同才能完成操作.
     * 盐长度必须为8字节
     *
     * @return byte[] 盐值
     */
    public static byte[] getSalt() {
        //实例化安全随机数
        SecureRandom random = new SecureRandom();
        //产出盐
        return random.generateSeed(8);
    }

    /**
     * 根据PBE密码生成一把密钥
     *
     * @param password 生成密钥时所使用的密码
     * @return Key PBE算法密钥
     */
    private static Key getPBEKey(String password) throws Exception {
        // 实例化使用的算法
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        // 设置PBE密钥参数
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        // 生成密钥
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        return secretKey;
    }

    @Override
    public String encrypt(String plainText) {

        try {
            PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATIONCOUNT);

            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

            byte encodeByte[] = cipher.doFinal(plainText.getBytes());

            return Base64.encodeToString(encodeByte, Base64.URL_SAFE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
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


        try {
            PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATIONCOUNT);

            Cipher cipher = Cipher.getInstance(ALGORITHM);


            cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
            byte[] base64Byte = Base64.decode(cipherText.getBytes(), Base64.URL_SAFE);
            byte[] passDec = cipher.doFinal(base64Byte);

            return new String(passDec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
