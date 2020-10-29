package com.zhong.cardinals;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * @deprecated 选择照片帮助类(拍照or从相册选择)
 * Created by guijun on 16/7/11.
 */
public class PhotoCaptureUtil {
    public static final int IMAGE_CAPTURE = 100;//拍照选择
    public static final int IMAGE_LOCATION = 101;//从相册选择
    public static final int IMAGE_CROP = 102;//裁剪
    private static volatile File sSdcardDir;

    /*
     * 拍照
     */
    public static Uri photoCapture(AppCompatActivity activity, int requstCode) {
        Uri mCaptureUri = null;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            try {
                mCaptureUri = Uri.fromFile(new File(getFileDir(), "pic_" + System.currentTimeMillis() +
                        ".jpg"));
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mCaptureUri);
                intent.putExtra("return-data", true);
                activity.startActivityForResult(intent, requstCode);
                // IntentUtil.openActivityAnim(SubmitActivity.this);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(activity, "请插入SD卡", Toast.LENGTH_SHORT).show();
        }
        return mCaptureUri;
    }

    /*
     * 本地图片
     */
    public static void photoLocation(AppCompatActivity activity, int requstCode) {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            activity.startActivityForResult(intent, requstCode);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 从相册获取图片的时查询图片路径
     *
     * @param intent
     * @param context
     * @return
     */
    public static String getImagePash(Intent intent, Context context) {
        Uri uri = intent.getData();
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String pash = null;
        if (scheme == null)
            pash = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            pash = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null,
                    null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        pash = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return pash;
    }


    /**
     * 图片裁剪
     *
     * @return
     */

    public static void startPhotoCrop(AppCompatActivity activity, String path, int requstCode) {
        Intent intent = new Intent(activity, PhotoCropActivity.class);
        intent.putExtra("path", path);
        activity.startActivityForResult(intent, requstCode);
    }


    public static File getFileDir() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {

            if (sSdcardDir == null) {
                initSdcardDir();
            }
            return sSdcardDir;
        } else {
            sSdcardDir = null;
            return sSdcardDir;
        }
    }

    private static void initSdcardDir() {
        String fileDir = Environment.getExternalStorageDirectory().getPath() + "/.zhangyutv/";
        ensureDirExists(fileDir);
        sSdcardDir = new File(fileDir);

    }

    public static boolean ensureDirExists(String dirString) {
        File dir = new File(dirString);
        if (!dir.exists()) {
            return dir.mkdirs();
        } else return dir.isDirectory();
    }

    /**
     * 将bitmap保存到本地图库
     *
     * @param bmp
     */
    public static File saveCroppedImage(Bitmap bmp) {
        bmp = ratio(bmp);
        File file = null;
        FileOutputStream fos = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // scale
        int options = 100;
        // Store the bitmap into output stream(no compress)
        bmp.compress(Bitmap.CompressFormat.JPEG, options, os);
        // Compress by loop
        while (os.toByteArray().length / 1024 > 100) {
            // Clean up os
            os.reset();
            // interval 10
            options -= 10;
            if (options < 1) break;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, os);
        }
        try {
            file = new File(getFileDir(), "IMG_" + System.currentTimeMillis() + ".jpg");
            fos = new FileOutputStream(file);
            fos.write(os.toByteArray());
        } catch (IOException e) {
            file = null;
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                    if (bmp != null) {
                        bmp.recycle();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return file;
    }

    public static Bitmap ratio(Bitmap image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, os);
        if (os.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            os.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, os);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        float ww = 400f;
        int be = (int) (newOpts.outWidth / ww);
        if (be <= 0) be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        is = new ByteArrayInputStream(os.toByteArray());
        bitmap = BitmapFactory.decodeStream(is, null, newOpts);
        return bitmap;
    }

    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)   //API 19

        {
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)  //API 12

        {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }

    public static Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;
        WeakReference<Bitmap> weak = new WeakReference<>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }

    public static Bitmap getImageThumbnail(String imagePath, int width, int height) {
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false; // 设为 false
        // 计算缩放比
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / width;
        int beHeight = h / height;
        int be = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    /**
     * 处理图片
     *
     * @param bm        所要转换的bitmap
     * @param newWidth  新的宽
     * @param newHeight 新的高
     * @return 指定宽高的bitmap
     */
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片   www.2cto.com
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }
}
