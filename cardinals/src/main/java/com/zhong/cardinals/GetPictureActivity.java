package com.zhong.cardinals;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zhong.cardinals.util.ImageUtil;
import com.zhong.cardinals.util.Logger;
import com.zhong.cardinals.util.PermissionUtils;
import com.zhong.cardinals.util.SDUtil;
import com.zhong.cardinals.util.ToastUtil;

import java.io.File;

/**
 * Created by zhong on 17/2/4.
 * 权限适配的坑 https://www.jianshu.com/p/765603bebced
 */

public class GetPictureActivity extends AppCompatActivity {
    public static final String IMAGE_TYPE = "image_type";
    public static final String IMAGE_PATH = "image_path";
    public static final String IMAGE_RESULT_PATH = "image_result_path";
    public static final int IMAGE_CAPTURE = 100;//拍照选择
    public static final int IMAGE_LOCATION = 101;//从相册选择
    public static final int IMAGE_CROP = 102;//裁剪
    private static final int IMAGE_MAX_SIZE = 600;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getIntent()) {
            int getImageRequestType = getIntent().getIntExtra(IMAGE_TYPE, -1);

            imageUri = Uri.fromFile(new File(SDUtil.getSdCardPackageFile(), "temp.jpg"));
            Intent intent;
            Logger.d("类型是：" + getImageRequestType);
            switch (getImageRequestType) {
                case IMAGE_CAPTURE:
                 /*   if (PermissionUtils.hasAlwaysDeniedPermission(this, Manifest.permission.CAMERA)){
                        PermissionUtils.requestPermissions(this, IMAGE_CAPTURE, new String[]{Manifest.permission.CAMERA}, new PermissionUtils.OnPermissionListener() {
                            @Override
                            public void onPermissionGranted() {

                            }

                            @Override
                            public void onPermissionDenied(String[] deniedPermissions) {
                                DialogUtil.getDialog(GetPictureActivity.this, null, "请在设置中打开相机权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                      GetPictureActivity.this.finish();
                                    }
                                },null);

                            }
                        });
                    }*/
                    File file = new File(imageUri.getPath());
                    if (file.exists()) {
                        boolean isDelete = file.delete();
                        if (isDelete) {//删除成功
                            Logger.d(file.getAbsolutePath() + " " + "删除成功");
                        } else {//删除失败
                            Logger.d(file.getAbsolutePath() + " " + "删除失败");
                        }
                    }
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, IMAGE_CAPTURE);
                    Logger.d("开始拍 ：" + imageUri.getPath());

                    break;
                case IMAGE_LOCATION:
                    intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                  /*  intent = new Intent();
                    intent.setType("image*//*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);// ACTION_OPEN_DOCUMENT
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);*/
                    startActivityForResult(intent, IMAGE_LOCATION);
                    break;
                case IMAGE_CROP:
                    String imagePath = getIntent().getStringExtra(IMAGE_PATH);
                    intent = new Intent(this, PhotoCropActivity.class);
                    intent.putExtra(PhotoCropActivity.PARAM_PATH, imagePath);
                    startActivityForResult(intent, IMAGE_CROP);
                    Logger.d("需要裁剪的path: " + imagePath);
                    break;

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imagePath;
        if (resultCode == Activity.RESULT_OK) {
            //获取成功
            Logger.d("获取成功 :" + requestCode);

            switch (requestCode) {
                case IMAGE_CAPTURE:
                    imagePath = ImageUtil.getImagePath(imageUri, this);
                    resultImage(imagePath);
                    break;
                case IMAGE_LOCATION:
                    imageUri = data.getData();

                    if (imageUri != null) {
                        Logger.d("this is location");
                        imagePath = ImageUtil.getImagePath(imageUri, this);
                        Logger.d("path=" + imagePath);
                        resultImage(imagePath);
                    } else {
                        Logger.d(" imageUri is null");
                    }
                    break;
                case IMAGE_CROP:
                    imagePath = data.getStringExtra(PhotoCropActivity.PARAM_PATH);
                    Logger.d("裁剪成功" + imagePath);
                    data.putExtra(IMAGE_RESULT_PATH, imagePath);
                    setResult(Activity.RESULT_OK, data);
                    finish();
                    break;
            }


        } else {
            //取消选择
            Logger.d("取消获取");
            // deleteTempPicture();
            setResult(resultCode);
            finish();

        }
    }

    /**
     * 获取图片成功后进行处理
     *
     * @param imagePath 图片路径
     */
    private void resultImage(String imagePath) {
        Bitmap bitmap;
        if (!TextUtils.isEmpty(imagePath)) {
            bitmap = ImageUtil.getImageThumbnail(imagePath, IMAGE_MAX_SIZE, IMAGE_MAX_SIZE);
            String filePath = new File(SDUtil.getFileDir(), "temp2.jpg").getAbsolutePath();

            ImageUtil.saveImage(bitmap, filePath, 50);
            // deleteTempPicture();
            Logger.d("处理图片" + filePath);

            Intent resultIntent = new Intent();
            resultIntent.putExtra(IMAGE_PATH, filePath);
            setResult(AppCompatActivity.RESULT_OK, resultIntent);
            GetPictureActivity.this.finish();

        } else {
            //没有获取到文件的路径
            ToastUtil.showShort("图片获取失败");

            setResult(AppCompatActivity.RESULT_CANCELED);
            finish();
        }
    }


   /* private void deleteTempPicture() {
        if (imageUri != null && !TextUtils.isEmpty(imageUri.getPath())) {
            new File(imageUri.getPath()).delete();
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(this, IMAGE_CAPTURE, new String[]{Manifest.permission.CAMERA}, grantResults);

    }
}
