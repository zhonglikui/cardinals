package com.zhong.cardinals.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by zhong on 17/2/4.
 * Image操作常见工具类
 */

public class ImageUtil {
    /**
     * 保存图片
     *
     * @param bitmap    需要被保存的bitmap对象
     * @param imagePath 保存的路径
     * @param quality   图片质量
     */
    public static void saveImage(Bitmap bitmap, String imagePath, int quality) {
        if (bitmap != null && !TextUtils.isEmpty(imagePath)) {
            try {
                FileOutputStream out = new FileOutputStream(imagePath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
                Logger.d("picturePath: " + imagePath + "   pictureSize: " + (new File(imagePath).length() / 1024) + " k");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

    }

    /**
     * 获取图片的缩略图
     *
     * @param imagePath 图片路径
     * @param maxWidth  最大宽度
     * @param maxHeight 最大高度
     * @return 一个bitmap缩略图
     */
    public static Bitmap getImageThumbnail(String imagePath, int maxWidth, int maxHeight) {
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false; // 设为 false
        // 计算缩放比
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / maxWidth;
        int beHeight = h / maxHeight;
        int be = Math.min(beWidth, beHeight);

        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, maxWidth, maxHeight,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    /**
     * 获取图片的缩略图
     *
     * @param imagePath 图片路径
     * @param width     最大宽度
     * @param height    最大高度
     * @return 指定宽高的缩略图
     */
    public static Bitmap getThumbnail(String imagePath, int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        int h = bitmap.getHeight();
        int w = bitmap.getWidth();
        float scale;//缩放倍数
        if (h > w) {
            scale = width / w;
        } else {
            scale = height / h;
        }
        Logger.d("原始图片 width :" + w + " ; height :" + h + "缩放：" + scale);
        //   bitmap = BitmapFactory.decodeFile(imagePath, options);
        //   Logger.d("解析出来的图片 width :" + bitmap.getWidth() + " ; height :" + bitmap.getHeight());
        if (scale != 0) {
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            Logger.d("参数：bitmapWidth" + bitmapWidth + " ; bitmapHeight" + bitmapHeight + " ; scale:" + scale);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, true);
            Logger.d("缩放后的图片 width :" + bitmap.getWidth() + " ; height :" + bitmap.getHeight());
            int b2Width = bitmap.getWidth();
            int b2height = bitmap.getHeight();
            int startX = 0, endX = 0, startY = 0, endY = 0;
            if (h > w && b2height >= height) {
                startX = 0;
                endX = b2Width;
                startY = (b2height - height) / 2;
                endY = startY + height;
                bitmap = Bitmap.createBitmap(bitmap, startX, startY, endX, endY);
                Logger.d("裁剪高度");
            } else if (w > h && b2Width > width) {
                startX = (b2Width - width) / 2;
                endX = (startX + width);
                startY = 0;
                endY = b2height;
                bitmap = Bitmap.createBitmap(bitmap, startX, startY, endX, endY);
                Logger.d("裁剪宽度");
            }
            Logger.d("裁剪后的图片 width :" + bitmap.getWidth() + " ; height :" + bitmap.getHeight() + " x :" + startX + "-" + endX + " ;y:" + startY
                    + "-" + endY);

        }


        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        Logger.d("创建的缩略图 width :" + bitmap.getWidth() + " ; height :" + bitmap.getHeight());
        return bitmap;
    }

    /**
     * @param uri 图片的资源路径
     * @return 图片的地址
     */
    public static String getImagePath(Uri uri, Context context) {
        String path = null;
        if (uri != null) {
            final String scheme = uri.getScheme();
            Logger.d("scheme=" + scheme + "; path=" + uri.getPath());

            if (scheme == null)
                path = uri.getPath();
            else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
                path = uri.getPath();
            } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
                //String column = MediaStore.Images.Media.DATA;
                String column = MediaStore.Images.ImageColumns.DATA;
                Cursor cursor = context.getContentResolver().query(uri,
                        new String[]{column}, null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(column);

                    if (index > -1) {
                        path = cursor.getString(index);

                    }

                    CloseUtil.close(cursor);

                }
            }

        }
        return path;


    }
}
