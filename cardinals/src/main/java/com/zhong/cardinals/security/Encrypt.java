package com.zhong.cardinals.security;

/**
 * Created by zhong on 2016/11/16.
 */

public interface Encrypt {
    /**
     * 将明文字符串进行加密
     *
     * @param plainText 需要被加密的字符串
     * @return 被加密后的String字符串
     */
    String encrypt(String plainText);

    /**
     * 将加密后的字符串解析为明文
     *
     * @param cipherText 被加密的字符串
     * @return 解码出来的明文
     */
    String decrypt(String cipherText);
}
