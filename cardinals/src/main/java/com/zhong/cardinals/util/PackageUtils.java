package com.zhong.cardinals.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.zhong.cardinals.App;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by zhong on 2017/4/1.
 */

public class PackageUtils {
    /**
     * 得到Application的指定的KEY的MetaData信息
     *
     * @param metaDataKey key
     * @return
     */
    public static String getApplicationMetadata(Context context,
                                                String metaDataKey) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            return String.valueOf(info.metaData.get(metaDataKey));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @return 渠道号标识
     * @deprecated 获取通过python脚本打包得到的渠道号
     */
    public static String[] getChannelInfo() {
        ApplicationInfo appinfo = App.getInstance().getContext().getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith("META-INF/channel")) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] split = ret.split("_");
        if (split == null || split.length <= 2) {
            split = new String[]{"META-INF/channel", "11", "offline"};

        }
        return split;
    }

    /**
     * 获取应用的包名
     *
     * @return 应用包名
     */
    public static String getPackageName() {
        return App.getInstance().getContext().getPackageName();
    }

    /**
     * 获取应用版本号标识：例如"1.4.1"
     *
     * @return 版本标识
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(1);
        }

    }

    /**
     * 获取应用的versionCode
     *
     * @return int类型的versionCode
     */
    public static int getVersionCode(Context context) {

        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获得当前进程名称
     *
     * @param context context对象
     * @return 当前京城的名称
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 判断当前应用是否在前端运行
     *
     * @param context Contex对象
     * @return 当前应用是否在前端运行
     */
    public static boolean isAppOnForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取手机的唯一识别码
     *
     * @param context Context对象
     * @return 手机的IMEI识别码
     * @deprecated DeviceUtils
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Logger.i("IMEI=" + tm.getDeviceId());
        return tm.getDeviceId();
    }

    /**
     * @return
     * @deprecated DeviceUtil
     */
    public static String getDeviceMode() {
        return android.os.Build.MODEL;
    }

    /**
     * @return
     * @deprecated Device
     */
    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getOsVersion() {
        return android.os.Build.VERSION.RELEASE;
    }


}
