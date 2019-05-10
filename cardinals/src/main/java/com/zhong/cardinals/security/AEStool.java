package com.zhong.cardinals.security;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AEStool {
    private IvParameterSpec ivSpec;
    private SecretKeySpec keySpec;

    public AEStool(String srckey) {
        String key = paddingkey(srckey);
        try {
            byte[] keyBytes = key.getBytes();
            byte[] buf = new byte[16];
            for (int i = 0; i < keyBytes.length && i < buf.length; i++) {
                buf[i] = keyBytes[i];
            }
            this.keySpec = new SecretKeySpec(buf, "AES");
            this.ivSpec = new IvParameterSpec(keyBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String src) {
        try {
            byte[] origData = src.getBytes();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, this.keySpec, this.ivSpec);
            byte[] re = cipher.doFinal(origData);
            return Base64.encodeToString(re, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String src) throws Exception {

        byte[] crypted = Base64.decode(src, Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, this.keySpec, this.ivSpec);
        byte re[] = cipher.doFinal(crypted);
        return new String(re);
    }

    private static String paddingkey(String liu) {
        StringBuilder sb = new StringBuilder(liu);
        for (int i = liu.length(); i < 16; i++) {
            sb.append("0");
        }
        return sb.toString();

    }
}