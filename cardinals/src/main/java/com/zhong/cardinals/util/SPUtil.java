package com.zhong.cardinals.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.zhong.cardinals.App;
import com.zhong.cardinals.security.Encrypt;

import java.util.Set;

/**
 * Created by zhong on 2016/3/21.
 * 获取sd卡路径的工具类
 */
public class SPUtil {

    private static final String INVALID_STRING_VALUE = null;
    private static final int INVALID_INT_VALUE = -1;
    private static final float INVALID_FLOAT_VALUE = -1F;
    private static final long INVALID_LONG_VALUE = -1L;
    private static final boolean INVALID_BOOLEAN_VALUE = false;
    private static SharedPreferences sharedPreferences;
    //进行数据加密的类
    private static Encrypt encrypt;


    private SPUtil() {

    }

    public static void init(String password) {

        // encrypt = new EncryptByPbe(password);
    }

    /**
     * @return 获取到的SharedPreferences实例
     */
    private static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance().getContext());
        }
        return sharedPreferences;
    }

    /**
     * @param key   要保存的String类型数据的key
     * @param value String类型的value
     */
    public static void putString(String key, String value) {
        key = encodString(key);
        value = encodString(value);
        getSharedPreferences().edit().putString(key, value).commit();

    }

    /**
     * @param key 获取String类型数据的key
     * @return String类型的value
     */
    public static String getString(String key) {
        key = encodString(key);
        String value = getSharedPreferences().getString(key, INVALID_STRING_VALUE);
        if (TextUtils.isEmpty(value)) {
            return null;
        } else {
            return decodeString(value);
        }

    }

    /**
     * @param key   要保存的int类型数据的key
     * @param value int类型的value
     */
    public static void putInt(String key, int value) {
        key = encodString(key);
        getSharedPreferences().edit().putInt(key, value).commit();
    }

    /**
     * @param key 获取int类型数据的key
     * @return int类型的value
     */
    public static int getInt(String key) {
        key = encodString(key);
        return getSharedPreferences().getInt(key, INVALID_INT_VALUE);
    }

    /**
     * @param key   要保存的boolean类型数据的key
     * @param value boolean类型的value
     */
    public static void putBoolean(String key, boolean value) {
        key = encodString(key);
        getSharedPreferences().edit().putBoolean(key, value).commit();
    }

    /**
     * @param key 获取boolean类型数据的key
     * @return boolean类型的value
     */
    public static boolean getBoolean(String key) {
        key = encodString(key);
        return getSharedPreferences().getBoolean(key, INVALID_BOOLEAN_VALUE);
    }

    /**
     * @param key   要保存的float类型数据的key
     * @param value float类型的value
     */
    public static void putFloat(String key, float value) {
        key = encodString(key);
        getSharedPreferences().edit().putFloat(key, value).commit();
    }

    /**
     * @param key 获取float类型数据的key
     * @return float类型的value
     */
    public static float getFloat(String key) {
        key = encodString(key);
        return getSharedPreferences().getFloat(key, INVALID_FLOAT_VALUE);
    }

    /**
     * @param key   要保存的long类型数据的key
     * @param value long类型的value
     */
    public static void putLong(String key, long value) {
        key = encodString(key);
        getSharedPreferences().edit().putLong(key, value).commit();
    }

    /**
     * @param key 获取long类型数据的key
     * @return long类型的value
     */
    public static long getLong(String key) {
        key = encodString(key);
        return getSharedPreferences().getLong(key, INVALID_LONG_VALUE);
    }

    /**
     * @param key   要保存的Set集合的key
     * @param value Ste集合的value
     */
    public static void putSet(String key, Set<String> value) {
        key = encodString(key);
        getSharedPreferences().edit().putStringSet(key, value).commit();
    }

    /**
     * @param key 获取Set集合的key
     * @return Set集合的value
     */
    public static Set<String> getSet(String key) {
        key = encodString(key);
        return getSharedPreferences().getStringSet(key, null);
    }

    /**
     * @param key 获取任意类型数据的key
     * @return 任意类型的value
     */
    public static <T> T getObject(String key, Class<T> classOfT) {
        String str = getString(key);
        if (!TextUtils.isEmpty(str)) {
            return new Gson().fromJson(str, classOfT);
        } else {
            return null;
        }

    }

    /**
     * @param key    要保存的任意类型数据的key
     * @param object 任意类型的value
     */
    public static void putObject(String key, Object object) {
        if (object != null) {
            putString(key, new Gson().toJson(object));
        }
    }


    /**
     * 删除指定key的数据
     *
     * @param key 要删除数据的key
     */
    public static void remove(String key) {

        if (contains(key)) {
            key = encodString(key);
            getSharedPreferences().edit().remove(key).commit();
        }

    }

    public static void clear() {
        getSharedPreferences().edit().clear().commit();
    }

    /**
     * @param key 需要判断的key
     * @return 是否存在指定的key
     */
    public static boolean contains(String key) {
        key = encodString(key);
        return getSharedPreferences().contains(key);
    }

    /**
     * 将明文加密
     *
     * @param str 需要被加密的明文
     * @return 加密后的密文
     */
    private static String encodString(String str) {
      /*  try {
            byte[] bytes=str.getBytes("utf-8");
            return Base64.encodeToString(bytes,Base64.URL_SAFE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;//encrypt.encrypt(str);str;
        }*/
        return str;
    }

    /**
     * 将密文解析为明文
     *
     * @param str 需要解析的密文
     * @return 解密后的明文
     */
    private static String decodeString(String str) {
        /*try {
            byte[] bytes=str.getBytes("utf-8");
            return Conversion.bytesToHexString(Base64.decode(bytes,Base64.URL_SAFE));
        } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
            return str;
        }*/
        return str;//encrypt.decrypt(str);
    }


}
