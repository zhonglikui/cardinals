package com.zhong.cardinals.util;

import android.text.TextUtils;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by zhong on 2017/5/18.
 * 文件常见操作类
 */

public class FileUtils {
    /**
     * 获取文件夹的大小
     *
     * @param file 传入的文件夹
     * @return 文件夹的体积大小
     */
    public static long getFolderSize(File file) {

        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;
    }

    /**
     * 删除指定的文件夹
     *
     * @param filePath 文件夹路径
     */
    public static void deleteFolderFile(String filePath) {
        if (!TextUtils.isEmpty(filePath) && new File(filePath).exists()) {
            try {
                File file = new File(filePath);
                if (file.isDirectory() && file.listFiles().length > 0) {
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath());
                    }
                } else {
                    boolean isDelete = file.delete();
                    if (isDelete) {//删除成功
                        Logger.d(filePath + " " + "删除成功");
                    } else {//删除失败
                        Logger.d(filePath + " " + "删除失败");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化数据大小单位
     *
     * @param size 单位B
     * @return 格式化之后的体积大小结果
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
