package com.zhong.cardinals.util;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.zhong.cardinals.App;
import com.zhong.cardinals.mode.DeviceInfo;

/**
 * Created by zhong on 2017/2/9.
 * 手机常见工具方法类
 */

public class DevicesUtil {
    private static final String IDENTITY = "identity";

    /**
     * 获取手机屏幕的高度
     *
     * @return 手机屏幕的高度
     */
    public static int getHeightPixels() {
        WindowManager wm = (WindowManager) App.getInstance().getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取手机屏幕的宽度
     *
     * @return 手机屏幕的宽度
     */
    public static int getWidthPixels() {
        WindowManager wm = (WindowManager) App.getInstance().getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }



    /*    public static String getPhoneInfo(Context context) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            StringBuilder sb = new StringBuilder();
            sb.append("\n设备Id(IMEI) = " + tm.getDeviceId());// 15位数字的设备id
            sb.append("\n手机型号：" + android.os.Build.MODEL);//例如GT-I9502
            sb.append("\n系统名称：" + android.os.Build.DISPLAY);
            sb.append("\n设备名称：" + android.os.Build.DEVICE);// m1metal--HM2014011--ja3gchnduos
            sb.append("\n硬件：" + android.os.Build.HARDWARE);// 一般显示处理器型号

            sb.append("\nID：" + android.os.Build.ID);// LMY47I--HM2014011--LRX22C
            sb.append("\n手机7：" + android.os.Build.TAGS);// 手机一般显示release-keys，可以区分是不是模拟器

            sb.append("\n用户类型：" + android.os.Build.TYPE);// user--user--user

            sb.append("\n手机11：" + android.os.Build.VERSION.CODENAME);// REL
            sb.append("\nandroid版本：" + android.os.Build.VERSION.RELEASE);//android版本例如 4.4.2
            sb.append("\nandroidApi版本：" + android.os.Build.VERSION.SDK_INT);//手机的android APi 19
            sb.append("\n手机14：" + android.os.Build.VERSION_CODES.BASE);// 1--1--1
            Logger.i("info:" + sb.toString());
            return sb.toString();
        }*/
    public static DeviceInfo getDeviceInfo(Context context) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setModel(Build.MODEL);
        deviceInfo.setDisplay(Build.DISPLAY);
        deviceInfo.setDevice(Build.DEVICE);
        deviceInfo.setHardware(Build.HARDWARE);
        deviceInfo.setId(Build.ID);
        deviceInfo.setTags(Build.TAGS);
        deviceInfo.setType(Build.TYPE);
        deviceInfo.setBoard(Build.BOARD);
        deviceInfo.setBrand(Build.BRAND);
        deviceInfo.setSdk_int(Build.VERSION.SDK_INT);
        deviceInfo.setCodeName(Build.VERSION.CODENAME);
        deviceInfo.setIncremental(Build.VERSION.INCREMENTAL);
        deviceInfo.setRelease(Build.VERSION.RELEASE);
        return deviceInfo;

    }


}
