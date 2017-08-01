package com.zhong.cardinals.util;

import android.content.Context;
import android.os.Environment;

import com.zhong.cardinals.App;

import java.io.File;

/**
 * Created by zhong on 2016/9/23.
 */

public class SDUtil {
    private static void getFilePath() {
        Logger.i("1、" + Environment.getRootDirectory()
                + ";2、" + Environment.getDataDirectory()
                + ";3、" + Environment.getDownloadCacheDirectory()
                + ";4、" + Environment.getExternalStorageDirectory()
                + ";5、" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
                + ";6、" + App.getInstance().getContext().getExternalFilesDir(null)
                + ";7、" + App.getInstance().getContext().getFilesDir()
                + ";8、" + App.getInstance().getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                + ";9、" + App.getInstance().getContext().getExternalCacheDir()
                + ";10、" + App.getInstance().getContext().getCacheDir()
                + ";11、" + App.getInstance().getContext().getFileStreamPath("custom1")
                + ";12、" + App.getInstance().getContext().getObbDir()
                + ";13、" + App.getInstance().getContext().getDir("custom2", Context.MODE_PRIVATE)

        );
        /*

        ///cardinals:
        // 1、/system;
        // 2、/data;
        // 3、/cache;
        // 4、/storage/emulated/0;
        // 5、/storage/emulated/0/Movies;
        // 6、/storage/emulated/0/Android/data/com.prprlive.gugu/files;
        // 7、/data/data/com.prprlive.gugu/files;
        // 8、/storage/emulated/0/Android/data/com.prprlive.gugu/files/Download;
        // 9、/storage/emulated/0/Android/data/com.prprlive.gugu/cache;
        // 10、/data/data/com.prprlive.gugu/cache;
        // 11、/data/data/com.prprlive.gugu/files/custom1;
        // 12、/storage/emulated/0/Android/obb/com.prprlive.gugu;
        // 13、/data/data/com.prprlive.gugu/app_custom2
        */


    }

    /**
     * 获取app的内存缓存路径
     *
     * @return /data/data/packageName/cache;
     */
    public static File getCacheFile() {
        return App.getInstance().getContext().getCacheDir();
    }

    /**
     * 获取app的内存文件路径
     *
     * @return /data/data/packageName/files;
     */
    public static File getFileDir() {
        return App.getInstance().getContext().getFilesDir();
    }


    /**
     * 获取程序在SD卡上包名下的文件夹
     *
     * @return /storage/emulated/0/Android/data/packageName/files;
     */
    public static File getSdCardPackageFile() {
        return App.getInstance().getContext().getExternalFilesDir(null);
    }

    /**
     * 获取程序在sd卡上包名下的缓存目录
     *
     * @return /storage/emulated/0/Android/data/com.prprlive.gugu/cache
     */
    public static File getSdCardPackageCacheFile() {
        return App.getInstance().getContext().getExternalCacheDir();
    }

    /**
     * 判断手机扩展内存卡是否可用
     *
     * @return true可用
     */
    public static boolean isExists() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 获取SD卡根目录
     *
     * @return /storage/emulated/0
     */
    public static File getSdCardFile() {
        return Environment.getExternalStorageDirectory();
    }


    /**
     * 获取手机的一些公共目录
     *
     * @param type 系统文件夹类型 例如：Environment.DIRECTORY_MUSIC
     * @return 文件夹的路径  例如：/storage/sdcard/Music
     */
    public static File getPublicDirectory(String type) {
        return Environment.getExternalStoragePublicDirectory(type);
    }


}
