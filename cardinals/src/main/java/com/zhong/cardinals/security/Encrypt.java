package com.zhong.cardinals.security;

/**
 * Created by zhong on 2016/11/16.
 * 所有加/解密方法都需要实现此接口以保证调用方式统一
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
